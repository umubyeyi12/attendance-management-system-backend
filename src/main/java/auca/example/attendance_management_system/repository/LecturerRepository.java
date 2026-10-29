package auca.example.attendance_management_system.repository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import auca.example.attendance_management_system.model.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer,UUID>{
    //partC1
    @Query("SELECT l FROM Lecturer l JOIN l.courses c WHERE LOWER(c.name)=Lower(:name)")
    List<Lecturer> findByCourseName(@Param("name") String name);
    //part D4
    boolean existsByLectureRoomIdAndIdNot(UUID lectureRoomId,UUID lecturerId);
}