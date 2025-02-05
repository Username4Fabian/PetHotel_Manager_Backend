package com.username4fabian.pethotel_manager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.pethotel_manager.Entities.Kunde;
import com.username4fabian.pethotel_manager.Repositories.KundeRepository;

@RestController
@RequestMapping("/kunde")
public class KundeController {
    @Autowired
    private KundeRepository kundeRepository;

    @PostMapping("/CreateNewKunde")
    public Kunde createKunde(@RequestBody Kunde kunde) {
        return kundeRepository.save(kunde);
    }

    @GetMapping("/GetAllKunden")
    public List<Kunde> getAllKunden() {
        return kundeRepository.findAll();
    }
}
