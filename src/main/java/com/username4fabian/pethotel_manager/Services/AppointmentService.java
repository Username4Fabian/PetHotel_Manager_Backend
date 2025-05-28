package com.username4fabian.pethotel_manager.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.username4fabian.pethotel_manager.Repositories.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public int getNextId() {
        return appointmentRepository.findMaxAppointmentNr();
    }
}