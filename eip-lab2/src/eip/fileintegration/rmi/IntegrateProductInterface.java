package eip.fileintegration.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ernestoexposito
 */

// the interface implementing the RMI Remote interface
public interface IntegrateProductInterface extends Remote {
    // the remote methods of the remote interface
    public void addProduct (Product p) throws RemoteException ;
    
}
