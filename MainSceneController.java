
import java.io.IOException;
import java.util.Date;
import java.util.List;

import MANAGEMENT.LoggedInAcc;
import MANAGEMENT.payment_details;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainSceneController {

    AirplaneManagementSystem A = new AirplaneManagementSystem();

    @FXML
    void gotocustomermenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/customer_interface.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Customer Interface");

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoadminmenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_menu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField admin_id_txt;

    @FXML
    private TextField admin_password_txt;

    @FXML
    void admin_login(ActionEvent event) {
        int adminId = Integer.parseInt(admin_id_txt.getText());
        String adminPassword = admin_password_txt.getText();

        Admin A = new Admin(adminId, adminPassword);
        Database db = new Database();

        if (db.checkAdminCredentials(A)) {

            load_admin_menu();
        } else {
            System.out.println("Invalid admin credentials");
        }
    }

    void load_admin_menu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_menu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void gotomain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/FirstPage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("FirstPage.fxml");

            stage.setScene(new Scene(root));

            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Adminopen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/Admin_login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin_login.fxml");
            stage.setScene(new Scene(root));

            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void customeropen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/customer_login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("customer_login.fxml");

            stage.setScene(new Scene(root));

            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void admin_register_customer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/Customer_register_page.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtcnic;

    @FXML
    private TextField txtcontactno;

    @FXML
    private TextField txtpassword;

    @FXML
    void register_button(ActionEvent event) {
        int id = Integer.parseInt(txtID.getText());

        if (A.doesCustomerIdExist(id)) {
            showAlert("Error 404", "CUSTOMER ID ALREADY EXISTS.");
            return;
        }

        else {
            String name = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            String address = txtAddress.getText();
            String password = txtpassword.getText();
            String cnic = txtcnic.getText();
            String contactno = txtcontactno.getText();

            A.register_customer(id, password, name, cnic, age, address, contactno);

            load_admin_menu();
            showAlert("SUCCESS", "NEW CUSTOMER REGISTERED.");
        }
    }

    @FXML
    private TextField login_customer_id;

    @FXML
    private TextField login_customer_password;

    @FXML
    void login_customer(ActionEvent event) {
        int customerId = Integer.parseInt(login_customer_id.getText());
        String password = login_customer_password.getText();

        if (A.doesCustomerExist(customerId, password)) {
            try {
                LoggedInAcc.setLoggedInCustomerId(customerId);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/customer_interface.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Customer Interface");
                stage.setScene(new Scene(root));
                stage.show();
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }

        else {
            showAlert("Error 404", "WRONG LOGIN CREDENTIALS");
        }
    }

    //////////////////////////// MANAGE AIRPLANE //////////////////////////

    @FXML
    void admin_manage_airplane(ActionEvent event) // button event
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_manage_airplane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void admin_add_airplane(ActionEvent event) // button event
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_add_airplane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField add_plane_id;

    @FXML
    private TextField add_plane_seats;

    @FXML
    private TextField add_plane_type;

    @FXML
    void add_plane_in_db(ActionEvent event) {
        try {
            int planeId = Integer.parseInt(add_plane_id.getText());
            int seatingCapacity = Integer.parseInt(add_plane_seats.getText());
            String airplaneType = add_plane_type.getText();

            if (A.doesAirplaneExist(planeId)) {
                showAlert("Error 404", "AIRPLANE ID ALREADY EXIXTS.");
            }

            else {
                A.register_plane(planeId, seatingCapacity, airplaneType);

                showAlert("SUCCESS", "NEW AIRPLANE REGISTERED");
            }

        }

        catch (NumberFormatException e) {
            // Handle the case where a number format exception occurs
            showAlert("Error 404", "INVALID INPUT");
        }
    }

    @FXML
    void admin_delete_airplane(ActionEvent event) // button event
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_delete_airplane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private TextField delete_plane_id;

    @FXML
    void delete_plane_id_db(ActionEvent event) {
        try {
            int planeIdToDelete = Integer.parseInt(delete_plane_id.getText());

            if (A.doesAirplaneExist(planeIdToDelete)) {
                A.deleteAirplane(planeIdToDelete);

                showAlert("SUCCESS", "AIRPLANE DELETED.");
            }

            else {
                // Airplane with the specified plane ID doesn't exist, show an alert message
                showAlert("ERROR 404", "AIRPLANE DOESNOT EXIST");
            }

        }

        catch (NumberFormatException e) {
            // Handle the case where a number format exception occurs (e.g., invalid input
            // in the delete_plane_id TextField)
            showAlert("ERROR 404", "INVALID INPUT");
        }
    }

    @FXML
    void admin_update_ariplane(ActionEvent event) // button event
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_update_airplane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // kam krna ha ;
    @FXML
    private TextField add_plane_availibility_update;

    @FXML
    private TextField add_plane_id_update;

    @FXML
    private TextField add_plane_ref_up;

    @FXML
    private TextField add_plane_seats_update;

    @FXML
    private TextField add_plane_type_update;

    @FXML
    void availibility_update_page(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/update_availibility_airplane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void refueling_update_page(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/update_refueling_airplane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void seats_update_page(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/update_seats_airplane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void type_update_page(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/update_type_airplane.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void update_plane_seats_in_db(ActionEvent event) {

        int planeId = Integer.parseInt(add_plane_id_update.getText());
        int seats = Integer.parseInt(add_plane_seats_update.getText());

        if (A.doesAirplaneExist(planeId)) {
            A.updateSeats(planeId, seats);

            showAlert("SUCCESS", "AIRPLANE SEATS UPDATED.");
        }

        else {
            showAlert("ERROR 404", "AIRPLANE DOESNT EXIST");
        }
    }

    @FXML
    void update_plane_refueling_in_db(ActionEvent event) {
        int planeId = Integer.parseInt(add_plane_id_update.getText());
        String refueling = add_plane_ref_up.getText();

        if (A.doesAirplaneExist(planeId)) {
            A.updateRefueling(planeId, refueling);

            showAlert("SUCCESS", "AIRPLANE REFUELED.");
        }

        else {
            // Show an error message if the plane ID does not exist
            showAlert("ERROR 404", "AIRPLANE DOESNT EXIST");
        }

    }

    @FXML
    void update_plane_type_in_db(ActionEvent event) {

        int planeId = Integer.parseInt(add_plane_id_update.getText());
        String airplaneType = add_plane_type_update.getText();

        if (A.doesAirplaneExist(planeId)) {
            A.updateType(planeId, airplaneType);

            showAlert("SUCCESS", "AIRPLANE REFUELED.");
        }

        else {
            showAlert("ERROR 404", "AIRPLANE DOESNT EXIST");
        }
    }

    @FXML
    void update_plane_availibility_in_db(ActionEvent event) {

        int planeId = Integer.parseInt(add_plane_id_update.getText());
        String availability = add_plane_availibility_update.getText();

        if (A.doesAirplaneExist(planeId)) {
            A.updateAvailibility(planeId, availability);

            showAlert("SUCCESS", "AIRPLANE AVAILIBILITY UPDATED.");
        }

        else {
            showAlert("ERROR 404", "AIRPLANE DOESNT EXIST");
        }

    }

    ///////////////////////////////////////// MANAGE
    ///////////////////////////////////////// FLIGHT////////////////////////////////

    @FXML
    void admin_manage_flight(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_manage_flight.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void admin_add_flight(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_add_flight.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void admin_delete_flight(ActionEvent event) {
        try {

            ObservableList<flight> flights = A.getFlightsByAirport();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_delete_flight.fxml"));
            Parent root = loader.load();
            MainSceneController showAllFlightsController = loader.getController();

            // Update the TableView in the controller
            showAllFlightsController.setFlightData(flights);

            // Display the stage
            Stage stage = new Stage();
            stage.setTitle("Admin Menu");
            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField delete_flight;

    @FXML
    void delete_flight_button(ActionEvent event) {
        try {
            int flightIdToDelete = Integer.parseInt(delete_flight.getText());

            if (A.doesFlightNumberExist(flightIdToDelete)) {
                // Flight ID exists, proceed with deletion
                A.deleteFlight(flightIdToDelete);
                A.deleteBooking(flightIdToDelete);

                // Display success message or take any other appropriate action
                showAlert("SUCCESS", "FLIGHT DELETED AND BOOKINGS DELETED");
            }

            else {
                // Flight ID doesn't exist, show an alert message
                showAlert("ERROR 404", "FLIGHT DOESNOT EXIST");
            }

        }

        catch (NumberFormatException e) {
            // Handle the case where a number format exception occurs (e.g., invalid input
            // in the flight_delete_id TextField)
            showAlert("ERROR 404", "INVALID INPUT");
        }
    }

    @FXML
    void update_flight_data(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_update_flight.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////// MANAGE AIRPORT//////////////////////////
    @FXML
    void admin_manage_airport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_manage_airport.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void admin_add_airport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_add_airport.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField add_airport_id;

    @FXML
    private TextField add_airport_location;

    @FXML
    void add_airport_db(ActionEvent event) {
        int airportId = Integer.parseInt(add_airport_id.getText());
        String airportLocation = add_airport_location.getText();

        A = new AirplaneManagementSystem();
        if (!A.isAIrportIdPreesent(airportId)) {
            // If the ID doesn't exist, insert the airport into the database
            A.insertAirport(airportId, airportLocation);
            showAlert("SUCCESS", "AIRPORT ADDED SUCCESSFULLY");
        }

        else {
            // Show an alert indicating that the airport ID is already present
            showAlert("ERROR 404", "AIRPORT ALREADY EXISTS");
        }
    }

    @FXML
    void admin_delete_airport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_delete_airport.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField admin_delete_airportx;

    @FXML
    void delete_airport_from_db(ActionEvent event) {
        String airportIdText = admin_delete_airportx.getText();

        try {
            int airportId = Integer.parseInt(airportIdText);

            boolean deleted = A.deleteAirportById(airportId);

            if (deleted) {
                showAlert("SUCCESS", "AIRPORT DELETED SUCCESSFULLY");
            }

            else {
                showAlert("Error 404", "AIRPORT DOESNOT EXIST");
            }
        }

        catch (NumberFormatException e) {
            showAlert("ERROR", "INVALID INPUT");
            return;
        }

    }

    //////////////////////////// MANAGE BOOKING //////////////////////////

    @FXML
    void manage_admin_booking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_manage_booking.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void admin_add_booking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_add_booking.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private TableView<booking> bookingTablex;

    @FXML
    private TableColumn<booking, Integer> bookingIdColumnx;

    @FXML
    private TableColumn<booking, Integer> customerIdColumnx;

    @FXML
    private TableColumn<booking, Integer> flightIdColumnx;

    @FXML
    private TableColumn<booking, Integer> seatsColumnx;

    @FXML
    private TableColumn<booking, Integer> amountColumnx;

    public void initializeTablee() {
        List<booking> bookingss = A.getBookings();

        bookingIdColumnx.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        customerIdColumnx.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        flightIdColumnx.setCellValueFactory(new PropertyValueFactory<>("fligth_id"));
        seatsColumnx.setCellValueFactory(new PropertyValueFactory<>("no_of_seats"));
        amountColumnx.setCellValueFactory(new PropertyValueFactory<>("amount"));

        bookingTablex.getItems().addAll(bookingss);
    }

    @FXML
    void admin_delete_booking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_delete_booking.fxml"));
            Parent root = loader.load();

            MainSceneController controller = loader.getController();
            controller.initializeTablee();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");
            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField from_booking_search;

    @FXML
    private TextField to_booking_search;

    @FXML
    private TableView<flight> flightTableView;

    @FXML
    private TableColumn<flight, Integer> flightNoColumn;

    @FXML
    private TableColumn<flight, Integer> planeIdColumn;

    @FXML
    private TableColumn<flight, String> arrivalTimeColumn;

    @FXML
    private TableColumn<flight, String> departureTimeColumn;

    @FXML
    private TableColumn<flight, String> arrivalAirportColumn;

    @FXML
    private TableColumn<flight, String> departureAirportColumn;

    @FXML
    private TableColumn<flight, String> availableSeatsColumn;

    public void setFlightData(ObservableList<flight> flights) {
        // Set the flight data to the TableView
        flightTableView.setItems(flights);
    }

    // ----------------------------------------
    @FXML
    private TextField fare_U;

    @FXML
    private TextField arrival_airport_id_U;

    @FXML
    private TextField arrival_time_hours_U;

    @FXML
    private TextField arrival_time_minutes_U;

    @FXML
    private TextField departure_airport_id_U;

    @FXML
    private TextField departure_time_hours_U;

    @FXML
    private TextField departure_time_minutes_U;

    @FXML
    private TextField flight_number_U;

    @FXML
    private TextField plane_id_u;

    @FXML
    void update_flight_details_button(ActionEvent event) {
        try {
            int flightNo = Integer.parseInt(flight_number_U.getText());

            if (!A.doesFlightNumberExist(flightNo)) {
                showAlert("ERROR", "FLIGHT DOESNOT EXIST");
                return;
            }

            String arrivalTime = arrival_time_hours_U.getText() + ":" + arrival_time_minutes_U.getText();
            String departureTime = departure_time_hours_U.getText() + ":" + departure_time_minutes_U.getText();
            String departureAirportID = departure_airport_id_U.getText();
            String arrivalAirportID = arrival_airport_id_U.getText();
            int planeid = Integer.parseInt(plane_id_u.getText());
            int fare = Integer.parseInt(fare_U.getText());

            if (!A.doesAirplaneExist(planeid)) {
                showAlert("ERROR 404", "PLANE NOT FOUND");
                return;
            }

            if (!A.isAirplaneAvailable(planeid)) {
                showAlert("ERROR 404", "PLANE NOT AVAILABLE");
                return;
            }

            if (A.isRefuelingRequired(planeid)) {
                showAlert("ERROR", "PLANE REQUIRES REFUELING");
                return;
            }

            int seats = A.getAvailableSeatsByFlightId(flightNo);

            // Update flight details in the database
            A.UpdateFlight(flightNo, arrivalTime, departureTime, planeid, arrivalAirportID, departureAirportID, seats,
                    fare);

            // Update the availability of the plane to "unavailable"
            A.updateAvailibility(planeid, "unavailable");

            // Display success message or take any other appropriate action
            showAlert("SUCCESS", "FLIGHT DETAILS ADDED SUCCESSFULLY");

        }

        catch (NumberFormatException e) {
            // Handle the case where a number format exception occurs (e.g., invalid input
            // in textboxes)
            showAlert("ERROR", "INVALID INPUT");
        }
    }
    // ---------------------------------------

    @FXML
    private TextField arrival_airport_id;

    @FXML
    private TextField arrival_time_hours;

    @FXML
    private TextField arrival_time_minutes;

    @FXML
    private TextField departure_airport_id;

    @FXML
    private TextField departure_time_hours;

    @FXML
    private TextField departure_time_minutes;

    @FXML
    private TextField flight_number;

    @FXML
    private TextField plane_id;

    @FXML
    private TextField farex;

    @FXML
    void add_flight_data(ActionEvent event) {
        try {
            int flightNo = Integer.parseInt(flight_number.getText());

            if (A.doesFlightNumberExist(flightNo)) {
                showAlert("ERROR", "FLIGHT ID ALREADY EXIST");
                return;
            }

            String arrivalTime = arrival_time_hours.getText() + ":" + arrival_time_minutes.getText();
            String departureTime = departure_time_hours.getText() + ":" + departure_time_minutes.getText();
            String departureAirportID = departure_airport_id.getText();
            String arrivalAirportID = arrival_airport_id.getText();
            int planeId = Integer.parseInt(plane_id.getText());
            int fareId = Integer.parseInt(farex.getText());

            if (!A.doesAirplaneExist(planeId)) {
                showAlert("ERROR 404", "PLANE NOT FOUND");
                return;
            }

            if (!A.isAirplaneAvailable(planeId)) {
                showAlert("ERROR 404", "PLANE NOT AVAILABLE");
                return;
            }

            if (A.isRefuelingRequired(planeId)) {
                showAlert("ERROR", "PLANE REQUIRES REFUELING");
                return;
            }

            int no_of_seats = A.getNumberOfSeatsByPlaneId(planeId);

            A.insertFlight(flightNo, arrivalTime, departureTime, planeId, arrivalAirportID, departureAirportID,
                    no_of_seats, fareId);

            A.updateAvailibility(planeId, "unavailable");

            showAlert("SUCCESS", "FLIGHT ADDED.");

        }

        catch (NumberFormatException e) {
            // Handle the case where a number format exception occurs (e.g., invalid input
            // in textboxes)
            showAlert("ERROR", "INVALID INPUT");
        }
    }

    // ------------------------------------
    @FXML
    private ComboBox<String> fromAirportComboBox;

    @FXML
    private ComboBox<String> toAirportComboBox;

    @FXML
    void initialize() {
        try {

            List<String> airportLocations = A.getAirportLocations();
            fromAirportComboBox.getItems().addAll(airportLocations);
            toAirportComboBox.getItems().addAll(airportLocations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void search_fligth_function(ActionEvent event) {
        try {
            String fromAirport = fromAirportComboBox.getValue();
            String toAirport = toAirportComboBox.getValue();

            ObservableList<flight> flights = A.getFlightsByAirports(fromAirport, toAirport);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/show_all_flights.fxml"));
            Parent root = loader.load();
            MainSceneController showAllFlightsController = loader.getController();

            showAllFlightsController.setFlightData(flights);

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField booked_seats_booking;

    @FXML
    private TextField customer_id_booking;

    @FXML
    private TextField flight_id_booking;

    @FXML
    void add_booking_in_db(ActionEvent event) {
        int customer_id = Integer.parseInt(customer_id_booking.getText());
        int flight_id = Integer.parseInt(flight_id_booking.getText());
        int no_of_seats = Integer.parseInt(booked_seats_booking.getText());

        if (A.doesCustomerIdExist(customer_id)) {
            int availableSeats = A.getAvailableSeatsByFlightId(flight_id);

            if (availableSeats >= no_of_seats) {
                int fareoo = A.getFlightFare(flight_id);
                int amount = no_of_seats * fareoo;

                int idx = A.getMaxBookingId();

                A.insertBooking(idx, customer_id, flight_id, no_of_seats, amount);

                int seats = availableSeats - no_of_seats;
                System.out.println(seats);

                A.updateAvailableSeats(flight_id, seats);

                String stat = "UNPAID";
                A.insertPayment(idx, customer_id, amount, stat);
            }

            else {
                showAlert("ERROR 404", "SEATS NOT AVAILABLE");
            }
        }

        else {
            showAlert("ERROR 404", "CUSTOMER DOESNOT EXIST");
        }
    }

    @FXML
    private TextField delete_booking_id;

    @FXML
    void admin_delete_booking_db(ActionEvent event) {
        try {
            int bookingID = Integer.parseInt(delete_booking_id.getText());

            if (A.doesBookingExist(bookingID)) {
                A.deleteBookingAndUpdateSeats(bookingID);
                showAlert("SUCCESS", "BOOKING DELETED");
            }

            else {
                showAlert("ERROR", "BOOKING DOESNPOT EXIST");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void book_flight_customer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/customer_book_flight.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));
            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField from_booking_search_customer;

    @FXML
    private TextField to_booking_search_customer;

    @FXML
    private TableView<flight> flightTableView_customer;

    @FXML
    private TableColumn<flight, Integer> flightNoColumn_customer;

    @FXML
    private TableColumn<flight, Integer> planeIdColumn_customer;

    @FXML
    private TableColumn<flight, String> arrivalTimeColumn_customer;

    @FXML
    private TableColumn<flight, String> departureTimeColumn_customer;

    @FXML
    private TableColumn<flight, String> arrivalAirportColumn_customer;

    @FXML
    private TableColumn<flight, String> departureAirportColumn_customer;

    @FXML
    private TableColumn<flight, String> availableSeatsColumn_customer;

    public void setFlightData_customer(ObservableList<flight> flights) {
        // Set the flight data to the TableView
        flightTableView_customer.setItems(flights);
    }

    @FXML
    void search_fligth_function_customer(ActionEvent event) {
        try {
            String fromAirport = fromAirportComboBox.getValue();
            String toAirport = toAirportComboBox.getValue();

            ObservableList<flight> flights = A.getFlightsByAirports(fromAirport, toAirport);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/show_all_flights_customer.fxml"));
            Parent root = loader.load();
            MainSceneController showAllFlightsController = loader.getController();

            // Update the TableView in the controller
            showAllFlightsController.setFlightData_customer(flights);

            // Display the stage
            Stage stage = new Stage();
            stage.setTitle("Admin Menu");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void add_booking_in_db_customer(ActionEvent event) {
        int customer_id = LoggedInAcc.getLoggedInCustomerId();
        ;
        int flight_id = Integer.parseInt(flight_id_booking.getText());
        int no_of_seats = Integer.parseInt(booked_seats_booking.getText());

        if (A.doesCustomerIdExist(customer_id)) {
            int availableSeats = A.getAvailableSeatsByFlightId(flight_id);

            if (availableSeats >= no_of_seats) {

                int fareoo = A.getFlightFare(flight_id);
                int amount = no_of_seats * fareoo;

                int idx = A.getMaxBookingId();

                A.insertBooking(idx, customer_id, flight_id, no_of_seats, amount);

                int seats = availableSeats - no_of_seats;
                System.out.println(seats);

                A.updateAvailableSeats(flight_id, seats);

                showAlert("SUCCESS", "BOOKING ADDED");

            }

            else {
                showAlert("ERROR 404", "SEATS NOT AVAILABLE");
            }
        }

        else {
            // Customer ID does not exist, show alert
            showAlert("ERROR 404", "CUSTOMER NOT FOUND");
        }
    }

    @FXML
    void baggagetracking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/baggage_tracking.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Baggage Tracking");

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TableView<Baggage> baggageTableView;

    @FXML
    private TableColumn<Baggage, String> baggageTagColumn;

    @FXML
    private TableColumn<Baggage, Integer> ownerIdColumn;

    @FXML
    private TableColumn<Baggage, Double> weightColumn;

    @FXML
    private TableColumn<Baggage, String> locationColumn;

    @FXML
    private TableColumn<Baggage, String> statusColumn_baggage;

    @FXML
    private TextField search_by_baggagetag;

    @FXML
    void searchbybaggagetag(ActionEvent event) {
        try {
            String tag = search_by_baggagetag.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/search_baggagetag.fxml"));
            Parent root = loader.load();

            MainSceneController searchbaggageComplaintController = loader.getController();

            // Populate the table with complaint data
            ObservableList<Baggage> baggages = A.getbaggagesby_baggagetag(tag);
            searchbaggageComplaintController.baggageTableView.setItems(baggages);

            Stage stage = new Stage();
            stage.setTitle("Search Baggage By Baggage Tag");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showallbookedbaggages(ActionEvent event) {
        try {

            int id = LoggedInAcc.getLoggedInCustomerId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/show_all_baggages.fxml"));
            Parent root = loader.load();

            // Get the controller for the review_complaint.fxml
            MainSceneController showallbaggagesComplaintController = loader.getController();

            // Populate the table with complaint data
            ObservableList<Baggage> baggages = A.getBaggagesByOwnerId(id);
            showallbaggagesComplaintController.baggageTableView.setItems(baggages);

            Stage stage = new Stage();
            stage.setTitle("Search Baggage By Owner Id");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void admin_manage_baggage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_manage_baggage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Baggage Tracking");

            stage.setScene(new Scene(root));

            stage.show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void admin_update_baggage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/show_all_baggage_admin.fxml"));
            Parent root = loader.load();

            MainSceneController showAllBaggageAdminController = loader.getController();

            // Populate the table with baggage data
            ObservableList<Baggage> baggages = A.getAllBaggagesxx();
            showAllBaggageAdminController.baggageTableView.setItems(baggages);

            Stage stage = new Stage();
            stage.setTitle("Show All Baggage Admin");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void admin_add_baggage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_add_baggage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Baggage Tracking");

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void add_baggage_in_db(ActionEvent event) {
        try {
            String id = add_plane_ref_up.getText();
            int oid = Integer.parseInt(lostitem_location.getText());
            double weight = Double.parseDouble(lostitem_uniqueidentifier.getText());
            String location = booked_seats_booking.getText();
            String status = lostitem_type.getText();

            if (A.baggageIdExists(id)) {
                showAlert("ERROR 404", "BAGGAGE ID ALREADY EXISTS");
            }

            else {
                A.insertBaggage(id, oid, weight, location, status);
            }
        }

        catch (NumberFormatException e) {
            showAlert("ERROR 404", "INVALID INPUT");
            e.printStackTrace();

        }
    }

    @FXML
    private TableView<booking> bookingTablexc;

    @FXML
    private TableColumn<booking, Integer> bookingIdColumnxc;

    @FXML
    private TableColumn<booking, Integer> customerIdColumnxc;

    @FXML
    private TableColumn<booking, Integer> flightIdColumnxc;

    @FXML
    private TableColumn<booking, Integer> seatsColumnxc;

    @FXML
    private TableColumn<booking, Integer> amountColumnxc;

    public void initializeTableee() {
        List<booking> bookingsss = A.getBookingsofcustomer(LoggedInAcc.getLoggedInCustomerId());

        bookingIdColumnxc.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        customerIdColumnxc.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        flightIdColumnxc.setCellValueFactory(new PropertyValueFactory<>("fligth_id"));
        seatsColumnxc.setCellValueFactory(new PropertyValueFactory<>("no_of_seats"));
        amountColumnxc.setCellValueFactory(new PropertyValueFactory<>("amount"));

        bookingTablexc.getItems().addAll(bookingsss);
    }

    @FXML
    void customer_cancel_booking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/UIUX/CUSTOMER/customer_delete_booking_show.fxml"));
            Parent root = loader.load();

            MainSceneController controller = loader.getController();
            controller.initializeTableee();

            Stage stage = new Stage();
            stage.setTitle("Admin Menu");

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField customer_delete_booking_id;

    @FXML
    void customer_delete_booking_db(ActionEvent event) {

        try {
            int bookingID = Integer.parseInt(customer_delete_booking_id.getText());

            if (A.doesBookingExist(bookingID)) {
                boolean success = A.deleteBookingAndUpdateSeats(bookingID);

                if (success) {
                    showAlert("SUCCESS", "BOOKING CANCELLED");
                }
            } else {
                showAlert("ERROR 404", "BOOKING DOESNOT EXIST");

            }
        } catch (NumberFormatException e) {
            showAlert("ERROR 404", "INVALID INPUT");
            e.printStackTrace();
        }
    }

    @FXML
    private TableView<payment> paymentTableView;

    @FXML
    private TableColumn<payment, Integer> paymentIdColumn;

    @FXML
    private TableColumn<payment, Integer> customerIdColumn;

    @FXML
    private TableColumn<payment, Integer> bookIdColumn;

    @FXML
    private TableColumn<payment, Integer> amountColumn;

    @FXML
    private TableColumn<payment, String> paymentStatusColumn;

    public void initializeTable(int customerId) {
        List<payment> payments = A.retrievePaymentsForCustomer(customerId);

        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("payment_status"));

        paymentTableView.getItems().addAll(payments);
    }

    @FXML
    private TextField payment_idxx;

    @FXML
    void customer_payments(ActionEvent event) {
        int id = LoggedInAcc.getLoggedInCustomerId();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/customer_payments.fxml"));
            Parent root = loader.load();

            MainSceneController controller = loader.getController();

            controller.initializeTable(id);

            Stage stage = new Stage();
            stage.setTitle("Customer Payments");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField payment_idxxx;

    @FXML
    private TextField payment_amount;

    @FXML
    void customer_go_payment_details(ActionEvent event) {
        int id = LoggedInAcc.getLoggedInCustomerId();
        String paymentIdText = payment_idxx.getText();
        payment_idxxx = new TextField();

        payment_amount = new TextField();

        try {
            int paymentId = Integer.parseInt(paymentIdText);
            int amountxx = A.getAmountByPaymentIdAndCustomerId(paymentId, id);

            boolean paymentExists = A.doesPaymentIdExist(paymentId, id);

            if (paymentExists) {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/UIUX/CUSTOMER/customer_payment_details.fxml"));

                Parent root = loader.load();

                MainSceneController controller = loader.getController();
                controller.setPaymentId(paymentId);
                controller.setamountx(amountxx);

                payment_idxxx.setText(paymentIdText);

                Stage stage = new Stage();
                stage.setTitle("Admin Menu");
                stage.setScene(new Scene(root));
                stage.show();
            }

            else {
                showAlert("ERROR 404", "PAYMENT DOESNOT EXIST");
            }

        }

        catch (NumberFormatException | IOException e) {
            showAlert("ERROR 404", "INVALID INPUT");
            e.printStackTrace();
        }
    }

    public void setPaymentId(int paymentId) {
        payment_idxxx.setText(String.valueOf(paymentId));
    }

    public void setamountx(int x) {
        payment_amount.setText(String.valueOf(x));
    }

    @FXML
    private DatePicker card_expiry;

    @FXML
    private TextField card_no_payment;

    @FXML
    private TextField cvv_payment;

    @FXML
    void payment_status_update_button_store_payment_details(ActionEvent event) {

        int id = LoggedInAcc.getLoggedInCustomerId();
        String paymentIdText = payment_idxxx.getText();

        int paymentId = Integer.parseInt(paymentIdText);
        boolean paymentExists = A.doesPaymentIdExist(paymentId, id);
        if (paymentExists) {
            if (A.isPaymentUnpaid(paymentId)) {
                int amountxx = A.getAmountByPaymentIdAndCustomerId(paymentId, id);
                int card_no = Integer.parseInt(card_no_payment.getText());
                Date card = java.sql.Date.valueOf(card_expiry.getValue());
                int cv = Integer.parseInt(cvv_payment.getText());

                payment_details pd = new payment_details(paymentId, id, amountxx, card_no, cv, card);
                A.insertPaymentDetailsAndUpdateStatus(pd);
            } else {
                showAlert("ERROR 404", "ALREADY PAID");
            }
        } else {
            showAlert("ERROR 404", "WRONG PAYMENT ID");
        }
    }

    @FXML
    void filecomplaint(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/file_complaint.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("File Complaint");

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void newcomplaint(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/new_complaint.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("New Complaint");

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField complaint_details;

    @FXML
    void submit_complaint(ActionEvent event) {
        try {
            int complainantId = LoggedInAcc.getLoggedInCustomerId();

            String complaintDetails = complaint_details.getText();

            int maxComplaintId = A.getMaxComplaintId();
            int newComplaintId = maxComplaintId + 1;

            String status = "Pending";

            A.insertComplaint(newComplaintId, complainantId, complaintDetails, status);

            showAlert("SUCCESS", "COMPLAINT SUBMITTED");
            Stage stage = (Stage) complaint_details.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TableColumn<ComplaintFile, Integer> complaintIdColumn;

    @FXML
    private TableColumn<ComplaintFile, Integer> complainantIdColumn;

    @FXML
    private TableColumn<ComplaintFile, String> detailsColumn;

    @FXML
    private TableColumn<ComplaintFile, String> statusColumn;

    @FXML
    private TableView<ComplaintFile> complaintTableView;

    @FXML
    private TableColumn<ComplaintFile, String> complaintColumn;

    @FXML
    void reviewcomplaint(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/review_complaint.fxml"));
            Parent root = loader.load();

            int id = LoggedInAcc.getLoggedInCustomerId();

            MainSceneController reviewComplaintController = loader.getController();

            ObservableList<ComplaintFile> complaints = A.getComplaints(id);
            reviewComplaintController.complaintTableView.setItems(complaints);

            Stage stage = new Stage();
            stage.setTitle("Review Complaints");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TableColumn<ComplaintFile, Integer> complaintIdColumn_admin;

    @FXML
    private TableColumn<ComplaintFile, Integer> complainantIdColumn_admin;

    @FXML
    private TableColumn<ComplaintFile, String> detailsColumn_admin;

    @FXML
    private TableColumn<ComplaintFile, String> statusColumn_admin;

    @FXML
    private TableView<ComplaintFile> complaintTableView_admin;

    @FXML
    private TableColumn<ComplaintFile, String> complaintColumn_admin;

    @FXML
    void check_complaints(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/ADMIN/admin_check_complaints.fxml"));
            Parent root = loader.load();

            MainSceneController reviewComplaintController = loader.getController();

            ObservableList<ComplaintFile> complaints = A.getComplaints_admin();
            reviewComplaintController.complaintTableView_admin.setItems(complaints);

            Stage stage = new Stage();
            stage.setTitle("Review Complaints");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField complaint_id_resolution;

    @FXML
    private TextField complaint_status_res;

    @FXML
    void update_admin_resolved_complaints(ActionEvent event) {
        try {
            int complaintId = Integer.parseInt(complaint_id_resolution.getText());
            String newStatus = complaint_status_res.getText();

            // Check if the complaint ID exists before attempting to update
            if (isComplaintIdPresent(complaintId)) {

                boolean success = A.updateComplaintStatus(complaintId, newStatus);

                if (success) {
                    showAlert("SUCCESS", "COMPLAINT UPDATES");

                } else {
                    showAlert("ERROR 404", "COMPLAINT UPDATION FAILED");
                }
            } else {
                showAlert("ERROR 404", "COMPLAINANT DOESNOT EXIST");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid complaint ID format.");
            // Handle the case where the complaint ID is not a valid integer
        }
    }

    private boolean isComplaintIdPresent(int complaintId) {
        return A.doesComplaintIdExist(complaintId);
    }

    @FXML
    void reportlostitem(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/new_lostitemreport.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("LostItem Report");

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField delete_baggage_id;
    @FXML
    private TextField lostitem_description;

    @FXML
    private DatePicker lostitem_lastseen;

    @FXML
    private TextField lostitem_location;

    @FXML
    private TextField lostitem_type;

    @FXML
    private TextField lostitem_uniqueidentifier;

    @FXML
    void newlostitemreport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/new_lostitemreport.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("New Lostitem Report");

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private CheckBox checkbox_tac;

    @FXML
    void submit_lostitemreport(ActionEvent event) {
        try {
            // Check if the checkbox is selected
            if (checkbox_tac.isSelected()) {

                int complainantId = LoggedInAcc.getLoggedInCustomerId();
                // Extracting values from the FXML fields
                String itemType = lostitem_type.getText();
                String itemDescription = lostitem_description.getText();
                String uniqueIdentifier = lostitem_uniqueidentifier.getText();
                Date lastSeen = java.sql.Date.valueOf(lostitem_lastseen.getValue());
                String location = lostitem_location.getText();

                int maxReportNo = A.getMaxReportNo();
                int newReportNo = maxReportNo + 1;

                A.insertLostItem(newReportNo, complainantId, itemType, itemDescription,
                        uniqueIdentifier,
                        lastSeen, location);

                System.out.println("Lost Item report submitted successfully!");

                Stage stage = (Stage) lostitem_description.getScene().getWindow();
                stage.close();
            } else {
                // Checkbox not selected, show an alert message
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please check the checkbox before submitting the report.");
                alert.showAndWait();
            }

        } catch (NumberFormatException e) {
            e.printStackTrace(); // You might want to log this or show an error message
        }
    }

    @FXML
    private TableView<LostItem> LostItemTableView;

    @FXML
    private TableColumn<LostItem, Integer> reportNoColumn;

    @FXML
    private TableColumn<LostItem, String> itemTypeColumn;

    @FXML
    private TableColumn<LostItem, String> itemDescriptionColumn;

    @FXML
    private TableColumn<LostItem, String> uniqueIdentifierColumn;

    @FXML
    private TableColumn<LostItem, Date> lastSeenColumn;

    @FXML
    private TableColumn<LostItem, String> locationColumn_lostitem;

    @FXML
    void checkstatusoflostitem(ActionEvent event) {
        try {

            int id = LoggedInAcc.getLoggedInCustomerId();
            System.out.println(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIUX/CUSTOMER/check_status_lostitem.fxml"));
            Parent root = loader.load();

            // Get the controller for the review_complaint.fxml
            MainSceneController showallLostItemComplaintController = loader.getController();

            // Populate the table with complaint data
            ObservableList<LostItem> lostitems = A.getLostItemsByOwnerId(id);
            showallLostItemComplaintController.LostItemTableView.setItems(lostitems);

            Stage stage = new Stage();
            stage.setTitle("Review Lost Item Reports");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void delete_baggage_db(ActionEvent event) {
        try {
            String baggageIdToDelete = delete_baggage_id.getText();

            Database db = new Database();

            if (db.baggageIdExists(baggageIdToDelete)) {
                boolean success = db.returnBaggageById(baggageIdToDelete);

                if (success) {
                    showAlert("SUCCESS", "BAGGAGE RETURNED");
                } else {
                    showAlert("ERROR", "Failed to return baggage");
                }
            } else {
                showAlert("ERROR 404", "BAGGAGE WITH ID NOT FOUND");
            }
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid baggage ID
            e.printStackTrace();
        }
    }

}
