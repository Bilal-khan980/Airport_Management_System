
import javafx.collections.ObservableList;

public class Customer {
    int id;
    String password;
    String name;
    String cnic;
    int age;
    String address;
    String contact_no;
    Database db = new Database();
    LostItem LI;

    ComplaintFile CF;

    public Customer() {
        CF = new ComplaintFile();
        LI = new LostItem();

    }

    public Customer(int id, String password, String name, String cnic, int age, String address, String contact_no) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.cnic = cnic;
        this.age = age;
        this.address = address;
        this.contact_no = contact_no;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getCnic() {
        return cnic;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void display_customer_info() {
        System.out.println("ID: " + id);
        System.out.println("NAME : " + name);
        System.out.println("CNIC: " + cnic);
        System.out.println("AGE : " + age);
        System.out.println("ADDRESS : " + address);
    }

    public boolean doesCustomerIdExist(int id) {
        return db.doesCustomerIdExist(id);
    }

    public void insertCustomer(Customer customer) {
        db.insertCustomer(customer);
    }

    public boolean doesCustomerExist(int customerId, String password) {
        return db.doesCustomerExist(customerId, password);
    }

    public void insertComplaint(int newComplaintId, int complainantId, String complaintDetails, String status) {
        CF = new ComplaintFile(newComplaintId, complainantId, complaintDetails, status);
        CF.insertComplaint(CF);
    }

    public ObservableList<ComplaintFile> getComplaints(int accountId) {
        return CF.getComplaints(accountId);
    }

    public boolean doesComplaintIdExist(int complaintId) {
        return CF.doesComplaintIdExist(complaintId);
    }

    public void insertLostItem(int newReportNo, int complainantId, String itemType, String itemDescription,
            String uniqueIdentifier,
            java.util.Date lastSeen, String location) {
        LI = new LostItem(newReportNo, complainantId, itemType, itemDescription,
                uniqueIdentifier,
                lastSeen, location);

        LI.insertLostItem(LI);

    }

    public int getMaxComplaintId() {
        return CF.getMaxComplaintId();
    }

    public ObservableList<LostItem> getLostItemsByOwnerId(int id2) {
        return db.getLostItemsByOwnerId(id2);
    }

}