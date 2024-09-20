package com.ata.BookingService.Booking_service.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	 private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
	        logger.error("Internal Server Error: {}", ex.getMessage(), ex);
	        ErrorDetails errorDetails = new ErrorDetails(
	            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	            ex.getMessage(), 
	            request.getDescription(false)
	        );
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    // Other specific exception handlers...

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<?> handleBookingNotFoundException(BookingNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
