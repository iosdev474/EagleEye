package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.omg.SendingContext.RunTime;

import java.io.*;
import java.util.ArrayList;

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
    void demoSelect(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Demo File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Demo Files", "*.dem"));
        File file = fileChooser.showOpenDialog((Stage) ((Node) (event.getSource())).getScene().getWindow());
        if (file != null) {
            if (file.exists()) {
                DemoFileAddress.setText(file.getCanonicalPath());
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
        if (file != null) {
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
    void runModel(ActionEvent event) throws IOException {
        if (!DemoFileAddress.equals("")) {
            File f = new File("../Dataset/test.csv");
            if(!f.exists()){
                f.createNewFile();
                FileWriter bw = new FileWriter(f);
                bw.write("name,team,kills,deaths,attacker_x,attacker_y,attacker_z,attacker_pitch,attacker_yaw,attacker_ducked,victim_x,victim_y,victim_z,victim_pitch,victim_yaw,victim_ducked,wallbang,isHeadshot,isHacker\n");
                bw.close();
            }
            else{
                f.delete();
                f.createNewFile();
                FileWriter bw = new FileWriter(f);
                bw.write("name,team,kills,deaths,attacker_x,attacker_y,attacker_z,attacker_pitch,attacker_yaw,attacker_ducked,victim_x,victim_y,victim_z,victim_pitch,victim_yaw,victim_ducked,wallbang,isHeadshot,isHacker\n");
                bw.close();
            }
            String[] sb = new String[5];
            sb[0] = "node";
            sb[1] = "../Parser/parser.js";
            sb[2] = (DemoFileAddress.getText());
            sb[3] = ("PyroManiac");
            sb[4] = ("../Dataset/test.csv");
            try {
                Process p = Runtime.getRuntime().exec(sb);
                int i = p.waitFor();
                System.out.println(p.exitValue());
            } catch (IOException | InterruptedException e) {
                System.out.println(e);
                System.out.println("Error in Parsing");
                return;
            }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            sb = new String[2];
            sb[0]= ("python");
            sb[1]=("../Model/Pickel_and_later.py");

            try {
                Process p = Runtime.getRuntime().exec(sb);
                System.out.println(p.waitFor());
            }
            catch (IOException | InterruptedException e){
                System.out.println(e);
                System.out.println("Error in Model Running");
                return;
            }
                Parent home_parent = null;
                try {
                    home_parent = FXMLLoader.load(getClass().getResource("../sample/Outcome.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene Home = new Scene(home_parent);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(Home);
                window.show();

            }
        }


    }
