package com.username4fabian.pethotel_manager.Controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.username4fabian.pethotel_manager.Entities.Appointment;
import com.username4fabian.pethotel_manager.Entities.Dog;
import com.username4fabian.pethotel_manager.Entities.Kunde;
import com.username4fabian.pethotel_manager.Repositories.AppointmentRepository;
import com.username4fabian.pethotel_manager.Repositories.DogRepository;
import com.username4fabian.pethotel_manager.Repositories.KundeRepository;
import com.username4fabian.pethotel_manager.Services.BCDNStorage;

@RestController
@RequestMapping("/dog")
public class DogController {
    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Value("${bunny.storageZoneName}")
    private String storageZoneName;

    @Value("${bunny.apiKey}")
    private String apiKey;

    @PostMapping("/saveDog")
    public ResponseEntity<Map<String, Object>> saveDog(@RequestBody Dog dog, @RequestParam("ownerId") int ownerId) {
        Kunde owner = kundeRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));
        dog.setDOwner(owner);
        Dog savedDog = dogRepository.save(dog);

        Map<String, Object> response = new HashMap<>();
        response.put("dog", savedDog);
        response.put("dogId", savedDog.getId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("dogId") int dogId,
            @RequestParam("image") MultipartFile image) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new RuntimeException("Dog not found with ID: " + dogId));

        String originalFilename = image.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
        String storagePath = storageZoneName + "/DogImages/" + uniqueFilename;

        try {
            BCDNStorage bcdnStorage = new BCDNStorage(storageZoneName, apiKey);
            File tempFile = File.createTempFile("temp", null);
            image.transferTo(tempFile);
            bcdnStorage.uploadObject(tempFile.getAbsolutePath(), storagePath);

            String imageUrl = "https://tierhotelmanager.b-cdn.net/" + storagePath;
            dog.setImageURL(imageUrl);
            dogRepository.save(dog);
            tempFile.delete();
            return ResponseEntity.ok("Image uploaded successfully");

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload image");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload image to BunnyCDN: " + e.getMessage());
        }
    }

    @GetMapping("/GetAllDogs")
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    @DeleteMapping("/DeleteDog/{id}")
    public ResponseEntity<String> deleteDog(@PathVariable int id) {
        Dog dog = dogRepository.findById(id).orElse(null);

        if (dog == null) {
            return ResponseEntity.status(404).body("Dog not found with id: " + id);
        }

        // Find and delete all appointments related to the dog
        List<Appointment> appointments = appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getDogs().contains(dog))
                .collect(Collectors.toList());
        appointmentRepository.deleteAll(appointments);

        // Delete the dog
        dogRepository.deleteById(id);

        return ResponseEntity.ok("Dog deleted with id: " + id);
    }

    @PostMapping("/UpdateDog")
    public ResponseEntity<Map<String, Object>> updateDog(@RequestBody Dog updatedDog) {
        // Fetch the existing dog from the database
        Dog existingDog = dogRepository.findById(updatedDog.getId())
                .orElseThrow(() -> new RuntimeException("Dog not found with ID: " + updatedDog.getId()));

        // Update the fields of the existing dog with the new data
        existingDog.setName(updatedDog.getName());
        existingDog.setRasse(updatedDog.getRasse());
        existingDog.setPassNr(updatedDog.getPassNr());
        existingDog.setChipNr(updatedDog.getChipNr());
        existingDog.setGeschlecht(updatedDog.getGeschlecht());
        existingDog.setTierart(updatedDog.getTierart());
        existingDog.setMedikamente(updatedDog.getMedikamente());

        // If the owner is being updated, fetch the new owner and set it
        if (updatedDog.getDOwner() != null) {
            Kunde owner = kundeRepository.findById(updatedDog.getDOwner().getId())
                    .orElseThrow(
                            () -> new RuntimeException("Owner not found with ID: " + updatedDog.getDOwner().getId()));
            existingDog.setDOwner(owner);
        }

        // Save the updated dog back to the database
        Dog savedDog = dogRepository.save(existingDog);

        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("dog", savedDog);
        response.put("dogId", savedDog.getId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/GetAllBreeds")
    public List<String> getAllBreeds() {
        return dogRepository.findAll().stream()
                .map(Dog::getRasse)
                .filter(rasse -> rasse != null && !rasse.isEmpty())
                .collect(Collectors.groupingBy(rasse -> rasse, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
