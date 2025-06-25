
import java.util.List;

import MANAGEMENT.payment_details;
import javafx.collections.ObservableList;

public class flight {
    private int flight_no;
    private String arrival_time;
    private String departure_time;
    private String departure_airport_id;
    private String arrival_airport_id;
    private int plane_id;
    private int available_seats;
    private int fare;

    Database db = new Database();

    booking B;

    public flight() {
        B = new booking();
    }

    public flight(int flight_no, String arrival_time, String departure_time, int plane_id, String arrival_airport_id,
            String departure_airport_id, int available_seats, int fare) {
        this.flight_no = flight_no;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.plane_id = plane_id;
        this.arrival_airport_id = arrival_airport_id;
        this.departure_airport_id = departure_airport_id;
        this.available_seats = available_seats;
        this.fare = fare;
    }

    public int getFlightNo() {
        return flight_no;
    }

    public String getArrivalTime() {
        return arrival_time;
    }

    public String getDepartureTime() {
        return departure_time;
    }

    public String getDepartureAirportID() {
        return departure_airport_id;
    }

    public String getArrivalAirportID() {
        return arrival_airport_id;
    }

    public int getPlaneId() {
        return plane_id;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public int getFare() {
        return fare;
    }

    public void showFlightDetails() {
        System.out.println("Flight Number: " + getFlightNo());
        System.out.println("Arrival Time: " + getArrivalTime());
        System.out.println("Departure Time: " + getDepartureTime());
        System.out.println("Capacity: " + getPlaneId());
        System.out.println("Arrival Airport ID: " + getArrivalAirportID());
        System.out.println("Departure Airport ID: " + getDepartureAirportID());
    }

    public void insertFlight(flight newFlight) {
        db.insertFlight(newFlight);
    }

    public boolean doesFlightNumberExist(int flightNo) {
        return db.doesFlightNumberExist(flightNo);
    }

    public ObservableList<flight> getFlightsByAirport() {
        return db.getFlightsByAirport();
    }

    public void deleteFlight(int id) {
        db.deleteFlight(id);
    }

    public void deleteBooking(int id) {
        B.deleteBooking(id);
    }

    public void updateFlight(flight newflight) {
        db.updateFlight(newflight);
    }

    public List<booking> getBookings() {
        return B.getBookings();
    }

    public ObservableList<flight> getFlightsByAirports(String fromAirport, String toAirport) {
        return db.getFlightsByAirports(fromAirport, toAirport);
    }

    public int getAvailableSeatsByFlightId(int flightId) {
        return db.getAvailableSeatsByFlightId(flightId);
    }

    public int getFlightFare(int flightId) {
        return db.getFlightFare(flightId);
    }

    public int getMaxBookingId() {
        return B.getMaxBookingId();
    }

    public void insertBooking(int idx, int customer_id, int flight_id, int no_of_seats, int amount) {
        B = new booking(idx, customer_id, flight_id, no_of_seats, amount);
        B.insertBooking(B);
    }

    public void updateAvailableSeats(int flightId, int bookedSeats) {
        db.updateAvailableSeats(flightId, bookedSeats);
    }

    public void insertPayment(int idx, int customer_id, int amount, String stat) {
        B.insertPayment(idx, customer_id, amount, stat);
    }

    public boolean deleteBookingAndUpdateSeats(int bookingID) {
        return B.deleteBookingAndUpdateSeats(bookingID);
    }

    public boolean doesBookingExist(int bookingID) {
        return B.doesBookingExist(bookingID);
    }

    public ObservableList<Baggage> getbaggagesby_baggagetag(String baggageTag) {
        return B.getbaggagesby_baggagetag(baggageTag);
    }

    public ObservableList<Baggage> getBaggagesByOwnerId(int ownerId) {
        return B.getBaggagesByOwnerId(ownerId);
    }

    public ObservableList<Baggage> getAllBaggagesxx() {
        return B.getAllBaggagesxx();
    }

    public boolean baggageIdExists(String baggageId) {
        return B.baggageIdExists(baggageId);
    }

    public void insertBaggage(String id, int oid, double weight, String location, String status) {
        B.insertBaggage(id, oid, weight, location, status);
    }

    public List<booking> getBookingsofcustomer(int customerId) {
        return B.getBookingsofcustomer(customerId);
    }

    public List<payment> retrievePaymentsForCustomer(int customerId) {
        return B.retrievePaymentsForCustomer(customerId);
    }

    public int getAmountByPaymentIdAndCustomerId(int paymentId, int customerId) {
        return B.getAmountByPaymentIdAndCustomerId(paymentId, customerId);
    }

    public boolean doesPaymentIdExist(int paymentId, int customerId) {
        return B.doesPaymentIdExist(paymentId, customerId);
    }

    public boolean isPaymentUnpaid(int paymentId) {
        return B.isPaymentUnpaid(paymentId);
    }

    public boolean insertPaymentDetailsAndUpdateStatus(payment_details paymentDetails) {
        return B.insertPaymentDetailsAndUpdateStatus(paymentDetails);
    }
};
