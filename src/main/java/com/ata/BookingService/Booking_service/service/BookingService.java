package com.ata.BookingService.Booking_service.service;

import java.util.List;

import com.ata.BookingService.Booking_service.entity.BookingEntity;

public interface BookingService {
    BookingEntity createBooking(BookingEntity booking);
    BookingEntity getBookingById(Long id);
    List<BookingEntity> getAllBookings();
    BookingEntity updateBooking(Long id, BookingEntity booking);
    void deleteBooking(Long id);
}
