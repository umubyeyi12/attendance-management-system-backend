package auca.example.attendance_management_system.model;

import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class Lecturer{
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name="lecture_room_id")
    private LectureRoom lectureRoom;

    @ManyToMany@JoinTable(name="lecturer_course",joinColumns=@JoinColumn(name="lecturer_id"),inverseJoinColumns=@JoinColumn(name="course_id"))
    private List<Course>courses;

    public Lecturer(){}
    
    public UUID getId(){
        return id;
    }
    public void setId(UUID id){
        this.id=id;
    }
    public String getFirstName(){
        return firstName;

    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public String getLastName(){
        return lastName;

    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;

    }
    public LectureRoom getLectureRoom(){
        return lectureRoom;
    }
    public void setLectureRoom(LectureRoom lectureRoom){
        this.lectureRoom=lectureRoom;
    }
    public List<Course>getCourses(){
        return courses;
    }
    public void setCourses(List<Course> courses){
        this.courses=courses;
    }
}