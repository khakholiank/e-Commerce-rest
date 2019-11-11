
package com.rgu.ecommerce.model;

import java.util.List;

/**
 *
 * @author Nikit Khakholia
 */
public class Product {
    private int id;
    private String nameOfItem;
    private String description;
    private String tags;
    private int imgMediaId;
    private String brand;
    private int subCategoryId;
    
    private Double price;
    
    private List<Stock> stocks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getImgMediaId() {
        return imgMediaId;
    }

    public void setImgMediaId(int imgMediaId) {
        this.imgMediaId = imgMediaId;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSubCategoryId() {
        return subCategoryId;
        
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
        
    }


    

    
    
    
    
}
