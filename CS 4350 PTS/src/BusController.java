import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	public int addBus(String model, String year) {
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO Bus (Model, Year) VALUES (?, ?)");
			stmt.setString(1, model);
			stmt.setObject(2, year, java.sql.Types.INTEGER); // allows null
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public int deleteBus(int busID) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM Bus WHERE BusID=?");
			stmt.setInt(1, busID);
			return stmt.executeUpdate(); // 0 not found, 1 found
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public String[] getBus(int busID) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT Model, Year FROM Bus WHERE BusID=?");
			stmt.setInt(1, busID);
			ResultSet rs = stmt.executeQuery();
			String[] bus = new String[2];
			rs.next();
			bus[0] = rs.getString("Model");
			bus[1] = String.valueOf(rs.getObject("Year"));
			return bus;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public int updateBus(int busID, String model, String year) {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE Bus SET Model=?, Year=? WHERE BusID=?");
			stmt.setString(1, model);
			stmt.setObject(2, year, java.sql.Types.INTEGER);
			stmt.setInt(3, busID);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public String[][] getBuses() {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Bus", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery();

			rs.last(); // cursor at end
			int rows = rs.getRow();
			int cols = 3;
			rs.beforeFirst(); // return cursor

			String[][] buses = new String[rows][cols];

			int i = 0;
			while (rs.next()) {
				buses[i][0] = String.valueOf(rs.getInt("BusID"));
				buses[i][1] = rs.getString("Model");
				buses[i][2] = String.valueOf(rs.getObject("Year"));
				i++;
			}
			return buses;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
}
