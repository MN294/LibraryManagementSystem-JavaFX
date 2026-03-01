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
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariam_youssef
 */
public class Admin_booksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField copiesField;
    @FXML
    private Button addBookBtn;
    @FXML
    private Hyperlink backLink;
    @FXML
    private Label addMessage;

    public void addBtnOnAction(ActionEvent e) {
        addBook();
    }

    public void backLinkOnAction(ActionEvent e) {//Finished
        Stage currentStage = (Stage) addBookBtn.getScene().getWindow();
        WindowSwitcher.openNewWindow("Dashboard.fxml", "Dashboard Screen", true, currentStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void addBook() {
        Connection connectDb = DatabaseConnection.getInstance().getConnection();
        String bTitle = titleField.getText();
        String bAuthor = authorField.getText();
        int copies = Integer.parseInt(copiesField.getText());
        String isbn = isbnField.getText();

        if (bTitle.isEmpty() || bAuthor.isEmpty() || copiesField.getText().isBlank() || isbn.isEmpty()) {
            addMessage.setText("Please fill in all the fields");
        }
        if (existISBN(isbn, connectDb)) {
            addMessage.setText("Book already exists.");
        }

        String insertFields = "INSERT INTO BOOKS(title,author,available_copies,Book_ISBN) VALUES('";
        String insertedValues = bTitle + "','" + bAuthor + "','" + copies + "','" + isbn + "')";
        String insertToRegister = insertFields + insertedValues;

        try {
            Statement st = connectDb.createStatement();
            int count = st.executeUpdate(insertToRegister);//counts the number of rows that got edited/added
            if (count > 0) {
                addMessage.setText("Book added successfully!");
                clearForm();
            } else {
                addMessage.setText("Unable to add Book.Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private boolean existISBN(String isbn, Connection c) {
        String query = "SELECT * FROM BOOKS WHERE Book_ISBN ='" + isbn + "'";
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
        titleField.clear();
        authorField.clear();
        copiesField.clear();
        isbnField.clear();
    }

}
