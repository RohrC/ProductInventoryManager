package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static Model.Inventory.*;

/**
 * This is the Controller class for handling the Main Menu operations.
 */
public class MainMenuFormController implements Initializable {

        /**
         * Object for the selected product from the Products table to be modified.
         */
        private static Product productForMod;

        /**
         * Method to get the selected product from the Products table to be modified.
         *
         * @return Returns product for modification.
         */
        public static Product getProductForMod () {
                return productForMod;
        }

        /**
         * The primary stage for the scene.
         */
        Stage stage;
        /**
         * The class for the nodes.
         */
        Parent scene;

        /**
         * The Parts table view.
         */
        @FXML
        private TableView<Part> PartsTableView;

        /**
         * The Part ID column for the Parts table..
         */
        @FXML
        private TableColumn<Part, Integer> PartIDCol;

        /**
         * The Part Name column for the Parts table..
         */
        @FXML
        private TableColumn<Part, String> PartNameCol;

        /**
         * The Inventory Level column for the Parts table..
         */
        @FXML
        private TableColumn<Part, Integer> PartsInvLvlCol;

        /**
         * The Price/Cost Per Unit column for the Parts table..
         */
        @FXML
        private TableColumn<Part, Double> PartsPriceCostCol;

        /**
         * The text field for part searches in the Parts table.
         */
        @FXML
        private TextField PartSearchTxt;

        /**
         * The Products table view.
         */
        @FXML
        private TableView<Product> ProductsTableView;

        /**
         * The Product ID column for the Products table.
         */
        @FXML
        private TableColumn<Product, Integer> ProductIDCol;

        /**
         * The Product Name column for the Products table.
         */
        @FXML
        private TableColumn<Product, String> ProductNameCol;

        /**
         * The Inventory Level column for the Products table.
         */
        @FXML
        private TableColumn<Product, Integer> ProductsInvLvlCol;

        /**
         * The Price/Cost Per Unit column for the Products table.
         */
        @FXML
        private TableColumn<Product, Double> ProductsPriceCostCol;

        /**
         * The text field for product searches in the Products table.
         */
        @FXML
        private TextField ProductSearchTxt;

        /**
         * Loads the Add Part Menu.
         *
         * @param event Add button is selected from the Parts pane.
         * @throws IOException
         */
        @FXML
        void onActionAddPart(ActionEvent event) throws IOException {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/AddPartMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
        }

        /**
         * Loads the Modify Part Menu for a selected part.
         *
         * @param event Modify button is selected from the Parts pane.
         * @throws IOException
         */
        @FXML
        void onActionModifyPart(ActionEvent event) throws IOException {
                Part selectedPart = PartsTableView.getSelectionModel().getSelectedItem();

                if(selectedPart == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Modified Part");
                        alert.setHeaderText("A part must be selected to proceed.");
                        alert.setContentText("Please select a part and try again.");
                        alert.showAndWait();
                }
                else {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/View/ModifyPartMenu.fxml"));
                        loader.load();

                        ModifyPartMenuController ModPartController = loader.getController();
                        ModPartController.getPartTableInfo(PartsTableView.getSelectionModel().getSelectedItem());

                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        Parent scene = loader.getRoot();
                        stage.setScene(new Scene(scene));
                        stage.show();
                }
        }

        /**
         * Loads the Add Product Menu.
         *
         * @param event Add button is selected from the Products pane.
         * @throws IOException
         */
        @FXML
        void onActionAddProduct(ActionEvent event) throws IOException {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/AddProductMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
        }

        /**
         * Loads the Modify Product Menu for a selected product.
         *
         * @param event Modify button is selected from the Products pane.
         * @throws IOException
         */
        @FXML
        void OnActionModifyProduct(ActionEvent event) throws IOException {
                productForMod = ProductsTableView.getSelectionModel().getSelectedItem();

                if (productForMod == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Modified Product");
                        alert.setHeaderText("A product must be selected to proceed.");
                        alert.setContentText("Please select a product and try again.");
                        alert.showAndWait();
                }
                else {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/View/ModifyProductMenu.fxml"));
                        loader.load();

                        ModifyProductMenuController ModProductController = loader.getController();
                        ModProductController.getProductTableInfo(ProductsTableView.getSelectionModel().getSelectedItem());

                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        Parent scene = loader.getRoot();
                        stage.setScene(new Scene(scene));
                        stage.show();
                }
        }

