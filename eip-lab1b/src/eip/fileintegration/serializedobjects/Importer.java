package eip.fileintegration.serializedobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * *
 * * @author ernestoexposito
 * 
 */
/*
 * *
 * * Explications :
 * * Ce fichier permet d'ouvrir un fichier ".txt" et de le lire
 * *
 * 
 */
public class Importer {

    // Nos attributs
    /*
     * * Explications :
     * * par rapport au lab précédent, nous n'avons plus besoins de délimiteur
     * car la
     * * façon dont nos données seront stockées dans le fichier sera différente
     * * elles seront sérialisées et stockées dans un fichier binaire.
     * * Nous avons donc juste besoin du nom du fichier et de notre mémoire
     * tampon
     * * pour lire le fichier
     * 
     */

    private ObjectInputStream reader;
    private String filename;

    // Notre constructeur
    // Il prend en paramètre le nom du fichier à lire

    public Importer(String filename) throws IOException {
        this.filename = filename;
        // Notre mémoire tampon est initialisée avec le fichier à lire
        reader = new ObjectInputStream(new FileInputStream(filename));

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
         * Cela peut améliorer les performances de lecture, car les données sont
         * chargées
         * en bloc plutôt qu'un caractère à la fois.
         * Par conséquent, un BufferedReader peut être considéré comme un Reader avec un
         * buffer interne qui peut être utilisé pour lire
         * les caractères plus efficacement.
         */
    }

    /*
     * Explications :
     * * Notre méthode pour importer les fichiers du fichier ".txt"
     * * Elle retourne un objet (Object est un terme pour parler de n'importe
     * quel objet en Java. Ici, ce sera un objet de type "Product", mais si nous
     * utilisions cette méthode pour
     * * importer différentes classes, le type Object permet de ne pas avoir à
     * changer le type de retour de la méthode pour chacune de nos classes dans
     * notre projet)
     * 
     */

    public Object importObject() {
        // On initialise notre objet générique à null
        Object result = null;
        try {
            try {
                // On lit le fichier et on stocke le résultat dans notre objet générique
                // ici readObject() est une méthode de la classe ObjectInputStream qui permet de
                // transformer nos données en un objet.
                // On désérialise donc les données du fichier pour les transformer en objet
                result = reader.readObject();
            } catch (ClassNotFoundException ex) {
                System.out.println("Error reading the object" + ex);
            }
        } catch (IOException ex) {
            System.out.println("Error reading the object" + ex);
        }
        // On retourne notre objet
        return result;
    }

    // Notre méthode pour fermer le fichier

    public void close() {
        try {
            reader.close();
            File f = new File(filename);
            f.delete();
        } catch (IOException ex) {
            System.out.println("Error closing the file" + ex);
        }

    }
}
