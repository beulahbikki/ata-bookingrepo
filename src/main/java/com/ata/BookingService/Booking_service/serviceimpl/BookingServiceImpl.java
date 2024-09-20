package com.ata.BookingService.Booking_service.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ata.BookingService.Booking_service.entity.BookingEntity;
import com.ata.BookingService.Booking_service.exception.BookingNotFoundException;
import com.ata.BookingService.Booking_service.repository.BookingRepository;
import com.ata.BookingService.Booking_service.service.BookingService;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingEntity createBooking(BookingEntity booking) {
        logger.info("Creating booking: {}", booking);
        return bookingRepository.save(booking);
    }

    @Override
    public BookingEntity getBookingById(Long id) {
        logger.info("Fetching booking with id: {}", id);
        Optional<BookingEntity> booking = bookingRepository.findById(id);
        return booking.orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));
    }


    @Override
    public List<BookingEntity> getAllBookings() {
        logger.info("Fetching all bookings");
        try {
            return bookingRepository.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving bookings", e);
            throw new RuntimeException("Error retrieving bookings", e);
        }
    }
    @Override
    public BookingEntity updateBooking(Long id, BookingEntity booking) {
        logger.info("Updating booking with id: {}", id);
        if (bookingRepository.existsById(id)) {
            booking.setId(id);
            return bookingRepository.save(booking);
        } else {
            throw new BookingNotFoundException("Booking not found with id: " + id);
        }
    }

    @Override
    public void deleteBooking(Long id) {
        logger.info("Deleting booking with id: {}", id);
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        } else {
            throw new BookingNotFoundException("Booking not found with id: " + id);
        }
    }
}
