package com.saikumaryamarthi.identity_matching.Service;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saikumaryamarthi.identity_matching.DTOs.IdentifyResponse;
import com.saikumaryamarthi.identity_matching.Entity.Contact;
import com.saikumaryamarthi.identity_matching.Entity.LinkPrecedence;
import com.saikumaryamarthi.identity_matching.Repository.ContactRepository;

import jakarta.transaction.Transactional;
public class ContactService {
     @Autowired
    private ContactRepository contactRepo;

    @Transactional
    public IdentifyResponse identify(String email, String phoneNumber) {
        // 1) find any existing contacts matching email OR phone
        List<Contact> matches = contactRepo.findByEmailOrPhoneNumber(email, phoneNumber);

        Contact primary;
        // List<Contact> allSecondaries = new ArrayList<>();

        if (matches.isEmpty()) {
            // no matches → brand‑new primary
            primary = Contact.builder()
                             .email(email)
                             .phoneNumber(phoneNumber)
                             .linkPrecedence(LinkPrecedence.PRIMARY)
                             .createdAt(LocalDateTime.now())
                             .updatedAt(LocalDateTime.now())
                             .build();
            contactRepo.save(primary);

        }

        else {
            // just pick the first matched contact as primary (simplified)
            primary = matches.get(0);
        }

        // Build simple response with primary contact info only
        IdentifyResponse.ContactDTO dto = new IdentifyResponse.ContactDTO();
        dto.setPrimaryContactId(primary.getId());
        dto.setEmails(Collections.singletonList(primary.getEmail()));
        dto.setPhoneNumbers(Collections.singletonList(primary.getPhoneNumber()));
        dto.setSecondaryContactIds(Collections.emptyList());

        IdentifyResponse resp = new IdentifyResponse();
        resp.setContact(dto);

        return resp;

        

        
        
    }

}
