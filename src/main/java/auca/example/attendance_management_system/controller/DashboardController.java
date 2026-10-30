package auca.example.attendance_management_system.controller;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import auca.example.attendance_management_system.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")

public class DashboardController {

    @Autowired 
    private DashboardService dashboardService;

    @GetMapping("/summary")
      public Map<String,Long> summary(){
        return dashboardService.getSummary();
      }
    
}
