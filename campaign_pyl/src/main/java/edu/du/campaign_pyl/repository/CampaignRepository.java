package edu.du.campaign_pyl.repository;

import edu.du.campaign_pyl.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Campaign c SET c.currentCount = c.currentCount + 1 WHERE c.campaignNo = :campaignNo")
    void incrementCurrentCount(Long campaignNo);
}
