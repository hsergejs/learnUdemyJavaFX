/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udemyjavafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;





/**
 * FXML Controller class
 *
 * @author prulj
 */
public class AvailableRoomScreenController implements Initializable {

    @FXML
    private AnchorPane availableRoomScreen;

    @FXML
    private JFXTreeTableView<Room> treeTableView;

    @FXML
    private JFXTextField searchOrAvailabilityField;


    @FXML
    private JFXCheckBox busyCheckBox;

    @FXML
    private JFXCheckBox availableCheckBox;

    @FXML
    private JFXButton searchButtonBusyAvailableCheckBox;
    
    private String roomAvailability;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllRooms("SELECT * FROM room");
    }    
    
    private void loadAllRooms(String sql){
        JFXTreeTableColumn<Room,String> room_id = new JFXTreeTableColumn<>("Room id");
        room_id.setPrefWidth(100);
        room_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> arg0) {
                return arg0.getValue().getValue().id;
            }
        });
        
        JFXTreeTableColumn<Room,String> room_num = new JFXTreeTableColumn<>("Room number");
        room_num.setPrefWidth(100);
        room_num.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> arg0) {
                return arg0.getValue().getValue().roomNumber;
            }
        });
     
        JFXTreeTableColumn<Room,String> room_price = new JFXTreeTableColumn<>("Room price");
        room_price.setPrefWidth(100);
        room_price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> arg0) {
                return arg0.getValue().getValue().rooomPrice;
            }
        });
        
        JFXTreeTableColumn<Room,String> room_status = new JFXTreeTableColumn<>("Room status");
        room_status.setPrefWidth(100);
        room_status.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> arg0) {
                return arg0.getValue().getValue().roomStatus;
            }
        });
        
        ObservableList<Room> room = FXCollections.observableArrayList();
        
        Connection connection = DBconnector.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                room.add(new Room(rs.getInt(1)+"", rs.getInt(2)+"",rs.getFloat(3)+"",rs.getInt(4)+""));
            }
            
        } catch (Exception e) {
            Logger.getLogger(AvailableRoomScreenController.class.getName()).log(Level.SEVERE,null,e);
        }
        
        final TreeItem<Room> treeItemRootItem = new RecursiveTreeItem<Room>(room, RecursiveTreeObject::getChildren);
        treeTableView.getColumns().setAll(room_id, room_num, room_price, room_status);
        treeTableView.setRoot(treeItemRootItem);
        treeTableView.setShowRoot(false);
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AvailableRoomScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchByRoomNumberClicked(MouseEvent event) {
        String filedText = searchOrAvailabilityField.getText().toString().trim();
        int availability = Integer.parseInt(filedText);
        loadAllRooms("SELECT * FROM room WHERE roomNumber = '" + availability + "'");
    }

    @FXML
    private void makeAvailableClicked(MouseEvent event) {
        int result = 0;
        String filedText = searchOrAvailabilityField.getText().toString().trim();
        int availability = Integer.parseInt(filedText);
        String sql = "UPDATE room SET roomStatus = ? WHERE roomNumber = ?";
        Connection connection = DBconnector.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement)connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, availability);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AvailableRoomScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(result>0){
            Alerts customAlert = new Alerts();
            customAlert.showInfoAlert("Room status update", "", "Room status updated successfully!");
            loadAllRooms("SELECT * FROM room");
        }
        else{
            Alerts customAlert = new Alerts();
            customAlert.showErrorAlert("Error", "", "Rooms status update error!");
        }
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AvailableRoomScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchBusyAvailablePressed(MouseEvent event) {
        loadAllRooms(roomAvailability);
    }

    @FXML
    private void backButtonClicked(MouseEvent event) {
        SceneLoader sceneLoader = new SceneLoader(availableRoomScreen, "userScreen.fxml", true);
        sceneLoader.loadScene();
    }

    @FXML
    private void closeButtonClicked(MouseEvent event) {
        Stage currentStage = (Stage) availableRoomScreen.getScene().getWindow();
        currentStage.close();
        System.exit(0);
    }

    @FXML
    private void onActionBusyCheckBox(ActionEvent event) {
        busyCheckBox.setSelected(false);
        roomAvailability = "SELECT * FROM room WHERE roomStatus = 1";
    }

    @FXML
    private void onActionAvailableCheckBox(ActionEvent event) {
        availableCheckBox.setSelected(false);
        roomAvailability = "SELECT * FROM room WHERE roomStatus = 0";
    }
}

