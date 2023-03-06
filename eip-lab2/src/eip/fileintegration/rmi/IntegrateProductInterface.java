package eip.fileintegration.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ernestoexposito
 */

// L'interface distante doit étendre l'interface Remote
public interface IntegrateProductInterface extends Remote {
    // La méthode distante doit lever une exception RemoteException
    public void addProduct (Product p) throws RemoteException ;
    
}
