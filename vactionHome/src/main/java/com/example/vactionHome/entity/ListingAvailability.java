package com.example.vactionHome.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "listing_availability")
public class ListingAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int availableId;

    @ManyToOne
    @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;

    @Column(name = "available_from")
    private LocalDate availableFrom;

    @Column(name = "available_to")
    private LocalDate availableTo;

}
