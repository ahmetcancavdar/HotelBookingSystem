# Hotel Booking System
This project was created with İbrahim Alp Karınca.
## Overview
Hotel Booking System is a simple console-based application developed in Java to simulate basic hotel reservation operations. The project is designed using object-oriented programming principles and models real-world entities such as rooms, guests, staff, and reservations. Its main goal is to demonstrate class design, responsibility separation, and date-based availability logic in Java.

## Features
The system allows managing hotel rooms and handling reservations in a structured way. Users can check room availability for specific date ranges, create reservations with check-in and check-out dates, and prevent overlapping bookings. Both guests and staff members can perform booking operations through the central system logic. The project also includes a basic test class to validate room availability behavior.

## Project Structure
The project consists of the following main classes:
- `Main.java` – Entry point of the application, demonstrates basic usage
- `HotelBookingSystem.java` – Core system logic that manages rooms and reservations
- `Room.java` – Represents a hotel room and contains availability checks
- `Reservation.java` – Stores reservation details such as dates and status
- `Guest.java` – Represents a guest who can make reservations
- `Staff.java` – Represents staff who can book rooms on behalf of guests
- `RoomTest.java` – Tests room availability logic with sample scenarios

## Class Responsibilities
HotelBookingSystem acts as the central manager, storing rooms and reservations while coordinating availability checks and booking operations. Room objects store room-specific information and determine whether a room is available for a given date range. Reservation objects link guests to rooms with defined check-in and check-out dates. Guest and Staff classes represent different actors that can initiate reservations. The Main class runs the application, and RoomTest verifies correctness of the availability logic.

## Technologies Used
- Java (JDK 8 or later)
- Object-Oriented Programming (OOP)
- Java Time API (`LocalDate`)
- Console-based input/output

