import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	public int recordData(int tripNumber, Date date, Time scheduledStartTime, int stopNumber, Time actualStartTime,
			Time actualArrivalTime, int numberOfPassengersIn, int numberOfPassengersOut) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO ActualTripStopInfo (TripNumber, Date, ScheduledStartTime, ScheduledArrivalTime, StopNumber, ActualStartTime, ActualArrivalTime, NumberOfPassengersIn, NumberOfPassengersOut"
							+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, tripNumber);
			stmt.setDate(2, date);
			stmt.setTime(3, scheduledStartTime);
			stmt.setInt(4, stopNumber);
			stmt.setTime(5, actualStartTime);
			stmt.setTime(6, actualArrivalTime);
			stmt.setInt(7, numberOfPassengersIn);
			stmt.setInt(8, numberOfPassengersOut);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public String[][] getWeeklySchedule(int driverID, Date date) {

		return null;
	}

	public String[][] getSchedules(String startLocationName, String destinationName, Date date) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT StartLocationName, DestinationName, Date, ScheduledStartTime, ScheduledArrivalTime, DriverID, BusID FROM Trip T, TripOffering TO WHERE T.TripNumber = TO.TripNumber AND StartionLocationName = ? AND DestinationName = ? AND DATE = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery();

			rs.last(); // cursor at end
			int rows = rs.getRow();
			int cols = 7;
			rs.beforeFirst(); // return cursor

			String[][] schedules = new String[rows][cols];

			int i = 0;
			while (rs.next()) {
				schedules[i][0] = rs.getString("StartLocationName");
				schedules[i][1] = rs.getString("DestinationName");
				schedules[i][2] = String.valueOf(rs.getDate("Date"));
				schedules[i][3] = String.valueOf(rs.getTime("ScheduledStartTime"));
				schedules[i][4] = String.valueOf(rs.getTime("ScheduledArrivalTime"));
				schedules[i][5] = String.valueOf(rs.getInt("DriverID"));
				schedules[i][6] = String.valueOf(rs.getInt("BusID"));
				i++;
			}
			return schedules;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}

	public String[][] getStops(int tripNumber) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT TripNumber, StopNumber, SequenceNumber, DrivingTime FROM TripStopInfo WHERE TripStopInfo = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery();

			rs.last(); // cursor at end
			int rows = rs.getRow();
			int cols = 4;
			rs.beforeFirst(); // return cursor

			String[][] stops = new String[rows][cols];

			int i = 0;
			while (rs.next()) {
				stops[i][0] = String.valueOf(rs.getInt("TripNumber"));
				stops[i][1] = String.valueOf(rs.getInt("StopNumber"));
				stops[i][2] = String.valueOf(rs.getInt("SequenceNumber"));
				stops[i][3] = String.valueOf(rs.getTime("DrivingTime"));
				i++;
			}
			return stops;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}
}
