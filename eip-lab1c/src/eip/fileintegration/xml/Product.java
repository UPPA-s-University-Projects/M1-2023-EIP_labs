/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eip.fileintegration.xml;

import java.io.Serializable;

/**
 *
 * @author ernestoexposito
 */
/*
* Notre classe entité
* Elle nous permet de manipuler les données sous forme d'objet
* Elle doit implémenter l'interface Serializable car nous voulons compiler et décompiler les données dans des fichiers 
*/ 
public class Product implements Serializable {
    
    //Nos attributs de classe
    private int productId;
    private String productDescription;
    private float productPrice;
    private int productAmount;

    public Product(int productId, String productDescription, float productPrice, int productAmount) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the productDescription
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return the productPrice
     */
    public float getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the productAmount
     */
    public int getProductAmount() {
        return productAmount;
    }

    /**
     * @param productAmount the productAmount to set
     */
    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }
    
    
    
}
