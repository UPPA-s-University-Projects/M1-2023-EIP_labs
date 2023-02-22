package eip.fileintegration.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ernestoexposito
 */
public class Exporter {

    // Java Database Connectivity (JDBC) driver for JavaDB (Derby)   
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    // The URL to the database (can be retrieved from netbeans/services/Databases
    static final String DB_URL = "jdbc:postgresql://localhost:5432/DATABASE_TEST";   
    // the database user
    static final String USER = "VOTRE_NOM_D'UTILISATEUR"; //de base : postgres
    // the databased password
    static final String PASS = "VOTRE_MOT_DE_PASS";
    // the connection to the database to be used to export data 
    private Connection conn = null;
    

    public Exporter() throws Exception {
        // the connection is established to the database
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }

    public void exportObject(Product p)  {
    	try {
	        // a SQL statement to be sent via the database connection is created
	    	Statement stmt = conn.createStatement();
	        // the SQL string statement is prepared including the product attributes
	    	String sql = "INSERT INTO \"PRODUCT\" (\"ID\", \"DESCRIPTION\", \"PRICE\", \"AMOUNT\") VALUES ("
	    			+p.getProductId()+","
	    			+"'"+p.getProductDescription()+"',"
	    			+p.getProductPrice()+","
	    			+p.getProductAmount()+");";
	        // the statement is executed
	    	stmt.executeUpdate(sql);
	        // the statmeent is closed.
	    	stmt.close();
    	}catch(SQLException ex) {
    		Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
    	}

    }
    
    public void close()  
    {
        // the connection is closed.
    	try {
    		conn.close();
    	}catch(SQLException ex) {
    		System.out.println("Error closing the DB");
    	}
    }

}
