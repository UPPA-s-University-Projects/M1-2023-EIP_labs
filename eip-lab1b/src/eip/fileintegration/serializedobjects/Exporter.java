package eip.fileintegration.serializedobjects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/*
/**
 *
 * @author ernestoexposito
 */
/**
 * La classe Exporter permet d'exporter des données dans un fichier .txt 
 * Elle possède des méthodes pour ouvrir un fichier, écrire des données à l'intérieur et fermer le fichier.
 * Nous utiliserons le concept de sérialisation pour écrire des objets dans un fichier
 */
public class Exporter {
	
    // La mémoire tampon pour écrire dans le fichier
	private ObjectOutputStream writer;

    /**
     * Le constructeur de la classe prend en paramètre le nom du fichier à écrire.
     * 
     * @param filename le nom du fichier à écrire
     * @throws FileNotFoundException si le fichier spécifié ne peut être trouvé ou créé
     */
    public Exporter(String filename) throws IOException {
        writer = new ObjectOutputStream(new FileOutputStream(filename));
    }

    /**
     * La méthode exportObject sérialise un objet et écrit les données spécifiées dans le fichier .txt
     * 
     * @param data l'objet à sérialiser et à écrire dans le fichier
     */
    public void exportObject(Object data) {
        try {
        	// Nous sérialisons l'objet et l'écrivons dans le fichier
        	writer.writeObject(data);
        	writer.flush();
        }catch (IOException ex) {
			System.out.println("Error writing the object"+ex);
		}
    }
    
    /**
     * La méthode close ferme le fichier .txt
     * 
     */
    public void close()
    {
        try {
        	writer.close();
        }catch(IOException ex) {
        	System.out.println("Error closing the file");
        }
    }

}

