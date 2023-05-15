package estore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
   private static DatabaseManager instance;
    private String databaseInfo = "jdbc:mysql://127.0.0.1:3306/e_store";
    private String username = "root";
    private String password = "Esraa@1032002";
    private Connection connection; 
    
    private DatabaseManager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseInfo, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void close(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        instance = null;
    }
}
