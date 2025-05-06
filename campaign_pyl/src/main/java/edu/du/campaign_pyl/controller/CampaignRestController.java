package edu.du.campaign_pyl.controller;

import edu.du.campaign_pyl.entity.Campaign;
import edu.du.campaign_pyl.service.CampaignService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/campaign")
public class CampaignRestController {

    private final CampaignService campaignService;

    public CampaignRestController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Campaign> detail(@PathVariable Long id, Model model) {
        Campaign campaign = campaignService.findById(id);
        campaignService.updateProgressStatusIfExpired(campaign);
        return ResponseEntity.ok(campaign);
    }
}
