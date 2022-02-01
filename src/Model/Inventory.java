package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is for managing the Inventory application. */
public class Inventory {

    /**
     * A list to hold parts.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * A list to hold products.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * This method gets the allParts list.
     * @return Returns the allParts list.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This method gets the allProducts list.
     * @return Returns the allProducts list.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * This method adds a new part to the allParts list.
     * @param newPart The new part to add to the allParts list.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * This method adds a new product to the allProducts list.
     * @param newProduct the new product to add to the allProducts list.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * This method searches for a specified part in the allParts list.
     * @param partId The part to look up using its ID.
     * @return Returns the part if found.
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     This method searches for a specified product in the allProducts list.
     @param productId The product to look up using its ID.
     @return Returns the product if found.
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * This method searches for a specified part in the allParts list.
     * @param partName The part to look up using its name.
     * @return Returns the part if found.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundPartList = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())) {
                foundPartList.add(allParts.get(i));
            }
        }
        return foundPartList;
    }

    /**
     * This method searches for a specified product in the allProducts list.
     * @param productName the product to look up by name.
     * @return Returns the product if found.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProductList = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().toLowerCase().contains(productName.toLowerCase())) {
                foundProductList.add(allProducts.get(i));
            }
        }
        return foundProductList;
    }

    /**
     * This method updates a part in the allParts list.
     * @param selectedPart the part to be updated.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * This method updates a product in the allProducts list.
     * @param newProduct the product to be updated.
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * This method removes a part from the allParts list.
     * @param selectedPart the part to be deleted.
     */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method removes a product from the allProducts list.
     * @param selectedProduct the product to be deleted.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }
}
