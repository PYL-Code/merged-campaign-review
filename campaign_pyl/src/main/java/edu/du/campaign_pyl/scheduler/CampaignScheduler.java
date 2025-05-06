package edu.du.campaign_pyl.scheduler;

import edu.du.campaign_pyl.entity.Campaign;
import edu.du.campaign_pyl.repository.CampaignRepository;
import edu.du.campaign_pyl.service.CampaignService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampaignScheduler {

    private final CampaignRepository campaignRepository;
    private final CampaignService campaignService;

    public CampaignScheduler(CampaignRepository campaignRepository, CampaignService campaignService) {
        this.campaignRepository = campaignRepository;
        this.campaignService = campaignService;
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정 0시
    public void updateAllCampaignStatuses() {
        List<Campaign> campaigns = campaignRepository.findAll();

        for (Campaign campaign : campaigns) {
            campaignService.updateProgressStatusIfExpired(campaign);
        }
    }
}
