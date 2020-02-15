package sample;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

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

    }

    @FXML
    void minimize(MouseEvent event) {

    }

    public void initialize() {
        Gson gson = new Gson();
        String input="{\"pname\": \"p1\",\"team\": \"ct\",\"kill\": 2,\"death\": 3,\"ishacker\": 0}";
        PlayerInfo playerInfo = gson.fromJson(input, PlayerInfo.class);
        pname.setText(playerInfo.pname);
        pkill.setText("Kills: " + playerInfo.kill);
        pdeath.setText("Deaths: " + playerInfo.death);
        status.setText("");
        if(playerInfo.ishacker == 1) {
            p.setImage(new Image("sample/cross.png"));
        } else {
            p.setImage(new Image("sample/tick.png"));
        }
        userImage.setFill(new ImagePattern(new Image("sample/1.jpg")));
    }

}
