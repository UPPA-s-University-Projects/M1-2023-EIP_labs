package eip.fileintegration.rmi;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ernestoexposito
 */

// Cette classe implémente l'interface distante et étend UnicastRemoteObject
public class Importer extends UnicastRemoteObject implements IntegrateProductInterface {
    
    // Parce que ce patron permet une communication synchrone,
    // nous devons stocker la référence au consommateur
    // pour pouvoir ajouter automatiquement le produit quand l'importeur est
    // invoqué par l'exportateur
    private Consumer c=null;

    /*
     * Creates a new instance of Importer
     */
    public Importer(Consumer c) throws Exception {
        
        // L'importeur stocke la référence au consommateur
        this.c=c;

        
        // Un nouveau registre est créé au port 1099
        LocateRegistry.createRegistry(1099);

        // Le service actuel fourni par l'importeur est enregistré
        // sous le nom RemoteIntegration
        Naming.rebind("rmi://localost:1099/RemoteIntegration", this);
    }
    
    public void addProduct(Product p)  {
        
        // Cette méthode est appelée par l'exportateur pour ajouter un produit
        // dans la table du consommateur
       c.addProduct(p);
    }  
}
