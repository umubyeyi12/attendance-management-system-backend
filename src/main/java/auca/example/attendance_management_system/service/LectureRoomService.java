package auca.example.attendance_management_system.service;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.example.attendance_management_system.model.LectureRoom;
import auca.example.attendance_management_system.repository.LectureRoomRepository;

@Service
public class LectureRoomService{
    @Autowired
    private LectureRoomRepository roomRepo;

    public LectureRoom saveRoom(LectureRoom r){
        return roomRepo.save(r);
    }
    public List<LectureRoom> getAllRooms(){
        return roomRepo.findAll();
    }
    public LectureRoom getRoomById(UUID id){
        return roomRepo.findById(id).orElse(null);

    }
    public String deleteRoom(UUID id){
        roomRepo.deleteById(id);
        return "Lecture Room deleted";
    }
    //partC2
    public List<LectureRoom> getFreeRooms(){
        return roomRepo.findFreeRooms();
    }
}
