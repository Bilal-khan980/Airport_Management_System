package MANAGEMENT;

import java.util.Date;

public class payment_details {
    int payment_id;
    int customer_id;
    int amount;
    int card_no;
    int cvv;
    Date card_expiry;

    public payment_details(int payment_id, int customer_id, int amount, int card_no, int cvv, Date card) {
        this.payment_id = payment_id;
        this.customer_id = customer_id;
        this.amount = amount;
        this.card_no = card_no;
        this.card_expiry = card;
    }

    public int getAmount() {
        return amount;
    }

    public Date getCard_expiry() {
        return card_expiry;
    }

    public int getCard_no() {
        return card_no;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getCvv() {
        return cvv;
    }

    public int getPayment_id() {
        return payment_id;
    }

};
