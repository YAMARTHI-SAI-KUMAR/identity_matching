package com.saikumaryamarthi.identity_matching.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saikumaryamarthi.identity_matching.Entity.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByEmailOrPhoneNumber(String email, String phoneNumber);

    List<Contact> findByLinkedId(Long id);

    
    
}
