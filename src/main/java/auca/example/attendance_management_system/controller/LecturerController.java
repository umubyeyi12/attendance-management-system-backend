package auca.example.attendance_management_system.controller;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import auca.example.attendance_management_system.model.Lecturer;
import auca.example.attendance_management_system.service.LecturerService;


@RestController 
@RequestMapping("/api/lecturers")

public class LecturerController {
    @Autowired 
    private LecturerService lecturerService;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Lecturer l){
        Lecturer saved=lecturerService.saveLecturer(l);
        return new ResponseEntity<>("saved id"+saved.getId(),HttpStatus.OK);


    }
    @GetMapping("/all")
    public List<Lecturer> getAll(){
        return lecturerService.getAllLecturers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
      Lecturer l=lecturerService.getLecturerById(id);
      if(l !=null) return new ResponseEntity<>(l,HttpStatus.OK);
      return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id){
        return lecturerService.deleteLecturer(id);


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLecturer(@PathVariable UUID id,@RequestBody Lecturer newInfo){
        Lecturer existing=lecturerService.getLecturerById(id);
        if(existing !=null){
            existing.setFirstName(newInfo.getFirstName());
            existing.setLastName(newInfo.getLastName());
            existing.setEmail(newInfo.getEmail());
            lecturerService.saveLecturer(existing);
            return new ResponseEntity<>("lecturer updated",HttpStatus.OK);

        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
    //partC1
    @GetMapping("/by-course")
    public List<Lecturer> getByCourse(@RequestParam String name){
        return lecturerService.getLecturersByCourse(name);
    }
    //partD3
    @PutMapping("/{lecturerId}/courses/{courseId}")
    public ResponseEntity<?> addCourse(@PathVariable UUID lecturerId,@PathVariable UUID courseId){
        String msg=lecturerService.addCourse(lecturerId,courseId);
        if(msg.contains("does not exist")) return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        if(msg.contains("alread has")) return new ResponseEntity<>(msg,HttpStatus.CONFLICT);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    
    }
    //part D4
    @PatchMapping("/{lecturerId}/lecture-room")
    public ResponseEntity<?> moveLecturer(@PathVariable UUID lecturerId,@RequestParam int roomNumber){
        String msg=lecturerService.moveLecturer(lecturerId,roomNumber);
        if(msg.contains("does not exist")) return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        if(msg.contains("taken"))  return new ResponseEntity<>(msg,HttpStatus.CONFLICT);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
