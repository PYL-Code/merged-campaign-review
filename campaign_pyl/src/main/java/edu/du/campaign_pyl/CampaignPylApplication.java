package edu.du.campaign_pyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CampaignPylApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampaignPylApplication.class, args);
    }

}
