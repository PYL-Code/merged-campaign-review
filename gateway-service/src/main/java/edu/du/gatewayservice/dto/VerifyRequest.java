package edu.du.gatewayservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyRequest {
    private String Phone;
    private String code;
}
