package eip.fileintegration.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ernestoexposito
 */
public class Importer {

	// Java Database Connectivity (JDBC) driver for JavaDB (Derby)   
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    // The URL to the database (can be retrieved from netbeans/services/Databases
    static final String DB_URL = "jdbc:postgresql://localhost:5432/PRODUCT";   
    // the database user
    static final String USER = "postgres"; //de base : postgres
    // the databased password
    static final String PASS = "40c275410B";
    // the connection to the database to be used to export data 
    private Connection conn = null;
    

    public Importer() throws Exception {
        // the connection is established to the database
    	Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

    }

    Object[] importObjects() throws SQLException {
        // a SELECT SQL command intended to retrieve all the products from the database is prepared
        String sql = "SELECT * FROM \"PRODUCT\"";
        // a SQL statement to be sent via the database connection is created 
        Statement stmt = conn.createStatement();
        // the SELECT command is executed and the results are retrieved
        ResultSet rs = stmt.executeQuery(sql);
        // a list is created to add the retrieved products
        ArrayList l = new ArrayList();
        Product p = null;
        while(rs.next()) {
            // for each result set the columns are retrieved
            int id =rs.getInt("ID");
            String desc = rs.getString("DESCRIPTION");
            float price = rs.getFloat("PRICE");
            int amount = rs.getInt("AMOUNT");
            // a product is created with the retrieved data
            p = new Product(id, desc, price, amount);
            // the product is added to the list
            l.add(p);
        }
        
        // the results set is cloed
        rs.close();
        // an object of products is sent back
        return l.toArray();
        
    }

    public void close() {
        
    	try {
    		// the connection is closed
    		conn.close();
    	}catch(SQLException ex) {
    		Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }
}
