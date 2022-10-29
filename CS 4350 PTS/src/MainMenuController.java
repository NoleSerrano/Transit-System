import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Time;

public class MainMenuController {
	
	private Connection con;

	public MainMenuController() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/pts";
			String username = "root";
			String password = "DBpassword1";
			Class.forName(driver);

			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public MainMenuController(Connection con) {
		this.con = con;
	}

	public void recordData(int tripNumber, Date date, Time scheduledStartTime, int stopNumber, Time actualStartTime,
			Time actualArrivalTime, int numberOfPassengersIn, int numberOfPassengersOut) {

	}

	public String[][] getWeeklySchedule(int driverID, Date date) {

		return null;
	}

	public String[][] getSchedules(String startLocationName, String destinationName, Date date) {
		
		return null;
	}

	public String[][] getStops(int tripNumber, int stopNumber) {

		return null;
	}
}
