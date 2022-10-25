import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecordData extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField startLocationNameTextField;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RecordData dialog = new RecordData();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecordData() {
		setBounds(100, 100, 346, 334);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Record Data of Trip Offering");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(10, 11, 310, 17);
		contentPanel.add(titleLabel);
		
		JLabel tripNumberLabel = new JLabel("Trip Number");
		tripNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tripNumberLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(tripNumberLabel);
		
		startLocationNameTextField = new JTextField();
		startLocationNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		startLocationNameTextField.setBounds(10, 60, 150, 25);
		contentPanel.add(startLocationNameTextField);
		startLocationNameTextField.setColumns(10);
		
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
			}
		});
		recordButton.setBackground(SystemColor.textInactiveText);
		recordButton.setForeground(Color.WHITE);
		recordButton.setBounds(90, 250, 150, 30);
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
	}
}
