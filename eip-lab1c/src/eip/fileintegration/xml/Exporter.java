package eip.fileintegration.xml;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/*
/**
 *
 * @author ernestoexposito
 */

 /**
 * La classe Exporter permet d'exporter des données dans un fichier .xml 
 * Elle possède des méthodes pour ouvrir un fichier, écrire des données à l'intérieur et fermer le fichier.
 * Nous utiliserons le concept de sérialisation pour écrire des objets dans un fichier
 * Nous compilerons nos données pour respecter le schéma XSD que nous avons créé (respect des règles XML)
 */
public class Exporter {
    
    //Nos attributs

    // the file system document name
    //Le nom du document du système de fichiers
    private String filename;

    // the DOM tree structure of the document to be created
    //La structure de l'arbre DOM du document à créer
    private Document doc;

    // the root of the DOM tree
    //La racine de l'arbre DOM
    private Element rootElement;

    // various constant strings allowing to add XML declarations
    //Plusieurs chaînes de constantes permettant d'ajouter des déclarations XML
    /*
     * Explications : 
     * Ces constantes sont utilisées pour ajouter des déclarations XML
     * Elles permettent de définir le schéma de validation W3C XML
     * Ces lignes nous permettent de vérifier que le document XML est conforme au schéma
     * Elles nous permetten également l'endroit et le nom des fichiers XSD où nous pouvons trouver les informations sur le schéma
     *
     * Le premier attribut est "xmlns:xsi" et sa valeur est "http://www.w3.org/2001/XMLSchema-instance"
     *  Le deuxième attribut est "xsi:noNamespaceSchemaLocation" et sa valeur est "Product.xsd"
     */
    private static final String decl11 = "xmlns:xsi";
    private static final String decl12 = "http://www.w3.org/2001/XMLSchema-instance";
    private static final String decl21 = "xsi:noNamespaceSchemaLocation";
    private static final String decl22 = "Product.xsd";

    // Le constructeur de la classe Exporter
    // Le constructeur prend en paramètre le nom du fichier
    // Le constructeur va créer un document DOM vide
    // Il va ensuite ajouter un élément racine "Products" à ce document
    // Il va ensuite ajouter deux attributs à l'élément racine "Products" 
    public Exporter(String filename) throws Exception {
        // On stocke le nom du fichier dans notre attribut filename
        this.filename = filename;

        //Un patron de fabrique de constructeur
        //La méthode newInstance() de la classe DocumentBuilderFactory permet de créer une instance de DocumentBuilderFactory
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        // the factory allows to create a document buidler
        //La méthode newDocumentBuilder() de la classe DocumentBuilderFactory permet de créer un constructeur de document
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        

        // de document builder allows to create an empty DOM document
        //La méthode newDocument() de la classe DocumentBuilder permet de créer un document DOM vide
        doc = dBuilder.newDocument();

        // the Products element will be created and it will be referenced as the root of the DOM tree
        //L'élément Products sera créé et il sera référencé comme la racine de l'arbre DOM
        rootElement = doc.createElement("Products");

        // the empty document will be added to the empty DOM tree. 
        //Le document vide sera ajouté à l'arbre DOM vide
        doc.appendChild(rootElement);
        
        //  Two attributes will be added to the Products element
        //  The xmlns:xsi attribut and its value
        //Deux attributs seront ajoutés à l'élément Products
        //L'attribut xmlns:xsi et sa valeur
       rootElement.setAttribute(decl11, decl12);

        //  The xsi:noNamespaceSchemaLocation attribute and its value
        //L'attribut xsi:noNamespaceSchemaLocation et sa valeur
        rootElement.setAttribute(decl21, decl22);
        
    }

