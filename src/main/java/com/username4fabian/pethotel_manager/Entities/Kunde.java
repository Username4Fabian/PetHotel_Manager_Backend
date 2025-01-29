package com.username4fabian.pethotel_manager.Entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private int telefonnummer;
    private String email;
    private String ausweisnr; 
    private Date geburtsdatum;
    private String geburtsort; 
    private String sprache; 
    private boolean agbsAkzeptiert;
    private boolean noMWST; 

    public Kunde() {
        // Default constructor
    }

    public Kunde(String anrede, String firstName, String lastName, String straße, String plz, String ort, int telefonnummer, String email, String ausweisnr, Date geburtsdatum, String geburtsort, String sprache, boolean agbsAkzeptiert, boolean noMWST) {
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


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnrede() {
        return this.anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStraße() {
        return this.straße;
    }

    public void setStraße(String straße) {
        this.straße = straße;
    }

    public String getPlz() {
        return this.plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return this.ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public int getTelefonnummer() {
        return this.telefonnummer;
    }

    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAusweisnr() {
        return this.ausweisnr;
    }

    public void setAusweisnr(String ausweisnr) {
        this.ausweisnr = ausweisnr;
    }

    public Date getGeburtsdatum() {
        return this.geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getGeburtsort() {
        return this.geburtsort;
    }

    public void setGeburtsort(String geburtsort) {
        this.geburtsort = geburtsort;
    }

    public String getSprache() {
        return this.sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    public boolean isAgbsAkzeptiert() {
        return this.agbsAkzeptiert;
    }

    public boolean getAgbsAkzeptiert() {
        return this.agbsAkzeptiert;
    }

    public void setAgbsAkzeptiert(boolean agbsAkzeptiert) {
        this.agbsAkzeptiert = agbsAkzeptiert;
    }

    public boolean isNoMWST() {
        return this.noMWST;
    }

    public boolean getNoMWST() {
        return this.noMWST;
    }

    public void setNoMWST(boolean noMWST) {
        this.noMWST = noMWST;
    }

}