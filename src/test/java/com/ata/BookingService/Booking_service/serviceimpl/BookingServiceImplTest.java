package com.ata.BookingService.Booking_service.serviceimpl;

import com.ata.BookingService.Booking_service.entity.BookingEntity;
import com.ata.BookingService.Booking_service.exception.BookingNotFoundException;
import com.ata.BookingService.Booking_service.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateBooking() {
        BookingEntity booking = new BookingEntity();
        when(bookingRepository.save(booking)).thenReturn(booking);

        BookingEntity createdBooking = bookingService.createBooking(booking);
        assertEquals(booking, createdBooking);
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void testGetBookingById_Success() {
        BookingEntity booking = new BookingEntity();
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        BookingEntity retrievedBooking = bookingService.getBookingById(1L);
        assertEquals(booking, retrievedBooking);
        verify(bookingRepository, times(1)).findById(1L);
    }

    @Test
    void testGetBookingById_NotFound() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.getBookingById(1L);
        });
        verify(bookingRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllBookings() {
        List<BookingEntity> bookings = new ArrayList<>();
        bookings.add(new BookingEntity());
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<BookingEntity> retrievedBookings = bookingService.getAllBookings();
        assertEquals(bookings, retrievedBookings);
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void testUpdateBooking_Success() {
        BookingEntity booking = new BookingEntity();
        when(bookingRepository.existsById(1L)).thenReturn(true);
        when(bookingRepository.save(booking)).thenReturn(booking);

        BookingEntity updatedBooking = bookingService.updateBooking(1L, booking);
        assertEquals(booking, updatedBooking);
        verify(bookingRepository, times(1)).existsById(1L);
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void testUpdateBooking_NotFound() {
        BookingEntity booking = new BookingEntity();
        when(bookingRepository.existsById(1L)).thenReturn(false);

        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.updateBooking(1L, booking);
        });
        verify(bookingRepository, times(1)).existsById(1L);
        verify(bookingRepository, never()).save(booking);
    }

    @Test
    void testDeleteBooking_Success() {
        when(bookingRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookingRepository).deleteById(1L);

        bookingService.deleteBooking(1L);
        verify(bookingRepository, times(1)).existsById(1L);
        verify(bookingRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBooking_NotFound() {
        when(bookingRepository.existsById(1L)).thenReturn(false);

        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.deleteBooking(1L);
        });
        verify(bookingRepository, times(1)).existsById(1L);
        verify(bookingRepository, never()).deleteById(1L);
    }
}
