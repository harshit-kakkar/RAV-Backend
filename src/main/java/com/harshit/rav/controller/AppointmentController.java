package com.harshit.rav.controller;


import com.harshit.rav.dto.AppointmentRequestDTO;
import com.harshit.rav.entity.Appointment;
import com.harshit.rav.exception.AppointmentValidityException;
import com.harshit.rav.exception.NotFoundException;
import com.harshit.rav.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment")
    public ResponseEntity<String> setAppointment(@RequestBody AppointmentRequestDTO appointmentRequest, Principal principal) throws NotFoundException, AppointmentValidityException {
        String email = principal.getName();
        return new ResponseEntity<>(appointmentService.setAppointment(appointmentRequest, email), HttpStatus.CREATED);
    }

    @GetMapping("/appointment")
    public List<Appointment> getAppointmentsList(@RequestParam("asMentor") Boolean asMentor, Principal principal){
        String email = principal.getName();
        return appointmentService.getAppointmentsList(email, asMentor);

    }


}
