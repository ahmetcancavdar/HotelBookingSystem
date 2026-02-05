package org.example;

import java.time.LocalDate;

public class Staff {
    private String staffName;

    public Staff(String staffName) {
        this.staffName = staffName;
    }

    public Reservation bookRoom(HotelBookingSystem system, Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        System.out.println("Staff " + staffName +
                " is booking for " + guest.getName() +
                " with payment: " + guest.getPaymentDetails());
       return system.createReservation(guest, room, checkIn, checkOut);
    }

    public void updateRoomStatus(HotelBookingSystem system, int roomNumber, String newStatus) {
        System.out.println("Staff " + staffName +
                " is updating room " + roomNumber + " to " + newStatus);
        system.updateRoomStatus(roomNumber, newStatus);
    }

    //HELPER AND GETTER SETTER METHODS
    public String getName() {
        return staffName;
    }

    public String setName(String name){
        this.staffName = name;
        return staffName;
    }
}