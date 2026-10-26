package auca.example.attendance_management_system.repository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import auca.example.attendance_management_system.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,UUID>{
    //Part B1
    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    //part C4
    @Query("SELECT DISTINCT a.student FROM AttendanceRecord a WHERE a.lecturer.id=:lecturerId")
    List<Student> findStudentsByLecturer(@Param("lecturerId") UUID lecturerId);
}
