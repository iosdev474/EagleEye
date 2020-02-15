package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Outcome {

    @FXML
    private AnchorPane back;

    @FXML
    private Circle userImage;

    @FXML
    private Text status;

    @FXML
    private Text pkill;

    @FXML
    private Text pdeath;

    @FXML
    private Text pname;

    @FXML
    private ImageView p;

    @FXML
    void close(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.toBack();
    }

    public void initialize() {

//        Gson gson = new Gson();
        String input="{\"p1\",\"ct\",2,3,0}";
//        PlayerInfo playerInfo = gson.fromJson(input, PlayerInfo.class);
        pname.setText(input.split(",")[0].split("\"")[1]);
        pkill.setText("Kills: " + input.split(",")[2]);
        pdeath.setText("Deaths: " + input.split(",")[3]);
        status.setText("");
        if(Integer.parseInt(input.split(",")[4].split("}")[0]) == 1) {
            p.setImage(new Image("sample/cross.png"));
        } else {
            p.setImage(new Image("sample/tick.png"));
        }
        userImage.setFill(new ImagePattern(new Image("sample/1.jpg")));
    }

}
