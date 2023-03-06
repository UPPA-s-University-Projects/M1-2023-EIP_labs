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
    public Importer() throws Exception {
        // La connexion à la base de données est établie
    	Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

    }

    // Cette méthode est appelée par le consommateur pour importer un objet
    Object[] importObjects() throws SQLException {
        // Une requête SQL pour récupérer tous les produits est créée
        String sql = "SELECT * FROM \"PRODUCT\"";

        // Une requête SQL à envoyer via la connexion à la base de données est créée
        Statement stmt = conn.createStatement();

        // La requête est exécutée et les résultats sont récupérés
        ResultSet rs = stmt.executeQuery(sql);

        // Une liste est créée pour ajouter les produits récupérés
        ArrayList l = new ArrayList();
        // un produit est créé
        Product p = null;

        // Tant qu'il y a des résultats
        while(rs.next()) {
            // Pour chaque résultat, on récupère les attributs du produit
            int id =rs.getInt("ID");
            String desc = rs.getString("DESCRIPTION");
            float price = rs.getFloat("PRICE");
            int amount = rs.getInt("AMOUNT");

            // Un produit est créé avec les attributs récupérés
            p = new Product(id, desc, price, amount);

            // le produit est ajouté à la liste
            l.add(p);
        }

        // le résultat de la requête est fermé
        rs.close();

        // Un objet tableau est créé à partir de la liste et est retourné
        return l.toArray();
        
    }

    // Cette méthode est appelée pour fermer la connexion à la base de données
    public void close() {
        
    	try {
            // La connexion est fermée
    		conn.close();
    	}catch(SQLException ex) {
    		Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }
}
