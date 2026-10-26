package auca.example.attendance_management_system.controller;
import java.util.List;
import  java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import auca.example.attendance_management_system.model.Student;
import auca.example.attendance_management_system.service.StudentService;

@RestController 
@RequestMapping("/api/students")

public class StudentController {
    @Autowired 
    private StudentService studentService;

    @GetMapping("/all")
    public List<Student>getAll(){
        return studentService.getAllStudents();
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Student s){
        Student saved=studentService.saveStudent(s);
        return new ResponseEntity<>("Savedi Id:"+ saved.getId(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable UUID id){
        Student s=studentService.getStudentById(id);
        if(s !=null) return new ResponseEntity<>(s,HttpStatus.OK);
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id){
        return studentService.deleteStudent(id);
    } 
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable UUID id,@RequestBody Student newInfo){
        Student existing=studentService.getStudentById(id);
        if(existing !=null){
            existing.setFirstName(newInfo.getFirstName()) ;    
            existing.setLastName (newInfo.getLastName());
            existing.setEmail(newInfo.getEmail());
            existing.setProgramme(newInfo.getProgramme());
            existing.setRegNumber(newInfo.getRegNumber());
            studentService.saveStudent(existing);
            return new ResponseEntity<>("student update",HttpStatus.OK);


         }
         return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
    
}
