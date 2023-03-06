package eip.fileintegration.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author ernestoexposito
 */
/*
 *
 * Explications :
 * Ce fichier permet d'ouvrir notre fichier ".xml" et de le lire
 *  Nous implémentons l'interface ErrorHandler pour pouvoir gérer les erreurs
 * 
 */
public class Importer implements ErrorHandler {

    //Nos attributs

    // the DOM structure representingthe imported XML document
    //La structure DOM représentant le document XML importé
    private Document doc;

    // the file system document name
    //Le nom du document du système de fichiers
    private String filename;

    // constants for SCHEMA validation
    //Constantes pour la validation du SCHEMA
    /*
     * Explications : 
     * Ces constantes sont utilisées pour la validation du SCHEMA
     * Le premier est le nom de la propriété du schéma de validation JAXP 
     * Le deuxième est le nom du schéma de validation W3C XML
     * Ces lignes nous permettent de vérifier que le document XML est conforme au schéma
     */
    public static final String 
        JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    public static final String
        W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    // the list of all the <Product> elements
    //La liste de tous les éléments <Product>
    private NodeList elements;

    // the current element being imported
    //L'élément actuel en cours d'importation
    private int nbElement = 0;

    // a flag of the potential detected erros
    //Un indicateur des erreurs potentiellement détectées
    private boolean errorDetected =false;

    // Notre constructeur
    // Le constructeur reçoit en paramètre le nom du fichier XML à importer
     public Importer(String filename) throws Exception {
        // On stocke le nom du fichier dans notre attribut filename
        this.filename= filename;

        // a builder factory pattern
        //Un patron de fabrique de constructeur
        //La méthode newInstance() de la classe DocumentBuilderFactory permet de créer une instance de DocumentBuilderFactory
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();

        // the factory is configured to check XML namespaces
        //La fabrique est configurée pour vérifier les espaces de noms XML
        fac.setNamespaceAware(true);

        // the XSD validation is activated
        //La validation XSD est activée
        //La méthode setValidating() de la classe DocumentBuilderFactory permet de définir si la validation est activée ou non
        //Pour plus d'informations sur la validation XSD et XML en général, vous pouvez lire le cours sous Notion
        fac.setValidating(true);

        // The attributes for XSD declaration are added
        //Les attributs pour la déclaration XSD sont ajoutés
        //La méthode setAttribute() de la classe DocumentBuilderFactory permet de définir les attributs pour la déclaration XSD
        fac.setAttribute(JAXP_SCHEMA_LANGUAGE,  W3C_XML_SCHEMA);

        // a document builder is created from the configured factory
        //Un constructeur de document est créé à partir de la fabrique configurée
        //La méthode newDocumentBuilder() de la classe DocumentBuilderFactory permet de créer un constructeur de document
        //Un documentBuilder est une classe qui permet de construire un document XML
        DocumentBuilder builder = fac.newDocumentBuilder();

        // in case of errors during the document parsing, the error handler methods
        // implemented by the importer will be invoked
        //En cas d'erreurs lors de l'analyse du document, les méthodes de gestionnaire d'erreurs
        //implémentées par l'importateur seront invoquées
        //La méthode setErrorHandler() de la classe DocumentBuilder permet de définir le gestionnaire d'erreurs
        builder.setErrorHandler(this);

        // the XML document is parsed and if no errors are found
        // the DOM tree will be populated
        //Le document XML est analysé et si aucune erreur n'est trouvée
        //l'arborescence DOM sera remplie
        //La méthode parse() de la classe DocumentBuilder permet d'analyser le document XML
        //Si aucune erreur n'est trouvée, la méthode parse() retourne un objet Document
        //Un objet Document est une classe qui représente un document XML
        doc = builder.parse(new File(filename));

        // the root <Products> element is retrieved
        //L'élément racine <Products> est récupéré
        //La méthode getElementsByTagName() de la classe Document permet de récupérer tous les éléments qui correspondent à un nom de balise donné
        //La méthode item() de la classe NodeList permet de récupérer l'élément à la position donnée
        Element rootElement = (Element) doc.getElementsByTagName("Products").item(0);

        // all the <Product> elements that are part of the root element are retrieved
        //Tous les éléments <Product> qui font partie de l'élément racine sont récupérés
        //La méthode getElementsByTagName() de la classe Element permet de récupérer tous les éléments qui correspondent à un nom de balise donné
        elements = rootElement.getElementsByTagName("Product");
    }

