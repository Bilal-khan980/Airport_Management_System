
import java.util.List;

import MANAGEMENT.payment_details;

public class payment {
    int payment_id = 0;
    int book_id;
    int customer_id;
    int amount;
    String payment_status;

    Database db = new Database();

    public payment() {

    }

    public payment(int book_id, int customer_id, int amount, String payment_status) {
        this.book_id = book_id;
        this.customer_id = customer_id;
        this.amount = amount;
        this.payment_status = payment_status;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public int getAmount() {
        return amount;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setpaymentId(int payment) {
        this.payment_id = payment;
    }

    public void insertPayment(payment P) {
        db.insertPayment(P);
    }

    public List<payment> retrievePaymentsForCustomer(int customerId) {
        return db.retrievePaymentsForCustomer(customerId);
    }

    public int getAmountByPaymentIdAndCustomerId(int paymentId, int customerId) {
        return db.getAmountByPaymentIdAndCustomerId(paymentId, customerId);
    }

    public boolean doesPaymentIdExist(int paymentId, int customerId) {
        return db.doesPaymentIdExist(paymentId, customerId);
    }

    public boolean isPaymentUnpaid(int paymentId) {
        return db.isPaymentUnpaid(paymentId);
    }

    public boolean insertPaymentDetailsAndUpdateStatus(payment_details paymentDetails) {
        return db.insertPaymentDetailsAndUpdateStatus(paymentDetails);
    }

}
