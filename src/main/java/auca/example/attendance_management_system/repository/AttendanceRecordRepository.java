package auca.example.attendance_management_system.repository;
import java.util.UUID;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
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
 //Part D1
 boolean existsByLecturerIdAndClassDateAndStatusNot(UUID lecturerId,LocalDate classDate,AttendanceStatus status);
 //Part F1
 @Query("SELECT a.status, COUNT(a) FROM AttendanceRecord a GROUP BY a.status")
 List<Object[]> countRecordsByStatus();
 //Part F2
 @Modifying 
 @Transactional 
 @Query("UPDATE AttendanceRecord a SET a.status='ABSENT' WHERE a.lecturer.id= :lecturerId AND a.classDate= :date AND a.status<>'VERIFIED'")
     int markAbsentForLecturer(@Param("lecturerId") UUID lecturerId,@Param("date")LocalDate date);
}