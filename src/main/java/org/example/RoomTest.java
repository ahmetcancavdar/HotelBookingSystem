package org.example;

import java.time.LocalDate;

public class RoomTest {

    public static void main(String[] args) {
        System.out.println("--- Room Availability Test Case Initialized ---");

        Room testRoom = new Room(101, "clean", 1000);

        Guest dummyGuest = new Guest("TestGuest", "cash");
        Reservation existingRes = new Reservation(
                1,
                dummyGuest,
                testRoom,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 5)
        );

        testRoom.addReservation(existingRes);
        System.out.println("Current Reservation added: 2025-01-01 to 2025-01-05.");

        System.out.println("\nScenario 1: Checking overlapping dates (Jan 3 - Jan 4)...");

        boolean result1 = testRoom.isAvailable(
                LocalDate.of(2025, 1, 3),
                LocalDate.of(2025, 1, 4)
        );

        if (result1 == false) {
            System.out.println("[SUCCESS] System returned 'false' as expected. (Room is occupied)");
        } else {
            System.out.println("[ERROR] System returned 'true'. Date overlap was not detected!");
        }

        System.out.println("\nScenario 2: Checking available dates (Jan 6 - Jan 8)...");

        boolean result2 = testRoom.isAvailable(
                LocalDate.of(2025, 1, 6),
                LocalDate.of(2025, 1, 8)
        );

        if (result2 == true) {
            System.out.println("[SUCCESS] System returned 'true' as expected. (Room is available)");
        } else {
            System.out.println("[ERROR] System returned 'false'. Room should be available!");
        }

        System.out.println("\n--- Test Completed ---");
    }
}