package auca.example.attendance_management_system.model;


import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class AttendanceRecord{

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private LocalDate classDate;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    private String courseName;
    private String timeSlot;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name="lecturer_id")
    private Lecturer lecturer;

    public AttendanceRecord(){}

    public UUID getId(){
        return id;
    }
    public void setId(UUID id){
        this.id=id;
    }
    public LocalDate getClassDate(){
        return classDate;
    }
    public void setClassDate(LocalDate classDate){
        this.classDate=classDate;
    }
    public AttendanceStatus getStatus(){
        return status;
    }
    public  void setStatus(AttendanceStatus status){
        this.status=status;
    }
    public String getCourseName(){
        return courseName;
    }
    public void setCourseName(String courseName){
        this.courseName=courseName;
    }
    public String getTimeSlot(){
        return timeSlot;
    }
    public void setTimeSlot(String timeSlot){
        this.timeSlot=timeSlot;
    }
    public Student getStudent(){
        return student;
    }
    public void setStudent(Student student){
        this.student=student;
    }
    public Lecturer getLecturer(){
        return lecturer;
    }
    public void setLecturer(Lecturer lecturer){
        this.lecturer=lecturer;
    }
}