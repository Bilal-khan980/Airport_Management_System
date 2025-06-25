
import javafx.collections.ObservableList;

public class Admin {

    int id;
    String password;
    ComplaintFile CF;
    Database db;

    public Admin() {
        CF = new ComplaintFile();
        db = new Database();
    }

    public Admin(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getpassword() {
        return password;
    }

    public ObservableList<ComplaintFile> getComplaints_admin() {
        return CF.getComplaints_admin();
    }

    public boolean updateComplaintStatus(int complaintId, String newStatus) {
        return db.updateComplaintStatus(complaintId, newStatus);
    }

    public int getMaxReportNo() {
        return db.getMaxReportNo();
    }
}
