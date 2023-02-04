package eip.fileintegration.textfiles;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * La classe Exporter permet d'exporter des données dans un fichier .txt au format CSV (Comma-Separated Values).
 * Elle possède des méthodes pour ouvrir un fichier, écrire des données à l'intérieur et fermer le fichier.
 */
public class Exporter {

    // Le délimiteur utilisé pour séparer les données dans le fichier .txt
    private final static String DELIMITER = ",";
    // La mémoire tampon pour écrire dans le fichier
    private PrintWriter writer;

    /**
     * Le constructeur de la classe prend en paramètre le nom du fichier à écrire.
     * 
     * @param filename le nom du fichier à écrire
     * @throws FileNotFoundException si le fichier spécifié ne peut être trouvé ou créé
     */
    public Exporter(String filename) throws FileNotFoundException {
        // Initialisation de la mémoire tampon avec le fichier à écrire
        writer = new PrintWriter(new FileOutputStream(filename));
    }

    /**
     * La méthode exportData écrit les données spécifiées dans le fichier .txt
     * 
     * @param data un tableau de données à écrire dans le fichier
     */
    public void exportData(String[] data) {
        // Parcours du tableau de données
        for (int i = 0; i < data.length; i++) {
            // Écriture des données dans le fichier, ligne par ligne
            writer.print(data[i]);
            // Vérification si on est à la fin de la ligne
            if (i + 1 < data.length) {
                // Ajout du délimiteur si ce n'est pas le cas
                writer.print(DELIMITER);
            }
        }
        // Ajout d'un retour à la ligne
        writer.println();
        writer.flush();
    }

    /**
     * La méthode close ferme le fichier .txt
	 * 
	 */
    public void close()
    {
        writer.close();
    }

}
