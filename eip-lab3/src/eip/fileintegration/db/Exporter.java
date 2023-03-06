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

    // La classe du driver JDBC pour PostgreSQL
    static final String JDBC_DRIVER = "org.postgresql.Driver";

    // L'URL de la base de données (Si vous avez crée une connexion vers une BDD sous Eclipse, vous pouvez copier l'URL de la connexion)
    static final String DB_URL = "jdbc:postgresql://localhost:5432/DATABASE_TEST";   

    // L'utilisateur de la base de données
    static final String USER = "VOTRE_NOM_D'UTILISATEUR"; //de base : postgres

    // Le mot de passe de la base de données
    static final String PASS = "VOTRE_MOT_DE_PASS";

    // La référence à la connexion à la base de données pour exporter les objets
    private Connection conn = null;
    

    /*
     * Creates a new instance of Exporter
     */
    public Exporter() throws Exception {
        // La connexion à la base de données est établie
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }

    // Cette méthode est appelée par le producteur pour exporter un objet
    public void exportObject(Product p)  {
    	try {
            // Une requête SQL à envoyer via la connexion à la base de données est créée
	    	Statement stmt = conn.createStatement();

            // La chaîne de caractères de la requête SQL est préparée avec les attributs du produit
            // Nous utilisons des '\' pour échapper les caractères spéciaux (")
	    	String sql = "INSERT INTO \"PRODUCT\" (\"ID\", \"DESCRIPTION\", \"PRICE\", \"AMOUNT\") VALUES ("
	    			+p.getProductId()+","
	    			+"'"+p.getProductDescription()+"',"
	    			+p.getProductPrice()+","
	    			+p.getProductAmount()+");";

            // La requête est envoyée à la base de données
	    	stmt.executeUpdate(sql);

	        // La connexion est fermée
	    	stmt.close();
    	}catch(SQLException ex) {
    		Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
    	}

    }
    
    // Cette méthode est appelée pour fermer la connexion à la base de données
    public void close()  
    {
        // la connexion est fermée
    	try {
    		conn.close();
    	}catch(SQLException ex) {
    		System.out.println("Error closing the DB");
    	}
    }

}
