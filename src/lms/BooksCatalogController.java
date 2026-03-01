/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author mariam_youssef
 */
public class BooksCatalogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Book> booksTableView;
    @FXML
    private TableColumn<Book, Integer> IDColumn;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, String> authorCol;
    @FXML
    private TableColumn<Book, Integer> copiesCol;
    @FXML
    private TableColumn<Book, String> ISBNCol;
    @FXML
    private TextField searchTextField;
    @FXML private Button backBtn;
    //To create a dynamic list of books        
    ObservableList<Book> Books_List = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection connectDb = DatabaseConnection.getInstance().getConnection();
        String viewBooksQuery = "SELECT ID,title,author,available_copies,Book_ISBN FROM BOOKS;";
        try {
            Statement st = connectDb.createStatement();
            ResultSet queryRes = st.executeQuery(viewBooksQuery);
            while (queryRes.next()) {//assign each column in the query to a variable
                Integer ID = queryRes.getInt("ID");
                String title = queryRes.getString("title");
                String author = queryRes.getString("author");
                Integer copies = queryRes.getInt("available_copies");
                String isbn = queryRes.getString("Book_ISBN");

                Books_List.add(new Book(ID, title, author, copies, isbn));
            }
            //Property value factory represents the new book fields that will be put in each cell of the table (takes the fields of book class as parameters in string format)
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
            copiesCol.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));
            ISBNCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));

            booksTableView.setItems(Books_List);
            //Up till here, the list will appear but will be static ,( search functionality is not yet enabled)
            //FilteredList class enables the search functionality.
            FilteredList<Book> filteredBooks = new FilteredList<>(Books_List, b -> true);
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredBooks.setPredicate(Book -> {
                    //If there is no search value(the filed is empty then display all the records)
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String keyword = newValue.toLowerCase();
                    if (Book.getTitle().toLowerCase().indexOf(keyword) > -1) {

                        return true;
                    } else if (Book.getAuthor().toLowerCase().indexOf(keyword) > -1) {

                        return true;
                    } else if (Book.getISBN().toLowerCase().indexOf(keyword) > -1) {

                        return true;
                    } else {
                        return false;
                    }

                });
            });

            SortedList<Book> sortedBooks = new SortedList<>(filteredBooks);
            //Bind sorted data (search results0 to tableView
            sortedBooks.comparatorProperty().bind(booksTableView.comparatorProperty());

            //Add them to the table view       
            booksTableView.setItems(sortedBooks);
        } catch (SQLException s) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, s);
            s.printStackTrace();
        }
    }
     

}
