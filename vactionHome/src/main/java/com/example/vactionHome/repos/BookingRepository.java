package com.example.vactionHome.repos;

import com.example.vactionHome.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByUser_UserId(int userId);
    List<Booking> findByListing_ListingId(int listingId);

}
