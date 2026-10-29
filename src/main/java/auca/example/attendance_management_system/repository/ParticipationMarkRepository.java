package auca.example.attendance_management_system.repository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import auca.example.attendance_management_system.model.ParticipationMark;

@Repository 
   
      // Part E3
public interface ParticipationMarkRepository extends JpaRepository<ParticipationMark,UUID>{
    boolean existsByAttendanceRecordId(UUID attendanceRecordId);

    //Part E2
    @Query("SELECT p FROM ParticipationMark p WHERE p.attendanceRecord.student.regNumber= :regNumber ORDER BY  p.awardedDate DESC")
    List<ParticipationMark> findByStudentRegNumber(@Param("regNumber") String regNumber);
}
