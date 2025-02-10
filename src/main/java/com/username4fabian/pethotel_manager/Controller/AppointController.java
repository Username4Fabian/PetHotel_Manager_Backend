package com.username4fabian.pethotel_manager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.pethotel_manager.Repositories.KundeRepository;
import com.username4fabian.pethotel_manager.Entities.Appoint;
import com.username4fabian.pethotel_manager.Repositories.AppointRepository;
import com.username4fabian.pethotel_manager.Repositories.DogRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/appoint")
public class AppointController {
    @Autowired
    private AppointRepository appointRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private KundeRepository kundenRepository;

    @PostMapping("/saveAppoint")
    public ResponseEntity<String> saveAppoint(@RequestBody Appoint appoint) {
        appointRepository.save(appoint);
        return ResponseEntity.ok("Appoint saved");
    }

    @GetMapping("/getAllAppoints")
    public List<Appoint> getAllAppoints() {
        return appointRepository.findAll();
    }

}
