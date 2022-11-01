import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Dialog.ModalityType;
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

public class UpdateTripOffering extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tripNumberTextField;
	private JTextField dateTextField;
	private JTextField scheduledStartTimeTextField;
	private JTextField scheduledArrivalTimeTextField;
	private JTextField driverIDTextField;
	private JTextField busIDTextField;
	private JLabel scheduledArrivalTimeLabel;
	private JLabel driverIDLabel;
	private JLabel busIDLabel;

	private boolean isSelected = false;
	private String[] tripOffering;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateTripOffering dialog = new UpdateTripOffering(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateTripOffering(Connection con) {

		JOptionPane message = new JOptionPane(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		TripOfferingController toc = new TripOfferingController(con);

		setBounds(100, 100, 346, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		setLocationRelativeTo(null); // center

		JLabel titleLabel = new JLabel("Update Trip Offering");
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

		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int tripNumber = Integer.valueOf(tripNumberTextField.getText());
					Date date = Date.valueOf(dateTextField.getText());
					Time scheduledStartTime = formatTime(scheduledStartTimeTextField.getText());
					Time scheduledArrivalTime = formatTime(scheduledArrivalTimeTextField.getText());
					int driverID = Integer.valueOf(driverIDTextField.getText());
					int busID = Integer.valueOf(busIDTextField.getText());

					toc.updateTripOffering(tripNumber, date, scheduledStartTime, scheduledArrivalTime, driverID, busID);
					message.showMessageDialog(contentPanel, "Trip offering updated");
				} catch (Exception e2) {
					System.out.println(e2);
					message.showMessageDialog(contentPanel, "Invalid input");
				}
			}
		});
		updateButton.setBackground(SystemColor.textInactiveText);
		updateButton.setForeground(Color.WHITE);
		updateButton.setBounds(170, 200, 150, 30);
		updateButton.setFocusPainted(false);
		contentPanel.add(updateButton);

		scheduledArrivalTimeTextField = new JTextField();
		scheduledArrivalTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scheduledArrivalTimeTextField.setColumns(10);
		scheduledArrivalTimeTextField.setBounds(170, 60, 150, 25);
		contentPanel.add(scheduledArrivalTimeTextField);

		driverIDTextField = new JTextField();
		driverIDTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverIDTextField.setColumns(10);
		driverIDTextField.setBounds(170, 110, 150, 25);
		contentPanel.add(driverIDTextField);

		busIDTextField = new JTextField();
		busIDTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		busIDTextField.setColumns(10);
		busIDTextField.setBounds(170, 160, 150, 25);
		contentPanel.add(busIDTextField);

		scheduledArrivalTimeLabel = new JLabel("Scheduled Arrival Time");
		scheduledArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scheduledArrivalTimeLabel.setBounds(170, 40, 150, 17);
		contentPanel.add(scheduledArrivalTimeLabel);

		driverIDLabel = new JLabel("Driver ID");
		driverIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverIDLabel.setBounds(170, 90, 150, 17);
		contentPanel.add(driverIDLabel);

		busIDLabel = new JLabel("Bus ID");
		busIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		busIDLabel.setBounds(170, 140, 150, 17);
		contentPanel.add(busIDLabel);

		updateButton.setEnabled(false);
		scheduledArrivalTimeTextField.setEnabled(false);
		scheduledArrivalTimeLabel.setForeground(Color.LIGHT_GRAY);

		driverIDTextField.setEnabled(false);
		driverIDLabel.setForeground(Color.LIGHT_GRAY);

		busIDTextField.setEnabled(false);
		busIDLabel.setForeground(Color.LIGHT_GRAY);

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
							updateButton.setEnabled(true);

							// enable
							scheduledArrivalTimeTextField.setEnabled(true);
							scheduledArrivalTimeTextField.setText(tripOffering[0]);
							scheduledArrivalTimeLabel.setForeground(Color.BLACK);

							driverIDTextField.setEnabled(true);
							driverIDTextField.setText(tripOffering[1]);
							driverIDLabel.setForeground(Color.BLACK);

							busIDTextField.setEnabled(true);
							busIDTextField.setText(tripOffering[2]);
							busIDLabel.setForeground(Color.BLACK);

							// disable
							tripNumberTextField.setEnabled(false);
							tripNumberLabel.setForeground(Color.LIGHT_GRAY);

							dateTextField.setEnabled(false);
							dateLabel.setForeground(Color.LIGHT_GRAY);

							scheduledStartTimeTextField.setEnabled(false);
							scheduledStartTimeLabel.setForeground(Color.LIGHT_GRAY);

							selectButton.setText("Unselect");

						}
					} catch (Exception e2) {
						System.out.println(e2);
						message.showMessageDialog(contentPanel, "Invalid input");
					}
				} else { // item not selected
					isSelected = false;
					updateButton.setEnabled(false);

					// disable
					scheduledArrivalTimeTextField.setEnabled(false);
					scheduledArrivalTimeTextField.setText("");
					scheduledArrivalTimeLabel.setForeground(Color.LIGHT_GRAY);

					driverIDTextField.setEnabled(false);
					driverIDTextField.setText("");
					driverIDLabel.setForeground(Color.LIGHT_GRAY);

					busIDTextField.setEnabled(false);
					busIDTextField.setText("");
					busIDLabel.setForeground(Color.LIGHT_GRAY);

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

					selectButton.setText("Select");

				}
			}
		});
		selectButton.setForeground(Color.WHITE);
		selectButton.setFocusPainted(false);
		selectButton.setBackground(SystemColor.textInactiveText);
		selectButton.setBounds(10, 200, 150, 30);
		contentPanel.add(selectButton);
	}

	private Time formatTime(String s) {
		if (s.length() < 6) { // ex: "12:00"
			return Time.valueOf(s + ":00");
		}
		return Time.valueOf(s);
	}
}