        /**
         * Deletes a selected part from the Parts table.
         *
         * @param event Delete button is selected in the Parts pane.
         */
        @FXML
        void onActionDeletePart(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected item. Do you want to continue?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                        Inventory.deletePart(PartsTableView.getSelectionModel().getSelectedItem());
                }
        }

        /**
         * Deletes a selected product from the Products table.
         *
         * @param event Delete button is selected in the Products pane.
         */
        @FXML
        void onActionDeleteProduct(ActionEvent event) {
                productForMod = ProductsTableView.getSelectionModel().getSelectedItem();

                if (productForMod.getAllAssociatedParts().size() >= 1) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This product contains associated parts. " + "\n Are you sure you want to delete it?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                                Inventory.deleteProduct(productForMod);
                        }
                } else {
                        if (productForMod.getAllAssociatedParts().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected item. Do you want to continue?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                                Inventory.deleteProduct(productForMod);
                                }
                        }
                }
        }
        /**
         * Closes the application.
         *
         * @param event Cancel button is selected.
         * */
        @FXML
        void onActionExit(ActionEvent event) {
                System.exit(0);
        }

        /**
         * A part can be searched for by ID or Name in
         * the Parts table when text is entered in the
         * "PartSearchTxt" text field.
         *
         * @param event Text is entered into the "PartSearchTxt" text field.
         * */
        @FXML
        void onActionPartSearch(ActionEvent event) {
        }

        /**
         * A product can be searched for by ID or Name in
         * the Products table when text is entered in the
         * "ProductSearchTxt" text field.
         *
         * @param event Text is entered into the "ProductSearchTxt" text field.
         * */
        @FXML
        void onActionProductSearch(ActionEvent event) {
        }

        /**
         * Initializes the controller after its root element has been completely processed.
         * Loads the values for the Parts and Products tables.
         * Filters the parts in the Parts table for values entered into the "PartSearchTxt" text field.
         * Filters the products in the Products table for values entered into the "ProductSearchTxt" text field.
         *
         * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
         * @param rb The resources used to localize the root object, or null if the root object was not localized.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {

                PartsTableView.setItems(getAllParts());
                PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                PartsInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                PartsPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

                FilteredList<Part> filteredPart = new FilteredList<>(getAllParts(), p -> true);
                PartSearchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                        filteredPart.setPredicate(part -> {
                                if (newValue == null || newValue.isEmpty()) {
                                        return true;
                                }
                                String lowerCaseFilter = newValue.toLowerCase();
                                if (part.getName().toLowerCase().contains(lowerCaseFilter)) {
                                        return true;
                                }
                                String idFilter = newValue.toLowerCase();
                                if (Integer.toString(part.getId()).toLowerCase().contains(idFilter)) {
                                        return true;
                                }
                                return false;
                        });
                });
                SortedList<Part> sortedPart = new SortedList<>(filteredPart);
                sortedPart.comparatorProperty().bind(PartsTableView.comparatorProperty());
                PartsTableView.setItems(sortedPart);

                ProductsTableView.setItems(Inventory.getAllProducts());
                ProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                ProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                ProductsPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
                ProductsInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

                FilteredList<Product> filteredProduct = new FilteredList<>(getAllProducts(), p -> true);
                ProductSearchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                        filteredProduct.setPredicate(part -> {
                                if (newValue == null || newValue.isEmpty()) {
                                        return true;
                                }
                                String lowerCaseFilter = newValue.toLowerCase();
                                if (part.getName().toLowerCase().contains(lowerCaseFilter)) {
                                        return true;
                                }
                                String idFilter = newValue.toLowerCase();
                                if (Integer.toString(part.getId()).toLowerCase().contains(idFilter)) {
                                        return true;
                                }
                                return false;
                        });
                });
                SortedList<Product> sortedProduct = new SortedList<>(filteredProduct);
                sortedProduct.comparatorProperty().bind(ProductsTableView.comparatorProperty());
                ProductsTableView.setItems(sortedProduct);
        }
}
