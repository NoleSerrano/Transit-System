import java.sql.Connection;
import java.sql.DriverManager;

public class BusController {

	private Connection con;
	
	public BusController() {
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

	public BusController(Connection con) {
		this.con = con;
	}

	public void addBus(String model, int year) {

	}

	public void deleteBus(int busID) {

	}

	public String[] getBus(int busID) {

		return null;
	}

	public void updateBus(int busID, String model, int year) {

	}

	public String[][] getBuses() {

		return null;
	}
}
