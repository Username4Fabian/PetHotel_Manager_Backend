package com.username4fabian.pethotel_manager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.username4fabian.pethotel_manager.Entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT COALESCE(MAX(a.id), 0) FROM Appointment a")
    Integer findMaxId();

}
