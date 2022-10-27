import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Color;

public class MainMenu extends JFrame {

	// nts: space for buttons, tab for swtiching between buttons/text fields, right
	// click for popup menus

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

		JLabel titleLabel = new JLabel("Transit System");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(30, 11, 590, 17);
		contentPane.add(titleLabel);

		JButton recordData = new JButton("Record Data");
		recordData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				disable();
				RecordData rd = new RecordData();
				rd.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				rd.setVisible(true);
				rd.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});

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
				disable();
				WeeklySchedule ws = new WeeklySchedule();
				ws.setLocationRelativeTo(contentPane);
				ws.setVisible(true);
				ws.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
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
				disable();
				DisplaySchedules ds = new DisplaySchedules();
				ds.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				ds.setVisible(true);
				ds.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
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
				disable();
				DisplayStops ds = new DisplayStops();
				ds.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				ds.setVisible(true);
				ds.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
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
				disable();
				AddDriver dr = new AddDriver();
				dr.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				dr.setVisible(true);
				dr.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
			}
		});
		addDr.setBackground(Color.WHITE);
		driverPM.add(addDr);

		JMenuItem deleteDr = new JMenuItem("Delete");
		deleteDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disable();
				DeleteDriver dr = new DeleteDriver();
				dr.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				dr.setVisible(true);
				dr.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
			}
		});
		deleteDr.setBackground(Color.WHITE);
		driverPM.add(deleteDr);

		JMenuItem updateDr = new JMenuItem("Update");
		updateDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disable();
				UpdateDriver dr = new UpdateDriver();
				dr.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				dr.setVisible(true);
				dr.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
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
				disable();
				AddTripOffering to = new AddTripOffering();
				to.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				to.setVisible(true);
				to.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
			}
		});
		addTO.setBackground(Color.WHITE);
		tripOfferingPM.add(addTO);

		JMenuItem deleteTO = new JMenuItem("Delete");
		deleteTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disable();
				DeleteTripOffering to = new DeleteTripOffering();
				to.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				to.setVisible(true);
				to.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
			}
		});
		deleteTO.setBackground(Color.WHITE);
		tripOfferingPM.add(deleteTO);

		JMenuItem updateTO = new JMenuItem("Update");
		updateTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disable();
				UpdateTripOffering to = new UpdateTripOffering();
				to.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				to.setVisible(true);
				to.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
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
				disable();
				AddBus bu = new AddBus();
				bu.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				bu.setVisible(true);
				bu.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
			}
		});
		addBu.setBackground(Color.WHITE);
		busPM.add(addBu);

		JMenuItem deleteBu = new JMenuItem("Delete");
		deleteBu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disable();
				DeleteBus bu = new DeleteBus();
				bu.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				bu.setVisible(true);
				bu.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
			}
		});
		deleteBu.setBackground(Color.WHITE);
		busPM.add(deleteBu);

		JMenuItem updateBu = new JMenuItem("Update");
		updateBu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disable();
				UpdateBus bu = new UpdateBus();
				bu.setLocationRelativeTo(contentPane); // puts it in middle of this frame
				bu.setVisible(true);
				bu.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						enable();
					}
				});
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

			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
