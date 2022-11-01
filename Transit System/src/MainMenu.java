import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	// nts: space for buttons, tab for swtiching between buttons/text fields, right
	// click for popup menus

	private static Connection con;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */

	// https://www.randomlists.com/city-name-generator
	// https://www.randomlists.com/random-addresses
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public MainMenu() throws Exception {

		createConnection();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setBackground(new Color(246, 249, 250));
		contentPane.setLayout(null);

		setLocationRelativeTo(null); // centers frame to the screen

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 121, 590, 300);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel titleLabel = new JLabel("Transit System");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(30, 11, 590, 17);
		contentPane.add(titleLabel);

		JButton recordData = new JButton("Record Data");

		recordData.setBackground(SystemColor.controlHighlight);
		recordData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		recordData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecordData rd = new RecordData(con);
				rd.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				rd.setVisible(true);
			}
		});
		recordData.setBounds(30, 39, 140, 30);
		recordData.setFocusPainted(false); // removes ugly focus
		contentPane.add(recordData);

		JButton weeklySchedule = new JButton("Weekly Schedule");
		weeklySchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WeeklySchedule ws = new WeeklySchedule(con);
				ws.setLocationRelativeTo(contentPane);
				ws.setVisible(true);
				ws.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
					}
				});
				// not sure why but it works after just calling the method
				if (ws.getFlag() == 1) {
					String[][] data = ws.getWeeklySchedule();
					String[] attributes = { "Trip Number", "Date", "Scheduled Arrival Time", "Scheduled Start Time",
							"Driver ID", "Bus ID" };
					displayTable2(data, attributes);
					displayJTable(data, attributes, scrollPane);
				}
			}
		});
		weeklySchedule.setFont(new Font("Tahoma", Font.PLAIN, 12));
		weeklySchedule.setFocusPainted(false);
		weeklySchedule.setBackground(SystemColor.controlHighlight);
		weeklySchedule.setBounds(180, 39, 140, 30);
		contentPane.add(weeklySchedule);

		JButton displaySchedules = new JButton("Display Schedules");
		displaySchedules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplaySchedules ds = new DisplaySchedules(con);
				ds.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				ds.setVisible(true);
				ds.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
					}
				});
				if (ds.getFlag() == 1) {
					String[][] data = ds.getSchedules();
					String[] attributes = { "Start Location Name", "Destination Name", "Date", "Scheduled Start Time",
							"Scheduled Arrival Time", "Driver ID", "Bus ID" };
					displayTable2(data, attributes);
					displayJTable(data, attributes, scrollPane);
				}
			}
		});
		displaySchedules.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displaySchedules.setFocusPainted(false);
		displaySchedules.setBackground(SystemColor.controlHighlight);
		displaySchedules.setBounds(330, 39, 140, 30);
		contentPane.add(displaySchedules);

		JButton displayStops = new JButton("Display Stops");

		displayStops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayStops ds = new DisplayStops(con);
				ds.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				ds.setVisible(true);
				ds.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
					}
				});
				if (ds.getFlag() == 1) {
					String[][] data = ds.getStops();
					String[] attributes = { "Trip Number", "Stop Number", "Sequence Number", "Driving Time" };
					displayTable2(data, attributes);
					displayJTable(data, attributes, scrollPane);
				}
			}
		});
		displayStops.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displayStops.setFocusPainted(false);
		displayStops.setBackground(SystemColor.controlHighlight);
		displayStops.setBounds(480, 39, 140, 30);
		contentPane.add(displayStops);

		JPopupMenu driverPM = new JPopupMenu();
		driverPM.setBackground(Color.WHITE);
		driverPM.setPopupSize(new Dimension(190, 90));
		driverPM.setLabel("");

		JMenuItem addDr = new JMenuItem("Add");
		addDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDriver dr = new AddDriver(con);
				dr.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				dr.setVisible(true);

			}
		});
		addDr.setBackground(Color.WHITE);
		driverPM.add(addDr);

		JMenuItem deleteDr = new JMenuItem("Delete");
		deleteDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DeleteDriver dr = new DeleteDriver(con);
				dr.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				dr.setVisible(true);

			}
		});
		deleteDr.setBackground(Color.WHITE);
		driverPM.add(deleteDr);

		JMenuItem updateDr = new JMenuItem("Update");
		updateDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UpdateDriver dr = new UpdateDriver(con);
				dr.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				dr.setVisible(true);

			}
		});
		updateDr.setBackground(Color.WHITE);
		driverPM.add(updateDr);

		JMenuItem displayDr = new JMenuItem("Display");
		displayDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DriverController dc = new DriverController(con);
				String[][] data = dc.getDrivers();
				String[] attributes = { "Driver ID", "Driver Name", "Telephone Number" };
				displayTable2(data, attributes);
				displayJTable(data, attributes, scrollPane);
			}
		});
		displayDr.setBackground(Color.WHITE);
		driverPM.add(displayDr);

		JButton driverButton = new JButton("Driver");
		driverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				driverPM.show(contentPane, driverButton.getX(), driverButton.getY() + 30);
			}
		});
		driverButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverButton.setBackground(SystemColor.controlHighlight);
		driverButton.setBounds(230, 80, 190, 30);
		driverButton.setFocusPainted(false);
		contentPane.add(driverButton);

		JPopupMenu tripOfferingPM = new JPopupMenu();
		tripOfferingPM.setBackground(Color.WHITE);
		tripOfferingPM.setPopupSize(new Dimension(190, 90));

		JMenuItem addTO = new JMenuItem("Add");
		addTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AddTripOffering to = new AddTripOffering(con);
				to.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				to.setVisible(true);

			}
		});
		addTO.setBackground(Color.WHITE);
		tripOfferingPM.add(addTO);

		JMenuItem deleteTO = new JMenuItem("Delete");
		deleteTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DeleteTripOffering to = new DeleteTripOffering(con);
				to.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				to.setVisible(true);

			}
		});
		deleteTO.setBackground(Color.WHITE);
		tripOfferingPM.add(deleteTO);

		JMenuItem updateTO = new JMenuItem("Update");
		updateTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UpdateTripOffering to = new UpdateTripOffering(con);
				to.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				to.setVisible(true);

			}
		});
		updateTO.setBackground(Color.WHITE);
		tripOfferingPM.add(updateTO);

		JMenuItem displayTO = new JMenuItem("Display");
		displayTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TripOfferingController toc = new TripOfferingController(con);
				String[][] data = toc.getTripOfferings();
				String[] attributes = { "Trip Number", "Date", "Scheduled Start Time", "Scheduled Arrival Time",
						"Driver ID", "Bus ID" };
				displayTable2(data, attributes);
				displayJTable(data, attributes, scrollPane);
			}
		});
		displayTO.setBackground(Color.WHITE);
		tripOfferingPM.add(displayTO);

		JButton tripOfferingButton = new JButton("Trip Offering");
		tripOfferingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tripOfferingPM.show(contentPane, tripOfferingButton.getX(), tripOfferingButton.getY() + 30);
			}
		});
		tripOfferingButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tripOfferingButton.setFocusPainted(false);
		tripOfferingButton.setBackground(SystemColor.controlHighlight);
		tripOfferingButton.setBounds(30, 80, 190, 30);
		contentPane.add(tripOfferingButton);

		JPopupMenu busPM = new JPopupMenu();
		busPM.setBackground(Color.WHITE);
		busPM.setPopupSize(new Dimension(190, 90));

		JMenuItem addBu = new JMenuItem("Add");
		addBu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AddBus bu = new AddBus(con);
				bu.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				bu.setVisible(true);

			}
		});
		addBu.setBackground(Color.WHITE);

		busPM.add(addBu);

		JMenuItem deleteBu = new JMenuItem("Delete");
		deleteBu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DeleteBus bu = new DeleteBus(con);
				bu.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				bu.setVisible(true);

			}
		});
		deleteBu.setBackground(Color.WHITE);
		busPM.add(deleteBu);

		JMenuItem updateBu = new JMenuItem("Update");
		updateBu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UpdateBus bu = new UpdateBus(con);
				bu.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				bu.setVisible(true);

			}
		});
		updateBu.setBackground(Color.WHITE);
		busPM.add(updateBu);

		JMenuItem displayBu = new JMenuItem("Display");
		displayBu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BusController bu = new BusController(con);
				String[][] data = bu.getBuses();
				String[] attributes = { "Bus ID", "Model", "Year" };
				displayTable2(data, attributes);
				displayJTable(data, attributes, scrollPane);
			}
		});
		displayBu.setBackground(Color.WHITE);
		busPM.add(displayBu);

		JButton busButton = new JButton("Bus");
		busButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busPM.show(contentPane, busButton.getX(), busButton.getY() + 30);
			}
		});
		busButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		busButton.setFocusPainted(false);
		busButton.setBackground(SystemColor.controlHighlight);
		busButton.setBounds(430, 80, 190, 30);
		contentPane.add(busButton);

	}

	public MainMenu(Connection con) throws Exception {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setBackground(new Color(246, 249, 250));
		contentPane.setLayout(null);

		setLocationRelativeTo(null); // centers frame to the screen

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 121, 590, 300);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel titleLabel = new JLabel("Transit System");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(30, 11, 590, 17);
		contentPane.add(titleLabel);

		JButton recordData = new JButton("Record Data");

		recordData.setBackground(SystemColor.controlHighlight);
		recordData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		recordData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecordData rd = new RecordData(con);
				rd.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				rd.setVisible(true);
			}
		});
		recordData.setBounds(30, 39, 140, 30);
		recordData.setFocusPainted(false); // removes ugly focus
		contentPane.add(recordData);

		JButton weeklySchedule = new JButton("Weekly Schedule");
		weeklySchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WeeklySchedule ws = new WeeklySchedule(con);
				ws.setLocationRelativeTo(contentPane);
				ws.setVisible(true);
				ws.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
					}
				});
				// not sure why but it works after just calling the method
				if (ws.getFlag() == 1) {
					String[][] data = ws.getWeeklySchedule();
					String[] attributes = { "Trip Number", "Date", "Scheduled Arrival Time", "Scheduled Start Time",
							"Driver ID", "Bus ID" };
					displayTable2(data, attributes);
					displayJTable(data, attributes, scrollPane);
				}
			}
		});
		weeklySchedule.setFont(new Font("Tahoma", Font.PLAIN, 12));
		weeklySchedule.setFocusPainted(false);
		weeklySchedule.setBackground(SystemColor.controlHighlight);
		weeklySchedule.setBounds(180, 39, 140, 30);
		contentPane.add(weeklySchedule);

		JButton displaySchedules = new JButton("Display Schedules");
		displaySchedules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplaySchedules ds = new DisplaySchedules(con);
				ds.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				ds.setVisible(true);
				ds.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
					}
				});
				if (ds.getFlag() == 1) {
					String[][] data = ds.getSchedules();
					String[] attributes = { "Start Location Name", "Destination Name", "Date", "Scheduled Start Time",
							"Scheduled Arrival Time", "Driver ID", "Bus ID" };
					displayTable2(data, attributes);
					displayJTable(data, attributes, scrollPane);
				}
			}
		});
		displaySchedules.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displaySchedules.setFocusPainted(false);
		displaySchedules.setBackground(SystemColor.controlHighlight);
		displaySchedules.setBounds(330, 39, 140, 30);
		contentPane.add(displaySchedules);

		JButton displayStops = new JButton("Display Stops");

		displayStops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayStops ds = new DisplayStops(con);
				ds.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				ds.setVisible(true);
				ds.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
					}
				});
				if (ds.getFlag() == 1) {
					String[][] data = ds.getStops();
					String[] attributes = { "Trip Number", "Stop Number", "Sequence Number", "Driving Time" };
					displayTable2(data, attributes);
					displayJTable(data, attributes, scrollPane);
				}
			}
		});
		displayStops.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displayStops.setFocusPainted(false);
		displayStops.setBackground(SystemColor.controlHighlight);
		displayStops.setBounds(480, 39, 140, 30);
		contentPane.add(displayStops);

		JPopupMenu driverPM = new JPopupMenu();
		driverPM.setBackground(Color.WHITE);
		driverPM.setPopupSize(new Dimension(190, 90));
		driverPM.setLabel("");

		JMenuItem addDr = new JMenuItem("Add");
		addDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDriver dr = new AddDriver(con);
				dr.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				dr.setVisible(true);

			}
		});
		addDr.setBackground(Color.WHITE);
		driverPM.add(addDr);

		JMenuItem deleteDr = new JMenuItem("Delete");
		deleteDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DeleteDriver dr = new DeleteDriver(con);
				dr.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				dr.setVisible(true);

			}
		});
		deleteDr.setBackground(Color.WHITE);
		driverPM.add(deleteDr);

		JMenuItem updateDr = new JMenuItem("Update");
		updateDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UpdateDriver dr = new UpdateDriver(con);
				dr.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				dr.setVisible(true);

			}
		});
		updateDr.setBackground(Color.WHITE);
		driverPM.add(updateDr);

		JMenuItem displayDr = new JMenuItem("Display");
		displayDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DriverController dc = new DriverController(con);
				String[][] data = dc.getDrivers();
				String[] attributes = { "Driver ID", "Driver Name", "Telephone Number" };
				displayTable2(data, attributes);
				displayJTable(data, attributes, scrollPane);
			}
		});
		displayDr.setBackground(Color.WHITE);
		driverPM.add(displayDr);

		JButton driverButton = new JButton("Driver");
		driverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				driverPM.show(contentPane, driverButton.getX(), driverButton.getY() + 30);
			}
		});
		driverButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverButton.setBackground(SystemColor.controlHighlight);
		driverButton.setBounds(230, 80, 190, 30);
		driverButton.setFocusPainted(false);
		contentPane.add(driverButton);

		JPopupMenu tripOfferingPM = new JPopupMenu();
		tripOfferingPM.setBackground(Color.WHITE);
		tripOfferingPM.setPopupSize(new Dimension(190, 90));

		JMenuItem addTO = new JMenuItem("Add");
		addTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AddTripOffering to = new AddTripOffering(con);
				to.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				to.setVisible(true);

			}
		});
		addTO.setBackground(Color.WHITE);
		tripOfferingPM.add(addTO);

		JMenuItem deleteTO = new JMenuItem("Delete");
		deleteTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DeleteTripOffering to = new DeleteTripOffering(con);
				to.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				to.setVisible(true);

			}
		});
		deleteTO.setBackground(Color.WHITE);
		tripOfferingPM.add(deleteTO);

		JMenuItem updateTO = new JMenuItem("Update");
		updateTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UpdateTripOffering to = new UpdateTripOffering(con);
				to.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				to.setVisible(true);

			}
		});
		updateTO.setBackground(Color.WHITE);
		tripOfferingPM.add(updateTO);

		JMenuItem displayTO = new JMenuItem("Display");
		displayTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TripOfferingController toc = new TripOfferingController(con);
				String[][] data = toc.getTripOfferings();
				String[] attributes = { "Trip Number", "Date", "Scheduled Start Time", "Scheduled Arrival Time",
						"Driver ID", "Bus ID" };
				displayTable2(data, attributes);
				displayJTable(data, attributes, scrollPane);
			}
		});
		displayTO.setBackground(Color.WHITE);
		tripOfferingPM.add(displayTO);

		JButton tripOfferingButton = new JButton("Trip Offering");
		tripOfferingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tripOfferingPM.show(contentPane, tripOfferingButton.getX(), tripOfferingButton.getY() + 30);
			}
		});
		tripOfferingButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tripOfferingButton.setFocusPainted(false);
		tripOfferingButton.setBackground(SystemColor.controlHighlight);
		tripOfferingButton.setBounds(30, 80, 190, 30);
		contentPane.add(tripOfferingButton);

		JPopupMenu busPM = new JPopupMenu();
		busPM.setBackground(Color.WHITE);
		busPM.setPopupSize(new Dimension(190, 90));

		JMenuItem addBu = new JMenuItem("Add");
		addBu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AddBus bu = new AddBus(con);
				bu.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				bu.setVisible(true);

			}
		});
		addBu.setBackground(Color.WHITE);

		busPM.add(addBu);

		JMenuItem deleteBu = new JMenuItem("Delete");
		deleteBu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DeleteBus bu = new DeleteBus(con);
				bu.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				bu.setVisible(true);

			}
		});
		deleteBu.setBackground(Color.WHITE);
		busPM.add(deleteBu);

		JMenuItem updateBu = new JMenuItem("Update");
		updateBu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UpdateBus bu = new UpdateBus(con);
				bu.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				bu.setVisible(true);

			}
		});
		updateBu.setBackground(Color.WHITE);
		busPM.add(updateBu);

		JMenuItem displayBu = new JMenuItem("Display");
		displayBu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BusController bu = new BusController(con);
				String[][] data = bu.getBuses();
				String[] attributes = { "Bus ID", "Model", "Year" };
				displayTable2(data, attributes);
				displayJTable(data, attributes, scrollPane);
			}
		});
		displayBu.setBackground(Color.WHITE);
		busPM.add(displayBu);

		JButton busButton = new JButton("Bus");
		busButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busPM.show(contentPane, busButton.getX(), busButton.getY() + 30);
			}
		});
		busButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		busButton.setFocusPainted(false);
		busButton.setBackground(SystemColor.controlHighlight);
		busButton.setBounds(430, 80, 190, 30);
		contentPane.add(busButton);

	}

	private static void createConnection() throws Exception {
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

	private static String nullToEmpty(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	private void displayJTable(String[][] data, String[] attributes, JScrollPane scrollPane) {
		table = new JTable(data, attributes);
		table.setRowSelectionAllowed(false);
		table.setShowGrid(false);
		table.setRowSelectionAllowed(false);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setDefaultEditor(Object.class, null); // makes cells uneditable
		table.setFillsViewportHeight(false); // fills in empty rows if set to true
		scrollPane.setViewportView(table);
	}

	private void displayTable2(String[][] data, String[] attributes) { // nicer format
		if (data.length > 0) {
			String[] lcv = largestColumnValues(data, attributes);
			for (int i = 0; i < attributes.length; i++) { // attributes row
				System.out.print(appendSpaces(attributes[i], lcv[i]) + " | "); // "\033[4;2m" for underlining
			}
			// System.out.print("\033[0m"); // stops underlining
			System.out.println();
			for (int i = 0; i < data.length; i++) { // data matrix
				for (int j = 0; j < data[0].length; j++) {
					System.out.print(appendSpaces(nullToEmpty(data[i][j]), lcv[j]) + " | ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	private String[] largestColumnValues(String[][] data, String[] attributes) { // searches data gets largest and sees
																					// if that's bigger than respective
																					// attribute
		String[] largestColumnValues = new String[attributes.length];
		for (int i = 0; i < data[0].length; i++) { // rows
			String largestValue = attributes[i];
			for (int j = 0; j < data.length; j++) { // cols, compare data values
				String dataValue = data[j][i];
				if (dataValue != null && dataValue.length() > largestValue.length()) {
					largestValue = dataValue;
				}
			}
			largestColumnValues[i] = largestValue;
		}
		return largestColumnValues;
	}

	private String appendSpaces(String a, String b) { // appends spaces to a to match b length (helps with formatting
														// table)
		int spacesToAdd = b.length() - a.length();
		for (int i = 0; i < spacesToAdd; i++) {
			a += " ";
		}
		return a;
	}
}
