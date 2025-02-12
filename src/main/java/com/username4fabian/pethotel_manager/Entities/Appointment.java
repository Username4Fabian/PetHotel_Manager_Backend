package com.username4fabian.pethotel_manager.Entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String anmerkung;
    private LocalDateTime ankunft;
    private LocalDateTime abfahrt;
    private boolean bezahlt;

    @OneToMany
    @JoinTable(name = "appointment_dogs", joinColumns = @JoinColumn(name = "appointment_id"), inverseJoinColumns = @JoinColumn(name = "dog_id"))
    private List<Dog> dogs;

    @ManyToOne
    private Kunde kunde;

    public Appointment() {
        // Default constructor
    }

    public Appointment(String anmerkung, LocalDateTime ankunft, LocalDateTime abfahrt, boolean bezahlt, List<Dog> dogs,
            Kunde kunde) {
        this.anmerkung = anmerkung;
        this.ankunft = ankunft;
        this.abfahrt = abfahrt;
        this.bezahlt = bezahlt;
        this.dogs = dogs;
        this.kunde = kunde;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnmerkung() {
        return this.anmerkung;
    }

    public void setAnmerkung(String anmerkung) {
        this.anmerkung = anmerkung;
    }

    public LocalDateTime getAnkunft() {
        return this.ankunft;
    }

    public void setAnkunft(LocalDateTime ankunft) {
        this.ankunft = ankunft;
    }

    public LocalDateTime getAbfahrt() {
        return this.abfahrt;
    }

    public void setAbfahrt(LocalDateTime abfahrt) {
        this.abfahrt = abfahrt;
    }

    public boolean isBezahlt() {
        return this.bezahlt;
    }

    public boolean getBezahlt() {
        return this.bezahlt;
    }

    public void setBezahlt(boolean bezahlt) {
        this.bezahlt = bezahlt;
    }

    public List<Dog> getDogs() {
        return this.dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public Kunde getKunde() {
        return this.kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

}