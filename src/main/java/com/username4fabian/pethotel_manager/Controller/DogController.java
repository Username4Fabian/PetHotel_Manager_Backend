package com.username4fabian.pethotel_manager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.username4fabian.pethotel_manager.Entities.Dog;
import com.username4fabian.pethotel_manager.Entities.Kunde;
import com.username4fabian.pethotel_manager.Repositories.DogRepository;
import com.username4fabian.pethotel_manager.Repositories.KundeRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/dog")
public class DogController {
    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private KundeRepository kundeRepository;

    @PostMapping("CreateNewDog")
    public Dog createNewDog(@RequestBody Dog dog, @RequestParam int ownerId) {
        return createDog(dog, ownerId);
    }

    @GetMapping("GetAllDogs")
    public List<Dog> getMethodName() {
        return dogRepository.findAll();
    }

    private Dog createDog(Dog dog, int ownerId) {
        Kunde owner = kundeRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner not found"));
        dog.setDOwner(owner);
        return dogRepository.save(dog);
    }
}