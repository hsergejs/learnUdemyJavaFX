package udemyjavafx;

import java.sql.Connection;
import java.sql.DriverManager;


 
public class DBconnector {
    static Connection connection = null;
    public static Connection getConnection(){
        try {
            String url = "jdbc:mysql://localhost/sergejsh_hotel";
            connection = DriverManager.getConnection(url, "root", "root");
            System.out.println("Connected to requested database!");
        } catch (Exception e) {
            System.out.println("Error in database connection!");
        }
        return connection;
    }
    
    public static void main(String[] args){
        getConnection();
    }
}
