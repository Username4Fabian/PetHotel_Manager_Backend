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

    private String imageURL;

    private String tierart;

    private String medikamente;

    @ManyToOne
    @JoinColumn(name = "kunde_id")
    private Kunde DOwner;

    public Dog() {
        // Default constructor
    }

    public Dog(String name, String rasse, String geschlecht, int passNr, String chipNr, Kunde DOwner, String imageURL,
            String tierart, String medikamente) {
        this.medikamente = medikamente;
        this.tierart = tierart;
        this.name = name;
        this.rasse = rasse;
        this.geschlecht = geschlecht;
        this.passNr = passNr;
        this.chipNr = chipNr;
        this.DOwner = DOwner;
        this.imageURL = imageURL;
    }
}
