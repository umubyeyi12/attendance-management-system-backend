package auca.example.attendance_management_system.model;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;

@Entity 
public class ParticipationMark {
    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private String comment;
    private String activity;
    private int points;
    private LocalDate awardedDate;

    @ManyToOne
    @JoinColumn(name="attendance_record_id")
    private AttendanceRecord attendanceRecord;

    public ParticipationMark(){}
    public UUID getId(){
        return id;
    }
    public void setId(UUID id){
        this.id=id;
    }
    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment=comment;
    }
    public String getActivity(){
        return activity;
    }
    public void setActivity(String activity){
        this.activity=activity;
    }
    public int getPoints(){
        return points;
    }
    public void setPoints(int points){
        this.points=points;
    }
    public LocalDate getAwardedDate(){
        return awardedDate;
    }
    public void setAwardedDate(LocalDate awardedDate){
        this.awardedDate=awardedDate;
    }
    public AttendanceRecord getAttendanceRecord(){
        return attendanceRecord;
    }
    public void setAttendanceRecord(AttendanceRecord attendanceRecord){
        this.attendanceRecord=attendanceRecord;
    }
    
}
