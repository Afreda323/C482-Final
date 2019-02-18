package pa.models;

import java.util.ArrayList;

/**
 * 
 * @author anthonyfreda
 */
public class Inventory {
    private ArrayList<Product> products;
    private ArrayList<Part> allParts;
    
    /**
     * Add a product to inventory
     * @param product 
     */
    public void addProduct(Product product) {
        this.products.add(product);
    }
    
    /**
     * update a product at an index
     * @param index 
     * @param product 
     */
    public void updateProduct(int index, Product product) {
        this.products.set(index, product);
    }
    
    /**
     * Remove product by id
     * @param productID
     * @return 
     */
    public boolean removeProduct(int productID) {
        boolean hasRemoved = false;
        for (Product product : this.products) {
            if(product.getProductID()== productID) {
                this.products.remove(product);
                hasRemoved = true;
            }
        }
        return hasRemoved;
    }
    
    /**
     * Get a product by id
     * @param productID
     * @return 
     */
    public Product lookupProduct(int productID) {
        for (Product product : this.products) {
            if(product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }
    
    
    /**
     * Add a part to inventory
     * @param part 
     */
    public void addPart(Part part) {
        this.allParts.add(part);
    }
    
    /**
     * update a part at an index
     * @param index 
     * @param part 
     */
    public void updatePart(int index, Part part) {
        this.allParts.set(index, part);
    }
    
    /**
     * Remove part by id
     * @param partID
     * @return 
     */
    public boolean deletePart(int partID) {
        boolean hasRemoved = false;
        for (Part part : this.allParts) {
            if(part.getPartID()== partID) {
                this.allParts.remove(part);
                hasRemoved = true;
            }
        }

        return hasRemoved;
    }
    
    /**
     * Get a part by id
     * @param partID
     * @return 
     */
    public Part lookupPart(int partID) {
        for (Part part : this.allParts) {
            if(part.getPartID() == partID) {
                return part;
            }
        }
        return null;
    }
}
