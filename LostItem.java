
import java.util.Date;

public class LostItem {
    private int reportNo;
    private int complainant_id;
    private String itemType;
    private String itemDescription;
    private String uniqueIdentifier;
    private Date lastSeen;
    private String location;

    Database db;

    // Constructor
    public LostItem(int reportNo, int complainant_id, String itemType, String itemDescription, String uniqueIdentifier,
            Date lastSeen,
            String location) {
        this.reportNo = reportNo;
        this.complainant_id = complainant_id;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.uniqueIdentifier = uniqueIdentifier;
        this.lastSeen = lastSeen;
        this.location = location;
    }

    // Getter and Setter methods

    public LostItem() {
        db = new Database();
    }

    public int getReportNo() {
        return reportNo;
    }

    public void setReportNo(int reportNo) {
        this.reportNo = reportNo;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setComplainantId(int complainant_id) {
        this.complainant_id = complainant_id;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public int getcomplainantid() {
        return complainant_id;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void insertLostItem(LostItem LI) {
        db.insertLostItem(LI);
    }
};