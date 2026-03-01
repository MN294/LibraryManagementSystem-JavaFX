/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author mariam_youssef
 */
public class IssueReturnController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML private TableView<?> issuedBooksTable;
    @FXML private Button returnBookBtn;
    @FXML private Button backBtn;

    @FXML
    public void handleBackToDashboard(ActionEvent E) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
       WindowSwitcher.openNewWindow("Dashboard.fxml","Dashboard Screen", true, stage);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
