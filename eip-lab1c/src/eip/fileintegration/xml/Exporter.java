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
public class Exporter {
    
    // the file system document name
    private String filename;
    // the DOM tree structure of the document to be created
    private Document doc;
    // the root of the DOM tree
    private Element rootElement;
    // various constant strings allowing to add XML declarations
    private static final String decl11 = "xmlns:xsi";
    private static final String decl12 = "http://www.w3.org/2001/XMLSchema-instance";
    private static final String decl21 = "xsi:noNamespaceSchemaLocation";
    private static final String decl22 = "Product.xsd";

    public Exporter(String filename) throws Exception {
        this.filename = filename;
        // builder factory pattern
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        // the factory allows to create a document buidler
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
        // de document builder allows to create an empty DOM document
        doc = dBuilder.newDocument();
        // the Products element will be created and it will be referenced as the root of the DOM tree
        rootElement = doc.createElement("Products");
        // the empty document will be added to the empty DOM tree. 
        doc.appendChild(rootElement);
        //  Two attributes will be added to the Products element
        //  The xmlns:xsi attribut and its value
       rootElement.setAttribute(decl11, decl12);
        //  The xsi:noNamespaceSchemaLocation attribute and its value
        rootElement.setAttribute(decl21, decl22);
        
        
        
    }

    public void exportObject(Object object) {
        Product data = (Product) object;
        // Product element
        Element product = doc.createElement("Product");
        rootElement.appendChild(product);
        // ProductID element
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
    
    public void close()
    {
         // transformer factory pattern
        TransformerFactory tfFactory = TransformerFactory.newInstance();
        // the factory allows to create a transformer
        Transformer tf;
        try {
        	tf = tfFactory.newTransformer();
        	// the transformer is configured to use the UTF-8 encoding type 
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            // to indent the document
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            
            // to produce an xml format
            tf.setOutputProperty(OutputKeys.METHOD, "xml");
            
            // a DOMsource object is created based on the previously created DOM document
            DOMSource src = new DOMSource(doc);
            
            // a Stream result is instanciated as an empty file using the provided filename
            StreamResult res = new StreamResult(new File(filename));
            
            // Finally the transformer will transform the DOM to an xml file. 
            tf.transform(src, res);
        }catch(TransformerConfigurationException ex) {
        	Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE,null,ex);
        }catch(TransformerException ex) {
        	Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
            
            
            
            
       
        
    }

}