    // Cette méthode permet d'importer un objet Product
    /*
     * Explications :
     * Cette méthode permet d'importer un objet Product à partir d'un fichier XML
     * Si il y a des éléments à importer et qu'aucune erreur n'est détectée, un nouveau produit vide est créé
     * Un élément <Product> est retrouvé à la position nbElement
     * L'élément <ProductId> est récupéré depuis notre fichier XML et sa valeur est stockée dans l'objet Product à l'aide de son setter
     * Nous faisons de même pour les autres attributs de notre objet Product
     * 
     * Nous devons faire attention à la conversion des types de données
     * C'est pourquoi nous utilisons les méthodes parseXXX() de la classe Integer, Float, etc.
     */
    public Object importObject() {

        //Création d'un objet Product null
        Product res = null;

        // if there are elements to be imported and no errors are detected
        //Si il y a des éléments à importer et qu'aucune erreur n'est détectée
        if(elements != null&&!errorDetected) {
            // a new empty Product is created
            //Un nouveau produit vide est créé
            res = new Product(0,"",0,0);

            //  a <Product> element is retrived at the nbElement position
            //Un élément <Product> est retrouvé à la position nbElement
            Element currentElement = (Element) elements.item(nbElement++);

            // the <ProductId> element is retrieved and its value stored into the product object
            //L'élément <ProductId> est récupéré et sa valeur est stockée dans l'objet produit
            Element e = (Element) currentElement.getElementsByTagName("ProductId").item(0);
            res.setProductId(Integer.parseInt(e.getTextContent()));

            // the <ProductDescription> element is retrieved and its value stored into the product object
            //L'élément <ProductDescription> est récupéré et sa valeur est stockée dans l'objet produit
            e = (Element) currentElement.getElementsByTagName("ProductDescription").item(0);
            res.setProductDescription(e.getTextContent());

            // the <ProductPrice> element is retrieved and its value stored into the product object
            //L'élément <ProductPrice> est récupéré et sa valeur est stockée dans l'objet produit
            e = (Element) currentElement.getElementsByTagName("ProductPrice").item(0);
            res.setProductPrice(Float.parseFloat(e.getTextContent()));

            // the <ProductAmount> element is retrieved and its value stored into the product object
            //L'élément <ProductAmount> est récupéré et sa valeur est stockée dans l'objet produit
            e = (Element) currentElement.getElementsByTagName("ProductAmount").item(0);
            res.setProductAmount(Integer.parseInt(e.getTextContent()));

            // if this is the last element, the list is initialized to null
            //Si c'est le dernier élément, la liste est initialisée à null
            if(nbElement <= elements.getLength()) {
            	elements=null;
            }
        }
       
        // the product object is returned
        //L'objet produit est retourné
        return res;
    }
    
    // Cette méthode permet de fermer le fichier XML
    // A NOTER : le fichier XML est supprimé
    public void close()
    {
        // the imported file is deleted
    	 File f = new File(filename);
    	 f.delete();
        
    }
     
     // the error handler interface method are implemented\
    // in our case the identified warning o errors are printed
    // in case of error, the errorDetect flag is activated 
    // to avoid continuing importing the XML document
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("Warning : " + exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("Error! XML file will not be used : " + exception);
        errorDetected = true;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("Fatal Error! XML file will not be used : " + exception);
         errorDetected = true;
    }
}
