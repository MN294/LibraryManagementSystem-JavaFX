/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lms;

/**
 *
 * @author mariam_youssef
 */
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowSwitcher {

    public static void openNewWindow(String fxmlPath, String title, boolean closeCurrentStage, Stage currentStage) {
        try {
            Parent root = FXMLLoader.load(WindowSwitcher.class.getResource(fxmlPath));
            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(new Scene(root));
            newStage.show();

            if (closeCurrentStage && currentStage != null) {
                currentStage.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}