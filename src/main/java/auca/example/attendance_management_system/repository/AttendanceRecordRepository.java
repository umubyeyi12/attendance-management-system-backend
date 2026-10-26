package auca.example.attendance_management_system.repository;
import java.util.UUID;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import auca.example.attendance_management_system.model.AttendanceStatus;
import auca.example.attendance_management_system.model.AttendanceRecord;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord,UUID>{
    //partB2
 List<AttendanceRecord> findByStatusOrderByClassDateAsc(AttendanceStatus status); 
 //partB3
 List<AttendanceRecord> findByClassDateBetweenOrderByClassDateAsc(LocalDate start,LocalDate end);  
}