package auca.example.attendance_management_system.controller;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import auca.example.attendance_management_system.model.LectureRoom;
import auca.example.attendance_management_system.service.LectureRoomService;

@RestController 
@RequestMapping("/api/lecture-rooms")

public class LectureRoomController {
    @Autowired 
    private LectureRoomService roomService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody LectureRoom r){
        LectureRoom saved=roomService.saveRoom(r);
        return new ResponseEntity<>("saved id:"+saved.getId(),HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<LectureRoom> getAll(){
        return roomService.getAllRooms();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        LectureRoom r=roomService.getRoomById(id);
        if(r!=null) return new ResponseEntity<>(r,HttpStatus.OK);
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);

    }
   @ DeleteMapping("/delete/{id}")
   public String delete(@PathVariable UUID id){
   return roomService.deleteRoom(id);
   } 

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable UUID id,@RequestBody LectureRoom newInfo){
        LectureRoom existing= roomService.getRoomById(id);
        if(existing != null){
            existing.setRoomNumber(newInfo.getRoomNumber());
            existing.setName(newInfo.getName());
            roomService.saveRoom(existing);
            return new ResponseEntity<>("lecture room updated",HttpStatus.OK);

        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);

    }
    @GetMapping("/free")
    public List<LectureRoom> getFree(){
        return roomService.getFreeRooms();
    }
    
}
