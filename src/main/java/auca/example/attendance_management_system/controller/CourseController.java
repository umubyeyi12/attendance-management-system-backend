package auca.example.attendance_management_system.controller;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import auca.example.attendance_management_system.model.Course;
import auca.example.attendance_management_system.service.CourseService;

@RestController 
@RequestMapping("/api/courses")

public class CourseController {

    @Autowired 
    private CourseService courseService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Course c){
        Course saved=courseService.saveCourse(c);
        return new ResponseEntity<>("saved id:"+saved.getId(),HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<Course> getAll(){
        return courseService.getAllCourses();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        Course c=courseService.getCourseById(id);
        if(c!= null) return new ResponseEntity<>(c,HttpStatus.OK);
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable UUID id,@RequestBody Course newInfo){
        Course existing=courseService.getCourseById(id);
        if(existing != null){
            existing.setName(newInfo.getName());
            courseService.saveCourse(existing);
            return new ResponseEntity<>("course updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id){
        return courseService.deleteCourse(id);
    }
    
}
