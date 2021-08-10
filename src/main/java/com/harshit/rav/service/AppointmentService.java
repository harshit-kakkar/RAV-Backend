package com.harshit.rav.service;

import com.harshit.rav.dto.AppointmentRequestDTO;
import com.harshit.rav.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {


    @Autowired
    private AppointmentRepository appointmentRepository;


    public String setAppointment(AppointmentRequestDTO appointmentRequest, String email){
        return "Appointment set successfully !";
    }
}
