package com.username4fabian.pethotel_manager.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteKunde(@PathVariable int id) {
        Kunde kunde = kundeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kunde not found with id: " + id));

        // Find all dogs belonging to the customer
        List<Dog> dogs = dogRepository.findByDOwner(kunde);

        // Find and delete all appointments related to the customer's dogs
        for (Dog dog : dogs) {
            List<Appointment> appointments = appointmentRepository.findAll().stream()
                    .filter(appointment -> appointment.getDogs().contains(dog))
                    .collect(Collectors.toList());
            appointmentRepository.deleteAll(appointments);
        }

        // Delete all dogs belonging to the customer
        dogRepository.deleteAll(dogs);

        // Delete the customer
        kundeRepository.deleteById(id);
    }

    @PostMapping("/UpdateKunde")
    public Kunde updateKunde(@RequestBody Kunde kunde) {
        return kundeRepository.save(kunde);
    }
}