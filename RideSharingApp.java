import java.util.*;

// ========== INTERFACE ==========
interface FareCalculator {
    double calculateFare(double distance);
}

// ========== ABSTRACT CLASS ==========
abstract class Vehicle implements FareCalculator {
    protected String vehicleNumber;
    protected String driverName;

    public Vehicle(String vehicleNumber, String driverName) {
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
    }

    public abstract String getType();

    public void displayInfo() {
        System.out.println("Vehicle Type: " + getType());
        System.out.println("Driver Name: " + driverName);
        System.out.println("Vehicle Number: " + vehicleNumber);
    }
}

// ========== CONCRETE CLASSES ==========
class Auto extends Vehicle {
    public Auto(String vehicleNumber, String driverName) {
        super(vehicleNumber, driverName);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 10; // ₹10 per km
    }

    @Override
    public String getType() {
        return "Auto";
    }
}

class Hatchback extends Vehicle {
    public Hatchback(String vehicleNumber, String driverName) {
        super(vehicleNumber, driverName);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 15; // ₹15 per km
    }

    @Override
    public String getType() {
        return "Hatchback";
    }
}

class Sedan extends Vehicle {
    public Sedan(String vehicleNumber, String driverName) {
        super(vehicleNumber, driverName);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 20; // ₹20 per km
    }

    @Override
    public String getType() {
        return "Sedan";
    }
}

// ========== RIDER CLASS ==========
class Rider {
    private String name;

    public Rider(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// ========== DRIVER CLASS ==========
class Driver {
    private String name;
    private Vehicle vehicle;
    private boolean available;

    public Driver(String name, Vehicle vehicle) {
        this.name = name;
        this.vehicle = vehicle;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

// ========== TRIP CLASS ==========
class Trip {
    private Rider rider;
    private Driver driver;
    private double distance;
    private double fare;
    private String source;
    private String destination;

    public Trip(Rider rider, Driver driver, double distance, String source, String destination) {
        this.rider = rider;
        this.driver = driver;
        this.distance = distance;
        this.source = source;
        this.destination = destination;
        this.fare = driver.getVehicle().calculateFare(distance);
    }

    public void displayTripDetails() {
        System.out.println("\n===== TRIP DETAILS =====");
        System.out.println("Rider: " + rider.getName());
        System.out.println("Driver: " + driver.getName());
        System.out.println("Vehicle: " + driver.getVehicle().getType());
        System.out.println("From: " + source + "  To: " + destination);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Total Fare: ₹" + fare);
    }

    public double getFare() {
        return fare;
    }

    public Driver getDriver() {
        return driver;
    }
}

// ========== RIDE SERVICE CLASS ==========
class RideService {
    private List<Driver> drivers = new ArrayList<>();
    private List<Trip> trips = new ArrayList<>();

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public void showAvailableDrivers() {
        System.out.println("\n=== Available Drivers ===");
        for (Driver d : drivers) {
            if (d.isAvailable()) {
                System.out.println("- " + d.getName() + " (" + d.getVehicle().getType() + ")");
            }
        }
    }

    public Trip bookRide(Rider rider, double distance, String source, String destination) {
        for (Driver d : drivers) {
            if (d.isAvailable()) {
                d.setAvailable(false);
                Trip trip = new Trip(rider, d, distance, source, destination);
                trips.add(trip);
                simulateRide(trip); // Start the ride in a new thread (Anonymous class)
                return trip;
            }
        }
        System.out.println(" No drivers available right now!");
        return null;
    }

    // Demonstrating Anonymous Class for simulating trip progress
    private void simulateRide(Trip trip) {
        Runnable rideTask = new Runnable() {
            public void run() {
                System.out.println("\n🚗 Trip started for " + trip.getDriver().getName());
                try {
                    Thread.sleep(2000); // simulate trip duration
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("✅ Trip completed! Total Fare: ₹" + trip.getFare());
                trip.getDriver().setAvailable(true);
            }
        };
        new Thread(rideTask).start();
    }

    public void showAllTrips() {
        System.out.println("\n=== All Trips ===");
        if (trips.isEmpty()) {
            System.out.println("No trips completed yet!");
        } else {
            for (Trip t : trips) {
                t.displayTripDetails();
            }
        }
    }
}

// ========== MAIN CLASS ==========
public class RideSharingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RideService rideService = new RideService();

        // Add some default drivers
        rideService.addDriver(new Driver("Rajesh", new Auto("MH12AB1234", "Rajesh")));
        rideService.addDriver(new Driver("Anita", new Hatchback("MH14XY5678", "Anita")));
        rideService.addDriver(new Driver("Vikram", new Sedan("MH16ZZ9999", "Vikram")));

        System.out.println("=== Welcome to Ride Sharing App ===");

        while (true) {
            System.out.println("\n1. Show Available Drivers");
            System.out.println("2. Book Ride");
            System.out.println("3. View All Trips");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");1
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    rideService.showAvailableDrivers();
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    Rider rider = new Rider(name);

                    System.out.print("Enter source: ");
                    String source = sc.nextLine();
                    System.out.print("Enter destination: ");
                    String destination = sc.nextLine();
                    System.out.print("Enter distance (km): ");
                    double distance = sc.nextDouble();

                    Trip trip = rideService.bookRide(rider, distance, source, destination);
                    if (trip != null) {
                        trip.displayTripDetails();
                    }
                    break;

                case 3:
                    rideService.showAllTrips();
                    break;

                case 4:
                    System.out.println("Thank you for using Ride Sharing App!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

