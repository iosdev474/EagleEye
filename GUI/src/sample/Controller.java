package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {

    @FXML
    Label DemoFileAddress;

    @FXML
    Label modelAddress;

    @FXML
    void close(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    void demoSelect(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Demo File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Demo Files", "*.dem"));
        File file = fileChooser.showOpenDialog((Stage) ((Node) (event.getSource())).getScene().getWindow());
        if(file != null) {
            if (file.exists()) {
                DemoFileAddress.setText(file.getAbsolutePath());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No File Found");
                alert.setContentText("No file was found or none were selected");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.toBack();
    }

    @FXML
    void modelSelect(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Model File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model Files", "*.py"));
        File file = fileChooser.showOpenDialog((Stage) ((Node) (event.getSource())).getScene().getWindow());
        if(file != null) {
            if (file.exists()) {
                modelAddress.setText(file.getAbsolutePath());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No File Found");
                alert.setContentText("No file was found or none were selected");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void runModel(ActionEvent event) {

    }




}
