package edu.du.campaign_pyl.repository;

import edu.du.campaign_pyl.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUsers_UserNo(Long userNo);

    Optional<Application> findByUsers_UserNoAndCampaign_CampaignNo(Long userNo, Long campaignNo);
}
