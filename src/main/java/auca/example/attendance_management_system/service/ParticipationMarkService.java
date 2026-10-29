package auca.example.attendance_management_system.service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import auca.example.attendance_management_system.model.AttendanceRecord;
import auca.example.attendance_management_system.model.AttendanceStatus;
import auca.example.attendance_management_system.model.ParticipationMark;
import auca.example.attendance_management_system.repository.AttendanceRecordRepository;
import auca.example.attendance_management_system.repository.ParticipationMarkRepository;

@Service
public class ParticipationMarkService {

  @Autowired 
  private ParticipationMarkRepository markRepo;
  @Autowired 
  private AttendanceRecordRepository recordRepo;
  
  //Part E1
  public String saveMark(UUID attendanceRecordId, ParticipationMark p){
    AttendanceRecord r=recordRepo.findById(attendanceRecordId).orElse(null);
    if(r==null) return "The attendance record with that id does not exist";
    if(r.getStatus() !=AttendanceStatus.VERIFIED) return"Participation marks are only for Verified attendance records";
    
    p.setAwardedDate(LocalDate.now());
    p.setAttendanceRecord(r);
    markRepo.save(p);
    return "Participation mark saved successfully";

 }

 //Part E2
 public List<ParticipationMark> getMarksByStudent(String regNumber){
    return markRepo.findByStudentRegNumber(regNumber);

 } 
 //Part E3
 public String deleteMark(UUID id){
    ParticipationMark p=markRepo.findById(id).orElse(null);
    if(p==null) return"The participation mark with that id does not exist";
    markRepo.delete(p);
    return "Participation mark deleted succcessfully";
 }

}
