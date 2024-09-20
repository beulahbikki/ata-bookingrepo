package com.ata.BookingService.Booking_service.controller;

import com.ata.BookingService.Booking_service.entity.BookingEntity;
import com.ata.BookingService.Booking_service.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateBooking() {
        BookingEntity booking = new BookingEntity();
        when(bookingService.createBooking(booking)).thenReturn(booking);

        ResponseEntity<BookingEntity> response = bookingController.createBooking(booking);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(booking, response.getBody());
        verify(bookingService, times(1)).createBooking(booking);
    }

    @Test
    void testGetAllBookings() {
        List<BookingEntity> bookings = new ArrayList<>();
        bookings.add(new BookingEntity());
        when(bookingService.getAllBookings()).thenReturn(bookings);

        ResponseEntity<List<BookingEntity>> response = bookingController.getAllBookings();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookings, response.getBody());
        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    void testGetAllBookings_NoContent() {
        when(bookingService.getAllBookings()).thenReturn(new ArrayList<>());

        ResponseEntity<List<BookingEntity>> response = bookingController.getAllBookings();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    void testGetBookingById() {
        BookingEntity booking = new BookingEntity();
        when(bookingService.getBookingById(1L)).thenReturn(booking);

        ResponseEntity<BookingEntity> response = bookingController.getBookingById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(booking, response.getBody());
        verify(bookingService, times(1)).getBookingById(1L);
    }

    @Test
    void testUpdateBooking() {
        BookingEntity booking = new BookingEntity();
        when(bookingService.updateBooking(1L, booking)).thenReturn(booking);

        ResponseEntity<BookingEntity> response = bookingController.updateBooking(1L, booking);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(booking, response.getBody());
        verify(bookingService, times(1)).updateBooking(1L, booking);
    }

    @Test
    void testDeleteBooking() {
        doNothing().when(bookingService).deleteBooking(1L);

        ResponseEntity<Void> response = bookingController.deleteBooking(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookingService, times(1)).deleteBooking(1L);
    }
}
