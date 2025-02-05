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
@Table(name = "Dog")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String rasse;
    private String geschlecht;

    private int passNr;
    private String chipNr;

    @ManyToOne
    @JoinColumn(name = "kunde_id")
    private Kunde DOwner;

    public Dog() {
        // Default constructor
    }

    public Dog(String name, String rasse, String geschlecht, int passNr, String chipNr, Kunde DOwner) {
        this.name = name;
        this.rasse = rasse;
        this.geschlecht = geschlecht;
        this.passNr = passNr;
        this.chipNr = chipNr;
        this.DOwner = DOwner;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRasse() {
        return this.rasse;
    }

    public void setRasse(String rasse) {
        this.rasse = rasse;
    }

    public String getGeschlecht() {
        return this.geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public int getPassNr() {
        return this.passNr;
    }

    public void setPassNr(int passNr) {
        this.passNr = passNr;
    }

    public String getChipNr() {
        return this.chipNr;
    }

    public void setChipNr(String chipNr) {
        this.chipNr = chipNr;
    }

    public Kunde getDOwner() {
        return this.DOwner;
    }

    public void setDOwner(Kunde DOwner) {
        this.DOwner = DOwner;
    }
}
