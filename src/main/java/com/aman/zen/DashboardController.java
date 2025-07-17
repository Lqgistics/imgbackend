package com.aman.zen;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/metrics")
    public ResponseEntity<DashboardDTOs> getMetrics(Principal principal) {
        // Get the email of the currently authenticated user
        String userEmail = principal.getName();
        // Pass the user's email to the service
        DashboardDTOs metrics = dashboardService.getDashboardMetrics(userEmail);
        return ResponseEntity.ok(metrics);
    }
}