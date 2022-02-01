package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



/**
 * This is the Controller class for handling the Add Product Menu operations.
 */
public class AddProductMenuController implements Initializable {

    /**
     * A list to hold parts associated with a product.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * The index position of an object.
     */
    int index;

    /**
     * The primary stage for the scene.
     */
    Stage stage;

    /**
     * The class for the nodes.
     */
    Parent scene;

    /**
     * The text field for the product ID.
     */
    @FXML
    private TextField ProductIDTxt;

    /**
     * The text field for the product name.
     */
    @FXML
    private TextField ProductNameTxt;

    /**
     * The text field for the product inventory level.
     */
    @FXML
    private TextField InvTxt;

    /**
     * The text field for the product price/cost.
     */
    @FXML
    private TextField PriceTxt;

    /**
     * The text field for the product max inventory level.
     */
    @FXML
    private TextField MaxTxt;

    /**
     * The text field for the product min inventory level.
     */
    @FXML
    private TextField MinTxt;

    /**
     * The Add Parts table view.
     */
    @FXML
    private TableView<Part> AddPartTable;

    /**
     * The Part ID column for the Add Parts table..
     */
    @FXML
    private TableColumn<Part, Integer> PartIDCol;

    /**
     * The Part Name column for the Add Parts table..
     */
    @FXML
    private TableColumn<Part, String> PartNameCol;

    /**
     * The Inventory Level column for the Add Parts table..
     */
    @FXML
    private TableColumn<Part, Integer> InvLvlCol;

    /**
     * The Price/Cost Per Unit column for the Add Parts table..
     */
    @FXML
    private TableColumn<Part, Double> PriceCol;

    /**
     * The text field for part searches in the Add Parts table.
     */
    @FXML
    private TextField PartSearchTxt;

    /**
     * The Associated Parts table view.
     */
    @FXML
    private TableView<Part> AssociatedPartTable;

    /**
     * The Part ID column for the Associated Parts table.
     */
    @FXML
    private TableColumn<Part, Integer> PartIDCol2;

    /**
     * The Part Name column for the Associated Parts table.
     */
    @FXML
    private TableColumn<Part, String> PartNameCol2;

    /**
     * The Inventory Level column for the Associated Parts table..
     */
    @FXML
    private TableColumn<Part, Integer> InvLvlCol2;

    /**
     * The Price/Cost Per Unit column for the Associated Parts table..
     */
    @FXML
    private TableColumn<Part, Double> PriceCol2;

    /**
     * Adds a selected part from the Add Parts table to the Associated Parts table.
     *
     * @param event Add button is selected.
     */
    @FXML
    void onActionAddPart(ActionEvent event) {
        Part part = AddPartTable.getSelectionModel().getSelectedItem();
        associatedParts.add(part);
    }

    /**
     * Loads the Main Menu Form.
     *
     * @param event Cancel button is selected.
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Removes a selected part from the Associated Parts table.
     *
     * @param event Remove Associated Part button is selected.
     */
    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected item. Do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
        for (Part part : associatedParts) {
            if (associatedParts.get(index).getId() == part.getId()) {
                associatedParts.remove(part);
            }
        }
    }
}

    /**
     *  RUNTIME ERROR occurs when saving a new product if not all fields have values, or the values entered are
     *  not consistent with formatting requirements when trying to save the product. A correction for this uses
     *  "try/catch."
     *
     * Saves values to the Main Menu Form.
     * Loads the Main Menu Form.
     * Validates the inventory levels of a product being added.
     * Validates the values entered for a product being added.
     *
     * @param event Save button is selected.
     * @throws IOException
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            int id = (Inventory.getAllProducts().size() + 1);
            String name = ProductNameTxt.getText();
            int stock = Integer.parseInt(InvTxt.getText());
            double price = Double.parseDouble(PriceTxt.getText());
            int max = Integer.parseInt(MaxTxt.getText());
            int min = Integer.parseInt(MinTxt.getText());

            if (ProductNameTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Name Entry");
                alert.setHeaderText("A valid name must be entered.");
                alert.setContentText("Please complete the \"name\" field using alphanumeric values.");
                alert.showAndWait();
            } else {
                if (max < min) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Max/Min Values");
                    alert.setHeaderText("Min inventory value cannot exceed Max");
                    alert.setContentText("Please set Max > Min");
                    alert.showAndWait();
                } else {
                    if (stock > max || stock < min) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Inventory Value");
                        alert.setHeaderText("Inventory level must be less than or equal to max, and greater than or equal to min.");
                        alert.setContentText("Please set Min <= Inventory and Inventory <= Max");
                        alert.showAndWait();
                    } else {
                        Product newProduct = new Product(id, name, price, stock, min, max);
                        Inventory.addProduct(newProduct);

                        for (Part part : associatedParts) {
                            if (associatedParts.get(index).getId() == part.getId()) {
                                newProduct.addAssociatedPart(part);
                            }
                        }
                            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                            scene = FXMLLoader.load(getClass().getResource("/View/MainMenuForm.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                    }
                }
            }
        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: Invalid entry");
            alert.setContentText
                    ("A valid value is required for each field." +
                            "\n\n \"Name\" may contain alphanumeric values." +
                            "\n \"Inv\" requires a numeric value." +
                            "\n \"Price\" requires a numeric value." +
                            "\n \"Max\" requires a numeric value." +
                            "\n \"Min\" requires a numeric value.");
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * Loads the values for the AddPartTable and AssociatedPartsTable.
     * Filters the parts in the AddPartTable for values entered into the "PartSearchTxt" text field.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AddPartTable.setItems(Inventory.getAllParts());
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        InvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        AssociatedPartTable.setItems(associatedParts);
        PartIDCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvLvlCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
        PriceCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));

        FilteredList<Part> filteredPart = new FilteredList<>(Inventory.getAllParts(), p -> true);
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
        sortedPart.comparatorProperty().bind(AddPartTable.comparatorProperty());
        AddPartTable.setItems(sortedPart);
    }
}
