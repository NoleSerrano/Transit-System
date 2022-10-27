import java.sql.Connection;
import java.sql.DriverManager;

public class DriverController {

	public DriverController() {
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
	
	public void addDriver() {

	}

	public void deleteDriver() {

	}

	public void updateDriver() {

	}

	public String[][] getDrivers() {

		return null;
	}
}
