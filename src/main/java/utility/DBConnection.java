package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static String url = "jdbc:mysql://localhost:3306/project";
    private static String userName = "root";
    private static String pass = "1234";
    private static Connection con;

    public static Connection getConnection() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use the correct MySQL driver class.
            con = DriverManager.getConnection(url, userName, pass);
        } catch (Exception e) {
            System.out.println("Database connection is not available!!");
        }
        return con;
    }
}
