package org.example;

import java.time.LocalDate;

public class Guest {
    private String guestName;
    private String paymentDetails;

    public Guest(String guestName, String paymentDetails) {
        this.guestName = guestName;
        this.paymentDetails = paymentDetails;
    }

    public Reservation bookRoom(HotelBookingSystem system, Room room, LocalDate checkIn, LocalDate checkOut) {

        System.out.println("Guest " + guestName +
                " is initiating booking with payment: " + paymentDetails);
        return system.createReservation(this, room, checkIn, checkOut);
    }

    public void cancelBooking(HotelBookingSystem system, Reservation reservation) {
        system.cancelReservation(reservation.getReservationID());
    }

    public void checkRoomAvailability(HotelBookingSystem system, LocalDate checkIn, LocalDate checkOut) {
        system.checkRoomAvailability(checkIn, checkOut);
    }

    //HELPER AND GETTER SETTER METHODS
    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getName() {
        return guestName;
    }

    public String setName(String guestName) {
        this.guestName = guestName;
        return guestName;
    }
}