package pa.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pa.ValidationException;

/**
 *
 * @author anthonyfreda
 */
public class Product {

    private final ObservableList<Part> associatedParts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    /**
     * Constructor
     */
    public Product() {
        this.associatedParts = FXCollections.observableArrayList();
    }

    /**
     * Get all parts
     *
     * @return
     */
    public ObservableList<Part> getAssociatedParts() {
        return this.associatedParts;
    }

    /**
     * Add a part
     *
     * @param part
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * Remove part by id
     *
     * @param partID
     * @return
     */
    public boolean removeAssociatedPart(int partID) {
        boolean hasRemoved = false;
        for (Part part : this.associatedParts) {
            if (part.getPartID() == partID) {
                this.associatedParts.remove(part);
                hasRemoved = true;
            }
        }
        return hasRemoved;
    }

    /**
     * Get a part by id
     *
     * @param partID
     * @return
     */
    public Part lookupAssociatedPart(int partID) {
        for (Part part : this.associatedParts) {
            if (part.getPartID() == partID) {
                return part;
            }
        }
        return null;
    }

    /**
     * Get the product ID
     *
     * @return
     */
    public int getProductID() {
        return this.productID;
    }

    /**
     * Set the product ID
     *
     * @param id
     */
    public void setProductID(int id) {
        this.productID = id;
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
     * Remove all parts from product
     */
    public void removeAllParts() {
        this.associatedParts.clear();
    }

    /**
     * Is the product valid?
     *
     * @return
     * @throws ValidationException
     */
    public boolean validate() throws ValidationException {
        // Add up parts price
        double totalPrice = 0.00;

        for (Part p : getAssociatedParts()) {
            totalPrice += p.getPrice();
        }

        // Product must cost more thsn its parts.
        if (totalPrice > getPrice()) {
            throw new ValidationException("Product must cost more thsn its parts.");
        }

        // Name isnt empty
        if (getName().equals("")) {
            throw new ValidationException("The name field is empty.");
        }

        // Positive numbers
        if (getInStock() < 0 || getPrice() < 0 || getMin() < 0) {
            throw new ValidationException("Numeric values must be positive.");
        }

        // a product must have at least one part
        if (associatedParts.size() < 1) {
            throw new ValidationException("The product must contain at least 1 part.");
        }

        // Min is greater than max
        if (getMin() > getMax()) {
            throw new ValidationException("Maximum must be more than minimum.");
        }

        // In stock must be less than max and more than min
        if (getInStock() < getMin() || getInStock() > getMax()) {
            throw new ValidationException("In stock must be less than max and more than min.");
        }

        return true;
    }
}
