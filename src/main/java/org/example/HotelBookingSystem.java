package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingSystem {

    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    private int reservationCounter = 1000;

    public void checkRoomAvailability(LocalDate checkIn, LocalDate checkOut) {
        System.out.println("\nAvailability between " + checkIn + " and " + checkOut + ":");

        for (Room room : rooms) {
            boolean available =
                    room.getRoomStatus().equalsIgnoreCase("clean") &&
                            room.isAvailable(checkIn, checkOut);

            System.out.println("Room " + room.getRoomNumber() +
                    " | Status: " + room.getRoomStatus() +
                    " | " + (available ? "AVAILABLE" : "NOT AVAILABLE"));
        }
    }

    public Reservation createReservation(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        if (room == null) {
            System.out.println("Room not found. Cannot create reservation.");
            return null;
        }

        if (!room.getRoomStatus().equalsIgnoreCase("clean")) {
            System.out.println("Room " + room.getRoomNumber() +
                    " is DIRTY. Cannot create reservation.");
            return null;
        }

        if (!checkOut.isAfter(checkIn)) {
            System.out.println("Check-out must be after check-in.");
            return null;
        }

        if (!room.isAvailable(checkIn, checkOut)) {
            System.out.println("Room " + room.getRoomNumber() +
                    " is NOT available between " + checkIn + " and " + checkOut);
            return null;
        }
        if (reservationCounter > 10000) {
            System.out.println("Maximum reservation limit reached (ID > 10000).");
            return null;
        }
        int newId = reservationCounter++;

        Reservation reservation = new Reservation(newId, guest, room, checkIn, checkOut);

        reservations.add(reservation);
        room.addReservation(reservation);

        System.out.println("Reservation created successfully. ID: " + reservation.getReservationID());
        return reservation;
    }

    public void cancelReservation(int reservationID) {
        Reservation res = findReservationById(reservationID);
        if (res == null) {
            System.out.println("Reservation not found.");
            return;
        }
        res.cancel();
    }

    public void updateRoomStatus(int roomNumber, String newStatus) {
        Room room = getRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        if (!newStatus.equalsIgnoreCase("clean") &&
                !newStatus.equalsIgnoreCase("dirty")) {
            System.out.println("Invalid status. Only 'clean' or 'dirty' allowed.");
            return;
        }

        room.setStatus(newStatus);
        System.out.println("Room " + roomNumber + " status set to: " + newStatus);
    }

    ////HELPER AND GETTER SETTER METHODS
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room getRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public Reservation findReservationById(int id) {
        for (Reservation r : reservations) {
            if (r.getReservationID() == id) {
                return r;
            }
        }
        return null;
    }
}