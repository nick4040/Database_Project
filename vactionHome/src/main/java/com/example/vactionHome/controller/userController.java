package com.example.vactionHome.controller;

import com.example.vactionHome.Service.generalServices;
import com.example.vactionHome.dto.BookingForm;
import com.example.vactionHome.entity.Booking;
import com.example.vactionHome.entity.Listing;
import com.example.vactionHome.entity.User;
import com.example.vactionHome.repos.BookingRepository;
import com.example.vactionHome.repos.ListingRepository;
import com.example.vactionHome.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
public class userController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ListingRepository listingRepo;

    @Autowired
    private BookingRepository bookingRepository;

    // @Autowired
    //private generalServices mainServices;

    //this is the route mapping to the different pages
    @GetMapping("/")
    public String mainMenu(Model model) {

        List<Listing> ListingIds = listingRepo.findAll();
        model.addAttribute("ListIds", ListingIds);

        return "Views/index";
    }

    @GetMapping("/deleteListing")
    public String deleteListing(Model model) {

        model.addAttribute("addList", new Listing());

        return "Views/deleteListing";
    }

    @PostMapping("/deleteListing")
    public String postDeleteListing(@ModelAttribute Listing listing) {

        Optional<Listing> allListings = listingRepo.findById(listing.getListingId());

        if (allListings.isPresent()) {

            Listing existingListing = allListings.get();
            listingRepo.delete(existingListing);

            return "redirect:/";
        }else {
            return "redirect:/Views/errorPage";
        }
    }

    @GetMapping("/addListing")
    public String addListing(Model model) {

        model.addAttribute("addList", new Listing());

        return "Views/addListing";
    }

    @PostMapping("/addListing")
    public String addPostListing(@ModelAttribute Listing listing) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = userRepo.findByUsername(username);

        listing.setUser(currentUser);
        listing.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        listingRepo.save(listing);

        return "redirect:/";
        //  if this isnt working change to be redirect: or to the index
    }

    @GetMapping("/updateListing")
    public String updateListing(Model model) {

        model.addAttribute("addList", new Listing());
        model.addAttribute("listings", listingRepo.findAll());

        return "Views/updateListing";
    }

    @PostMapping("/updateListing")
    public String postUpdateListing(@ModelAttribute Listing listing) {

        System.out.println("Submitted ID: " + listing.getListingId());

        Optional<Listing> allListings = listingRepo.findById(listing.getListingId());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = userRepo.findByUsername(username);


        if (allListings.isPresent()){

            Listing existingListing = allListings.get();

            existingListing.setUser(currentUser);
            existingListing.setTitle(listing.getTitle());
            existingListing.setDescription(listing.getDescription());
            existingListing.setLocation(listing.getLocation());
            existingListing.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            listingRepo.save(existingListing);

            return "redirect:/";
        }else {
            return "redirect:/Views/errorPage";
        }
    }

    @GetMapping("/addAmenity")
    public String addAmenity(Model model){
        return "Views/addAmenity";
    }

    @GetMapping("/BookingPage")
    public String addBooking(Model model){

        model.addAttribute("addBook", new BookingForm());

        return "Views/BookingPage";
    }

    @PostMapping("/BookingPage")
    public String postAddBooking(@ModelAttribute BookingForm bookingForm){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = userRepo.findByUsername(username);

        Listing listing = listingRepo.findById(bookingForm.getListingId()).orElse(null);

        if (listing == null){
            return "redirect:/Views/errorPage";
        }
        Booking booking = new Booking();
        booking.setUser(currentUser);
        booking.setListing(listing);
        booking.setCreateAt(new Timestamp(System.currentTimeMillis()));
        booking.setStartDate(bookingForm.getStartDate());
        booking.setEndDate(bookingForm.getEndDate());

        bookingRepository.save(booking);

        return "redirect:/";
    }

    @GetMapping("/errorPage")
    public String errorHandler(Model model){
        return "Views/errorPage";
    }
}
