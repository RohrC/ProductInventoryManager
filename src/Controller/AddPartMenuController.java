package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the Controller class for handling the Add Part Menu operations.
 */
public class AddPartMenuController implements Initializable {

    /**
     * The primary stage for the scene.
     */
    Stage stage;

    /**
     * The class for the nodes.
     */
    Parent scene;

    /**
     * The InHouse radio button.
     */
    @FXML
    private RadioButton InHouseRBtn;
    /**
     * The Outsourced radio button.
     */
    @FXML
    private RadioButton OutsourcedRBtn;
    /**
     * The toggle group for the In-house and Outsourced radio buttons.
     */
    @FXML
    private ToggleGroup AddPartTG;
    /**
     * The text field for the part ID.
     */
    @FXML
    private TextField IDTxt;
    /**
     * The text field for the part name.
     */
    @FXML
    private TextField PartNameTxt;
    /**
     * The text field for the part min inventory.
     */
    @FXML
    private TextField MinTxt;
    /**
     * The text field for the part max inventory level.
     */
    @FXML
    private TextField MaxTxt;
    /**
     * The text field for the part inventory level.
     */
    @FXML
    private TextField InvTxt;
    /**
     * The text field for the part price/cost.
     */
    @FXML
    private TextField PriceCostTxt;
    /**
     * The text field for the part machine id.
     */
    @FXML
    private TextField MachineIDTxt;
    /**
     * The label for the machineID or company name.
     */
    @FXML
    private Label MachineIDLabel;

    /**
     * Loads the Main Menu Form.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * For the Part ID text field.
     *
     * @param event
     */
    @FXML
    void onActionIDTxtField(ActionEvent event) {

    }

    /**
     * When InHouse radio button is selected, sets the Label for "MachineIDLabel" to "Machine ID."
     *
     * @param event The InHouse radio button is selected.
     */
    @FXML
    void onActionInHouse(ActionEvent event) {
        if (InHouseRBtn.isSelected()) {
            MachineIDLabel.setText("Machine ID");
        }
    }

    /**
     * When Outsourced radio button is selected, sets the Label for "MachineIDLabel" to "Company Name."
     *
     * @param event The Outsourced radio button is selected.
     */
    @FXML
    void onActionOutsourced(ActionEvent event) {
        if (OutsourcedRBtn.isSelected()) {
            MachineIDLabel.setText("Company Name");
        }
    }

    /**
     * Saves values to the Main Menu Form.
     * Loads the Main Menu Form.
     * Validates the inventory levels of a part being added.
     * Validates the values entered for a part being added.
     *
     * @param event Save button is selected.
     * @throws IOException
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            int id = (Inventory.getAllParts().size() + 1);
            String name = PartNameTxt.getText();
            int stock = Integer.parseInt(InvTxt.getText());
            double price = Double.parseDouble(PriceCostTxt.getText());
            int max = Integer.parseInt(MaxTxt.getText());
            int min = Integer.parseInt(MinTxt.getText());

            if (max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Max/Min Values");
                alert.setHeaderText("Min inventory value cannot exceed Max");
                alert.setContentText("Please set Max > Min");
                alert.showAndWait();
            }
            if (stock > max || stock < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Inventory Value");
                alert.setHeaderText("Inventory level must be less than or equal to max, and greater than or equal to min.");
                alert.setContentText("Please set Min <= Inventory and Inventory <= Max");
                alert.showAndWait();
            }
            else {
                if (InHouseRBtn.isSelected()) {
                    int machineId = Integer.parseInt(MachineIDTxt.getText());
                    Part newPart = new InHouse(id, name, price, stock, min, max, machineId);
                    Inventory.addPart(newPart);
                }
                if (OutsourcedRBtn.isSelected()) {
                    String companyName = MachineIDTxt.getText();
                    Part newPart = new OutSourced(id, name, price, stock, min, max, companyName);
                    Inventory.addPart(newPart);
                }
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/MainMenuForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: Invalid entry");
            alert.setContentText
                    ("A valid value is required for each field." +
                            "\n\n \"Name\" may contain alphanumeric values." +
                            "\n \"Inv\" requires a numeric value." +
                            "\n \"Price\" requires a numeric value." +
                            "\n \"Max\" requires a numeric value." +
                            "\n \"Min\" requires a numeric value." +
                            "\n \"MachineID\" requires a numeric value." +
                            "\n \"Company Name\" may contain alphanumeric values.");
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IDTxt.setText("AutoGen - Disabled");
    }
}