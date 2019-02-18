package pa.models;

/**
 *
 * @author anthonyfreda
 */
public abstract class Part {
    private int partID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    /**
     * Constructor
     */
    public Part() {

    }
    
    /**
     * Get the part ID
     * @return 
     */
    public int getPartID() {
        return this.partID;
    }
    /**
     * Set the part ID
     * @param id 
     */
    public void setPartID(int id) {
        this.partID = id;
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
