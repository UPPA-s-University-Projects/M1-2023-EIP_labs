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

    // the reference to the server implementing the remote interface
	private IntegrateProductInterface ri;

    public Exporter() throws Exception {
        // when the exporter is created, it will request the server name
        // accessing the registry available at localhost port 1099
        // where a service has been registred under the name RemoteIntegration
    	ri = (IntegrateProductInterface) Naming.lookup("rmi://localhost:8765/RemoteIntegration");
    }

    public void exportObject(Product p)  {
        // the remote method is invoked
        try {
        	ri.addProduct(p);
        }catch(RemoteException ex) {
        	Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
