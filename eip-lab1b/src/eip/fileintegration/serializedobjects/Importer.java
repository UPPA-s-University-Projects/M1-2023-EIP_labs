package eip.fileintegration.serializedobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author ernestoexposito
 */
public class Importer {

	private ObjectInputStream reader;
	private String filename;
	
    public Importer(String filename) throws IOException {
       this.filename=filename;
       reader=new ObjectInputStream(new FileInputStream(filename));
    }

    public Object importObject() {
        Object result = null;
        try {
        	try {
        		result = reader.readObject();
        	}catch(ClassNotFoundException ex) {
        		System.out.println("Error reading the object"+ex);
        	}
        }catch(IOException ex) {
        	System.out.println("Error reading the object"+ex);
        }
        return result;
    }
    
     public void close()
    {
        try {
        	reader.close();
        	File f = new File(filename);
        	f.delete();
        }catch(IOException ex) {
        	System.out.println("Error closing the file"+ex);
        }
        
    }
}
