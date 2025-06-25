
import javafx.collections.ObservableList;

public class ComplaintFile {
    private int complaintId;
    private int complainantId;
    private String details;
    private String status;

    Database db;

    public ComplaintFile() {
        db = new Database();
    }

    public ComplaintFile(int complaintId, int complainantId, String details, String status) {
        this.complaintId = complaintId;
        this.complainantId = complainantId;
        this.details = details;
        this.status = status;
    }

    // Getter methods
    public int getComplaintId() {
        return complaintId;
    }

    public int getComplainantId() {
        return complainantId;
    }

    public String getDetails() {
        return details;
    }

    public String getStatus() {
        return status;
    }

    // Setter methods (if needed)
    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public void setComplainantId(int complainantId) {
        this.complainantId = complainantId;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMaxComplaintId() {
        return db.getMaxComplaintId();
    }

    public void insertComplaint(ComplaintFile newComplaint) {
        db.insertComplaint(newComplaint);

    }

    public ObservableList<ComplaintFile> getComplaints(int accountId) {
        return db.getComplaints(accountId);
    }

    public ObservableList<ComplaintFile> getComplaints_admin() {
        return db.getComplaints_admin();
    }

    public boolean doesComplaintIdExist(int complaintId) {
        return db.doesComplaintIdExist(complaintId);
    }

}