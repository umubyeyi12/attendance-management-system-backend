package auca.example.attendance_management_system.repository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import auca.example.attendance_management_system.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,UUID>{
    //partC3
    @Query("SELECT c FROM Course c WHERE c.lecturers IS EMPTY")
    List<Course> findByUnusedCourses();
}
