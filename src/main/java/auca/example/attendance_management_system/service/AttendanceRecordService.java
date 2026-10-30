package auca.example.attendance_management_system.service;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import auca.example.attendance_management_system.model.AttendanceStatus;
import auca.example.attendance_management_system.model.AttendanceRecord;
import auca.example.attendance_management_system.repository.AttendanceRecordRepository;

@Service
public class AttendanceRecordService{
    @Autowired
    private AttendanceRecordRepository attendanceRepo;
   
    
    public List<AttendanceRecord> getAllRecords(){
        return attendanceRepo.findAll();
    }
    public AttendanceRecord getRecordById(UUID id){
        return attendanceRepo.findById(id).orElse(null);
    }
    public String deleteRecord(UUID id){
        attendanceRepo.deleteById(id);
        return"Record deleted";
    }
    //partB2
    public List<AttendanceRecord> getRecordsByStatus(AttendanceStatus status){
        return attendanceRepo.findByStatusOrderByClassDateAsc(status);
    }
    //partB3
    public List<AttendanceRecord> getRecordsBetween(LocalDate start,LocalDate end){
        return attendanceRepo.findByClassDateBetweenOrderByClassDateAsc(start,end);
    }
    //Part D1
    public String saveRecord(AttendanceRecord r){
        // check if date is in paste
        if(r.getClassDate().isBefore(java.time.LocalDate.now())){
            return "Attendance record date cannot be in the past";
        }
        //check if lecturer is already booked on that date
        boolean isBooked=attendanceRepo.existsByLecturerIdAndClassDateAndStatusNot(r.getLecturer().getId(),r.getClassDate(),AttendanceStatus.ABSENT);
           if(isBooked){
            return "Lecturer already has a class record on that date";
           }
           //class EXPECTED and Save
           r.setStatus(AttendanceStatus.EXPECTED);
           attendanceRepo.save(r);
           return "Attendance record saved successfully";
    }
    //partD2
    public String updateStatus(UUID id,AttendanceStatus newStatus){
        AttendanceRecord r =attendanceRepo.findById(id).orElse(null);
        if(r==null) return "The attendance record with that id does not exist";
        AttendanceStatus current=r.getStatus();
        boolean allowed =false;

        if(current==AttendanceStatus.EXPECTED && (newStatus==AttendanceStatus.PRESENT || newStatus==AttendanceStatus.ABSENT))allowed=true;
        if(current==AttendanceStatus.PRESENT &&(newStatus==AttendanceStatus.VERIFIED || newStatus==AttendanceStatus.ABSENT))allowed=true;
        if(!allowed){
            return " Cannot change status from" + current + " to "+ newStatus;
        }
        r.setStatus(newStatus);
        attendanceRepo.save(r);
        return "Attendance record status updated successfully";
    }
    //Part F1
    public List<Object[]> getCountByStatus(){
        return attendanceRepo.countRecordsByStatus();
    }
    //Part F2
    @Transactional
    public String cancelDay(UUID lecturerId,LocalDate date){
        int count= attendanceRepo.markAbsentForLecturer(lecturerId,date);
        return count+ " attendance records cancelled";
    }
    //Part 2 Bonus
    public Page<AttendanceRecord> getAllRecords(Pageable pageable){
        return attendanceRepo.findAll(pageable);
    } 
}