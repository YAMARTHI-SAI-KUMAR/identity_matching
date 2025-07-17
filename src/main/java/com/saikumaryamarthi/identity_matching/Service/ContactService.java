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
@Service
public class ContactService {
     @Autowired
    private ContactRepository contactRepo;

    @Transactional
    public IdentifyResponse identify(String email, String phoneNumber) {
        // 1) find any existing contacts matching email OR phone
        List<Contact> matches = contactRepo.findByEmailOrPhoneNumber(email, phoneNumber);

        Contact primary;
        List<Contact> allSecondaries = new ArrayList<>();

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

        } else {
            // a) pick out all existing primaries among the matches
            List<Contact> primaries = matches.stream()
                .filter(c -> c.getLinkPrecedence() == LinkPrecedence.PRIMARY)
                .sorted(Comparator.comparing(Contact::getCreatedAt))
                .collect(Collectors.toList());

            if (!primaries.isEmpty()) {
                // oldest primary stays primary
                primary = primaries.get(0);

                // demote any extra primaries to secondaries
                for (int i = 1; i < primaries.size(); i++) {
                    Contact toDemote = primaries.get(i);
                    toDemote.setLinkPrecedence(LinkPrecedence.SECONDARY);
                    toDemote.setLinkedId(primary.getId());
                    toDemote.setUpdatedAt(LocalDateTime.now());
                    contactRepo.save(toDemote);
                }

            } else {
                // all matches were secondaries → follow linkedId to get the real primary
                Contact someSec = matches.get(0);
                primary = contactRepo.findById(someSec.getLinkedId())
                    .orElseThrow(() -> new IllegalStateException(
                        "Linked primary not found for secondary id=" + someSec.getId()));
            }

            // b) load every secondary linked to that primary
            allSecondaries = contactRepo.findByLinkedId(primary.getId());

            // c) check across entire chain (primary + secondaries) before creating a new secondary
            List<Contact> chain = new ArrayList<>();
            chain.add(primary);
            chain.addAll(allSecondaries);

            boolean hasEmail = chain.stream()
                                     .anyMatch(c -> email != null && email.equals(c.getEmail()));
            boolean hasPhone = chain.stream()
                                     .anyMatch(c -> phoneNumber != null && phoneNumber.equals(c.getPhoneNumber()));

            if ((email != null && !hasEmail) || (phoneNumber != null && !hasPhone)) {
                Contact newSec = Contact.builder()
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .linkPrecedence(LinkPrecedence.SECONDARY)
                    .linkedId(primary.getId())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
                contactRepo.save(newSec);
                allSecondaries.add(newSec);
            }
        }

        // 2) Build ordered list: primary first, then secondaries by creation time
        List<Contact> ordered = new ArrayList<>();
        ordered.add(primary);
        ordered.addAll(
            allSecondaries.stream()
                          .sorted(Comparator.comparing(Contact::getCreatedAt))
                          .collect(Collectors.toList())
        );

        // 3) Extract emails, phones, and secondary IDs
        List<String> emails = ordered.stream()
            .map(Contact::getEmail)
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());

        List<String> phones = ordered.stream()
            .map(Contact::getPhoneNumber)
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());

        List<Long> secondaryIds = allSecondaries.stream()
            .map(Contact::getId)
            .collect(Collectors.toList());

        // 4) Populate DTO and return
        IdentifyResponse.ContactDTO dto = new IdentifyResponse.ContactDTO();
        dto.setPrimaryContactId(primary.getId());
        dto.setEmails(emails);
        dto.setPhoneNumbers(phones);
        dto.setSecondaryContactIds(secondaryIds);

        IdentifyResponse resp = new IdentifyResponse();
        resp.setContact(dto);
        return resp;
    }
}
