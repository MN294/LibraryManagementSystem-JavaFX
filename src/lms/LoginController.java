/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lms;

import java.sql.ResultSet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Connection;
import javafx.application.Platform;
import java.sql.PreparedStatement;

/**
 * FXML Controller class
 *
 * @author mariam_youssef
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label loginMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
     
    public void loginBtnOnAction(ActionEvent e){
        if(usernameField.getText().isBlank()==false && passwordField.getText().isBlank()==false){
            //loginMessage.setText("You tried to login!");
            validateLogin();
        }
        else{
        loginMessage.setText("PLease enter username and password!");
        }
    }
    
    public void linkOnAction(ActionEvent e){
    //goToRegister();WindowSwitcher can replace the goToRegister func.
    Stage currentStage = (Stage)loginMessage.getScene().getWindow();
    WindowSwitcher.openNewWindow("register.fxml","Registration Screen", true,currentStage);
    } 
    
    public void validateLogin(){
      String username = usernameField.getText();
        String password = passwordField.getText();

        new Thread(() -> {
            try {   
            DatabaseConnection connectNow = DatabaseConnection.getInstance();
            Connection connectDB = connectNow.getConnection();

            String verify = "SELECT COUNT(1) FROM USERS WHERE username = ? AND password = ?";

            PreparedStatement ps = connectDB.prepareStatement(verify);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet queryRes = ps.executeQuery();

            boolean loginSuccess = false;
            if (queryRes.next()) {
                loginSuccess = queryRes.getInt(1) == 1;
            }

            boolean finalLoginSuccess = loginSuccess;

                    // Update UI on JavaFX thread
                    Platform.runLater(() -> {
                        if (finalLoginSuccess) {
                            loginMessage.setText("Login Successful!");
                            Stage currentStage = (Stage) loginMessage.getScene().getWindow();
                            WindowSwitcher.openNewWindow("Dashboard.fxml", "Dashboard Screen", true, currentStage);
                        } else {
                            loginMessage.setText("Please enter a valid username and password.");
                        }
                    });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> loginMessage.setText("Error during login."));
            }
        }).start();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
