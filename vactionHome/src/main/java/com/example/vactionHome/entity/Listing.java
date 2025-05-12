package com.example.vactionHome.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "listings")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int listingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String title;

    private String description;

    private String location;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "listing_url")
    private String listingUrl;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListingAvailability> availability = new ArrayList<>();

    //basiclly makes a join table for listing_id and amenity_id
    @ManyToMany
    @JoinTable( name = "listing_amenities",
            joinColumns = @JoinColumn(name = "listing_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities = new HashSet<>();

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListingAdditionalItem> additionalItems = new ArrayList<>();

    //getters, setters and constructor

    public Listing() {
    }

    public Listing(User user, String title, String description, String location, String imageUrl,
                   String listingUrl, Timestamp createdAt) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
        this.listingUrl = listingUrl;
        this.createdAt = createdAt;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getListingUrl() {
        return listingUrl;
    }

    public void setListingUrl(String listingUrl) {
        this.listingUrl = listingUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<ListingAvailability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<ListingAvailability> availability) {
        this.availability = availability;
    }

    public Set<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<ListingAdditionalItem> getAdditionalItems() {
        return additionalItems;
    }

    public void setAdditionalItems(List<ListingAdditionalItem> additionalItems) {
        this.additionalItems = additionalItems;
    }

}
