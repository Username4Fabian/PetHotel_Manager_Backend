package com.username4fabian.pethotel_manager.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Zimmer")
public class Zimmer {
    @Id
    private int zimmerId; // User-defined Zimmer ID

    private String tierart; // Hund, Katze, Kleintier

    public Zimmer() {
        // Default constructor
    }

    public Zimmer(int zimmerId, String tierart) {
        this.zimmerId = zimmerId;
        this.tierart = tierart;
    }
}