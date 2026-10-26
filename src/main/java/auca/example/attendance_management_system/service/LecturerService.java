package auca.example.attendance_management_system.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.example.attendance_management_system.model.Lecturer;
import auca.example.attendance_management_system.repository.LecturerRepository;

@Service
public class LecturerService{
    @Autowired
    private LecturerRepository lecturerRepo;

    public Lecturer saveLecturer(Lecturer l){
        return lecturerRepo.save(l);
    }
    public List<Lecturer> getAllLecturers(){
        return lecturerRepo.findAll();
    }
    public Lecturer getLecturerById(UUID id){
        return  lecturerRepo.findById(id).orElse(null);
    }
    public String deleteLecturer(UUID id){
        lecturerRepo.deleteById(id);
        return "Lecturer deleted";
    }
    //partC1
    public List<Lecturer> getLecturersByCourse(String name){
        return lecturerRepo.findByCourseName(name);
    }
}