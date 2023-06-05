package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    public Button btn11;
    @FXML
    public Button btn12;
    @FXML
    public Button btn13;
    @FXML
    public Button btn14;
    @FXML
    public Button btn15;

    @FXML
    public Button btn21;
    @FXML
    public Button btn22;
    @FXML
    public Button btn23;
    @FXML
    public Button btn24;
    @FXML
    public Button btn25;

    @FXML
    private void circlePls(ActionEvent e) {
        System.out.println("working");
    }
    @FXML
    private void btn11(ActionEvent e) {
        System.out.println("chwdp11");
    }
    @FXML
    private void btn12(ActionEvent e) {
        System.out.println("chwdp12");
    }
    @FXML
    private void btn13(ActionEvent e) {
        System.out.println("chwdp13");
    }
    @FXML
    private void btn14(ActionEvent e) {
        System.out.println("chwdp14");
    }
    @FXML
    private void btn15(ActionEvent e) {
        System.out.println("chwdp15");
    }

    @FXML
    private void btn21(ActionEvent e) {
        System.out.println("chwdp11");
    }
    @FXML
    private void btn22(ActionEvent e) {
        System.out.println("chwdp12");
    }
    @FXML
    private void btn23(ActionEvent e) {
        System.out.println("chwdp13");
    }
    @FXML
    private void btn24(ActionEvent e) {
        System.out.println("chwdp14");
    }
    @FXML
    private void btn25(ActionEvent e) {
        System.out.println("chwdp15");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn11.setPickOnBounds(false);
        btn12.setPickOnBounds(false);
        btn13.setPickOnBounds(false);
        btn14.setPickOnBounds(false);
        btn15.setPickOnBounds(false);

        btn21.setPickOnBounds(false);
        btn22.setPickOnBounds(false);
        btn23.setPickOnBounds(false);
        btn24.setPickOnBounds(false);
        btn25.setPickOnBounds(false);
    }
}
