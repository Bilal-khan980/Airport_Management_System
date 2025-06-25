
import java.util.List;

import MANAGEMENT.payment_details;
import javafx.collections.ObservableList;

public class AirplaneManagementSystem {
    Customer C;
    plane P;
    flight F;
    Airport A;
    Admin AD;

    public AirplaneManagementSystem() {
        P = new plane();
        C = new Customer();
        F = new flight();
        AD = new Admin();
        A = new Airport();
    }

    public boolean doesCustomerIdExist(int id) {
        return C.doesCustomerIdExist(id);
    }

    public void register_customer(int id, String password, String name, String cnic, int age, String address,
            String contactno) {
        C = new Customer(id, password, name, cnic, age, address, contactno);
        C.insertCustomer(C);

    }

    public boolean doesCustomerExist(int customerId, String password) {
        return C.doesCustomerExist(customerId, password);
    }

    public boolean doesAirplaneExist(int planeId) {
        return P.doesAirplaneExist(planeId);

    }

    public void register_plane(int planeId, int seatingCapacity, String airplaneType) {
        P = new plane(planeId, seatingCapacity, airplaneType);
        P.insertAirplane(P);
    }

    public void deleteAirplane(int planeIdToDelete) {
        P.deleteAirplane(planeIdToDelete);
    }

    public void updateSeats(int planeId, int seats) {
        P.updateSeats(planeId, seats);
    }

    public void updateRefueling(int planeId, String refueling) {
        P.updateRefueling(planeId, refueling);
    }

    public void updateAvailibility(int planeId, String availibility) {
        P.updateAvailability(planeId, availibility);
    }

    public void updateType(int planeId, String type) {
        P.updateType(planeId, type);
    }

    public boolean isAirplaneAvailable(int id) {
        return P.isAirplaneAvailable(id);
    }

    public boolean isRefuelingRequired(int id) {
        return P.isRefuelingRequired(id);
    }

    public int getNumberOfSeatsByPlaneId(int id) {
        return P.getNumberOfSeatsByPlaneId(id);
    }

    public void insertFlight(int flightNo, String arrivalTime, String departureTime, int planeId,
            String arrivalAirportID, String departureAirportID, int no_of_seats, int fareId) {
        flight f = new flight(flightNo, arrivalTime, departureTime, planeId, arrivalAirportID, departureAirportID,
                no_of_seats, fareId);

        f.insertFlight(f);

    }

    public boolean doesFlightNumberExist(int flightNo) {
        return F.doesFlightNumberExist(flightNo);
    }

    public ObservableList<flight> getFlightsByAirport() {
        return F.getFlightsByAirport();
    }

    public void deleteFlight(int id) {
        F.deleteFlight(id);
    }

    public void deleteBooking(int id) {
        F.deleteBooking(id);
    }

    public void UpdateFlight(int flightNo, String arrivalTime, String departureTime, int planeId,
            String arrivalAirportID, String departureAirportID, int no_of_seats, int fareId) {
        flight f = new flight(flightNo, arrivalTime, departureTime, planeId, arrivalAirportID, departureAirportID,
                no_of_seats, fareId);

        f.updateFlight(f);
    }

    public void insertAirport(int airportId, String airportLocation) {
        A = new Airport(airportId, airportLocation);
        A.insertAirport(A);
    }

    public boolean isAIrportIdPreesent(int id) {
        return A.isAIrportIdPreesent(id);
    }

    public boolean deleteAirportById(int id) {
        return A.deleteAirportById(id);
    }

    public List<booking> getBookings() {
        return F.getBookings();
    }

    public List<String> getAirportLocations() {
        return A.getAirportLocations();
    }

    public ObservableList<flight> getFlightsByAirports(String fromAirport, String toAirport) {
        return F.getFlightsByAirport();
    }

    public int getAvailableSeatsByFlightId(int flightId) {
        return F.getAvailableSeatsByFlightId(flightId);
    }

    public int getFlightFare(int flightId) {
        return F.getFlightFare(flightId);
    }

    public int getMaxBookingId() {
        return F.getMaxBookingId();
    }

    public void insertBooking(int idx, int customer_id, int flight_id, int no_of_seats, int amount) {
        F.insertBooking(idx, customer_id, flight_id, no_of_seats, amount);
    }

    public void updateAvailableSeats(int flightId, int bookedSeats) {
        F.updateAvailableSeats(flightId, bookedSeats);
    }

    public void insertPayment(int idx, int customer_id, int amount, String stat) {
        F.insertPayment(idx, customer_id, amount, stat);
    }

    public boolean deleteBookingAndUpdateSeats(int bookingID) {
        return F.deleteBookingAndUpdateSeats(bookingID);
    }

    public boolean doesBookingExist(int bookingID) {
        return F.doesBookingExist(bookingID);
    }

    public ObservableList<Baggage> getbaggagesby_baggagetag(String baggageTag) {
        return F.getbaggagesby_baggagetag(baggageTag);
    }

    public ObservableList<Baggage> getBaggagesByOwnerId(int ownerId) {
        return F.getBaggagesByOwnerId(ownerId);
    }

    public ObservableList<Baggage> getAllBaggagesxx() {
        return F.getAllBaggagesxx();
    }

    public boolean baggageIdExists(String baggageId) {
        return F.baggageIdExists(baggageId);
    }

    public void insertBaggage(String id, int oid, double weight, String location, String status) {
        F.insertBaggage(id, oid, weight, location, status);
    }

    public List<booking> getBookingsofcustomer(int customerId) {
        return F.getBookingsofcustomer(customerId);
    }

    public List<payment> retrievePaymentsForCustomer(int customerId) {
        return F.retrievePaymentsForCustomer(customerId);
    }

    public int getAmountByPaymentIdAndCustomerId(int paymentId, int customerId) {
        return F.getAmountByPaymentIdAndCustomerId(paymentId, customerId);
    }

    public boolean doesPaymentIdExist(int paymentId, int customerId) {
        return F.doesPaymentIdExist(paymentId, customerId);
    }

    public boolean isPaymentUnpaid(int paymentId) {
        return F.isPaymentUnpaid(paymentId);
    }

    public boolean insertPaymentDetailsAndUpdateStatus(payment_details paymentDetails) {
        return F.insertPaymentDetailsAndUpdateStatus(paymentDetails);
    }

    public void insertComplaint(int newComplaintId, int complainantId, String complaintDetails, String status) {
        C.insertComplaint(newComplaintId, complainantId, complaintDetails, status);
    }

    public ObservableList<ComplaintFile> getComplaints(int accountId) {
        return C.getComplaints(accountId);
    }

    public ObservableList<ComplaintFile> getComplaints_admin() {
        return AD.getComplaints_admin();
    }

    public boolean updateComplaintStatus(int complaintId, String newStatus) {
        return AD.updateComplaintStatus(complaintId, newStatus);
    }

    public boolean doesComplaintIdExist(int complaintId) {
        return C.doesComplaintIdExist(complaintId);
    }

    public int getMaxReportNo() {
        return AD.getMaxReportNo();
    }

    public void insertLostItem(int newReportNo, int complainantId, String itemType, String itemDescription,
            String uniqueIdentifier,
            java.util.Date lastSeen, String location) {
        C.insertLostItem(newReportNo, complainantId, itemType, itemDescription, uniqueIdentifier, lastSeen, location);
    }

    public int getMaxComplaintId() {
        return C.getMaxComplaintId();
    }

    public ObservableList<LostItem> getLostItemsByOwnerId(int id) {
        return C.getLostItemsByOwnerId(id);
    }

}
