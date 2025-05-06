package edu.du.campaign_pyl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 윈도우 경로를 URI로 매핑
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + Paths.get("C:/CampaignImg/").toUri().getPath());
    }
}
