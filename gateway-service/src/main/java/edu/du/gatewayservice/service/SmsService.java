package edu.du.gatewayservice.service;

import edu.du.gatewayservice.dto.SmsRequest;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SmsService {

    private final DefaultMessageService messageService;
    private final RedisTemplate<String, String> redisTemplate;

    public SmsService(DefaultMessageService messageService, RedisTemplate<String, String> redisTemplate) {
        this.messageService = messageService;
        this.redisTemplate = redisTemplate;
    }

    public String sendSms(String phoneNumber) {
        String authCode = createAuthCode();
        try {
            redisTemplate.opsForValue().set(phoneNumber, authCode, 3, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw new RuntimeException("Redis 저장 실패 → 문자 전송 안 함");
        }

        try {
            Message message = new Message();
            message.setFrom("01062376551");
            message.setTo(phoneNumber);
            message.setText("[CT 체험단] 인증번호 [" + authCode + "]");

            messageService.sendOne(new SingleMessageSendingRequest(message));
            return authCode;
        } catch (Exception e) {
            throw new RuntimeException("SMS 전송 실패: " + e.getMessage());
        }
    }

    // 인증번호 생성
    private String createAuthCode() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public boolean verifyCode(String phone, String inputCode) {
        String savedCode = redisTemplate.opsForValue().get(phone);
        return inputCode.equals(savedCode);
    }
}