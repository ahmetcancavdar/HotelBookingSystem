package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HotelBookingSystem system = new HotelBookingSystem();
        Scanner input = new Scanner(System.in);

        // ROOMS
        system.addRoom(new Room(101, "clean", 1000));
        system.addRoom(new Room(102, "clean", 1000));
        system.addRoom(new Room(103, "clean", 1000));
        system.addRoom(new Room(104, "clean", 1000));

        // PREDEFINED GUEST
        List<Guest> guests = new ArrayList<>();
        guests.add(new Guest("Ahmetcan", "cash"));
        guests.add(new Guest("Alp", "cash"));
        guests.add(new Guest("Kerem", "cash"));

        // PREDEFINED STAFF
        List<Staff> staffList = new ArrayList<>();
        staffList.add(new Staff("Mehmet"));
        staffList.add(new Staff("Zeynep"));
        staffList.add(new Staff("Ayşe"));

        System.out.println("=== HOTEL BOOKING SYSTEM ===");

        while (true) {

            System.out.println("\n1) Guest Login");
            System.out.println("2) Staff Login");
            System.out.println("3) Create New Guest / Staff");

            int roleChoice = readIntSafe(input, "Choose (1-3): ");

            // CREATE NEW USER
            if (roleChoice == 3) {
                createNewUser(guests, staffList, input);
                continue;
            }

            // GUEST LOGIN
            if (roleChoice == 1) {
                Guest loggedGuest = readGuestSafe(guests, input);
                runGuestMenu(loggedGuest, system, guests);
            }

            // STAFF LOGIN
            if (roleChoice == 2) {
                Staff loggedStaff = readStaffSafe(staffList, input);
                runStaffMenu(loggedStaff, system, guests);
            }
        }
    }

    //CREATE NEW USER
    private static void createNewUser(List<Guest> guests, List<Staff> staffList, Scanner sc) {

        System.out.println("\n--- Create New User ---");
        System.out.println("1) Create Guest");
        System.out.println("2) Create Staff");

        int choice = readIntSafe(sc, "Choose (1-2): ");
        sc.nextLine();

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        if (choice == 1) {
            for (Guest g : guests)
                if (g.getName().equalsIgnoreCase(name)) {
                    System.out.println("Guest already exists!");
                    return;
                }
            guests.add(new Guest(name, "cash"));
            System.out.println("Guest created successfully.");
        }

        if (choice == 2) {
            for (Staff s : staffList)
                if (s.getName().equalsIgnoreCase(name)) {
                    System.out.println("Staff already exists!");
                    return;
                }
            staffList.add(new Staff(name));
            System.out.println("Staff created successfully.");
        }
    }

    //GUEST MENU
    private static void runGuestMenu(Guest guest, HotelBookingSystem system, List<Guest> guests) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Guest Menu (" + guest.getName() + ") ---");
            System.out.println("1) Book Room");
            System.out.println("2) Check Room Availability");
            System.out.println("3) Cancel Reservation");
            System.out.println("4) Exit");

            int c = readIntSafe(sc, "Choose: ");

            if (c == 1) {

                Room room = readRoomSafe(system, sc);

                LocalDate ci = readDateSafe(sc, "Check-in (YYYY-MM-DD): ");
                LocalDate co = readDateSafe(sc, "Check-out (YYYY-MM-DD): ");

                if (room.isAvailable(ci, co)) {
                    System.out.println("Room is available (" + room.getPrice() + " TL for each day). Proceeding to payment...");
                    String payment = readPaymentType(sc);
                    guest.setPaymentDetails(payment);
                    Reservation r = guest.bookRoom(system, room, ci, co);
                    if (r != null) {
                        System.out.println("ID: " + r.getReservationID() +
                                " | Room: " + r.getRoom().getRoomNumber() +
                                " | Dates: " + r.getCheckInDate() + " -> " + r.getCheckOutDate() +
                                " | Status: " + r.getReservationStatus() +
                                " | Payment: " + r.getGuest().getPaymentDetails());
                    }

                } else {
                    System.out.println("Desired room is not available for these dates.");

                }

            } else if (c == 2) {

                LocalDate ci = readDateSafe(sc, "Check-in (YYYY-MM-DD) : ");
                LocalDate co = readDateSafe(sc, "Check-out (YYYY-MM-DD) : ");

                guest.checkRoomAvailability(system, ci, co);

            } else if (c == 3) {
                int id = readIntSafe(sc, "Reservation ID: ");
                Reservation r = system.findReservationById(id);
                    if (r != null) {
                        guest.cancelBooking(system, r);
                        System.out.println("ID: " + r.getReservationID() +
                                " | Room: " + r.getRoom().getRoomNumber() +
                                " | Dates: " + r.getCheckInDate() + " -> " + r.getCheckOutDate() +
                                " | Status: " + r.getReservationStatus() +
                                " | Payment: " + r.getGuest().getPaymentDetails());
                    } else {
                        System.out.println("Reservation not found.");
                    }
            }
             else if (c == 4) return;
        }
    }
    //STAFF MENU
    private static void runStaffMenu(Staff staff, HotelBookingSystem system, List<Guest> guests) {
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- Staff Menu (" + staff.getName() + ") ---");
            System.out.println("1) Create Reservation for Guest");
            System.out.println("2) Update Room Status");
            System.out.println("3) Exit");

            int c = readIntSafe(sc, "Choose: ");

            if (c == 1) {

                Guest g = readGuestSafe(guests, sc);
                Room room = readRoomSafe(system, sc);

                LocalDate ci = readDateSafe(sc, "Check-in (YYYY-MM-DD) : ");
                LocalDate co = readDateSafe(sc, "Check-out (YYYY-MM-DD) : ");

                if (room.isAvailable(ci, co)) {
                    System.out.println("Room is available. Proceeding to payment details...");
                    String payment = readPaymentType(sc);
                    g.setPaymentDetails(payment);
                    Reservation r = staff.bookRoom(system, g, room, ci, co);
                    if (r != null) {
                        System.out.println("ID: " + r.getReservationID() +
                                " | Guest: " + r.getGuest().getName() +
                                " | Room: " + r.getRoom().getRoomNumber() +
                                " | Dates: " + r.getCheckInDate() + " -> " + r.getCheckOutDate() +
                                " | Payment: " + r.getGuest().getPaymentDetails());
                    }
                } else {
                    System.out.println("Desired room is NOT available for these dates.");
                }

            } else if (c == 2) {

                Room room = readRoomSafe(system, sc);

                String newStatus;
                while (true) {
                    System.out.println("Current Status: " + room.getRoomStatus());
                    System.out.print("New Status (clean/dirty): ");
                    newStatus = sc.nextLine().trim();
                    if (newStatus.equalsIgnoreCase("clean") ||
                            newStatus.equalsIgnoreCase("dirty")) break;
                    System.out.println("Only clean/dirty allowed.");
                }

                staff.updateRoomStatus(system, room.getRoomNumber(), newStatus);

            } else if (c == 3) return;
        }
    }

    //SAFE INPUT HELPERS
    private static int readIntSafe(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            if (sc.hasNextInt()) {
                int v = sc.nextInt();
                sc.nextLine();
                return v;
            }
            System.out.println("Invalid number.");
            sc.next();
        }
    }

    private static LocalDate readDateSafe(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            try {
                return LocalDate.parse(input);
            } catch (Exception e) {
                System.out.println("Invalid date format. Example: 2025-12-01");
            }
        }
    }

    private static String readPaymentType(Scanner sc) {
        while (true) {
            System.out.print("Payment type (cash / credit card): ");
            String type = sc.nextLine().trim().toLowerCase();

            if (type.equals("cash") || type.equals("credit card"))
                return type;

            System.out.println("Invalid payment type.");
        }
    }

    private static Guest readGuestSafe(List<Guest> guests, Scanner sc) {
        while (true) {
            System.out.print("Guest Name: ");
            String name = sc.nextLine();

            for (Guest g : guests)
                if (g.getName().equalsIgnoreCase(name))
                    return g;

            System.out.println("Guest not found.");
        }
    }

    private static Staff readStaffSafe(List<Staff> staffList, Scanner sc) {
        while (true) {
            System.out.print("Staff Name: ");
            String name = sc.nextLine();

            for (Staff s : staffList)
                if (s.getName().equalsIgnoreCase(name))
                    return s;

            System.out.println("Staff not found.");
        }
    }

    private static Room readRoomSafe(HotelBookingSystem system, Scanner sc) {
        while (true) {
            int rn = readIntSafe(sc, "Room Number (101-104): ");
            Room room = system.getRoomByNumber(rn);
            if (room != null) return room;
            System.out.println("Room not found.");
        }
    }
}