package com.harshit.rav.service;

import com.harshit.rav.dto.AppointmentRequestDTO;
import com.harshit.rav.dto.AppointmentResDTO;
import com.harshit.rav.entity.Account;
import com.harshit.rav.entity.Appointment;
import com.harshit.rav.entity.Schedule;
import com.harshit.rav.exception.AppointmentValidityException;
import com.harshit.rav.exception.NotFoundException;
import com.harshit.rav.repository.AccountRepository;
import com.harshit.rav.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AppointmentService {


    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AccountRepository accountRepository;


    public String setAppointment(AppointmentRequestDTO appointmentRequest, String email) throws NotFoundException, AppointmentValidityException {
        Optional<Account> mentorOpt = accountRepository.findById(appointmentRequest.getMentor());
        if (mentorOpt.isPresent()) {
            Account mentee = accountRepository.findByEmail(email);
            Account mentor = mentorOpt.get();

            // validations
            if(!validateAppointmentDateTime(mentor, appointmentRequest) || mentor.getIsMentor()==null || !mentor.getIsMentor()){
                throw new AppointmentValidityException("Invalid appointment request");
            }
            // Create an appointment entry in DB.
            Appointment appointment = new Appointment(appointmentRequest.getDate(), appointmentRequest.getStartTime(), appointmentRequest.getLocation(), mentee, mentor);
            appointmentRepository.save(appointment);

            return "Appointment set successfully !";
        }
        else{
            throw new NotFoundException("Mentor does not exist.");
        }
    }

    private Boolean validateAppointmentDateTime(Account mentor, AppointmentRequestDTO appointmentRequest){
        LocalDate appointmentDate = appointmentRequest.getDate();
        Integer appointmentTime = appointmentRequest.getStartTime();
        List<Schedule> mentorScheduleList = mentor.getSchedule();
        String appointmentDay = appointmentDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase();
        for(Schedule schedule : mentorScheduleList){
            if(schedule.getDay().equals(appointmentDay) && schedule.getStartTimeHour()<=appointmentTime &&
                    schedule.getEndTimeHour()>appointmentTime && isMentorAvailable(mentor, appointmentRequest)){
                return true;
            }
        }
        return false;
    }

    private Boolean isMentorAvailable(Account mentor, AppointmentRequestDTO appointmentRequest){
        // Check if there is an appointment at the particular date & time and return true if mentor is available at the requested time.
        return accountRepository.findByMentorAppointmentDateAndAppointmentTime(mentor.getId(),
                appointmentRequest.getDate(), appointmentRequest.getStartTime()) == null;
    }

    public List<AppointmentResDTO> getAppointmentsList(String email, Boolean asMentor){
        Account account = accountRepository.findByEmail(email);
        if(asMentor){
            List<Appointment> appointments = appointmentRepository.findByMentor(account);
            List<AppointmentResDTO> appointmentResDTOList = new ArrayList<>();
            for(Appointment appointment : appointments){
                appointmentResDTOList.add(new AppointmentResDTO(appointment.getId(),
                        appointment.getAppointmentStartTime(), appointment.getAppointmentDate(),
                        appointment.getLocation(), appointment.getMentee().getName()));
            }
            return appointmentResDTOList;
        }
        List<Appointment> appointments = appointmentRepository.findByMentee(account);
        List<AppointmentResDTO> appointmentResDTOList = new ArrayList<>();
        for(Appointment appointment : appointments){
            appointmentResDTOList.add(new AppointmentResDTO(appointment.getId(),
                    appointment.getAppointmentStartTime(), appointment.getAppointmentDate(),
                    appointment.getLocation(), appointment.getMentor().getName()));
        }
        return appointmentResDTOList;
    }
}
