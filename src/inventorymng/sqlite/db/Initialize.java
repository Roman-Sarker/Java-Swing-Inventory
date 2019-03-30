
package inventorymng.sqlite.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Initialize {
    public static Connection con = null;

    // Main Function
      public static void main( String args[] ) {
          ResultSet rs = null;
      try {
        Connection con = getConnection();
          //System.out.println("con is : " + con);
          if(con != null){
              //tableCreate();
              //insertData(1,"A4Tech","Mouse","500");
              rs = veiwData();
              while(rs.next())
              {
                  System.out.println(rs.getInt("id")+"  "+ rs.getString("name")+"  "+rs.getString("category")+"  "+rs.getString("price"));
                  //System.out.println( "ID = " + rs.getInt("id"));
                  //System.out.println( "NAME = " + rs.getString("name") );
              }
             
              
              //deleteData();
              
              rs.close();
              con.close();
          
          }
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      
   }

// DB Connection    
    public static Connection getConnection(){
      try {
         Class.forName("org.sqlite.JDBC");
         con = DriverManager.getConnection("jdbc:sqlite:test.db");
          //System.out.println("con = " + con);
          return con;
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
    }
      return con;
    }

// Table Create
    public static void tableCreate() throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        String sqlTable = "CREATE TABLE product(id integer, name varchar(50), category  varchar(50), price  varchar(50))";
        String dropTable = "DROP TABLE product;";
        ResultSet rs = null;
        try{
            System.out.println("Droping product table.");
            stmt.execute(dropTable);
            System.out.println("Building a table.");
            stmt.execute(sqlTable);
           /* rs = stmt.executeQuery("Select * from product;");
            if(!rs.next()){
                System.out.println("Droping product table.");
                stmt.execute(dropTable);
                System.out.println("Building a table.");
                stmt.execute(sqlTable);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
// Insert Data    
    public static void insertData(int pId, String pName, String pCat, String pPrice) throws SQLException{
        Connection con = getConnection();
        Statement st = con.createStatement();
        int vId = pId; // Integer.parseInt(pId);
        String vName = pName.toString();
        String vCat = pCat.toString();
        String vPrice = pPrice.toString();
        //String sqlInsert = "INSERT INTO product VALUES(2,'Sakil', )";
        String sqlInsert = "INSERT INTO product VALUES("+vId+",'"+vName+"','"+vCat+"','"+vPrice+"')";
        //System.out.println("sqlInsert = " + sqlInsert);
        st.executeUpdate(sqlInsert);
        st.close();

        con.close();
        
        /*
        String sqlInsert = "INSERT INTO product (id, name) VALUES(?,?)";
        PreparedStatement ps = con.prepareCall(sqlInsert);
        ps.setString(1, "Roman");
        ps.execute();
        System.out.println("Data saved successfully.");
*/

    
    }
// View Data   
    public static ResultSet veiwData() throws SQLException{
    Connection con = getConnection();
    Statement st = con.createStatement();
    String sqlSelect = "SELECT * FROM product";
    ResultSet rs = st.executeQuery(sqlSelect);
        return rs;
    
    }
// Delete data
    public static void deleteData(String pId) throws SQLException {
    Connection con = getConnection();
    Statement st = con.createStatement();
    String sqlDeleteAll = "DELETE FROM product"; 
    String sqlDeleteId = "DELETE FROM product WHERE id = '"+pId+"'"; 
        System.out.println("sqlDeleteId = " + sqlDeleteId);
    if(pId.equals("N"))
        st.executeUpdate(sqlDeleteAll);
    else
        st.executeUpdate(sqlDeleteId);
    
    }
    
// Update data
    public static void updateData(String pId, String pName, String pCat, String pPrice) throws SQLException{
    Connection con = getConnection();
    String sqlUpdate = "UPDATE product SET name = '"+pName+"', category = '"+pCat+"', price = '"+pPrice+"' WHERE id = '"+pId+"'"; // UPDATE Students SET DepartmentId = 3, SALARY = 20000.00 WHERE StudentId = 6;
        System.out.println("sqlUpdate = " + sqlUpdate);
    Statement st = con.createStatement();
    st.executeUpdate(sqlUpdate);
    
    }
}
