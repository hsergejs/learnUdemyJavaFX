
package udemyjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author prulj
 */
public class BootScreenController implements Initializable {

    @FXML
    private AnchorPane bootScreen;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private ImageView bootScreenImage;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add fade animation to the screen
        FadeTransition fadeTransitionPane = new FadeTransition(Duration.millis(2500), bootScreenImage);
        fadeTransitionPane.setFromValue(1.0);
        fadeTransitionPane.setToValue(0);
        fadeTransitionPane.play();
        
        FadeTransition fadeTransitionIndicator = new FadeTransition(Duration.millis(2500), progressIndicator);
        fadeTransitionIndicator.setFromValue(1.0);
        fadeTransitionIndicator.setToValue(0);
        fadeTransitionIndicator.play();
        
        fadeTransitionPane.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneLoader sceneLoader = new SceneLoader(bootScreen, "loginScreen.fxml", false);
                sceneLoader.loadScene();
            }
        });
    }    
    
}
