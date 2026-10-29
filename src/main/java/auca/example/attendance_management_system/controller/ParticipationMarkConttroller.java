package auca.example.attendance_management_system.controller;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import auca.example.attendance_management_system.model.ParticipationMark;
import auca.example.attendance_management_system.service.ParticipationMarkService;

@RestController 
@RequestMapping("/api/participation-marks")
public class ParticipationMarkConttroller {
    @Autowired 
    private ParticipationMarkService markService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam UUID attendanceRecordId,@RequestBody ParticipationMark p){
        String msg=markService.saveMark(attendanceRecordId,p);
        if(msg.equals("The attendance record with that id does not exist"))
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        if(msg.equals("Participation marks are only for VERIFIED attendance records"))
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }

    @GetMapping("/student/{regNumber}")
    public List<ParticipationMark> getByStudent(@PathVariable String regNumber){
        return markService.getMarksByStudent(regNumber);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        String msg=markService.deleteMark(id);
        if(msg.equals("The participation mark with that id does not exist"))
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
    
}
