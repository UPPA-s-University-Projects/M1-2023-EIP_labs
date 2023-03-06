package eip.fileintegration.rmi;




import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
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

    // La référence au service distant
	private IntegrateProductInterface ri;

    /*
     * Creates a new instance of Exporter
     */
    public Exporter() throws Exception {
        
        // Quand l'exporteur est créé, il va demander le nom du serveur
        // en accédant au registre disponible à l'adresse localhost port 1099
        // où un service a été enregistré sous le nom RemoteIntegration
        // Attention, le port 1099 est le port par défaut du registre RMI mais peut être modifié si nécessaire
        // Attention, le service doit être enregistré avant que l'exporteur ne soit créé
    	ri = (IntegrateProductInterface) Naming.lookup("rmi://localhost:1099/RemoteIntegration");
    }

    // Cette méthode est appelée par le producteur pour exporter un objet
    public void exportObject(Product p)  {
        // the remote method is invoked
        try {
            // On appelle la méthode distante pour ajouter un produit
        	ri.addProduct(p);
        }catch(RemoteException ex) {
        	Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
