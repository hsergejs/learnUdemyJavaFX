/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udemyjavafx;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author prulj
 */
public class Room extends RecursiveTreeObject<Room>{
    StringProperty id;
    StringProperty roomNumber;
    StringProperty rooomPrice;
    StringProperty roomStatus;
    
    public Room(){
        super();
    }

    public Room(String id, String roomNumber, String rooomPrice, String roomStatus) {
        this.id = new SimpleStringProperty(id);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.rooomPrice = new SimpleStringProperty(rooomPrice);
        this.roomStatus = new SimpleStringProperty(roomStatus);
        
    }
    
    
}
