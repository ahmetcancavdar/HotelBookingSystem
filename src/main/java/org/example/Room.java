package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {

    private int roomNumber;
    private String roomStatus;
    private double price;

    private List<Reservation> reservations;

    public Room(int roomNumber, String roomStatus, double price) {
        this.roomNumber = roomNumber;
        this.roomStatus = roomStatus;
        this.price = price;
        this.reservations = new ArrayList<>();
    }

    public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {

        if ("Maintenance".equalsIgnoreCase(this.roomStatus)) {
            return false;
        }

        for (Reservation existingRes : reservations) {
            if ("CANCELLED".equals(existingRes.getReservationStatus())) {
                continue;
            }
            LocalDate existingCheckIn  = existingRes.getCheckInDate();
            LocalDate existingCheckOut = existingRes.getCheckOutDate();

            boolean isOverlap = checkIn.isBefore(existingCheckOut) &&
                    checkOut.isAfter(existingCheckIn);

            if (isOverlap) {
                return false;
            }
        }
        return true;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    //HELPER AND GETTER SETTER METHODS
    public List<Reservation> getReservations() {
        return reservations;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setStatus(String status) {
        this.roomStatus = status;
    }

    public double getPrice() {
        return price;
    }
}