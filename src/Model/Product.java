package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is for creating Products for the inventory application. */
public class Product {

    /**
     * A list for associated parts.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * The id for the product.
     */
    private int id;
    /**
     * The name for the product.
     */
    private String name;
    /**
     * The price for the product.
     */
    private double price;
    /**
     * The inventory for the product.
     */
    private int stock;
    /**
     * The min inventory level for the product.
     */
    private int min;
    /**
     * The max inventory level for the product.
     */
    private int max;

    /**
     * Constructor for creating a product.
     *
     * @param id The ID for the product.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param stock The inventory level for the product.
     * @param min The minimum inventory level for the product.
     * @param max The maximum inventory level for the product.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return The product id.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The product id to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The product name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The product name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price The product price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return The product stock or inventory level.
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock The product stock or inventory level to set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return The product min.
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min The product min to set.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return The product max.
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max The product max to set.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /** This method adds an associated part to a product.
     *
     * @param part The part to be added.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * This method removes an associated part from a product.
     *
     * @param selectedAssociatedPart The associated part selected.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else
            return false;
    }

    /**
     * This method returns a list of associated parts.
     *
     * @return Returns the associatedParts list.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}

