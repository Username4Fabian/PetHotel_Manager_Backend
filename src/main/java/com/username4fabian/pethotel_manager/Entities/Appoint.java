package com.username4fabian.pethotel_manager.Entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Appointment")
public class Appoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    Date deliveryDate;
    Date pickupDate;

    @ManyToOne
    @JoinColumn(name = "kunde_id")
    private Kunde Kunde;

    @OneToMany
    @JoinColumn(name = "dog_id")
    private List<Dog> dogs;

    public Appoint() {
        // Default constructor
    }

    public Appoint(String name, Date deliveryDate, Date pickupDate, Kunde Kunde, List<Dog> dogs) {
        this.name = name;
        this.deliveryDate = deliveryDate;
        this.pickupDate = pickupDate;
        this.Kunde = Kunde;
        this.dogs = dogs;
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

    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getPickupDate() {
        return this.pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Kunde getKunde() {
        return this.Kunde;
    }

    public void setKunde(Kunde Kunde) {
        this.Kunde = Kunde;
    }

    public List<Dog> getDogs() {
        return this.dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

}
