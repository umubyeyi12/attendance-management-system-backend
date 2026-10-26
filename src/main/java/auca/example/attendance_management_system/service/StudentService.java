package auca.example.attendance_management_system.service;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.example.attendance_management_system.model.Student;
import auca.example.attendance_management_system.repository.StudentRepository;

@Service
public class StudentService{
    @Autowired
    private StudentRepository studentRepo;

    public Student saveStudent(Student s){
        return studentRepo.save(s);
    }
    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }
    public Student getStudentById(UUID id){
        return studentRepo.findById(id).orElse(null);
    }
    public String deleteStudent(UUID id){
        studentRepo.deleteById(id);
        return"Student deleted";
    }
    //PatB1
    public List<Student> getStudentsByLastName(String lastName){
        return studentRepo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
}