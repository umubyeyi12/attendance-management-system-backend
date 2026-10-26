package auca.example.attendance_management_system.repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.example.attendance_management_system.model.AttendanceRecord;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord,UUID>{
    
}