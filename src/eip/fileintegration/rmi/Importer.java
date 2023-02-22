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
// the Importer implements the remote interface and extends the UnicastRemoteObject
public class Importer extends UnicastRemoteObject implements IntegrateProductInterface {
    
    // as this pattern allows synchronous communication, 
    // we will need to store the reference to the consumer
    // in order to automatically add the product when the importer is
    // invoked by the exporter
    private Consumer c=null;

    public Importer(Consumer c) throws Exception {
        // the consumer reference is stored
        this.c=c;
        // a new registry is created at port 1099
        LocateRegistry.createRegistry(1099);
        // the current service provided by the importer is registered
        // under the name RemoteIntegration
        Naming.rebind("rmi://localost:1099/RemoteIntegration", this);
    }
    
    public void addProduct(Product p)  {
        // this is the remote method implemented by the importer
        // the product will be automatically added into the
        // consumer table
       c.addProduct(p);
    }  
}
