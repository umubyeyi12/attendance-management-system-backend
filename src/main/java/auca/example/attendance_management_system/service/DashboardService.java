package auca.example.attendance_management_system.service;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import auca.example.attendance_management_system.model.AttendanceStatus;
import auca.example.attendance_management_system.repository.AttendanceRecordRepository;
import auca.example.attendance_management_system.repository.LecturerRepository;
import auca.example.attendance_management_system.repository.LectureRoomRepository;
import auca.example.attendance_management_system.repository.ParticipationMarkRepository;
import auca.example.attendance_management_system.repository.StudentRepository;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired 
    private LecturerRepository lecturerRepo;
    @Autowired
    private ParticipationMarkRepository markRepo;
    @Autowired 
    private AttendanceRecordRepository recordRepo;
    @Autowired 
    private LectureRoomRepository roomRepo;

    public Map<String,Long> getSummary(){
        Map<String,Long> map=new LinkedHashMap<>();

        map.put("students",studentRepo.count());
        map.put("lecturers",lecturerRepo.count());
        map.put("freeLectureRooms",(long) roomRepo.findFreeRooms().size());
        map.put("expected",(long) recordRepo.findByStatusOrderByClassDateAsc(AttendanceStatus.EXPECTED).size());
        map.put("participationMarks",markRepo.count());
        map.put("recordsToday",(long) recordRepo.findByClassDateBetweenOrderByClassDateAsc(LocalDate.now(),LocalDate.now()).size());
        return map;

    }
    
}
