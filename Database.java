
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import MANAGEMENT.payment_details;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9167909@mysql";

    private Connection connection = null;
    private Statement statement = null;

    public Database() {

    }

    public boolean doesCustomerIdExist(int customerId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);

            String checkIdQuery = "SELECT COUNT(*) FROM Customer WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkIdQuery)) {
                preparedStatement.setInt(1, customerId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);

                    return count > 0; // Return true if the ID exists, false otherwise
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean doesCustomerExist(int id, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Check if customer with given id and password exists
            String checkCustomerQuery = "SELECT COUNT(*) FROM customer WHERE id = ? AND password = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(checkCustomerQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);

                    return count > 0; // Return true if the customer exists, false otherwise
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            try {
                // Close resources in the reverse order of their creation to avoid potential
                // issues
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertCustomer(Customer customer) {
        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a connection to MySQL server
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Step 3: Create a statement
            statement = connection.createStatement();

            // Step 4: Create a new database
            String dbName = "Project";
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + dbName;
            statement.executeUpdate(createDatabaseQuery);

            // Step 5: Switch to the created database
            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);

            // Step 6: Create a new table "Customer" if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Customer ("
                    + "id INT PRIMARY KEY, "
                    + "password VARCHAR(255), "
                    + "name VARCHAR(255), "
                    + "cnic VARCHAR(255), "
                    + "age INT, "
                    + "address VARCHAR(255), "
                    + "contact_no VARCHAR(255))";
            statement.executeUpdate(createTableQuery);

            // Step 7: Insert values into the "Customer" table
            String insertDataQuery = "INSERT INTO Customer (id, password, name, cnic, age, address, contact_no) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getCnic());
            preparedStatement.setInt(5, customer.getAge());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setString(7, customer.getContact_no());

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close resources in the reverse order of their creation to avoid potential
                // issues
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkAdminCredentials(Admin A) {
        int adminId = A.getId();
        String adminPassword = A.getpassword();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Check if admin credentials are correct
            String checkAdminQuery = "SELECT COUNT(*) FROM admin WHERE id = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkAdminQuery)) {
                preparedStatement.setInt(1, adminId);
                preparedStatement.setString(2, adminPassword);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);

                    return count > 0; // Return true if the credentials are correct, false otherwise
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            try {
                // Close resources in the reverse order of their creation to avoid potential
                // issues
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertFlight(flight newFlight) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Create a new table "Flight" if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Flight ("
                    + "flight_no INT PRIMARY KEY, "
                    + "planeid INT, "
                    + "arrival_time VARCHAR(10), "
                    + "departure_time VARCHAR(10), "
                    + "arrival_airport VARCHAR(50), "
                    + "departure_airport VARCHAR(50), "
                    + "available_seats INT,"
                    + "fare INT)";

            statement.executeUpdate(createTableQuery);

            // Insert values into the "Flight" table
            String insertDataQuery = "INSERT INTO Flight (flight_no, planeid, arrival_time, departure_time, arrival_airport, departure_airport , available_seats,fare) VALUES (?, ?, ?, ?, ?, ?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
                preparedStatement.setInt(1, newFlight.getFlightNo());
                preparedStatement.setInt(2, newFlight.getPlaneId()); // Corrected order
                preparedStatement.setString(3, newFlight.getArrivalTime());
                preparedStatement.setString(4, newFlight.getDepartureTime());
                preparedStatement.setString(5, newFlight.getArrivalAirportID());
                preparedStatement.setString(6, newFlight.getDepartureAirportID());
                preparedStatement.setInt(7, newFlight.getAvailable_seats());
                preparedStatement.setInt(8, newFlight.getFare());

                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close resources in the reverse order of their creation to avoid potential
                // issues
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean doesFlightNumberExist(int flightNumber) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Check if flight number already exists
            String checkFlightQuery = "SELECT COUNT(*) FROM Flight WHERE flight_no = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkFlightQuery)) {
                preparedStatement.setInt(1, flightNumber);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);

                    return count > 0; // Return true if the flight number exists, false otherwise
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Log the error or throw a custom exception
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            // Close resources
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // Log the error or throw a custom exception
            e.printStackTrace();
        }
    }

    public void deleteFlight(int flightId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Retrieve plane_id before deleting the flight
            String getPlaneIdQuery = "SELECT planeid FROM Flight WHERE flight_no = ?";
            try (PreparedStatement getPlaneIdStatement = connection.prepareStatement(getPlaneIdQuery)) {
                getPlaneIdStatement.setInt(1, flightId);

                try (ResultSet resultSet = getPlaneIdStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int plane_idd = resultSet.getInt("planeid");

                        // Delete the flight with the specified ID from the Flight table
                        String deleteFlightQuery = "DELETE FROM Flight WHERE flight_no = ?";
                        try (PreparedStatement deleteFlightStatement = connection.prepareStatement(deleteFlightQuery)) {
                            deleteFlightStatement.setInt(1, flightId);
                            int rowsAffected = deleteFlightStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                String availability = "available";
                                updateAvailability(plane_idd, availability);
                                System.out.println("Flight with ID " + flightId + " deleted successfully.");
                            } else {
                                System.out.println("No flight found with ID " + flightId + ". Deletion failed.");
                            }
                        }
                    } else {
                        System.out.println("No flight found with ID " + flightId + ". Deletion failed.");
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void updateFlight(flight updatedFlight) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Update the Flight table with the new flight details
            String updateFlightQuery = "UPDATE Flight SET arrival_time=?, departure_time=?, planeid=?, arrival_airport=?, departure_airport=? ,fare=? WHERE flight_no=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateFlightQuery)) {
                preparedStatement.setString(1, updatedFlight.getArrivalTime());
                preparedStatement.setString(2, updatedFlight.getDepartureTime());
                preparedStatement.setInt(3, updatedFlight.getPlaneId());
                preparedStatement.setString(4, updatedFlight.getArrivalAirportID());
                preparedStatement.setString(5, updatedFlight.getDepartureAirportID());
                preparedStatement.setInt(6, updatedFlight.getFare());
                preparedStatement.setInt(7, updatedFlight.getFlightNo());

                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void insertAirplane(plane newAirplane) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);
            String availibility = "available";
            String refueling = "not required";

            // Create a new table "Airplane" if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Airplane ("
                    + "plane_id INT PRIMARY KEY, "
                    + "seating_capacity INT, "
                    + "airplane_type VARCHAR(255),"
                    + "availibility VARCHAR(50),"
                    + "refueling VARCHAR(50))";
            statement.executeUpdate(createTableQuery);

            // Insert values into the "Airplane" table
            String insertDataQuery = "INSERT INTO Airplane (plane_id, seating_capacity, airplane_type, availibility, refueling) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
                preparedStatement.setInt(1, newAirplane.getPlane_id());
                preparedStatement.setInt(2, newAirplane.getSeats());
                preparedStatement.setString(3, newAirplane.gettype());
                preparedStatement.setString(4, availibility);
                preparedStatement.setString(5, refueling);

                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void insertComplaint(ComplaintFile complaint) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Create a new table "ComplaintFile" if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS ComplaintFile ("
                    + "complaint_id INT PRIMARY KEY, "
                    + "complainant_id INT, "
                    + "details TEXT, "
                    + "status VARCHAR(255))";
            try (Statement createTableStatement = connection.createStatement()) {
                createTableStatement.executeUpdate(createTableQuery);
            }

            // Insert values into the "ComplaintFile" table
            String insertDataQuery = "INSERT INTO ComplaintFile (complaint_id, complainant_id, details, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
                preparedStatement.setInt(1, complaint.getComplaintId());
                preparedStatement.setInt(2, complaint.getComplainantId());
                preparedStatement.setString(3, complaint.getDetails());
                preparedStatement.setString(4, complaint.getStatus());

                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getMaxBookingId() {
        int maxBookingId = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Retrieve the maximum booking ID
            String getMaxBookingIdQuery = "SELECT MAX(booking_id) FROM booking";

            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(getMaxBookingIdQuery)) {
                    if (resultSet.next()) {
                        maxBookingId = resultSet.getInt(1);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return maxBookingId + 1; // Increment the maxBookingId by 1 for the new booking
    }

    public int getMaxComplaintId() {
        int maxComplaintId = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Retrieve the maximum complaint ID
            String getMaxComplaintIdQuery = "SELECT MAX(complaint_id) FROM ComplaintFile";

            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(getMaxComplaintIdQuery)) {
                    if (resultSet.next()) {
                        maxComplaintId = resultSet.getInt(1);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return maxComplaintId;

    }

    public boolean doesAirplaneExist(int planeId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);

            String checkIdQuery = "SELECT COUNT(*) FROM Airplane WHERE plane_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkIdQuery)) {
                preparedStatement.setInt(1, planeId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);

                    return count > 0; // Return true if the ID exists, false otherwise
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            closeResources();
        }
    }

    public void deleteAirplane(int planeId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Delete the airplane with the specified ID from the Airplane table
            String deleteAirplaneQuery = "DELETE FROM Airplane WHERE plane_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteAirplaneQuery)) {
                preparedStatement.setInt(1, planeId);
                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public boolean isAirplaneAvailable(int planeId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);
            String checkAvailabilityQuery = "SELECT availibility FROM Airplane WHERE plane_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkAvailabilityQuery)) {
                preparedStatement.setInt(1, planeId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String availability = resultSet.getString("availibility");
                        return "available".equalsIgnoreCase(availability);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isRefuelingRequired(int planeId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);
            String checkRefuelingQuery = "SELECT refueling FROM Airplane WHERE plane_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkRefuelingQuery)) {
                preparedStatement.setInt(1, planeId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String refueling = resultSet.getString("refueling");
                        return "required".equalsIgnoreCase(refueling);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<flight> getFlightsByAirports(String fromAirport, String toAirport) {
        ObservableList<flight> flights = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            String getFlightsQuery = "SELECT flight_no, planeid, arrival_time, departure_time, arrival_airport, departure_airport, available_seats, fare FROM Flight WHERE arrival_airport = ? AND departure_airport = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(getFlightsQuery)) {
                preparedStatement.setString(1, toAirport);
                preparedStatement.setString(2, fromAirport);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int flightNo = resultSet.getInt("flight_no");
                        int planeId = resultSet.getInt("planeid");
                        String arrivalTime = resultSet.getString("arrival_time");
                        String departureTime = resultSet.getString("departure_time");
                        String arrivalAirport = resultSet.getString("arrival_airport");
                        String departureAirport = resultSet.getString("departure_airport");
                        int available_seats = resultSet.getInt("available_seats");
                        int fare_seats = resultSet.getInt("fare");

                        flight flight = new flight(flightNo, arrivalTime, departureTime, planeId, arrivalAirport,
                                departureAirport, available_seats, fare_seats);
                        flights.add(flight);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }

        return flights;
    }

    public boolean insertBooking(booking booking) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Create a new table "Booking" if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS booking ("
                    + "booking_id INT PRIMARY KEY, "
                    + "customer_id INT, "
                    + "flight_id INT, "
                    + "no_of_seats INT,"
                    + "amount INT)";

            statement.executeUpdate(createTableQuery);

            // Insert values into the "Booking" table
            String insertDataQuery = "INSERT INTO booking (booking_id, customer_id, flight_id, no_of_seats, amount) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
                preparedStatement.setInt(1, booking.getBookingID());
                preparedStatement.setInt(2, booking.getCustomer_id());
                preparedStatement.setInt(3, booking.getFligth_id());
                preparedStatement.setInt(4, booking.getNo_of_seats());
                preparedStatement.setInt(5, booking.getAmount());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    public List<String> getAirportLocations() {
        List<String> locations = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Query to select distinct locations from the Airport table
            String selectLocationsQuery = "SELECT DISTINCT location FROM Airport";
            try (ResultSet resultSet = statement.executeQuery(selectLocationsQuery)) {
                while (resultSet.next()) {
                    String location = resultSet.getString("location");
                    locations.add(location);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return locations;
    }

    public List<booking> getBookings() {
        List<booking> bookings = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            String getBookingsQuery = "SELECT * FROM Booking";
            try (PreparedStatement preparedStatement = connection.prepareStatement(getBookingsQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("booking_id");
                        int customerID = resultSet.getInt("customer_id");
                        int flightID = resultSet.getInt("flight_id");
                        int noOfSeats = resultSet.getInt("no_of_seats");
                        int amount = resultSet.getInt("amount");

                        booking booking = new booking(bookingID, customerID, flightID, noOfSeats, amount);

                        bookings.add(booking);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }

        return bookings;
    }

    public int getNumberOfSeatsByPlaneId(int planeId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            String getSeatsQuery = "SELECT seating_capacity FROM Airplane WHERE plane_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(getSeatsQuery)) {
                preparedStatement.setInt(1, planeId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("seating_capacity");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return -1; // Return a default value or handle error as needed
    }

    public int getAvailableSeatsByFlightId(int flightId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            String getSeatsQuery = "SELECT available_seats FROM Flight WHERE flight_no = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(getSeatsQuery)) {
                preparedStatement.setInt(1, flightId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("available_seats");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return -1; // Return a default value or handle error as needed
    }

    public void updateAvailableSeats(int flightId, int bookedSeats) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            String updateSeatsQuery = "UPDATE Flight SET available_seats = ? WHERE flight_no = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSeatsQuery)) {
                preparedStatement.setInt(1, bookedSeats);
                preparedStatement.setInt(2, flightId);
                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public ObservableList<ComplaintFile> getComplaints(int accountId) {
        ObservableList<ComplaintFile> complaints = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Retrieve complaint data for the logged-in account from the database
            String getComplaintsQuery = "SELECT * FROM ComplaintFile WHERE complainant_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(getComplaintsQuery)) {
                preparedStatement.setInt(1, accountId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int complaintId = resultSet.getInt("complaint_id");
                        int complainantId = resultSet.getInt("complainant_id");
                        String details = resultSet.getString("details");
                        String status = resultSet.getString("status");

                        ComplaintFile complaint = new ComplaintFile(complaintId, complainantId, details, status);
                        complaints.add(complaint);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return complaints;
    }

    public ObservableList<ComplaintFile> getComplaints_admin() {
        ObservableList<ComplaintFile> complaints = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Retrieve complaint data for the logged-in account from the database
            String getComplaintsQuery = "SELECT * FROM ComplaintFile";

            try (PreparedStatement preparedStatement = connection.prepareStatement(getComplaintsQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int complaintId = resultSet.getInt("complaint_id");
                        int complainantId = resultSet.getInt("complainant_id");
                        String details = resultSet.getString("details");
                        String status = resultSet.getString("status");

                        ComplaintFile complaint = new ComplaintFile(complaintId, complainantId, details, status);
                        complaints.add(complaint);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return complaints;
    }

    public ObservableList<Baggage> getbaggagesby_baggagetag(String baggageTag) {
        ObservableList<Baggage> baggages = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Retrieve baggage data for the specified baggage tag from the database
            String getBaggagesQuery = "SELECT * FROM Baggage WHERE baggageTag = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(getBaggagesQuery)) {
                preparedStatement.setString(1, baggageTag);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {

                        int ownerId = resultSet.getInt("ownerId");
                        double weight = resultSet.getDouble("weight");
                        String location = resultSet.getString("location");
                        String status = resultSet.getString("status");

                        Baggage baggage = new Baggage(baggageTag, ownerId, weight, location, status);
                        baggages.add(baggage);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return baggages;
    }

    public ObservableList<Baggage> getBaggagesByOwnerId(int ownerId) {
        ObservableList<Baggage> baggages = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Retrieve baggage data for the specified owner ID from the database
            String getBaggagesQuery = "SELECT * FROM Baggage WHERE ownerId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(getBaggagesQuery)) {
                preparedStatement.setInt(1, ownerId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String baggageTag = resultSet.getString("baggageTag");
                        double weight = resultSet.getDouble("weight");
                        String location = resultSet.getString("location");
                        String status = resultSet.getString("status");

                        Baggage baggage = new Baggage(baggageTag, ownerId, weight, location, status);
                        baggages.add(baggage);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return baggages;
    }

    public void insertLostItem(LostItem lostItem) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Create a new table "LostItem" if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS LostItem ("
                    + "report_no INT PRIMARY KEY, "
                    + "complainant_id INT, "
                    + "item_type VARCHAR(255), "
                    + "item_description TEXT, "
                    + "unique_identifier VARCHAR(255), "
                    + "last_seen DATE, "
                    + "location VARCHAR(255))";
            try (Statement createTableStatement = connection.createStatement()) {
                createTableStatement.executeUpdate(createTableQuery);
            }

            // Insert values into the "LostItem" table
            String insertDataQuery = "INSERT INTO LostItem (report_no, complainant_id, item_type, item_description, unique_identifier, last_seen, location) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
                preparedStatement.setInt(1, lostItem.getReportNo());
                preparedStatement.setInt(2, lostItem.getcomplainantid());
                preparedStatement.setString(3, lostItem.getItemType());
                preparedStatement.setString(4, lostItem.getItemDescription());
                preparedStatement.setString(5, lostItem.getUniqueIdentifier());
                preparedStatement.setDate(6, new java.sql.Date(lostItem.getLastSeen().getTime()));
                preparedStatement.setString(7, lostItem.getLocation());

                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getMaxReportNo() {
        int maxReportNo = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Retrieve the maximum report number
            String getMaxReportNoQuery = "SELECT MAX(report_no) FROM LostItem";

            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(getMaxReportNoQuery)) {
                    if (resultSet.next()) {
                        maxReportNo = resultSet.getInt(1);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return maxReportNo;
    }

    public ObservableList<LostItem> getLostItemsByOwnerId(int complainant_id) {
        ObservableList<LostItem> lostItems = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Retrieve lost item data for the specified owner ID from the database
            String getLostItemsQuery = "SELECT * FROM LostItem WHERE complainant_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(getLostItemsQuery)) {
                preparedStatement.setInt(1, complainant_id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int reportNo = resultSet.getInt("report_no");
                        String itemType = resultSet.getString("item_type");
                        String itemDescription = resultSet.getString("item_description");
                        String uniqueIdentifier = resultSet.getString("unique_identifier");
                        Date lastSeen = resultSet.getDate("last_seen");
                        String location = resultSet.getString("location");

                        LostItem lostItem = new LostItem(reportNo, complainant_id, itemType, itemDescription,
                                uniqueIdentifier,
                                lastSeen, location);
                        lostItems.add(lostItem);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lostItems;
    }

    public void insertAirport(Airport airport) {
        try {
            // Load JDBC driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // Select the database
            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);

            // Create the "Airport" table if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Airport ("
                    + "id INT PRIMARY KEY, "
                    + "location VARCHAR(255))";
            statement.executeUpdate(createTableQuery);

            String insertDataQuery = "INSERT INTO Airport (id, location) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
                preparedStatement.setInt(1, airport.getAirport_id());
                preparedStatement.setString(2, airport.getName());

                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public boolean isAirportIdPresent(int airportId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // Select the database
            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);

            // Check if the airport with the given ID already exists
            String checkAirportQuery = "SELECT id FROM Airport WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkAirportQuery)) {
                preparedStatement.setInt(1, airportId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // Returns true if a row is present, indicating that the airport ID exists
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return false;
    }

    public boolean deleteAirportById(int airportId) {
        try {
            // Check if the airport ID exists in the database
            if (!isAirportIdPresent(airportId)) {
                return false; // Airport not found in the database
            }

            // Delete the row with the specified airport ID
            String deleteQuery = "DELETE FROM Airport WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, airportId);
                preparedStatement.executeUpdate();
            }

            return true; // Airport deleted successfully

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred during deletion
        } finally {
            closeResources();
        }
    }

    public void deleteBooking(int flightId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Delete the booking based on the flight ID
            String deleteBookingQuery = "DELETE FROM booking WHERE flight_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookingQuery)) {
                preparedStatement.setInt(1, flightId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Booking deleted successfully
                    System.out.println("Booking for flight with ID " + flightId + " deleted successfully.");
                } else {
                    // No booking found with the specified flight ID
                    System.out.println("No booking found for flight with ID " + flightId + ".");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public ObservableList<flight> getFlightsByAirport() {
        ObservableList<flight> flights = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            String getFlightsQuery = "SELECT flight_no, planeid, arrival_time, departure_time, arrival_airport, departure_airport, available_seats, fare FROM Flight";

            try (PreparedStatement preparedStatement = connection.prepareStatement(getFlightsQuery)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int flightNo = resultSet.getInt("flight_no");
                        int planeId = resultSet.getInt("planeid");
                        String arrivalTime = resultSet.getString("arrival_time");
                        String departureTime = resultSet.getString("departure_time");
                        String arrivalAirport = resultSet.getString("arrival_airport");
                        String departureAirport = resultSet.getString("departure_airport");
                        int available_seats = resultSet.getInt("available_seats");
                        int fare_seats = resultSet.getInt("fare");

                        flight flight = new flight(flightNo, arrivalTime, departureTime, planeId, arrivalAirport,
                                departureAirport, available_seats, fare_seats);
                        flights.add(flight);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }

        return flights;
    }

    public int getFlightFare(int flightId) {
        int fare = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Retrieve the fare based on the flight ID
            String getFareQuery = "SELECT fare FROM flight WHERE flight_no=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(getFareQuery)) {
                preparedStatement.setInt(1, flightId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        fare = resultSet.getInt("fare");
                    } else {
                        // Handle the case where no fare is found for the specified flight ID
                        System.out.println("No fare found for flight with ID " + flightId);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return fare;
    }

    public boolean doesComplaintIdExist(int complaintId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Check if the complaint ID exists in the "Complaint" table
            String checkComplaintQuery = "SELECT COUNT(*) FROM Complaintfile WHERE complaint_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkComplaintQuery)) {
                preparedStatement.setInt(1, complaintId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);

                    return count > 0; // Return true if the ID exists, false otherwise
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            closeResources();
        }
    }

    public boolean updateComplaintStatus(int complaintId, String newStatus) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Update the status of the complaint with the specified ID
            String updateComplaintQuery = "UPDATE complaintfile SET status = ? WHERE complaint_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateComplaintQuery)) {
                preparedStatement.setString(1, newStatus);
                preparedStatement.setInt(2, complaintId);

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0; // Return true if the update was successful, false otherwise
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            closeResources();
        }
    }

    public void insertBaggage(Baggage baggage) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Create the Baggage table if it doesn't exist
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Baggage (" +
                    "baggageTag VARCHAR(255) PRIMARY KEY," +
                    "ownerId INT," +
                    "weight DOUBLE," +
                    "location VARCHAR(255)," +
                    "status VARCHAR(255)" +
                    ")";

            try (PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery)) {
                createTableStatement.executeUpdate();
            }

            // Insert the Baggage object into the database
            String insertBaggageQuery = "INSERT INTO Baggage (baggageTag, ownerId, weight, location, status) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement insertStatement = connection.prepareStatement(insertBaggageQuery)) {
                insertStatement.setString(1, baggage.getBaggageTag());
                insertStatement.setInt(2, baggage.getOwnerId());
                insertStatement.setDouble(3, baggage.getWeight());
                insertStatement.setString(4, baggage.getLocation());
                insertStatement.setString(5, baggage.getStatus());

                insertStatement.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }
    }

    public boolean baggageIdExists(String baggageId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // SQL query to check if the baggage ID already exists
            String query = "SELECT * FROM Baggage WHERE baggageTag = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, baggageId);
                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next(); // If resultSet.next() is true, ID exists; otherwise, it does not
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle SQL exception
            return false; // Return false in case of an exception
        } finally {
            // Close resources in the finally block
            closeResources();
        }
    }

    public boolean insertPayment(payment paymentObj) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Create a new table "Payment" if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Payment ("
                    + "payment_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "book_id INT, "
                    + "customer_id INT, "
                    + "amount INT, "
                    + "payment_status VARCHAR(10))";

            statement.executeUpdate(createTableQuery);

            // Insert values into the "Payment" table
            String insertDataQuery = "INSERT INTO Payment (book_id, customer_id, amount, payment_status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery,
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, paymentObj.getBook_id());
                preparedStatement.setInt(2, paymentObj.getCustomer_id());
                preparedStatement.setInt(3, paymentObj.getAmount());
                preparedStatement.setString(4, paymentObj.getPayment_status());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    public List<payment> retrievePaymentsForCustomer(int customerId) {
        List<payment> payments = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Assuming you have a method to retrieve payments for a specific customer
            String retrievePaymentsQuery = "SELECT * FROM Payment WHERE customer_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(retrievePaymentsQuery)) {
                preparedStatement.setInt(1, customerId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int paymentId = resultSet.getInt("payment_id");
                    int bookId = resultSet.getInt("book_id");
                    int amount = resultSet.getInt("amount");
                    String paymentStatus = resultSet.getString("payment_status");

                    payment paymentObj = new payment(bookId, customerId, amount, paymentStatus);
                    paymentObj.setpaymentId(paymentId);
                    payments.add(paymentObj);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return payments;
    }

    public boolean doesPaymentIdExist(int paymentId, int customerId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            String checkPaymentIdQuery = "SELECT COUNT(*) FROM Payment WHERE payment_id = ? AND customer_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkPaymentIdQuery)) {
                preparedStatement.setInt(1, paymentId);
                preparedStatement.setInt(2, customerId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    public int getAmountByPaymentIdAndCustomerId(int paymentId, int customerId) {
        int amount = -1; // Default value, assuming -1 indicates an invalid or non-existent record

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Select the amount from the Payment table based on payment ID and customer ID
            String selectAmountQuery = "SELECT amount FROM Payment WHERE payment_id = ? AND customer_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectAmountQuery)) {
                preparedStatement.setInt(1, paymentId);
                preparedStatement.setInt(2, customerId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // If a record is found, retrieve the amount value
                    amount = resultSet.getInt("amount");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return amount;
    }

    public boolean insertPaymentDetailsAndUpdateStatus(payment_details paymentDetails) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Create a new table "PaymentDetails" if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS PaymentDetails ("
                    + "payment_id INT PRIMARY KEY, "
                    + "customer_id INT, "
                    + "amount INT, "
                    + "card_no INT, "
                    + "cvv INT, "
                    + "card_expiry DATE, "
                    + "FOREIGN KEY (payment_id) REFERENCES Payment(payment_id))";
            statement.executeUpdate(createTableQuery);

            // Insert values into the "PaymentDetails" table
            String insertDataQuery = "INSERT INTO PaymentDetails (payment_id, customer_id, amount, card_no, cvv, card_expiry) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
                preparedStatement.setInt(1, paymentDetails.getPayment_id());
                preparedStatement.setInt(2, paymentDetails.getCustomer_id());
                preparedStatement.setInt(3, paymentDetails.getAmount());
                preparedStatement.setInt(4, paymentDetails.getCard_no());
                preparedStatement.setInt(5, paymentDetails.getCvv());
                preparedStatement.setDate(6, new java.sql.Date(paymentDetails.getCard_expiry().getTime()));

                preparedStatement.executeUpdate();
            }

            // Update payment_status in the "Payment" table
            String updateStatusQuery = "UPDATE Payment SET payment_status = 'PAID' WHERE payment_id = ? AND customer_id = ?";
            try (PreparedStatement updateStatusStatement = connection.prepareStatement(updateStatusQuery)) {
                updateStatusStatement.setInt(1, paymentDetails.getPayment_id());
                updateStatusStatement.setInt(2, paymentDetails.getCustomer_id());

                int rowsAffected = updateStatusStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;

    }

    public boolean isPaymentUnpaid(int paymentId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Query to check if payment is unpaid
            String checkStatusQuery = "SELECT payment_status FROM Payment WHERE payment_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkStatusQuery)) {
                preparedStatement.setInt(1, paymentId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String paymentStatus = resultSet.getString("payment_status");
                    return !"PAID".equals(paymentStatus);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    public boolean deleteBookingAndUpdateSeats(int bookingID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Retrieve the flight ID and number of seats for the booking being deleted
            String getBookingInfoQuery = "SELECT flight_id, no_of_seats FROM Booking WHERE booking_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(getBookingInfoQuery)) {
                preparedStatement.setInt(1, bookingID);
                ResultSet resultSet = preparedStatement.executeQuery();

                int flightID = 0;
                int seatsToFree = 0;

                if (resultSet.next()) {
                    flightID = resultSet.getInt("flight_id");
                    seatsToFree = resultSet.getInt("no_of_seats");
                }

                // Delete the booking
                String deleteBookingQuery = "DELETE FROM Booking WHERE booking_id = ?";
                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteBookingQuery)) {
                    deleteStatement.setInt(1, bookingID);
                    int rowsAffected = deleteStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Update available seats in the Flight table
                        String updateSeatsQuery = "UPDATE Flight SET available_seats = available_seats + ? WHERE flight_no = ?";
                        try (PreparedStatement updateSeatsStatement = connection.prepareStatement(updateSeatsQuery)) {
                            updateSeatsStatement.setInt(1, seatsToFree);
                            updateSeatsStatement.setInt(2, flightID);
                            updateSeatsStatement.executeUpdate();
                        }

                        return true;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    public List<booking> getBookingsofcustomer(int customerId) {
        List<booking> bookings = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Use a WHERE clause to filter bookings by customer ID
            String getBookingsQuery = "SELECT * FROM Booking WHERE customer_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(getBookingsQuery)) {
                preparedStatement.setInt(1, customerId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("booking_id");
                        int customerID = resultSet.getInt("customer_id");
                        int flightID = resultSet.getInt("flight_id");
                        int noOfSeats = resultSet.getInt("no_of_seats");
                        int amount = resultSet.getInt("amount");

                        booking booking = new booking(bookingID, customerID, flightID, noOfSeats, amount);

                        bookings.add(booking);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }

        return bookings;
    }

    public ObservableList<Baggage> getAllBaggagesxx() {
        ObservableList<Baggage> baggages = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "project";
            String useDatabaseQuery = "USE " + dbName;

            try (Statement useDatabaseStatement = connection.createStatement()) {
                useDatabaseStatement.executeUpdate(useDatabaseQuery);
            }

            // Retrieve all baggage data from the database
            String getBaggagesQuery = "SELECT * FROM Baggage";

            try (PreparedStatement preparedStatement = connection.prepareStatement(getBaggagesQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String baggageTag = resultSet.getString("baggageTag");
                        int ownerId = resultSet.getInt("ownerId");
                        double weight = resultSet.getDouble("weight");
                        String location = resultSet.getString("location");
                        String status = resultSet.getString("status");

                        Baggage baggage = new Baggage(baggageTag, ownerId, weight, location, status);
                        baggages.add(baggage);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return baggages;
    }

    public boolean returnBaggageById(String baggageId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Update the status of the baggage with the specified baggage ID to 'returned'
            String updateStatusQuery = "UPDATE Baggage SET status = 'returned' WHERE baggageTag = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateStatusQuery)) {
                preparedStatement.setString(1, baggageId);
                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return false;
    }

    public void updateSeats(int planeId, int seats) {
        updateAirplaneProperty(planeId, "seating_capacity", seats);
    }

    public void updateAvailability(int planeId, String availability) {
        updateAirplaneProperty(planeId, "availibility", availability);
    }

    public void updateType(int planeId, String airplaneType) {
        updateAirplaneProperty(planeId, "airplane_type", airplaneType);
    }

    public void updateRefueling(int planeId, String refueling) {
        updateAirplaneProperty(planeId, "refueling", refueling);
    }

    private void updateAirplaneProperty(int planeId, String propertyName, Object propertyValue) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Update the Airplane table with the new airplane details
            String updateAirplaneQuery = "UPDATE Airplane SET " + propertyName + "=? WHERE plane_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateAirplaneQuery)) {
                if (propertyValue instanceof Integer) {
                    preparedStatement.setInt(1, (Integer) propertyValue);
                } else if (propertyValue instanceof String) {
                    preparedStatement.setString(1, (String) propertyValue);
                }
                preparedStatement.setInt(2, planeId);

                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

    }

    public boolean doesBookingExist(int bookingID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Check if the Booking table exists
            String checkTableQuery = "SHOW TABLES LIKE 'Booking'";
            ResultSet tableResultSet = statement.executeQuery(checkTableQuery);
            if (!tableResultSet.next()) {
                // Booking table does not exist
                return false;
            }

            // Check if the booking with the given ID exists
            String checkBookingQuery = "SELECT * FROM Booking WHERE booking_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkBookingQuery)) {
                preparedStatement.setInt(1, bookingID);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next(); // Returns true if a booking with the given ID exists
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    public List<Integer> getPlaneIds() {
        List<Integer> planeIds = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String dbName = "Project";
            String useDatabaseQuery = "USE " + dbName;
            statement = connection.createStatement();
            statement.executeUpdate(useDatabaseQuery);

            // Query to select distinct plane IDs from the plane table
            String selectPlaneIdsQuery = "SELECT DISTINCT plane_id FROM airplane";
            try (ResultSet resultSet = statement.executeQuery(selectPlaneIdsQuery)) {
                while (resultSet.next()) {
                    int planeId = resultSet.getInt("plane_id");
                    planeIds.add(planeId);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return planeIds;
    }

}
