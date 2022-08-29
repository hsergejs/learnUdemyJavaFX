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
public class AdminScreenController implements Initializable {

    @FXML
    private Pane homePane;
    @FXML
    private Pane employeesPane;
    @FXML
    private Pane logoutPane;
    @FXML
    private Pane exitPane;
    @FXML
    private Pane customersPane;
    @FXML
    private AnchorPane adminScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mouseExitHome(MouseEvent event) {
        homePane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseHoverHome(MouseEvent event) {
        homePane.setStyle("-fx-background-color: #3291a8; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseExitEmployees(MouseEvent event) {
        employeesPane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseHoverEmployees(MouseEvent event) {
        employeesPane.setStyle("-fx-background-color: #3291a8; -fx-background-radius: 6px;");
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
    private void mouseExitCustomers(MouseEvent event) {
        customersPane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void mouseHoverCustomers(MouseEvent event) {
        customersPane.setStyle("-fx-background-color: #3291a8; -fx-background-radius: 6px;");
    }

    @FXML
    private void homePaneClicked(MouseEvent event) {
        SceneLoader sc = new SceneLoader(adminScreen, "userScreen.fxml", false);
        sc.loadScene();
    }

    @FXML
    private void employeesPaneClicked(MouseEvent event) {
        SceneLoader sc = new SceneLoader(adminScreen, "employeesScreen.fxml", false);
        sc.loadScene();
    }

    @FXML
    private void logoutPaneClicked(MouseEvent event) {
        SceneLoader sc = new SceneLoader(adminScreen, "loginScreen.fxml", false);
        sc.loadScene();
    }

    @FXML
    private void exitPaneClicked(MouseEvent event) {
        Stage currentStage = (Stage) adminScreen.getScene().getWindow();
        currentStage.close();
        System.exit(0);
    }

    @FXML
    private void customersPaneClicked(MouseEvent event) {
        SceneLoader sc = new SceneLoader(adminScreen, "customersScreen.fxml", false);
        sc.loadScene();
    }
    
}
