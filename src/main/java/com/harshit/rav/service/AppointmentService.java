package com.harshit.rav.service;

import com.harshit.rav.dto.AppointmentRequestDTO;
import com.harshit.rav.entity.Account;
import com.harshit.rav.entity.Appointment;
import com.harshit.rav.exception.NotFoundException;
import com.harshit.rav.repository.AccountRepository;
import com.harshit.rav.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {


    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AccountRepository accountRepository;


    public String setAppointment(AppointmentRequestDTO appointmentRequest, String email) throws NotFoundException {
        Optional<Account> mentorOpt = accountRepository.findById(appointmentRequest.getMentor());
        if (mentorOpt.isPresent()) {
            Account mentee = accountRepository.findByEmail(email);
            Account mentor = mentorOpt.get();
            // Create an appointment entry in DB.
            Appointment appointment = new Appointment(appointmentRequest.getDate(), appointmentRequest.getStartTime(), appointmentRequest.getLocation());
            appointmentRepository.save(appointment);

            // add to mentor account.

            List<Appointment> appointmentsUpdateMentor = mentor.getMentor();
            appointmentsUpdateMentor.add(appointment);
            mentor.setMentor(appointmentsUpdateMentor);
            accountRepository.save(mentor);

            // Add mentee
            List<Appointment> appointmentsUpdateMentee = mentee.getMentee();
            appointmentsUpdateMentee.add(appointment);
            mentee.setMentee(appointmentsUpdateMentee);
            accountRepository.save(mentee);

            return "Appointment set successfully !";
        }
        else{
            throw new NotFoundException("Mentor does not exist.");
        }
    }
}
