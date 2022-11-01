import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RecordData extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tripNumberTextField;
	private JTextField dateTextField;
	private JTextField scheduledStartTimeTextField;
	private JTextField actualStartTimeTextField;
	private JTextField actualArrivalTimeTextField;
	private JTextField numberOfPassengersInTextField;
	private JLabel actualStartTimeLabel;
	private JLabel actualArrivalTimeLabel;
	private JLabel numberOfPassengersInLabel;
	private JLabel stopNumberLabel;
	private JTextField stopNumberTextField;
	private JLabel numberOfPassengersOutLabel;
	private JTextField numberOfPassengersOutTextField;

	private boolean isSelected = false;
	private String[] tripOffering;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RecordData dialog = new RecordData(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecordData(Connection con) {

		MainMenuController mmc = new MainMenuController(con);
		TripOfferingController toc = new TripOfferingController(con);
		JOptionPane message = new JOptionPane(null);
		setModalityType(ModalityType.APPLICATION_MODAL);

		setBounds(100, 100, 346, 334);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		setLocationRelativeTo(null); // center

		JLabel titleLabel = new JLabel("Record Data of Trip Offering");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		titleLabel.setBounds(10, 11, 310, 17);
		contentPanel.add(titleLabel);

		JLabel tripNumberLabel = new JLabel("Trip Number");
		tripNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tripNumberLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(tripNumberLabel);

		tripNumberTextField = new JTextField();
		tripNumberTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tripNumberTextField.setBounds(10, 60, 150, 25);
		contentPanel.add(tripNumberTextField);
		tripNumberTextField.setColumns(10);

		JLabel dateLabel = new JLabel("Date (YYYY-MM-DD)");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateLabel.setBounds(10, 90, 150, 17);
		contentPanel.add(dateLabel);

		dateTextField = new JTextField();
		dateTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateTextField.setColumns(10);
		dateTextField.setBounds(10, 110, 150, 25);
		contentPanel.add(dateTextField);

		JLabel scheduledStartTimeLabel = new JLabel("Scheduled Start Time");
		scheduledStartTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scheduledStartTimeLabel.setBounds(10, 140, 150, 17);
		contentPanel.add(scheduledStartTimeLabel);

		scheduledStartTimeTextField = new JTextField();
		scheduledStartTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scheduledStartTimeTextField.setColumns(10);
		scheduledStartTimeTextField.setBounds(10, 160, 150, 25);
		contentPanel.add(scheduledStartTimeTextField);

		JButton recordButton = new JButton("Record");
		recordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int tripNumber = Integer.valueOf(tripNumberTextField.getText());
					Date date = Date.valueOf(dateTextField.getText());
					Time scheduledStartTime = formatTime(scheduledStartTimeTextField.getText());
					int stopNumber = Integer.valueOf(stopNumberTextField.getText());
					Time scheduledArrivalTime = Time.valueOf(tripOffering[0]);
					Time actualStartTime = formatTime(actualStartTimeTextField.getText());
					Time actualArrivalTime = formatTime(actualArrivalTimeTextField.getText());
					int numberOfPassengersIn = Integer.valueOf(numberOfPassengersInTextField.getText());
					int numberOfPassengersOut = Integer.valueOf(numberOfPassengersOutTextField.getText());
					mmc.recordData(tripNumber, date, scheduledStartTime, stopNumber, scheduledArrivalTime,
							actualStartTime, actualArrivalTime, numberOfPassengersIn, numberOfPassengersOut);
					message.showMessageDialog(contentPanel, "Data recorded");
				} catch (Exception e2) {
					System.out.println(e2);
					message.showMessageDialog(contentPanel, "Invalid input");
				}
			}
		});
		recordButton.setBackground(SystemColor.textInactiveText);
		recordButton.setForeground(Color.WHITE);
		recordButton.setBounds(170, 250, 150, 30);
		recordButton.setFocusPainted(false);
		contentPanel.add(recordButton);

		actualStartTimeTextField = new JTextField();
		actualStartTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		actualStartTimeTextField.setColumns(10);
		actualStartTimeTextField.setBounds(170, 60, 150, 25);
		contentPanel.add(actualStartTimeTextField);

		actualArrivalTimeTextField = new JTextField();
		actualArrivalTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		actualArrivalTimeTextField.setColumns(10);
		actualArrivalTimeTextField.setBounds(170, 110, 150, 25);
		contentPanel.add(actualArrivalTimeTextField);

		numberOfPassengersInTextField = new JTextField();
		numberOfPassengersInTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		numberOfPassengersInTextField.setColumns(10);
		numberOfPassengersInTextField.setBounds(170, 160, 150, 25);
		contentPanel.add(numberOfPassengersInTextField);

		actualStartTimeLabel = new JLabel("Actual Start Time");
		actualStartTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		actualStartTimeLabel.setBounds(170, 40, 150, 17);
		contentPanel.add(actualStartTimeLabel);

		actualArrivalTimeLabel = new JLabel("Actual Arrival Time");
		actualArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		actualArrivalTimeLabel.setBounds(170, 90, 150, 17);
		contentPanel.add(actualArrivalTimeLabel);

		numberOfPassengersInLabel = new JLabel("Number of Passengers In");
		numberOfPassengersInLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		numberOfPassengersInLabel.setBounds(170, 140, 150, 17);
		contentPanel.add(numberOfPassengersInLabel);

		stopNumberLabel = new JLabel("Stop Number");
		stopNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		stopNumberLabel.setBounds(10, 190, 150, 17);
		contentPanel.add(stopNumberLabel);

		stopNumberTextField = new JTextField();
		stopNumberTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		stopNumberTextField.setColumns(10);
		stopNumberTextField.setBounds(10, 210, 150, 25);
		contentPanel.add(stopNumberTextField);

		numberOfPassengersOutLabel = new JLabel("Number of Passengers Out");
		numberOfPassengersOutLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		numberOfPassengersOutLabel.setBounds(170, 190, 150, 17);
		contentPanel.add(numberOfPassengersOutLabel);

		numberOfPassengersOutTextField = new JTextField();
		numberOfPassengersOutTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		numberOfPassengersOutTextField.setColumns(10);
		numberOfPassengersOutTextField.setBounds(170, 210, 150, 25);
		contentPanel.add(numberOfPassengersOutTextField);

		recordButton.setEnabled(false);
		actualStartTimeTextField.setEnabled(false);
		actualStartTimeLabel.setForeground(Color.LIGHT_GRAY);
		actualArrivalTimeTextField.setEnabled(false);
		actualArrivalTimeLabel.setForeground(Color.LIGHT_GRAY);
		numberOfPassengersInTextField.setEnabled(false);
		numberOfPassengersInLabel.setForeground(Color.LIGHT_GRAY);
		numberOfPassengersOutTextField.setEnabled(false);
		numberOfPassengersOutLabel.setForeground(Color.LIGHT_GRAY);

		JButton selectButton = new JButton("Select");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isSelected) { // item selected
					try {
						int tripNumber = Integer.valueOf(tripNumberTextField.getText());
						Date date = Date.valueOf(dateTextField.getText());
						Time scheduledStartTime = formatTime(scheduledStartTimeTextField.getText());
						tripOffering = toc.getTripOffering(tripNumber, date, scheduledStartTime);
						if (tripOffering == null) {
							message.showMessageDialog(contentPanel, "No trip offering found");
						} else { // item found
							isSelected = true;
							recordButton.setEnabled(true);

							// enable
							actualStartTimeTextField.setEnabled(true);
							actualStartTimeLabel.setForeground(Color.BLACK);

							actualArrivalTimeTextField.setEnabled(true);
							actualArrivalTimeLabel.setForeground(Color.BLACK);

							numberOfPassengersInTextField.setEnabled(true);
							numberOfPassengersInLabel.setForeground(Color.BLACK);

							numberOfPassengersOutTextField.setEnabled(true);
							numberOfPassengersOutLabel.setForeground(Color.BLACK);

							// disable
							tripNumberTextField.setEnabled(false);
							tripNumberLabel.setForeground(Color.LIGHT_GRAY);

							dateTextField.setEnabled(false);
							dateLabel.setForeground(Color.LIGHT_GRAY);

							scheduledStartTimeTextField.setEnabled(false);
							scheduledStartTimeLabel.setForeground(Color.LIGHT_GRAY);

							stopNumberTextField.setEnabled(false);
							stopNumberLabel.setForeground(Color.LIGHT_GRAY);

							selectButton.setText("Unselect");

						}
					} catch (Exception e2) {
						System.out.println(e2);
						message.showMessageDialog(contentPanel, "Invalid input");
					}
				} else { // item not selected
					isSelected = false;
					recordButton.setEnabled(false);

					// disable
					actualStartTimeTextField.setEnabled(false);
					actualStartTimeTextField.setText("");
					actualStartTimeLabel.setForeground(Color.LIGHT_GRAY);

					actualArrivalTimeTextField.setEnabled(false);
					actualArrivalTimeTextField.setText("");
					actualArrivalTimeLabel.setForeground(Color.LIGHT_GRAY);

					numberOfPassengersInTextField.setEnabled(false);
					numberOfPassengersInTextField.setText("");
					numberOfPassengersInLabel.setForeground(Color.LIGHT_GRAY);

					numberOfPassengersOutTextField.setEnabled(false);
					numberOfPassengersOutTextField.setText("");
					numberOfPassengersOutLabel.setForeground(Color.LIGHT_GRAY);

					// enable
					tripNumberTextField.setEnabled(true);
					tripNumberTextField.setText("");
					tripNumberLabel.setForeground(Color.BLACK);

					dateTextField.setEnabled(true);
					dateTextField.setText("");
					dateLabel.setForeground(Color.BLACK);

					scheduledStartTimeTextField.setEnabled(true);
					scheduledStartTimeTextField.setText("");
					scheduledStartTimeLabel.setForeground(Color.BLACK);

					stopNumberTextField.setEnabled(true);
					stopNumberTextField.setText("");
					stopNumberLabel.setForeground(Color.BLACK);

					selectButton.setText("Select");

				}
			}
		});
		selectButton.setForeground(Color.WHITE);
		selectButton.setFocusPainted(false);
		selectButton.setBackground(SystemColor.textInactiveText);
		selectButton.setBounds(10, 250, 150, 30);
		contentPanel.add(selectButton);

	}

	private Time formatTime(String s) {
		if (s.length() < 6) { // ex: "12:00"
			return Time.valueOf(s + ":00");
		}
		return Time.valueOf(s);
	}
}
