package com.example.vactionHome.repos;

import com.example.vactionHome.entity.ListingAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingAvailabilityRepository extends JpaRepository<ListingAvailability, Integer> {

    List<ListingAvailability> findByListing_listingId(int listingId);


}
