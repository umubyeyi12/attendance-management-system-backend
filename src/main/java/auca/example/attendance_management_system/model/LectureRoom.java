package auca.example.attendance_management_system.model;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class LectureRoom{
    @Id
    @GeneratedValue(strategy=GenerationType.UUID) 

    private UUID id;
    private int roomNumber;
    private String name;
    public LectureRoom(){}
    
    public UUID getId(){
        return id;
    }
    public void setId(UUID id){
        this.id=id;
    }

    public int getRoomNumber(){
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber){
        this.roomNumber=roomNumber;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

}