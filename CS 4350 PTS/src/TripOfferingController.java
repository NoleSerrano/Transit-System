import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

public class TripOfferingController {

	private Connection con;

	public TripOfferingController() {
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

	public TripOfferingController(Connection con) {
		this.con = con;
	}

	public int addTripOffering(int tripNumber, Date date, Time scheduledStartTime, Time scheduledArrivalTime,
			int driverID, int busID) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO TripOffering (TripNumber, Date, ScheduledStartTime, ScheduledArrivalTime, DriverID, BusID) VALUES (?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, tripNumber);
			stmt.setDate(2, date);
			stmt.setTime(3, scheduledStartTime);
			stmt.setObject(4, scheduledArrivalTime, java.sql.Types.TIME);
			stmt.setObject(5, driverID, java.sql.Types.INTEGER);
			stmt.setObject(6, busID, java.sql.Types.INTEGER);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public int deleteTripOffering(int tripNumber, Date date, Time scheduledStartTime) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"DELETE FROM TripOffering WHERE TripNumber=? AND Date=? AND ScheduledStartTime=?");
			stmt.setInt(1, tripNumber);
			stmt.setDate(2, date);
			stmt.setTime(3, scheduledStartTime);
			return stmt.executeUpdate(); // 0 not found, 1 found
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public String[] getTripOffering(int tripNumber, Date date, Time scheduledStartTime) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT ScheduledArrivalTime, DriverID, BusID FROM TripOffering WHERE TripNumber=? AND Date=? AND ScheduledStartTime=?");
			stmt.setInt(1, tripNumber);
			stmt.setDate(2, date);
			stmt.setTime(3, scheduledStartTime);
			ResultSet rs = stmt.executeQuery();
			String[] tripOffering = new String[3];
			rs.next();
			tripOffering[0] = String.valueOf(rs.getObject("ScheduledArrivalTime"));
			tripOffering[1] = String.valueOf(rs.getObject("DriverID"));
			tripOffering[2] = String.valueOf(rs.getObject("BusID"));
			return tripOffering;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public int updateTripOffering(int tripNumber, Date date, Time scheduledStartTime, Time scheduledArrivalTime,
			int driverID, int busID) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE TripOffering SET ScheduledARrivalTime=?, DriverID=?, BusID=? WHERE TripNumber=? AND Date=? AND ScheduledStartTime=?");
			stmt.setInt(4, tripNumber);
			stmt.setDate(5, date);
			stmt.setTime(6, scheduledStartTime);
			stmt.setObject(1, scheduledArrivalTime, java.sql.Types.TIME);
			stmt.setObject(2, driverID, java.sql.Types.INTEGER);
			stmt.setObject(3, busID, java.sql.Types.INTEGER);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public String[][] getTripOfferings() {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM TripOffering",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery();

			rs.last(); // cursor at end
			int rows = rs.getRow();
			int cols = 6;
			rs.beforeFirst(); // return cursor

			String[][] tripOfferings = new String[rows][cols];

			int i = 0;
			while (rs.next()) {
				tripOfferings[i][0] = String.valueOf(rs.getInt("TripNumber"));
				tripOfferings[i][1] = String.valueOf(rs.getDate("Date"));
				tripOfferings[i][2] = String.valueOf(rs.getTime("ScheduledStartTime"));
				tripOfferings[i][3] = String.valueOf(rs.getObject("ScheduledArrivalTime"));
				tripOfferings[i][4] = String.valueOf(rs.getObject("DriverID"));
				tripOfferings[i][5] = String.valueOf(rs.getObject("BusID"));
				i++;
			}
			return tripOfferings;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
