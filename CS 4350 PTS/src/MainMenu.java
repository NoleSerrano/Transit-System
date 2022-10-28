import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	// nts: space for buttons, tab for swtiching between buttons/text fields, right
	// click for popup menus

	private static Connection con;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
		setBounds(100, 100, 666, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setBackground(new Color(246, 249, 250));
		contentPane.setLayout(null);

		setLocationRelativeTo(null); // centers frame to the screen

		JLabel titleLabel = new JLabel("Transit System");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(30, 11, 590, 17);
		contentPane.add(titleLabel);

		JButton recordData = new JButton("Record Data");
		recordData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				RecordData rd = new RecordData(con);
				rd.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				rd.setVisible(true);

			}
		});
		recordData.setBackground(SystemColor.controlHighlight);
		recordData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		recordData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		recordData.setBounds(30, 39, 140, 30);
		recordData.setFocusPainted(false); // removes ugly focus
		contentPane.add(recordData);

		JButton weeklySchedule = new JButton("Weekly Schedule");
		weeklySchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				WeeklySchedule ws = new WeeklySchedule(con);
				ws.setLocationRelativeTo(contentPane);
				ws.setVisible(true);

			}
		});
		weeklySchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		weeklySchedule.setFont(new Font("Tahoma", Font.PLAIN, 12));
		weeklySchedule.setFocusPainted(false);
		weeklySchedule.setBackground(SystemColor.controlHighlight);
		weeklySchedule.setBounds(180, 39, 140, 30);
		contentPane.add(weeklySchedule);

		JButton displaySchedules = new JButton("Display Schedules");
		displaySchedules.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DisplaySchedules ds = new DisplaySchedules(con);
				ds.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				ds.setVisible(true);

			}
		});
		displaySchedules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		displaySchedules.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displaySchedules.setFocusPainted(false);
		displaySchedules.setBackground(SystemColor.controlHighlight);
		displaySchedules.setBounds(330, 39, 140, 30);
		contentPane.add(displaySchedules);

		JButton displayStops = new JButton("Display Stops");
		displayStops.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DisplayStops ds = new DisplayStops(con);
				ds.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				ds.setVisible(true);

			}
		});
		displayStops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		displayStops.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displayStops.setFocusPainted(false);
		displayStops.setBackground(SystemColor.controlHighlight);
		displayStops.setBounds(480, 39, 140, 30);
		contentPane.add(displayStops);

		JButton driverButton = new JButton("Driver");
		driverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		driverButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverButton.setBackground(SystemColor.controlHighlight);
		driverButton.setBounds(230, 80, 190, 30);
		driverButton.setFocusPainted(false);
		contentPane.add(driverButton);

		JPopupMenu driverPM = new JPopupMenu();
		driverPM.setBackground(Color.WHITE);
		driverPM.setPopupSize(new Dimension(190, 90));
		driverPM.setLabel("");
		addPopup(driverButton, driverPM);

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
		displayDr.setBackground(Color.WHITE);
		driverPM.add(displayDr);

		JButton tripOfferingButton = new JButton("Trip Offering");
		tripOfferingButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tripOfferingButton.setFocusPainted(false);
		tripOfferingButton.setBackground(SystemColor.controlHighlight);
		tripOfferingButton.setBounds(30, 80, 190, 30);
		contentPane.add(tripOfferingButton);

		JPopupMenu tripOfferingPM = new JPopupMenu();
		tripOfferingPM.setBackground(Color.WHITE);
		tripOfferingPM.setPopupSize(new Dimension(190, 90));
		addPopup(tripOfferingButton, tripOfferingPM);

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
		displayTO.setBackground(Color.WHITE);
		tripOfferingPM.add(displayTO);

		JButton busButton = new JButton("Bus");
		busButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		busButton.setFocusPainted(false);
		busButton.setBackground(SystemColor.controlHighlight);
		busButton.setBounds(430, 80, 190, 30);
		contentPane.add(busButton);

		JPopupMenu busPM = new JPopupMenu();
		busPM.setBackground(Color.WHITE);
		busPM.setPopupSize(new Dimension(190, 90));
		addPopup(busButton, busPM);

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
		displayBu.setBackground(Color.WHITE);
		busPM.add(displayBu);

		JPanel redPanel = new JPanel();
		redPanel.setBackground(Color.RED);
		redPanel.setBounds(30, 121, 590, 270);
		contentPane.add(redPanel);

		JPanel yellowPanel = new JPanel();
		yellowPanel.setBackground(Color.YELLOW);
		yellowPanel.setBounds(30, 121, 590, 270);
		contentPane.add(yellowPanel);

		JButton btnNewButton = new JButton("Swap Panels");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redPanel.setVisible(false);
				yellowPanel.setVisible(true);
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(430, 402, 190, 32);
		contentPane.add(btnNewButton);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), popup.getX(), popup.getY() + 30);
			}
		});
	}

	public static void createConnection() throws Exception {
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

}
