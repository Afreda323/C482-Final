/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa.views.PartScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import pa.models.InhousePart;
import pa.models.Inventory;
import pa.models.OutsourcedPart;

/**
 * FXML Controller class
 *
 * @author anthonyfreda
 */
public class PartScreenController implements Initializable {
    @FXML
    private final ToggleGroup radios = new ToggleGroup();
    @FXML
    private RadioButton InHouseRadio;
    @FXML
    private RadioButton OutsourcedRadio;
    @FXML
    private TextField IDInput;
    @FXML
    private TextField NameInput;
    @FXML
    private TextField InventoryInput;
    @FXML
    private TextField PriceInput;
    @FXML
    private TextField MaxInput;
    @FXML
    private TextField MinInput;
    @FXML
    private TextField COrMInput;
    @FXML
    private Label COrMText;
    
    private boolean inHouse = false;
    
    /**
     * Called on cancel button click
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
     * In house radio handler
     * @param event 
     */
    @FXML
    void handleInHouse(ActionEvent event) {
        inHouse = true;
        COrMText.setText("Machine ID");
    }
    /**
     * Out source radio handler
     * @param event 
     */
    @FXML
    void handleOutsourced(ActionEvent event) {
        inHouse = false;
        COrMText.setText("Company Name");
    }

    /**
     * Save or update a part
     * @param event 
     */
    @FXML
    void handleSave(ActionEvent event) throws IOException {
        String name = NameInput.getText();
        String inv = InventoryInput.getText();
        String price = PriceInput.getText();
        String min = MinInput.getText();
        String max = MaxInput.getText();
        String cOrM = COrMInput.getText();
        if (inHouse) {
            System.out.println("INHOUSE");
            InhousePart part = new InhousePart();
            part.setPartID(1);
            part.setName(name);
            part.setInStock(Integer.parseInt(inv));
            part.setPrice(Double.parseDouble(price));
            part.setMin(Integer.parseInt(min));
            part.setMax(Integer.parseInt(max));
            part.setMachineID(Integer.parseInt(cOrM));
            Inventory.addPart(part);
        } else {
            OutsourcedPart part = new OutsourcedPart();
            part.setPartID(2);
            part.setName(name);
            part.setInStock(Integer.parseInt(inv));
            part.setPrice(Double.parseDouble(price));
            part.setMin(Integer.parseInt(min));
            part.setMax(Integer.parseInt(max));
            part.setCompanyName(cOrM);
            Inventory.addPart(part);
        }
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
        InHouseRadio.setToggleGroup(radios);
        OutsourcedRadio.setToggleGroup(radios);
        inHouse = true;
        COrMText.setText("Machine ID");
        IDInput.setPromptText("Auto-Generated");
    }

}
