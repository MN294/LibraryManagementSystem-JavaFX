/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariam_youssef
 */
public class RegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField firstnameField, lastnameField, NewUsernameField;
    @FXML
    private PasswordField passField, confirmpassField;
    @FXML
    private ImageView registerImgView;
    @FXML
    private Label regBtnLabel, confirmPassLabel;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            File regImgFile = new File("images/register.png");
            Image regImg = new Image(regImgFile.toURI().toString());
            registerImgView.setImage(regImg);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void regBtnOnAction(ActionEvent e) {

        if (passField.getText().equals(confirmpassField.getText())) {
            confirmPassLabel.setText("");
            registerUser();
        } else {
            confirmPassLabel.setText("Passwords do not match!");
        }

    }

    public void backBtnOnAction(ActionEvent E) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        WindowSwitcher.openNewWindow("login.fxml", "Login Screen", true, stage);
    }

    public void registerUser() {
        // Capture UI inputs first (safe to access UI on FX thread)
        String fName = firstnameField.getText();
        String lName = lastnameField.getText();
        String username = NewUsernameField.getText();
        String pass = passField.getText();
        String confirmPass = confirmpassField.getText();

        // Early UI validations still on UI thread
        if (fName.isEmpty() || lName.isEmpty() || username.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            regBtnLabel.setText("Please fill in all the fields");
            return; // stop further execution
        }

        // Run DB operations in background thread
        new Thread(() -> {
            Connection connectDb = DatabaseConnection.getInstance().getConnection();

            // Check username availability on background thread
            if (isUsernameTaken(username, connectDb)) {
                Platform.runLater(() -> regBtnLabel.setText("Username already exists."));
                return;
            }

            String insertFields = "INSERT INTO USERS(first_name,last_name,username,password) VALUES('";
            String insertedValues = fName + "','" + lName + "','" + username + "','" + pass + "')";
            String insertToRegister = insertFields + insertedValues;

            try {
                Statement rst = connectDb.createStatement();
                int count = rst.executeUpdate(insertToRegister);

                Platform.runLater(() -> {
                    if (count > 0) {
                        regBtnLabel.setText("User registered successfully!");
                        clearForm();
                    } else {
                        regBtnLabel.setText("Unable to register. Please try again.");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> regBtnLabel.setText("An error occurred during registration."));
            }
        }).start();

    }

    private boolean isUsernameTaken(String username, Connection c) {
        String query = "SELECT * FROM users WHERE username ='" + username + "'";
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private void clearForm() {
        firstnameField.clear();
        lastnameField.clear();
        NewUsernameField.clear();
        passField.clear();
        confirmpassField.clear();
    }

}
