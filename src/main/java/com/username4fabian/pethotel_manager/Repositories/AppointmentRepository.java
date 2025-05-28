package com.username4fabian.pethotel_manager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.username4fabian.pethotel_manager.Entities.Appointment;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT COALESCE(MAX(a.appointment_nr), 0) FROM Appointment a")
    Integer findMaxAppointmentNr();

    @Query("SELECT a FROM Appointment a WHERE a.appointment_nr = :appointmentNr")
    Optional<Appointment> findByAppointmentNr(int appointmentNr);
}
