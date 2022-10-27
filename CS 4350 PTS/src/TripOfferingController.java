import java.sql.Connection;
import java.sql.DriverManager;

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
	
	public void addTripOffering() {
		
	}
	
	public void deleteTripOffering() {
		
	}
	
	public void updateTripOffering() {
		
	}
	
	public String[][] getTripOfferings() {
		
		return null;
	}
}
