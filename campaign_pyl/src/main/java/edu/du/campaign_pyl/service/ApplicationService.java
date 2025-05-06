package edu.du.campaign_pyl.service;

import edu.du.campaign_pyl.entity.Application;
import edu.du.campaign_pyl.repository.ApplicationRepository;
import edu.du.campaign_pyl.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CampaignRepository campaignRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              CampaignRepository campaignRepository) {
        this.applicationRepository = applicationRepository;
        this.campaignRepository = campaignRepository;
    }

    @Transactional
    public void apply(Application application) {

        Long campaignNo = application.getCampaign().getCampaignNo();
        Long userNo = application.getUsers().getUserNo();

        boolean alreadyApplied = applicationRepository
                .findByUsers_UserNoAndCampaign_CampaignNo(userNo, campaignNo)
                .isPresent();

        if (alreadyApplied) {
            throw new IllegalStateException("이미 신청한 캠페인입니다.");
        }

        campaignRepository.incrementCurrentCount(campaignNo);
        applicationRepository.save(application);
    }

    public List<Application> getApplication(Long id) {
        return applicationRepository.findByUsers_UserNo(id);
    }

    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}
