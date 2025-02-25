package com.username4fabian.pethotel_manager.Entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Kunde")
public class Kunde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String anrede;
    private String firstName;
    private String lastName;
    private String straße;
    private String plz;
    private String ort;

    private String telefonnummer;
    private String email;
    private String ausweisnr;
    private Date geburtsdatum;
    private String geburtsort;
    private String sprache;
    private boolean agbsAkzeptiert;
    private boolean noMWST;

    @OneToMany(mappedBy = "DOwner")
    @JsonIgnore
    private List<Dog> dogs;

    public Kunde() {
        // Default constructor
    }

    public Kunde(String anrede, String firstName, String lastName, String straße, String plz, String ort,
            String telefonnummer, String email, String ausweisnr, Date geburtsdatum, String geburtsort, String sprache,
            boolean agbsAkzeptiert, boolean noMWST) {
        this.anrede = anrede;
        this.firstName = firstName;
        this.lastName = lastName;
        this.straße = straße;
        this.plz = plz;
        this.ort = ort;
        this.telefonnummer = telefonnummer;
        this.email = email;
        this.ausweisnr = ausweisnr;
        this.geburtsdatum = geburtsdatum;
        this.geburtsort = geburtsort;
        this.sprache = sprache;
        this.agbsAkzeptiert = agbsAkzeptiert;
        this.noMWST = noMWST;
    }
}