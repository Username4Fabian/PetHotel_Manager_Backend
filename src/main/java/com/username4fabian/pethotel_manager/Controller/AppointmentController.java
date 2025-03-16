package com.username4fabian.pethotel_manager.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.pethotel_manager.Entities.Appointment;
import com.username4fabian.pethotel_manager.Entities.Dog;
import com.username4fabian.pethotel_manager.Entities.Kunde;
import com.username4fabian.pethotel_manager.Repositories.AppointmentRepository;
import com.username4fabian.pethotel_manager.Repositories.DogRepository;
import com.username4fabian.pethotel_manager.Repositories.KundeRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private DogRepository dogRepository;

    @PostMapping("/createNewAppointment")
    public Appointment createNewAppointment(@RequestBody Appointment appointmentRequest) {
        // Fetch the Kunde entity
        Kunde kunde = kundeRepository.findById(appointmentRequest.getKundeId())
                .orElseThrow(() -> new RuntimeException("Kunde not found with id: " + appointmentRequest.getKundeId()));

        // Fetch the Dog entities
        List<Dog> dogs = appointmentRequest.getDogIds().stream()
                .map(dogId -> dogRepository.findById(dogId)
                        .orElseThrow(() -> new RuntimeException("Dog not found with id: " + dogId)))
                .collect(Collectors.toList());

        // Set the Kunde and Dogs in the Appointment
        appointmentRequest.setKunde(kunde);
        appointmentRequest.setDogs(dogs);

        // Save the Appointment
        return appointmentRepository.save(appointmentRequest);
    }

    @GetMapping("/getAllAppointments")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @DeleteMapping("/DeleteAppointment/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);

        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found with id: " + id);
        }

        appointmentRepository.deleteById(id);
        return ResponseEntity.ok("Appointment deleted with id: " + id);
    }
}