/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udemyjavafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.crypto.spec.PSource;

/**
 * FXML Controller class
 *
 * @author prulj
 */
public class ReservationsScreenController implements Initializable {

    @FXML
    private AnchorPane reservationScreen;
    @FXML
    private JFXDatePicker startReservationDateField;
    @FXML
    private JFXDatePicker endReservationDateField;
    @FXML
    private JFXTextField customerSurnameField;
    @FXML
    private JFXTextField customerNameField;
    @FXML
    private JFXTextField roomNumberField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backButtonClicked(MouseEvent event) {
        SceneLoader sc = new SceneLoader(reservationScreen, "userScreen.fxml", true);
        sc.loadScene();
    }

    @FXML
    private void closeButtonClicked(MouseEvent event) {
        Stage currentStage = (Stage) reservationScreen.getScene().getWindow();
        currentStage.close();
        System.exit(0);
    }

    @FXML
    private void printButtonClicked(MouseEvent event) {
    }

    @FXML
    private void bookButtonClicked(MouseEvent event) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO reservations (roomNumber,customerName,customerSurname,reservationDateFrom,reservationDateTill) VALUES(?,?,?,?,?)";
        Connection connection = DBconnector.getConnection();
        try {
            PreparedStatement ps = (PreparedStatement)connection.prepareStatement(sql);
            int roomNum = Integer.valueOf(roomNumberField.getText().trim());
            ps.setInt(1, roomNum);
            ps.setString(2, customerNameField.getText().toString().trim());
            ps.setString(3, customerSurnameField.getText().toString().trim());
            Date sqlDateFrom = Date.valueOf(startReservationDateField.getValue());
            ps.setDate(4, sqlDateFrom);
            Date sqlDateTill = Date.valueOf(endReservationDateField.getValue());
            ps.setDate(5, sqlDateTill);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(result>0){
            updateRoomAvailability();
        }
        else{
            Alerts customAlerts = new Alerts();
            customAlerts.showErrorAlert("Error saving reservation", "", "Erorr reservation saving!");
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReservationsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    private void updateRoomAvailability(){
       int result = 0;
       int roomNum = Integer.valueOf(roomNumberField.getText().trim());
       String sql = "UPDATE room SET roomStatus = ? WHERE roomNumber = ?";
       Connection connection = DBconnector.getConnection();
            PreparedStatement ps;
        try {
            ps = (PreparedStatement) connection.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, roomNum);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            if(result>0){
                Alerts customAlerts = new Alerts();
                customAlerts.showInfoAlert("Reservation status", "", "Reservation saved!");
            }
            else{
                Alerts customAlerts = new Alerts();
                customAlerts.showErrorAlert("Room status update", "", "Reservation error!");
            }
            
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void resetButtonClicked(MouseEvent event) {
        roomNumberField.setText("");
        customerNameField.setText("");
        customerSurnameField.setText("");
        endReservationDateField.setValue(null);
        startReservationDateField.setValue(null);
    }
    
}
