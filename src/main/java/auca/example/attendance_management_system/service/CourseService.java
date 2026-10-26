package auca.example.attendance_management_system.service;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.example.attendance_management_system.model.Course;
import auca.example.attendance_management_system.repository.CourseRepository;

@Service
public class CourseService{
    @Autowired
    private CourseRepository courseRepo;

    public Course saveCourse(Course c){
        return courseRepo.save (c);
    }
    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }
    public Course getCourseById(UUID id){
        return courseRepo.findById(id).orElse(null);
    }
    public String deleteCourse(UUID id){
        courseRepo.deleteById(id);
        return "course deleted";
    }
}