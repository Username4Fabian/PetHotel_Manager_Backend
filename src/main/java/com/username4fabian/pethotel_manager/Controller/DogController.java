package com.username4fabian.pethotel_manager.Controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.username4fabian.pethotel_manager.Entities.Dog;
import com.username4fabian.pethotel_manager.Entities.Kunde;
import com.username4fabian.pethotel_manager.Repositories.DogRepository;
import com.username4fabian.pethotel_manager.Repositories.KundeRepository;
import com.username4fabian.pethotel_manager.Services.BCDNStorage;

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
}