package org.example;
import java.time.LocalDate;

public class Reservation {
    private int reservationID;
    private String reservationStatus;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;


    public Reservation(int reservationID, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationID = reservationID;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationStatus = "CONFIRMED";
    }

    public void cancel() {
        this.reservationStatus = "CANCELLED";
        System.out.println("Reservation " + reservationID + " is cancelled.");
    }

    //HELPER AND GETTER SETTER METHODS
    public int getReservationID() {
        return reservationID;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
}