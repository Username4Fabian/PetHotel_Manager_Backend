package com.username4fabian.pethotel_manager.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "zimmer_belegung")
public class ZimmerBelegung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "zimmer_id", nullable = false)
    private Zimmer zimmer;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "dog_id", nullable = false)
    private Dog dog;

    public ZimmerBelegung() {
        // Default constructor
    }

    public ZimmerBelegung(Zimmer zimmer, Appointment appointment, Dog dog) {
        this.zimmer = zimmer;
        this.appointment = appointment;
        this.dog = dog;
    }
}
