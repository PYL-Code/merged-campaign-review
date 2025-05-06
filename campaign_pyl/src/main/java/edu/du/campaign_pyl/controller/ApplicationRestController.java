package edu.du.campaign_pyl.controller;

import edu.du.campaign_pyl.entity.Application;
import edu.du.campaign_pyl.entity.Users;
import edu.du.campaign_pyl.service.ApplicationService;
import edu.du.campaign_pyl.service.CampaignService;
import edu.du.campaign_pyl.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/application")
public class ApplicationRestController {

    private final ApplicationService applicationService;

    private final UserService userService;

    public ApplicationRestController(
            ApplicationService applicationService,
            UserService userService,
            CampaignService campaignService) {
        this.applicationService = applicationService;
        this.userService = userService;
    }

    @GetMapping("/myapplication/{id}")
    public ResponseEntity<List<Application>> getMyApplications(@PathVariable Long id) {
        List<Application> applications = applicationService.getApplication(id);
        return ResponseEntity.ok(applications);
    }

    @DeleteMapping("/myapplication/delete/{id}")
    public ResponseEntity<String> deleteMyApplication(@PathVariable Long id) {
        applicationService.delete(id);
        return ResponseEntity.ok("신청 목록에서 삭제되었습니다.");
    }

    @GetMapping("/users/select/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        Users users = userService.getUserByUserNo(id);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/insert")
    public ResponseEntity<String> insertApplication(@RequestBody Application application) {
        try {
            applicationService.apply(application);
            return ResponseEntity.ok("신청 완료되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // 409
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("신청 처리 중 오류 발생");
        }
    }
}
