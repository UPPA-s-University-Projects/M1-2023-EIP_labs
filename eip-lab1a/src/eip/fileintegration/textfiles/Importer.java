package eip.fileintegration.textfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ernestoexposito
 */

/*
 * 
 * Explications :
 * Ce fichier permet d'ouvrir un fichier ".txt" et de le lire
 * 
 */

public class Importer {

    // Nos attributs
    /*
     * Explications :
     * Notre fichier étant au format de type CSV, nous avons besoin de définir un
     * délimiteur pour séparer nos données lors de la lecture et de
     * l'écriture dans notre fichier ".txt"
     * 
     * Notre fichier ".txt" contiendra les données au format :
     * id,description,prix,montant
     */

    // Notre délimiteur de données
    private final static String DELIMITER = ",";
    // Notre mémoire tampon pour lire le fichier
    private BufferedReader reader;
    // Notre fichier (son nom)
    private String filename;

    // Notre constructeur
    // Il prend en paramètre le nom du fichier à lire
    public Importer(String filename) throws FileNotFoundException {
        this.filename = filename;
        // Notre mémoire tampon est initialisée avec le fichier à lire
        reader = new BufferedReader(new FileReader(filename));

        /*
         * Explications :
         * Nous avons besoin de créer un mémoire tampon pour lire le fichier ".txt"
         *
         * Un buffer est une zone mémoire temporaire utilisée pour stocker des données
         * en vue d'une lecture ou d'une écriture ultérieure.
         * En Java, le Reader est une classe abstraite qui représente une source
         * d'entrée de caractères. Le BufferedReader est une classe
         * concrète qui implémente Reader et qui fournit une méthode pour lire des
         * lignes de caractères en utilisant un buffer interne.
         * Cela peut améliorer les performances de lecture, car les données sont chargées
         * en bloc plutôt qu'un caractère à la fois.
         * Par conséquent, un BufferedReader peut être considéré comme un Reader avec un
         * buffer interne qui peut être utilisé pour lire
         * les caractères plus efficacement.
         */
    }

    // Notre méthode pour importer les fichiers du fichier ".txt"
    // Elle retourne un tableau de String contenant les données du fichier ".txt"
    public String[] importData() {
        String[] results = null;
        try {
            // On lit la ligne du fichier
            String line = reader.readLine();
            // On sépare les données du fichier avec notre délimiteur
            results = line.split(DELIMITER);
        } catch (IOException ex) {
            System.out.println("Error reading the file" + ex);
        }
        return results;
    }

    // Notre méthode pour fermer le fichier ".txt"
    public void close() {
        try {
            // On ferme la mémoire tampon
            reader.close();
            // On supprime le fichier ".txt"
            File f = new File(filename);
            f.delete();
        } catch (IOException ex) {
            System.out.println("Error closing file" + ex);
        }

    }
}
