package com.username4fabian.pethotel_manager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.username4fabian.pethotel_manager.Entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
