import java.sql.Connection;
import java.sql.DriverManager;

public class BusController {

	public BusController() {
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

	public void addBus() {

	}

	public void deleteBus() {

	}

	public void updateBus() {

	}

	public String[][] getBuses() {

		return null;
	}
}
