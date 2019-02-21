/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa.views.MainScreen;

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
import static pa.models.Inventory.getProducts;
import pa.models.Part;
import pa.models.Product;

/**
 * FXML Controller class
 *
 * @author anthonyfreda
 */
public class MainScreenController implements Initializable {

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
    private TableView<Product> ProductsTable;
    @FXML
    private TableColumn<Product, Integer> ProductIDCol;
    @FXML
    private TableColumn<Product, String> ProductNameCol;
    @FXML
    private TableColumn<Product, Integer> ProductInStockCol;
    @FXML
    private TableColumn<Product, Double> ProductPriceCol;
    @FXML
    private TextField PartsSearchField;
    @FXML
    private TextField ProductsSearchField;

    /**
     * These values will be used by other pages to see if a part was selected to
     * be modified
     */
    private static Part selectedPart;
    private static Product selectedProduct;

    public void setSelectedPart(Part part) {
        MainScreenController.selectedPart = part;
    }

    public static Part getSelectedPart() {
        return selectedPart;
    }

    public void setSelectedProduct(Product product) {
        MainScreenController.selectedProduct = product;
    }

    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    /**
     * Handle clicking of modify part button
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void handleModifyPart(ActionEvent event) throws IOException {
        selectedPart = PartsTable.getSelectionModel().getSelectedItem();
        setSelectedPart(selectedPart);
        showPartsScreen(event);
    }

    /**
     * Handle clicking of modify product button
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void handleModifyProduct(ActionEvent event) throws IOException {
        selectedProduct = ProductsTable.getSelectionModel().getSelectedItem();
        setSelectedProduct(selectedProduct);
        showProductScreen(event);
    }

    /**
     * Handle clicking of delete part button
     *
     * @param event
     */
    @FXML
    void handleDeletePart(ActionEvent event) {
        selectedPart = PartsTable.getSelectionModel().getSelectedItem();
        Inventory.deletePart(selectedPart.getPartID());
        setSelectedPart(null);
        populateParts();
    }

    /**
     * Handle clicking of delete product button
     *
     * @param event
     */
    @FXML
    void handleDeleteProduct(ActionEvent event) {
        selectedProduct = ProductsTable.getSelectionModel().getSelectedItem();
        Inventory.removeProduct(selectedProduct.getProductID());
        setSelectedProduct(null);
        populateProducts();
    }

    /**
     * Exit the application on click of exit button
     *
     * @param event
     */
    @FXML
    void handleExit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setSelectedPart(null);
        setSelectedProduct(null);

        PartIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPartID()).asObject());
        PartNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        PartInStockCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInStock()).asObject());
        PartPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        ProductIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProductID()).asObject());
        ProductNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        ProductInStockCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInStock()).asObject());
        ProductPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        populateParts();
        populateProducts();
    }

    /**
     * parts table.
     */
    public void populateParts() {
        PartsTable.setItems(getParts());
    }

    /**
     * product table.
     */
    public void populateProducts() {
        ProductsTable.setItems(getProducts());
    }

    /**
     * Send user to the parts screen.
     *
     * @param event
     * @throws IOException
     */
    public void showPartsScreen(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/pa/views/PartScreen/PartScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
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

    /**
     * Handle the click of search button in products section
     *
     * @param event
     */
    @FXML
    void handleSearchProduct(ActionEvent event) {
        String input = ProductsSearchField.getText();
        if (input.trim().equals("")) {
            populateProducts();
            return;
        }

        Product searchedProduct = Inventory.lookupProduct(Integer.parseInt(input));

        if (searchedProduct != null) {
            ObservableList<Product> filteredProductsList = FXCollections.observableArrayList();
            filteredProductsList.add(searchedProduct);
            ProductsTable.setItems(filteredProductsList);
        } else {
            ProductsTable.setItems(FXCollections.observableArrayList());
        }
    }

    /**
     * Send user to the products screen.
     *
     * @param event
     * @throws IOException
     */
    public void showProductScreen(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/pa/views/ProductScreen/ProductScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
