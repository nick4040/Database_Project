package com.example.vactionHome.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int amenityId;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "amenities")
    private Set<Listing> listings = new HashSet<>();

}
