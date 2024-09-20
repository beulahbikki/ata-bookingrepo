package com.ata.BookingService.Booking_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ata.BookingService.Booking_service.entity.BookingEntity;
import com.ata.BookingService.Booking_service.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/booking")

public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

 
    @PostMapping("/add")
    public ResponseEntity<BookingEntity> createBooking(@RequestBody BookingEntity booking) {
        try {
            logger.info("Creating booking: {}", booking);
            BookingEntity createdBooking = bookingService.createBooking(booking);
            return ResponseEntity.ok(createdBooking);
        } catch (Exception e) {
            logger.error("Error creating booking", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookingEntity> getBookingById(@PathVariable Long id) {
        logger.info("Fetching booking with id: {}", id);
        BookingEntity booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }
    
    @GetMapping
    public ResponseEntity<List<BookingEntity>> getAllBookings() {
        try {
            List<BookingEntity> bookings = bookingService.getAllBookings();
            if (bookings.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            // Log the exception and return a 500 error response
            logger.error("Error fetching bookings", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    

    @PutMapping("/{id}")
    public ResponseEntity<BookingEntity> updateBooking(@PathVariable Long id, @RequestBody BookingEntity booking) {
        logger.info("Updating booking with id: {}", id);
        BookingEntity updatedBooking = bookingService.updateBooking(id, booking);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        logger.info("Deleting booking with id: {}", id);
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
