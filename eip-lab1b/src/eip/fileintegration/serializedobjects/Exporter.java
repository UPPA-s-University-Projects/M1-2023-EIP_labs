package eip.fileintegration.serializedobjects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/*
/**
 *
 * @author ernestoexposito
 */
public class Exporter {
	
	private ObjectOutputStream writer;

    public Exporter(String filename) throws IOException {
        writer = new ObjectOutputStream(new FileOutputStream(filename));
    }

    public void exportObject(Object data) {
        try {
        	
        	writer.writeObject(data);
        	writer.flush();
        }catch (IOException ex) {
			System.out.println("Error writing the object"+ex);
		}
    }
    
    public void close()
    {
        try {
        	writer.close();
        }catch(IOException ex) {
        	System.out.println("Error closing the file");
        }
    }

}

