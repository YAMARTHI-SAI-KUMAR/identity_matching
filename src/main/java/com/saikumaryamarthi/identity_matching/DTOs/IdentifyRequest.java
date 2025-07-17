package com.saikumaryamarthi.identity_matching.DTOs;

import lombok.Data;

@Data
public class IdentifyRequest {

    private String email;

    private String phoneNumber;
    
}
