Ride Sharing App (Console-Based, Java)

A mini ride-sharing console application (like Uber or Ola) built using Java OOP concepts.
This project demonstrates Encapsulation, Inheritance, Abstraction, Polymorphism, and Interface usage in a real-world scenario.

## Overview

This app allows a Rider to:

* View available drivers
* Book a ride (Auto / Hatchback / Sedan)
* View trip history

When a ride is booked, a trip is simulated using a background thread (anonymous class).

## Technologies Used

Language: Java (JDK 8 or higher)
Concepts: OOP, Interface, Abstract Class, Polymorphism, Encapsulation, Threads (Anonymous Class)
Environment: Console / Terminal

## Features

1. Vehicle Hierarchy - Auto, Hatchback, and Sedan inherit from an abstract Vehicle class implementing the FareCalculator interface.
2. Dynamic Fare Calculation - Each vehicle type has a different fare rate per kilometer.
3. Encapsulated Entities - Rider, Driver, and Trip classes maintain encapsulation.
4. Anonymous Class - Used to simulate trip progress using a background thread.
5. Interactive Console Menu - Simple functional CLI interface.

## Class Structure

Interface:

* FareCalculator (defines the method calculateFare(double distance))

Abstract Class:

* Vehicle (base class for all vehicle types)

Concrete Classes:

* Auto, Hatchback, Sedan (implement custom fare logic)

Entity Classes:

* Rider, Driver, Trip

Service Class:

* RideService (handles driver management, booking, and trip simulation)

Main Class:

* RideSharingApp (contains the main menu and drives the application)

## Sample Console Flow

=== Welcome to Ride Sharing App ===

1. Show Available Drivers
2. Book Ride
3. View All Trips
4. Exit
   Enter your choice: 2
   Enter your name: Vicky
   Enter source: Pune
   Enter destination: Mumbai
   Enter distance (km): 120

===== TRIP DETAILS =====
Rider: Vicky
Driver: Rajesh
Vehicle: Auto
From: Pune  To: Mumbai
Distance: 120.0 km
Total Fare: ₹1200.0

Trip started for Rajesh
Trip completed! Total Fare: ₹1200.0

---

## OOP Concepts Demonstrated

Abstraction: Vehicle is an abstract class, subclasses define calculateFare().
Interface: FareCalculator ensures all vehicles implement fare calculation.
Encapsulation: Private attributes with getters/setters in Rider, Driver, and Trip.
Inheritance: Auto, Hatchback, and Sedan extend Vehicle.
Polymorphism: Fare calculation varies depending on vehicle type.
Anonymous Class: Used for simulating ride progress (thread).

## Future Enhancements

* Add user authentication (login/signup)
* Real-time driver availability updates
* Store trip history in a file or database
* Implement dynamic driver assignment based on vehicle type
