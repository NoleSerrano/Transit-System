import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DriverController {

	private Connection con;

	public DriverController() {
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

	public DriverController(Connection con) {
		this.con = con;
	}

	public void addDriver(String driverName, String telephoneNumber) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO Driver (DriverName, TelephoneNumber) VALUES (?, ?)");
			stmt.setString(1, driverName);
			stmt.setString(2, telephoneNumber);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteDriver(int driverID) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM Driver WHERE DriverID=?");
			stmt.setInt(1, driverID);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String[] getDriver(int driverID) {
		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT DriverName, TelephoneNumber FROM Driver WHERE driverID=?");
			stmt.setInt(1, driverID);
			ResultSet rs = stmt.executeQuery();
			String[] driver = new String[2];
			rs.next();
			driver[0] = rs.getString("DriverName");
			driver[1] = rs.getString("TelephoneNumber");
			return driver;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public void updateDriver(int driverID, String driverName, String telephoneNumber) {

	}

	public String[][] getDrivers() {

		return null;
	}
}
