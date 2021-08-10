package com.harshit.rav.controller;


import com.harshit.rav.dto.AppointmentRequestDTO;
import com.harshit.rav.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment")
    public ResponseEntity<String> setAppointment(@RequestBody AppointmentRequestDTO appointmentRequest, Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(appointmentService.setAppointment(appointmentRequest, email), HttpStatus.CREATED);
    }


}
