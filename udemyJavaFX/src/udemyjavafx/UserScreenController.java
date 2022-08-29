/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udemyjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author prulj
 */
public class UserScreenController implements Initializable {

    @FXML
    private AnchorPane userScreen;
    @FXML
    private Pane reservationsPane;
    @FXML
    private Pane availableRoomPane;
    @FXML
    private Pane logoutPane;
    @FXML
    private Pane exitPane;
    @FXML
    private Pane customerInfoPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mouseExitReservations(MouseEvent event) {
        reservationsPane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseHoverReservations(MouseEvent event) {
        reservationsPane.setStyle("-fx-background-color: #3291a8; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseExitAvailableRoom(MouseEvent event) {
        availableRoomPane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseHoverAvailableRoom(MouseEvent event) {
        availableRoomPane.setStyle("-fx-background-color: #3291a8; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseExitLogout(MouseEvent event) {
        logoutPane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseHoverLogout(MouseEvent event) {
        logoutPane.setStyle("-fx-background-color: #3291a8; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseExitExit(MouseEvent event) {
        exitPane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseHoverExit(MouseEvent event) {
        exitPane.setStyle("-fx-background-color: #3291a8; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseExitCustomersInfo(MouseEvent event) {
        customerInfoPane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseHoverCustomersInfo(MouseEvent event) {
        customerInfoPane.setStyle("-fx-background-color: #3291a8; -fx-background-radius: 6px;");
    }

    @FXML
    private void reservationsPaneClicked(MouseEvent event) {
        SceneLoader sc = new SceneLoader(userScreen, "reservationsScreen.fxml", false);
        sc.loadScene();
    }

    @FXML
    private void availableRoomPaneClicked(MouseEvent event) {
        SceneLoader sc = new SceneLoader(userScreen, "availableRoomScreen.fxml", false);
        sc.loadScene();
    }

    @FXML
    private void logoutPaneClicked(MouseEvent event) {
        SceneLoader sc = new SceneLoader(userScreen, "loginScreen.fxml", false);
        sc.loadScene();
    }

    @FXML
    private void exitPaneClicked(MouseEvent event) {
        Stage currentStage = (Stage) userScreen.getScene().getWindow();
        currentStage.close();
        System.exit(0);
    }

    @FXML
    private void customersInfoPaneClicked(MouseEvent event) {
        SceneLoader sc = new SceneLoader(userScreen, "customersInfoScreen.fxml", false);
        sc.loadScene();
    }
    
}
