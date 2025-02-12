package com.username4fabian.pethotel_manager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.pethotel_manager.Entities.Appointment;
import com.username4fabian.pethotel_manager.Entities.Dog;
import com.username4fabian.pethotel_manager.Entities.Kunde;
import com.username4fabian.pethotel_manager.Repositories.AppointmentRepository;
import com.username4fabian.pethotel_manager.Repositories.DogRepository;
import com.username4fabian.pethotel_manager.Repositories.KundeRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private KundeRepository kundeRepository;

    @PostMapping("/createNewAppointment")
    public ResponseEntity<String> createNewAppointment(@RequestBody Appointment appointmentRequest) {
        Kunde kunde = kundeRepository.findById(appointmentRequest.getKunde().getId()).orElse(null);
        if (kunde == null) {
            return ResponseEntity.badRequest()
                    .body("Kunde not found with ID: " + appointmentRequest.getKunde().getId());
        }

        List<Dog> dogs = dogRepository.findByDOwner(kunde);

        Appointment appointment = new Appointment(appointmentRequest.getAnmerkung(), appointmentRequest.getAnkunft(),
                appointmentRequest.getAbfahrt(), appointmentRequest.isBezahlt(), dogs, kunde);

        appointmentRepository.save(appointment);

        return ResponseEntity.ok("Appointment saved");
    }

    @GetMapping("/getAllAppointments")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}