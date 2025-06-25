
import java.util.List;

import MANAGEMENT.payment_details;
import javafx.collections.ObservableList;

public class booking {
    int bookingID;
    int customer_id;
    int fligth_id;
    int no_of_seats;
    int amount;

    Database db = new Database();

    payment P = new payment();

    Baggage B = new Baggage();

    booking() {

    }

    public booking(int bookingID, int customer_id, int fligth_id, int no_of_seats, int amount) {
        this.bookingID = bookingID;
        this.customer_id = customer_id;
        this.fligth_id = fligth_id;
        this.no_of_seats = no_of_seats;
        this.amount = amount;
    }

    public int getCustomer_id() {
        return customer_id;

    }

    public int getFligth_id() {
        return fligth_id;
    }

    public int getNo_of_seats() {
        return no_of_seats;
    }

    public int getAmount() {
        return amount;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void deleteBooking(int id) {
        db.deleteBooking(id);
    }

    public List<booking> getBookings() {
        return db.getBookings();
    }

    public int getMaxBookingId() {
        return db.getMaxBookingId();
    }

    public void insertPayment(int idx, int customer_id, int amount, String stat) {
        P = new payment(idx, customer_id, amount, stat);
        P.insertPayment(P);
    }

    public boolean deleteBookingAndUpdateSeats(int bookingID) {
        return db.deleteBookingAndUpdateSeats(bookingID);
    }

    public boolean doesBookingExist(int bookingID) {
        return db.doesBookingExist(bookingID);
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
        Baggage B = new Baggage();
        B.insertBaggage(B);
    }

    public List<booking> getBookingsofcustomer(int customerId) {
        return db.getBookingsofcustomer(customerId);
    }

    public List<payment> retrievePaymentsForCustomer(int customerId) {
        return P.retrievePaymentsForCustomer(customerId);
    }

    public int getAmountByPaymentIdAndCustomerId(int paymentId, int customerId) {
        return P.getAmountByPaymentIdAndCustomerId(paymentId, customerId);
    }

    public boolean doesPaymentIdExist(int paymentId, int customerId) {
        return P.doesPaymentIdExist(paymentId, customerId);
    }

    public boolean isPaymentUnpaid(int paymentId) {
        return P.isPaymentUnpaid(paymentId);
    }

    public boolean insertPaymentDetailsAndUpdateStatus(payment_details paymentDetails) {
        return P.insertPaymentDetailsAndUpdateStatus(paymentDetails);
    }

    public void insertBooking(booking b2) {
        db.insertBooking(b2);
    }
}
