package com.username4fabian.pethotel_manager.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.pethotel_manager.Entities.Appointment;
import com.username4fabian.pethotel_manager.Entities.Dog;
import com.username4fabian.pethotel_manager.Entities.Kunde;
import com.username4fabian.pethotel_manager.Repositories.AppointmentRepository;
import com.username4fabian.pethotel_manager.Repositories.DogRepository;
import com.username4fabian.pethotel_manager.Repositories.KundeRepository;

@RestController
@RequestMapping("/kunde")
public class KundeController {
    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/CreateNewKunde")
    public Kunde createKunde(@RequestBody Kunde kunde) {
        return kundeRepository.save(kunde);
    }

    @GetMapping("/GetAllKunden")
    public List<Kunde> getAllKunden() {
        return kundeRepository.findAll();
    }

    @DeleteMapping("/DeleteKunde/{id}")
    public ResponseEntity<String> deleteKunde(@PathVariable int id) {
        Kunde kunde = kundeRepository.findById(id).orElse(null);

        if (kunde == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kunde not found with id: " + id);
        }

        List<Dog> dogs = dogRepository.findByDOwner(kunde);

        for (Dog dog : dogs) {
            List<Appointment> appointments = appointmentRepository.findAll().stream()
                    .filter(appointment -> appointment.getDogs().contains(dog))
                    .collect(Collectors.toList());
            appointmentRepository.deleteAll(appointments);
        }

        if (!dogs.isEmpty()) {
            dogRepository.deleteAll(dogs);
        }

        kundeRepository.deleteById(id);

        return ResponseEntity.ok("Kunde deleted with id: " + id);
    }

    @PostMapping("/UpdateKunde")
    public Kunde updateKunde(@RequestBody Kunde kunde) {
        return kundeRepository.save(kunde);
    }
}