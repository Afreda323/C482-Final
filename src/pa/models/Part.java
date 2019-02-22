package pa.models;

import pa.ValidationException;

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
     *
     * @return
     */
    public int getPartID() {
        return this.partID;
    }

    /**
     * Set the part ID
     *
     * @param id
     */
    public void setPartID(int id) {
        this.partID = id;
    }

    /**
     * Get the in stock value
     *
     * @return
     */
    public int getInStock() {
        return this.inStock;
    }

    /**
     * Set the part ID
     *
     * @param inStock
     */
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    /**
     * Get the min
     *
     * @return
     */
    public int getMin() {
        return this.min;
    }

    /**
     * Set the min
     *
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Get the max
     *
     * @return
     */
    public int getMax() {
        return this.max;
    }

    /**
     * Set the max
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Get the name
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the price
     *
     * @return
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Set the price
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Make sure the part is valid
     *
     * @return
     * @throws ValidationException
     */
    public boolean validate() throws ValidationException {
        // Ensure there is a name
        if (getName().equals("")) {
            throw new ValidationException("The name field is empty.");
        }

        // Numbers must be positive
        if (getInStock() < 0 || getPrice() < 0 || getMin() < 0) {
            throw new ValidationException("Numeric values must be positive.");
        }

        // Min < Max
        if (getMin() > getMax()) {
            throw new ValidationException("The minimum must be less than the maximum.");
        }

        // In stock must be in between min and max
        if (getInStock() < getMin() || getInStock() > getMax()) {
            throw new ValidationException("In stock must be less than max and more than min.");
        }

        return true;
    }
}
