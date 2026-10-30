package auca.example.attendance_management_system.controller;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import auca.example.attendance_management_system.model.AttendanceRecord;
import auca.example.attendance_management_system.service.AttendanceRecordService;
import auca.example.attendance_management_system.model.AttendanceStatus;
import auca.example.attendance_management_system.repository.ParticipationMarkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@RestController
@RequestMapping("/api/attendance-records")

public class AttendanceRecordController {
    @Autowired 
    private AttendanceRecordService recordService;
    @Autowired 
    private ParticipationMarkRepository markRepo;

    @GetMapping("/all")
    public List<AttendanceRecord> getAll(){
        return recordService.getAllRecords();
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        AttendanceRecord r=recordService.getRecordById(id);
        if(r!= null) return new ResponseEntity<>(r,HttpStatus.OK);
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
   
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,@RequestBody AttendanceRecord newInfo){
        AttendanceRecord existing=recordService.getRecordById(id);
        if(existing != null){
            existing.setClassDate(newInfo.getClassDate());
            existing.setCourseName(newInfo.getCourseName());
            existing.setTimeSlot(newInfo.getTimeSlot());
            existing.setStudent(newInfo.getStudent());
            existing.setLecturer(newInfo.getLecturer());
            recordService.saveRecord(existing);
            return new ResponseEntity<>("Attendance record upsted",HttpStatus.OK);

        }
        return new ResponseEntity<> ("not found",HttpStatus.NOT_FOUND);
    }
    //partB2
    @GetMapping("/by-status")
    public List<AttendanceRecord> getByStatus(@RequestParam AttendanceStatus status){
        return recordService.getRecordsByStatus(status);
    }
    //partB3
    @GetMapping("/between")
    public List<AttendanceRecord> getBetween(@RequestParam String start,@RequestParam String end){
        LocalDate startDate=LocalDate.parse(start);
        LocalDate endDate=LocalDate.parse(end);
        return recordService.getRecordsBetween(startDate,endDate);
    }
    //part D1 
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AttendanceRecord r){
        String msg= recordService.saveRecord(r);
        if(msg.equals("Attendance record date cannot be in the past")){
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);//400
        }
        if(msg.equals("Lecturer already has a class record on that date")){
            return new ResponseEntity<>(msg,HttpStatus.CONFLICT);//409

        }
        return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }
    //part D2
    @PatchMapping("/{id}/status")
    public ResponseEntity<?>updateStatus(@PathVariable UUID id,@RequestParam AttendanceStatus status){
        String msg=recordService.updateStatus(id,status);
        if(msg.equals("The attendance record with that id does not exist")) return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        if(msg.contains("Cannot change status")) return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(msg,HttpStatus.OK);
     }
     //Part E3
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<?> delete(@PathVariable UUID id){
        if(markRepo.existsByAttendanceRecordId(id)){
            return new ResponseEntity<>("That attendance record has participation marks, delete them first",HttpStatus.CONFLICT);
        }
        recordService.deleteRecord(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
     }
     //Part F1
     @GetMapping("/stats/by-status")
     public List<Object[]> getStatsByStatus(){
        return recordService.getCountByStatus();
     }
     @PatchMapping("/cancel-day")
     public String cancelDay(@RequestParam UUID lecturerId,@RequestParam String date){
        return recordService.cancelDay(lecturerId,LocalDate.parse(date));
     }
     //Bonus 2
     @GetMapping("/page")
     public Page<AttendanceRecord> getPage(
        @RequestParam int page,
        @RequestParam int size,
        @RequestParam String sort){
            String[] parts= sort.split(",");
            Pageable pageable=PageRequest.of(page,size,Sort.by(Sort.Direction.fromString(parts[1]),parts[0]));
             return recordService.getAllRecords(pageable);
        }
     

    
}
