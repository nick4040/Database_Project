package com.example.vactionHome.repos;

import com.example.vactionHome.entity.ListingAdditionalItem;
import com.example.vactionHome.entity.ListingAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingAdditionalItemRepository extends JpaRepository<ListingAdditionalItem, Integer> {

    List<ListingAdditionalItem> findByListing_ListingId(int listingId);

}
