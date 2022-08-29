package udemyjavafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginScreenController implements Initializable {

    @FXML
    private AnchorPane loginScreen;
    @FXML
    private JFXTextField loginName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginScreen.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    loginAction();
                }

                if (event.getCode().equals(KeyCode.ESCAPE)) {
                    logoutAction();
                }
            }
        });
    }

    @FXML
    private void loginButtonClicked(MouseEvent event) {
        loginAction();
    }

    @FXML
    private void cancelButtonClicked(MouseEvent event) {
        logoutAction();
    }

    private void logoutAction() {
        Stage currentStage = (Stage) loginScreen.getScene().getWindow();
        currentStage.close();
        System.exit(0);
    }

    private void loginAction() {
        boolean userExists = false;
        String pass = "";
        int userType = 99;

        if (loginName.getText().trim().isEmpty()) {
            Alerts.showErrorAlert("Error login name field",null,"Please enter Login name!");
        } else {
            if (password.getText().trim().isEmpty()) {
                Alerts.showErrorAlert("Error password field",null,"Please enter password!");
            } else {

                String sqlQuery = "SELECT * FROM users WHERE user_email = '" + loginName.getText().trim() + "'";
                Connection connection = DBconnector.getConnection();
                try {
                    PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sqlQuery);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        userExists = true;
                        pass = rs.getString(3);
                        userType = rs.getInt(4);
                    }

                    if (!userExists) {
                        Alerts.showErrorAlert("User Error", null, "User is not registered!");
                    } else {
                        if (password.getText().toString().trim().equals(pass)) {
                            if (userType == 0) {
                                SceneLoader sceneLoader = new SceneLoader(loginScreen,"adminScreen.fxml",true);
                                sceneLoader.loadScene();
                                
                            } else if (userType == 1) {
                                SceneLoader sceneLoader = new SceneLoader(loginScreen,"userScreen.fxml",true);
                                sceneLoader.loadScene();
                            } else {
                                Alerts.showErrorAlert("User access error", null, "This user don't have access!");
                            }
                        } else {
                            Alerts.showErrorAlert("Password error", null, "Please enter correct password!");
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
