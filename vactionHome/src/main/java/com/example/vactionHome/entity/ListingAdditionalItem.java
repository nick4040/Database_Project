package com.example.vactionHome.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "listing_additionalItem")
public class ListingAdditionalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @ManyToOne
    @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;

    @Column(name = "item_text")
    private String itemText;
}
