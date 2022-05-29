package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
//    public static Connection getConexion(){
//    String url="jdbc:sqlserver://localhost:1433;"
//            +"databaseName=CRIPTOMONEDA;"
//            +"integratedSecurity=true;";
//           /*+"user=usersql;"
//            +"passoword=123;";*/
//        try {
//            Connection con=DriverManager.getConnection(url);
//            return con;
//        } catch (SQLException e) {
//            System.out.println(e.toString());
//            return null;
//        }
//    }

    public static Connection getConexion() {
        try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=CRIPTOMONEDA","sa","123");
            return con;
        } catch (SQLException | ClassNotFoundException ex) {
           Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null, ex);
        }
    return null;
   
    }
}
