
import java.util.List;

public class Airport {
    int Airport_id;
    String name;
    Database db = new Database();

    public Airport() {

    }

    public Airport(int Airport_id, String name) {
        this.Airport_id = Airport_id;
        this.name = name;
    }

    public int getAirport_id() {
        return Airport_id;
    }

    public String getName() {
        return name;
    }

    public void insertAirport(Airport A) {
        db.insertAirport(A);
    }

    public boolean isAIrportIdPreesent(int idd) {
        return db.isAirportIdPresent(idd);
    }

    public boolean deleteAirportById(int id) {
        return db.deleteAirportById(id);
    }

    public List<String> getAirportLocations() {
        return db.getAirportLocations();
    }

    public void getMaxReportNo() {
    }
}
