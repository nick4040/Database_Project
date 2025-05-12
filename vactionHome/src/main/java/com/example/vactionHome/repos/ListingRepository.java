package com.example.vactionHome.repos;

import com.example.vactionHome.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Integer> {
    List<Listing> findByLocation(String location);
    List<Listing> findByUser_UserId(int userId);
    List<Listing> findByListingId(int ListingId);
}
