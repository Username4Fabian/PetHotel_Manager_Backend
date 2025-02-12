package com.username4fabian.pethotel_manager.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.username4fabian.pethotel_manager.Entities.Dog;
import com.username4fabian.pethotel_manager.Entities.Kunde;

public interface DogRepository extends JpaRepository<Dog, Integer> {
    List<Dog> findByDOwner(Kunde owner);
}
