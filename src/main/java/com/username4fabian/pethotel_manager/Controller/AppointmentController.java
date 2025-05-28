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
import com.username4fabian.pethotel_manager.Entities.Zimmer;
import com.username4fabian.pethotel_manager.Entities.ZimmerBelegung;
import com.username4fabian.pethotel_manager.Repositories.AppointmentRepository;
import com.username4fabian.pethotel_manager.Repositories.DogRepository;
import com.username4fabian.pethotel_manager.Repositories.KundeRepository;
import com.username4fabian.pethotel_manager.Repositories.ZimmerBelegungRepository;
import com.username4fabian.pethotel_manager.Repositories.ZimmerRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.username4fabian.pethotel_manager.Services.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private ZimmerBelegungRepository zimmerBelegungRepository;

    @Autowired
    private ZimmerRepository zimmerRepository;

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/createNewAppointment")
    public Appointment createNewAppointment(@RequestBody Appointment appointmentRequest) {
        Kunde kunde = kundeRepository.findById(appointmentRequest.getKundeId())
                .orElseThrow(() -> new RuntimeException("Kunde not found with id: " + appointmentRequest.getKundeId()));

        List<Dog> dogs = appointmentRequest.getDogIds().stream()
                .map(dogId -> dogRepository.findById(dogId)
                        .orElseThrow(() -> new RuntimeException("Dog not found with id: " + dogId)))
                .collect(Collectors.toList());

        appointmentRequest.setKunde(kunde);
        appointmentRequest.setDogs(dogs);

        appointmentRequest.setAppointment_nr(appointmentRepository.findMaxAppointmentNr() + 1);

        return appointmentRepository.save(appointmentRequest);
    }

    @GetMapping("/getAllAppointments")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @DeleteMapping("/DeleteAppointment/{appointmentNr}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int appointmentNr) {
        Appointment appointment = appointmentRepository.findByAppointmentNr(appointmentNr).orElse(null);

        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found with appointment_nr: " + appointmentNr);
        }

        appointmentRepository.delete(appointment);
        return ResponseEntity.ok("Appointment deleted with appointment_nr: " + appointmentNr);
    }

    @PostMapping("/updateAppointment")
    public Appointment updateAppointment(@RequestBody Appointment appointmentRequest) {
        Kunde kunde = kundeRepository.findById(appointmentRequest.getKundeId())
                .orElseThrow(() -> new RuntimeException("Kunde not found with id: " + appointmentRequest.getKundeId()));

        List<Dog> dogs = appointmentRequest.getDogIds().stream()
                .map(dogId -> dogRepository.findById(dogId)
                        .orElseThrow(() -> new RuntimeException("Dog not found with id: " + dogId)))
                .collect(Collectors.toList());

        appointmentRequest.setKunde(kunde);
        appointmentRequest.setDogs(dogs);

        return appointmentRepository.save(appointmentRequest);
    }

    @PostMapping("/assignRooms")
    public ResponseEntity<String> assignRooms(@RequestBody List<ZimmerBelegung> belegungen) {
        for (ZimmerBelegung belegung : belegungen) {
            zimmerBelegungRepository.save(belegung);
        }
        return ResponseEntity.ok("Rooms assigned successfully");
    }

    @PostMapping("/createNewZimmer")
    public ResponseEntity<String> createNewZimmer(@RequestBody Zimmer zimmer) {
        zimmerRepository.save(zimmer);
        return ResponseEntity.ok("Zimmer created successfully");
    }

    @GetMapping("/getAllRooms")
    public List<Zimmer> getAllRooms() {
        return zimmerRepository.findAll();
    }

    @GetMapping("/getLastAppointmentID")
    public ResponseEntity<Integer> getNextAppointmentId() {
        int nextAppointmentNr = appointmentService.getNextId();
        return ResponseEntity.ok(nextAppointmentNr);
    }
}
