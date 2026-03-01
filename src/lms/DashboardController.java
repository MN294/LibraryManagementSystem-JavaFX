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
import javafx.stage.Stage;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author mariam_youssef
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     *///handle the logout btn
    @FXML
    private Button viewBooksBtn;
    @FXML
    private Button issueReturnBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button addBookBtn;
    @FXML
    private Button manageUsersBtn;

    private void initialize() {
        // Optional: Hide admin controls if not an admin
        boolean isAdmin = true; // Replace with real logic
        addBookBtn.setVisible(isAdmin);
        manageUsersBtn.setVisible(isAdmin);
    }
//All handler methods should be public and have an ActionEvent object

    @FXML
    public void handleIssueReturn(ActionEvent E) {
        try {
            System.out.println("Issue button clicked!");
            Stage stage = (Stage) issueReturnBtn.getScene().getWindow();
            WindowSwitcher.openNewWindow("IssueReturn.fxml", "Isssue/Return Screen", true, stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleAddBook(ActionEvent E) {
        Stage stage = (Stage) addBookBtn.getScene().getWindow();
        WindowSwitcher.openNewWindow("admin_books.fxml", "Add Books Screen", true, stage);
    }

    public void handleViewBooks(ActionEvent E) {
        Stage stage = (Stage) viewBooksBtn.getScene().getWindow();
        WindowSwitcher.openNewWindow("booksCatalog.fxml", "Books Screen", false, stage);
    }

    public void handleLogout(ActionEvent E) {
        Stage stage = (Stage) viewBooksBtn.getScene().getWindow();
        WindowSwitcher.openNewWindow("login.fxml", "Login Screen", true, stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//
    }
}
