package com.saikumaryamarthi.identity_matching.DTOs;

import java.util.*;

import lombok.Data;

@Data
public class IdentifyResponse {

    private ContactDTO contact;


    @Data
    public static class ContactDTO{
        private Long primaryContactId;

        private List<String> emails;

        private List<String> phoneNumbers;

        private List<Long> SecondaryContactIds;
        

    }
    
}