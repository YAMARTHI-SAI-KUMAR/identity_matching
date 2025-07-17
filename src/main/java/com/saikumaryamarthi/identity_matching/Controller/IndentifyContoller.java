package com.saikumaryamarthi.identity_matching.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saikumaryamarthi.identity_matching.DTOs.IdentifyRequest;
import com.saikumaryamarthi.identity_matching.DTOs.IdentifyResponse;
import com.saikumaryamarthi.identity_matching.Service.ContactService;

@RestController

@RequestMapping("/identify")
public class IndentifyContoller {


    @Autowired
      private ContactService contactService;

    @PostMapping 
    public ResponseEntity<IdentifyResponse> identify(@RequestBody IdentifyRequest request){
        IdentifyResponse response = contactService.identify(request.getEmail(), request.getPhoneNumber());
        return ResponseEntity.ok(response);
    }
    
}

