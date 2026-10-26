package auca.example.attendance_management_system.service;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.example.attendance_management_system.model.AttendanceRecord;
import auca.example.attendance_management_system.repository.AttendanceRecordRepository;

@Service
public class AttendanceRecordService{
    @Autowired
    private AttendanceRecordRepository attendanceRepo;
    public AttendanceRecord saveRecord(AttendanceRecord r){
        return attendanceRepo.save(r);
    }
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
}