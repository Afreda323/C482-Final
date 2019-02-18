package pa.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author anthonyfreda
 */
public class Inventory {

    private final static ObservableList<Product> products = FXCollections.observableArrayList();
    private final static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public Inventory() {

    }

    /**
     * Add a product to inventory
     *
     * @param product
     */
    public static void addProduct(Product product) {
        products.add(product);
    }

    /**
     * update a product at an index
     *
     * @param index
     * @param product
     */
    public static void updateProduct(int index, Product product) {
        products.set(index, product);
    }

    /**
     * Remove product by id
     *
     * @param productID
     * @return
     */
    public static boolean removeProduct(int productID) {
        boolean hasRemoved = false;
        for (Product product : products) {
            if (product.getProductID() == productID) {
                products.remove(product);
                hasRemoved = true;
            }
        }
        return hasRemoved;
    }

    /**
     * Get a product by id
     *
     * @param productID
     * @return
     */
    public static Product lookupProduct(int productID) {
        for (Product product : products) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }

    /**
     * Add a part to inventory
     *
     * @param part
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * update a part at an index
     *
     * @param index
     * @param part
     */
    public static void updatePart(int index, Part part) {
        allParts.set(index, part);
    }

    /**
     * Remove part by id
     *
     * @param partID
     * @return
     */
    public static boolean deletePart(int partID) {
        boolean hasRemoved = false;
        for (Part part : allParts) {
            if (part.getPartID() == partID) {
                allParts.remove(part);
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
    public static Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (part.getPartID() == partID) {
                return part;
            }
        }
        return null;
    }

    /**
     * Get all parts
     *
     * @return
     */
    static public ObservableList<Part> getParts() {
        return allParts;
    }

    /**
     * Get all products
     *
     * @return
     */
    static public ObservableList<Product> getProducts() {
        return products;
    }
}
