import java.sql.Connection;
import java.sql.DriverManager;

public class DriverController {
	
	private Connection con;

	public DriverController() {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/pts";
			String username = "root";
			String password = "DBpassword1";
			Class.forName(driver);

			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public DriverController(Connection con) {
		this.con = con;
	}

	public void addDriver(String driverName, String telephoneNumber) {

	}

	public void deleteDriver(int driverID) {

	}

	public String[] getDriver(int driverID) {

		return null;
	}

	public void updateDriver(int driverID, String driverName, String telephoneNumber) {
		
	}

	public String[][] getDrivers() {

		return null;
	}
}
