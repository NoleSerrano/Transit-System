import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Time;

public class TripOfferingController {

	public TripOfferingController() {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/pts";
			String username = "root";
			String password = "DBpassword1";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void addTripOffering(int tripNumber, Date date, Time scheduledStartTime, Time scheduledArrivalTime,
			int driverID, int busID) {

	}

	public void deleteTripOffering(int tripNumber, Date date, Time scheduledStartTime) {

	}

	public String[] getTripOffering(int tripNumber, Date date, Time scheduledStartTime) {

		return null;
	}

	public void updateTripOffering(int tripNumber, Date date, Time scheduledStartTime, Time scheduledArrivalTime,
			int driverID, int busID) {

	}

	public String[][] getTripOfferings() {

		return null;
	}
}
