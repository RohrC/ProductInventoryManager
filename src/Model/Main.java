package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This class creates an app to display and manage an inventory system. */
public class Main extends Application {

/**
 * FUTURE ENHANCEMENT: A way to distinguish which Products in the Main Menu have parts associated with
 * them without having to open them with the modify products menu would be convenient. This could
 * be done by adding an "Associated Parts" column to the products table of the main menu that indicates
 * an associated part is present for a product with a checkbox or a "yes" or "no."
 * */

    /**
     * This is the start method. It creates the stage and opens the Main Menu of the application.
     *
     * @param primaryStage The primary stage.
     * */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/MainMenuForm.fxml"));
        primaryStage.setTitle("Inventory Application");
        primaryStage.setScene(new Scene(root, 850, 450));
        primaryStage.show();
    }

    /**
     * JAVADOCS is located in the "JavaDocs" folder of InventoryApplicationCR
     *
     * This is the main method. It is the first method that gets called to run the application.
     * */
    public static void main(String[] args) {

        Part part1 = new InHouse(1, "break pads", 35.00, 1, 1, 2, 001);
        Part part2 = new InHouse(2, "oil", 18.00, 1, 1, 2, 002);
        Inventory.addPart(part1);
        Inventory.addPart(part2);

        Part a1 = new OutSourced(3, "floor mat", 15.00, 1, 1, 3, "Ford");
        Part a2 = new OutSourced(4, "gloves", 12.00, 1, 1, 3, "John Deere");
        Inventory.addPart(a1);
        Inventory.addPart(a2);

        Product product1 = new Product(1, "muffler", 55.00, 1, 1, 3);
        Product product2 = new Product(2, "window wiper", 20.00, 1, 1, 3);
        Product product3 = new Product(3, "battery", 90.00, 1, 1, 3);
        Product product4 = new Product(4, "cleaner", 8.00, 1, 1, 3);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        launch(args);
    }
}
