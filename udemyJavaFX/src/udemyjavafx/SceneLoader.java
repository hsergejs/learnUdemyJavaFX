/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udemyjavafx;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author prulj
 */
public class SceneLoader {
    private AnchorPane paneName;
    private String sceneName;
    private boolean fullScreen;
    
    public SceneLoader(AnchorPane pn, String sc, boolean fscr){
        this.paneName = pn;
        this.sceneName = sc;
        this.fullScreen = fscr;
    }
    
    public void loadScene() {
        Stage stageToLoad = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(sceneName));
        } catch (IOException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage currentStage = (Stage) paneName.getScene().getWindow();
        Scene sceneToLoad = new Scene(root);
        stageToLoad.setScene(sceneToLoad);
        stageToLoad.initStyle(StageStyle.TRANSPARENT);
        stageToLoad.setResizable(false);
        currentStage.close();
        stageToLoad.show();
    }
}
