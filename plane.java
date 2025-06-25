
public class plane {
    int plane_id;
    int seats;
    String type;
    String availibility;
    String refueling; // true then refueling required and if false means refueling not required.

    Database db = new Database();

    public plane() {

    }

    public plane(int plane_id, int seats, String type) {
        this.plane_id = plane_id;
        this.seats = seats;
        this.availibility = "\0";
        this.refueling = "\0";
        this.type = type;
    }

    public int getPlane_id() {
        return plane_id;
    }

    public int getSeats() {
        return seats;
    }

    public String getavailibility() {
        return availibility;
    }

    public String getrefueling() {
        return refueling;
    }

    public String gettype() {
        return type;
    }

    public void display_plane_details() {
        System.out.println("plane_details");
        System.out.println("PLANE ID : " + plane_id);
        System.out.println("SEATS : " + seats);
    }

    public boolean doesAirplaneExist(int planeId) {
        return db.doesAirplaneExist(planeId);
    }

    public void insertAirplane(plane P) {
        db.insertAirplane(P);
    }

    public void deleteAirplane(int plaIdToDelete) {
        db.deleteAirplane(plaIdToDelete);
    }

    public void updateSeats(int planeId, int seats) {
        db.updateSeats(planeId, seats);
    }

    public void updateType(int planeId, String type) {
        db.updateType(planeId, type);
    }

    public void updateRefueling(int planeId, String refueling) {
        db.updateRefueling(planeId, refueling);
    }

    public void updateAvailability(int planeId, String availibility) {
        db.updateAvailability(planeId, availibility);
    }

    public boolean isAirplaneAvailable(int id) {
        return db.isAirplaneAvailable(id);
    }

    public boolean isRefuelingRequired(int id) {
        return db.isRefuelingRequired(id);
    }

    public int getNumberOfSeatsByPlaneId(int id) {
        return db.getNumberOfSeatsByPlaneId(id);
    }

}
