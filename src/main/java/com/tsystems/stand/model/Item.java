package com.tsystems.stand.model;

/**
 * Item entity.
 *
 * @author Siarhei
 * @version 1.0
 */
public class Item {

    private long id;

    private String name;

    private String price;

    private String image;

    private String numberOfSales;

    /**
     * Default constructor.
     */
    public Item() {
    }

    /**
     * Custom constructor to initialize product with necessary values.
     * @param name name.
     * @param price price.
     * @param image image.
     */
    public Item(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    /**
     * Custom constructor to initialize product with necessary values.
     * @param name name.
     * @param price price.
     * @param image image.
     * @param id id.
     * @param numberOfSales number of sales.
     */
    public Item(long id, String name, String price, String image, String numberOfSales) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.numberOfSales = numberOfSales;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(String numberOfSales) {
        this.numberOfSales = numberOfSales;
    }
}