    // Cette méthode permet d'exporer un objet dans le fichier XML
    // Elle prend en paramètre un objet
    // Elle va ensuite créer un élément "Product" et l'ajouter à l'élément racine "Products"
    // Elle va ensuite créer un élément "ProductId" et l'ajouter à l'élément "Product"
    // Notre élément "ProductId" contient un noeud de texte qui contient l'identifiant du produit récuperé à partir de l'objet passé en paramètre
    public void exportObject(Object object) {

        // Nous récupérons l'objet passé en paramètre
        // Nous le castons en Product
        /*
         * Explications :
         * Caster un objet permet de le convertir en un autre type d'objet
         * Ici, nous convertissons l'objet passé en paramètre en Product
         * Nous pouvons ensuite utiliser les méthodes de la classe Product
         */
        Product data = (Product) object;

        // Product element
        //Nous créons un élément "Product" et l'ajoutons à l'élément racine "Products"
        Element product = doc.createElement("Product");
        rootElement.appendChild(product);

        // ProductID element
        //Nous créons un élément "ProductId" et l'ajoutons à l'élément "Product"
        //Nous créons un noeud de texte qui contient l'identifiant du produit
        //Nous ajoutons ce noeud de texte à l'élément "ProductId"
        //Nous ajoutons l'élément "ProductId" à l'élément "Product"
        //Nous répétons ces étapes pour les autres éléments
        Element productId = doc.createElement("ProductId");
        productId.appendChild(doc.createTextNode(Integer.toString(data.getProductId())));
        product.appendChild(productId);
        
        // ProductDescription element
        Element productDesc = doc.createElement("ProductDescription");
        productDesc.appendChild(doc.createTextNode(data.getProductDescription()));
        product.appendChild(productDesc);
        
        // ProductPrice element
        Element productPrice = doc.createElement("ProductPrice");
        productPrice.appendChild(doc.createTextNode(Float.toString(data.getProductPrice())));
        product.appendChild(productPrice);
        
        // ProductAmount element
        Element productAmount = doc.createElement("ProductAmount");
        productAmount.appendChild(doc.createTextNode(Integer.toString(data.getProductAmount())));
        product.appendChild(productAmount);
        
    }
    
    // Cette méthode permet de fermer le fichier XML
    public void close()
    {
        // transformer factory pattern
        // La méthode newInstance() de la classe TransformerFactory permet de créer une instance de TransformerFactory
        //Un transformer factory permet de créer un transformer
        //Un transformer permet de transformer un document DOM en un fichier XML
        TransformerFactory tfFactory = TransformerFactory.newInstance();

        // the factory allows to create a transformer
        // L'usine permet de créer un transformer
        Transformer tf;

        /*
         * Explications :
         * Nous allons configurer notre fichier XML à l'aide du transformer
         * Nous indiquierons le format à utiliser
         * Si nous souhaitons indenter le fichier XML
         * Quel type de fichier nous souhaitons créer
         * etc.
         */
        try {
            //Le transformer est créé
        	tf = tfFactory.newTransformer();

        	// the transformer is configured to use the UTF-8 encoding type 
            //Le transformer est configuré pour utiliser le type d'encodage UTF-8
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            // to indent the document
            //Pour indenter le document
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            
            // to produce an xml format
            //Pour produire un format xml
            tf.setOutputProperty(OutputKeys.METHOD, "xml");
            
            // a DOMsource object is created based on the previously created DOM document
            //Un objet DOMsource est créé sur la base du document DOM précédemment créé
            //Le transformer va transformer le document DOM en un fichier XML
            DOMSource src = new DOMSource(doc);
            
            // a Stream result is instanciated as an empty file using the provided filename
            //Un résultat de flux est instancié comme un fichier vide en utilisant le nom de fichier fourni
            //Ce flux va contenir le fichier XML
            StreamResult res = new StreamResult(new File(filename));
            
            // Finally the transformer will transform the DOM to an xml file. 
            //Enfin, le transformer transformera le DOM en un fichier xml.
            tf.transform(src, res);

        }catch(TransformerConfigurationException ex) {
        	Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE,null,ex);
        }catch(TransformerException ex) {
        	Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }

}

