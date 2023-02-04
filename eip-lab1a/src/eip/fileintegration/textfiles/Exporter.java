package eip.fileintegration.textfiles;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

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
 * Explications :
 * 
 * Ce fichier permet d'ouvir un fichier .txt et d'écrire des données à l'intérieur.
 */
public class Exporter {

	//Nos attributs

	/*
	 * Explications :
	 * Notre fichier étant au format de type CSV, nous avons besoin de définir un délimiteur pour séparer nos données los de la lécture et de 
	 * l'écriture dans notre fichier .txt
	 * 
	 * Notre fichier .txt contiendra les données au format:
	 * id,description,prix,montant
	 */

	//Notre délimiteur de données
	private final static String DELIMITER =",";
	//Notre mémoire tampon pour écrire dans le fichier
	private PrintWriter writer;
    

	//Notre constructeur
	//Il prend en paramètre le nom du fichier à écrire
    public Exporter(String filename) throws FileNotFoundException {
		//Notre mémoire tampon est initialisée avec le fichier à écrire
        writer = new PrintWriter(new FileOutputStream(filename));

		
       /*
        * Explications :
        * Nous avons besoin de créer un mémoire tampon pour écrire dans le fichier .txt
        *
		* PrintWriter est une classe en Java qui représente une destination d'écriture de caractères. 
		* Elle offre une interface simple pour écrire des chaînes de caractères et des données numériques dans un fichier ou un autre support
		* d'entrée/sortie, tels qu'un fichier, une socket réseau ou un OutputStream. Cette classe peut être utile pour écrire des données de 
		* manière structurée, comme des lignes de texte, en utilisant des méthodes telles que println() et print(). De plus, elle peut utiliser
		* un buffer interne pour améliorer les performances d'écriture en réduisant le nombre d'opérations d'E/S nécessaires.
		*
		* Un buffer est une zone mémoire temporaire utilisée pour stocker des données en vue d'une lecture ou d'une écriture ultérieure. 
        */
    }

	//Notre méthode d'écriture dans le fichier
    public void exportData(String[] data) {
		//On parcourt notre tableau de données
    	for (int i=0; i<data.length; i++) {
			//On écrit dans le fichier les données ligne par ligne
    		writer.print(data[i]);
			//On vérifie si on est à la fin de la ligne
    		if(i+1<data.length) {
				//Si ce n'est pas le cas, on ajoute notre délimiteur
    			writer.print(DELIMITER);
    		}
    	}
		//On ajoute un retour à la ligne
    	writer.println();
    	writer.flush();
    }
    
	//Notre méthode de fermeture du fichier
    public void close()
    {
        writer.close();
    }

}
