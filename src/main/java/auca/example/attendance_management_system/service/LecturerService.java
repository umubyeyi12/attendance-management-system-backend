package auca.example.attendance_management_system.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.example.attendance_management_system.model.Lecturer;
import auca.example.attendance_management_system.repository.LecturerRepository;
import auca.example.attendance_management_system.repository.CourseRepository;
import auca.example.attendance_management_system.model.Course;
import auca.example.attendance_management_system.repository.LectureRoomRepository;
import auca.example.attendance_management_system.model.LectureRoom;

@Service
public class LecturerService{
    @Autowired
    private LecturerRepository lecturerRepo;
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private LectureRoomRepository roomRepo;

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
    //partD3
    public String addCourse(UUID lecturerId,UUID courseId){
        Lecturer l=lecturerRepo.findById(lecturerId).orElse(null);
        if(l==null) return ("lecturer with that id does not exist");
        Course c=courseRepo.findById(courseId).orElse(null);
        if(c== null) return"that  course does not exist";
        if(l.getCourses().contains(c)) return "lecturer already has that course";
        l.getCourses().add(c);
        lecturerRepo.save(l);
        return"course added to the lecturer";
    }
    //part D4
    public String moveLecturer(UUID lecturerId,int roomNumber){
        Lecturer l=lecturerRepo.findById(lecturerId).orElse(null);
        if(l==null) return "the lecturer with that id does not exist";

        LectureRoom r=roomRepo.findByRoomNumber(roomNumber).orElse(null);
        if(r== null) return "lecture room with that roomNumber does not exist";
        if(lecturerRepo.existsByLectureRoomIdAndIdNot(r.getId(),lecturerId)){
            return "lecture room is taken by another lecture";

        }
        l.setLectureRoom(r);
        lecturerRepo.save(l);
        return"lecture room changed  successfully";
    }
}