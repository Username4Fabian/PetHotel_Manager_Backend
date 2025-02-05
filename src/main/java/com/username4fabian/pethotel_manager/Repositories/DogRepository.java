package com.username4fabian.pethotel_manager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.username4fabian.pethotel_manager.Entities.Dog;

public interface DogRepository extends JpaRepository<Dog, Integer> {

}
