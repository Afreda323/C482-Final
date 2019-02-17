package pa.models;

import java.util.ArrayList;

/**
 *
 * @author anthonyfreda
 */
public class Product {
    private ArrayList<Part> associatedParts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    /**
     * Add a part
     * @param part 
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }
    
    public boolean removeAssociatedPart(int partId) {
        boolean hasRemoved = false;
        for (Part part : this.associatedParts) {
            if(part.getPartID() == partId) {
                this.associatedParts.remove(part);
                hasRemoved = true;
            }
        }
        return hasRemoved;
    }
    
    public Part lookupAssociatedPart(int partId) {
        for (Part part : this.associatedParts) {
            if(part.getPartID() == partId) {
                return part;
            }
        }
        return null;
    }
    
    /**
     * Get the product ID
     * @return 
     */
    public int getProductID() {
        return this.productID;
    }
    /**
     * Set the product ID
     * @param id 
     */
    public void setProductID(int id) {
        this.productID = id;
    }
    
    /**
     * Get the in stock value
     * @return 
     */
    public int getInStock() {
        return this.inStock;
    }
    /**
     * Set the part ID
     * @param inStock 
     */
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
    
    
    /**
     * Get the min
     * @return 
     */
    public int getMin() {
        return this.min;
    }
    /**
     * Set the min
     * @param min 
     */
    public void setMin(int min) {
        this.min = min;
    }
    /**
     * Get the max
     * @return 
     */
    public int getMax() {
        return this.max;
    }
    /**
     * Set the max
     * @param max 
     */
    public void setMax(int max) {
        this.min = max;
    }
    
    /**
     * Get the name
     * @return 
     */
    public String getName() {
        return this.name;
    }
    /**
     * Set the name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the price
     * @return 
     */
    public double getPrice() {
        return this.price;
    }
    /**
     * Set the price
     * @param price 
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
