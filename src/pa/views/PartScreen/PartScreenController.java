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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pa.models.InhousePart;
import pa.models.Inventory;
import pa.models.OutsourcedPart;
import pa.models.Part;
import static pa.views.MainScreen.MainScreenController.getSelectedPart;

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
    @FXML
    private Text PageTitle;

    private boolean inHouse ;
    private final Part selectedPart;

    /**
     * Constructor
     */
    public PartScreenController() {
        this.selectedPart = getSelectedPart();
    }

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
     * In house radio handler
     *
     * @param event
     */
    @FXML
    void handleInHouse(ActionEvent event) {
        inHouse = true;
        COrMText.setText("Machine ID");
    }

    /**
     * Out source radio handler
     *
     * @param event
     */
    @FXML
    void handleOutsourced(ActionEvent event) {
        inHouse = false;
        COrMText.setText("Company Name");
    }

    /**
     * Save or update a part
     *
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
            InhousePart part = new InhousePart();
            part.setName(name);
            part.setInStock(Integer.parseInt(inv));
            part.setPrice(Double.parseDouble(price));
            part.setMin(Integer.parseInt(min));
            part.setMax(Integer.parseInt(max));
            part.setMachineID(Integer.parseInt(cOrM));
            
            if (selectedPart == null) {
                part.setPartID(Inventory.getParts().size());
                Inventory.addPart(part);
            } else {
                int partID = selectedPart.getPartID();
                part.setPartID(partID);
                Inventory.updatePart(part);
            }
        } else {
            OutsourcedPart part = new OutsourcedPart();
            part.setName(name);
            part.setInStock(Integer.parseInt(inv));
            part.setPrice(Double.parseDouble(price));
            part.setMin(Integer.parseInt(min));
            part.setMax(Integer.parseInt(max));
            part.setCompanyName(cOrM);
            
            if (selectedPart == null) {
                part.setPartID(Inventory.getParts().size());
                Inventory.addPart(part);
            } else {
                int partID = selectedPart.getPartID();
                part.setPartID(partID);
                Inventory.updatePart(part);
            }
        }
        Parent loader = FXMLLoader.load(getClass().getResource("/pa/views/MainScreen/MainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InHouseRadio.setToggleGroup(radios);
        OutsourcedRadio.setToggleGroup(radios);
        
        if (selectedPart == null) {
            PageTitle.setText("Add Part");
            IDInput.setText("AUTO GEN");

            inHouse = true;
            COrMText.setText("Mach ID");
        } else {
            PageTitle.setText("Modify Part");
            IDInput.setText(Integer.toString(selectedPart.getPartID()));
            NameInput.setText(selectedPart.getName());
            InventoryInput.setText(Integer.toString(selectedPart.getInStock()));
            PriceInput.setText(Double.toString(selectedPart.getPrice()));
            MinInput.setText(Integer.toString(selectedPart.getMin()));
            MaxInput.setText(Integer.toString(selectedPart.getMax()));

            if (selectedPart instanceof InhousePart) {
                COrMInput.setText(Integer.toString(((InhousePart) selectedPart).getMachineID()));
                inHouse = true;
                COrMText.setText("Machine ID");
                COrMInput.setPromptText("Machine ID");
                InHouseRadio.setSelected(true);

            } else {
                inHouse = false;
                COrMInput.setText(((OutsourcedPart) selectedPart).getCompanyName());
                COrMText.setText("Company Name");
                COrMInput.setPromptText("Company Name");
                OutsourcedRadio.setSelected(true);
            }
        }
    }
}
