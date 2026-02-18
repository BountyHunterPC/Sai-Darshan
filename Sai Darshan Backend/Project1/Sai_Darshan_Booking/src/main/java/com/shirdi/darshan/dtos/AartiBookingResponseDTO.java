package com.shirdi.darshan.dtos;

public class AartiBookingResponseDTO {

    private int bookingId;
    private String message;

    public AartiBookingResponseDTO(int bookingId, String message) {
        this.bookingId = bookingId;
        this.message = message;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getMessage() {
        return message;
    }
}
