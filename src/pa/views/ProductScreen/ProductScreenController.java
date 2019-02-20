/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa.views.ProductScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pa.models.Inventory;
import static pa.models.Inventory.getParts;
import pa.models.Part;

/**
 * FXML Controller class
 *
 * @author anthonyfreda
 */
public class ProductScreenController implements Initializable {

    @FXML
    private TableView<Part> PartsTable;
    @FXML
    private TableColumn<Part, Integer> PartIDCol;
    @FXML
    private TableColumn<Part, String> PartNameCol;
    @FXML
    private TableColumn<Part, Integer> PartInStockCol;
    @FXML
    private TableColumn<Part, Double> PartPriceCol;
    @FXML
    private TextField PartsSearchField;

    @FXML
    private TableView<Part> ProductPartsTable;
    @FXML
    private TableColumn<Part, Integer> ProductPartIDCol;
    @FXML
    private TableColumn<Part, String> ProductPartNameCol;
    @FXML
    private TableColumn<Part, Integer> ProductPartInStockCol;
    @FXML
    private TableColumn<Part, Double> ProductPartPriceCol;

    private ObservableList<Part> productParts = FXCollections.observableArrayList();

    /**
     * Called on cancel button click
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/pa/views/MainScreen/MainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PartIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPartID()).asObject());
        PartNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        PartInStockCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInStock()).asObject());
        PartPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        populateParts();

        ProductPartIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPartID()).asObject());
        ProductPartNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        ProductPartInStockCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInStock()).asObject());
        ProductPartPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        populateProductParts();
    }

    /**
     * parts table.
     */
    public void populateParts() {
        PartsTable.setItems(getParts());
    }

    /**
     * product parts table.
     */
    public void populateProductParts() {
        ProductPartsTable.setItems(productParts);
    }

    /**
     * Handle the click of add button
     *
     * @param event
     */
    @FXML
    void handleAddPart(ActionEvent event) {
        Part selectedPart = PartsTable.getSelectionModel().getSelectedItem();
        productParts.add(selectedPart);
        populateProductParts();
    }

    /**
     * Handle the click of delete button
     *
     * @param event
     */
    @FXML
    void handleDeletePart(ActionEvent event) {
        Part part = ProductPartsTable.getSelectionModel().getSelectedItem();
        productParts.remove(part);

    }

    /**
     * Handle the click of search button in parts section
     *
     * @param event
     */
    @FXML
    void handleSearchPart(ActionEvent event) {
        String input = PartsSearchField.getText();
        if (input.trim().equals("")) {
            populateParts();
            return;
        }

        Part searchedPart = Inventory.lookupPart(Integer.parseInt(input));

        if (searchedPart != null) {
            ObservableList<Part> filteredPartsList = FXCollections.observableArrayList();
            filteredPartsList.add(searchedPart);
            PartsTable.setItems(filteredPartsList);
        } else {
            PartsTable.setItems(FXCollections.observableArrayList());
        }
    }

}
