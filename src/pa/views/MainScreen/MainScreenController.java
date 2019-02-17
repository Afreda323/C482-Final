/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa.views.MainScreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author anthonyfreda
 */
public class MainScreenController implements Initializable {
    @FXML
    private Button myButton;
    
    /**
     * Button click event handler
     * @param event 
     */
    @FXML
    private void handleButtonClick(ActionEvent event) {
        System.out.println("Button Clicked");
        myButton.setText("HELLO WORLD");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
