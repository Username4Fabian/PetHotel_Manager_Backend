package com.username4fabian.pethotel_manager.Entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int appointment_nr;

    @Column(length = 1000)
    private String anmerkung;

    private Date date_ankunft;
    private String time_ankunft;
    private Date date_abfahrt;
    private String time_abfahrt;

    private boolean bezahlt;

    @ManyToMany
    @JoinTable(name = "appointment_dogs", joinColumns = @JoinColumn(name = "appointment_id"), inverseJoinColumns = @JoinColumn(name = "dog_id"))
    private List<Dog> dogs;

    @ManyToOne
    @JoinColumn(name = "kunde_id")
    private Kunde kunde;

    // Transient fields for receiving kundeId and dogIds from the frontend
    private transient Integer kundeId;
    private transient List<Integer> dogIds;

    public Appointment() {
        // Default constructor
    }

    public Appointment(String anmerkung, Date date_ankunft, String time_ankunft, Date date_abfahrt, String time_abfahrt,
            boolean bezahlt, List<Dog> dogs, Kunde kunde, int appointment_nr) {
        this.anmerkung = anmerkung;
        this.date_ankunft = date_ankunft;
        this.time_ankunft = time_ankunft;
        this.date_abfahrt = date_abfahrt;
        this.time_abfahrt = time_abfahrt;
        this.bezahlt = bezahlt;
        this.dogs = dogs;
        this.kunde = kunde;
        this.appointment_nr = appointment_nr;
    }
}