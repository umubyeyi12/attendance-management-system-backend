package auca.example.attendance_management_system.repository;
import java.util.UUID;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import auca.example.attendance_management_system.model.LectureRoom;

@Repository
public interface LectureRoomRepository extends JpaRepository<LectureRoom,UUID>{
 //part:C2 
 @Query("SELECT r FROM LectureRoom r WHERE NOT EXISTS (SELECT l FROM Lecturer l WHERE l.lectureRoom=r) ORDER BY r.roomNumber ASC")
      List<LectureRoom> findFreeRooms();
      //PART D4
      Optional<LectureRoom> findByRoomNumber(int roomNumber);
      
}