
import javafx.collections.ObservableList;

public class Baggage {
    private String baggageTag;
    private int ownerId;
    private double weight;
    private String location;
    private String status;

    Database db = new Database();

    Baggage() {

    }

    // Constructors
    public Baggage(String baggageTag, int ownerId, double weight, String location, String status) {
        this.baggageTag = baggageTag;
        this.ownerId = ownerId;
        this.weight = weight;
        this.location = location;
        this.status = status;
    }

    // Getter and Setter methods
    public String getBaggageTag() {
        return baggageTag;
    }

    public void setBaggageTag(String baggageTag) {
        this.baggageTag = baggageTag;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ObservableList<Baggage> getbaggagesby_baggagetag(String baggageTag) {
        return db.getbaggagesby_baggagetag(baggageTag);
    }

    public ObservableList<Baggage> getBaggagesByOwnerId(int ownerId) {
        return db.getBaggagesByOwnerId(ownerId);
    }

    public ObservableList<Baggage> getAllBaggagesxx() {
        return db.getAllBaggagesxx();
    }

    public boolean baggageIdExists(String baggageId) {
        return db.baggageIdExists(baggageId);
    }

    public void insertBaggage(Baggage baggage) {
        db.insertBaggage(baggage);
    }
}