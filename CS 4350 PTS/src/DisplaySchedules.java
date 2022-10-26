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

public class DisplaySchedules extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField startLocationNameTextField;
	private JTextField destinationNameTextField;
	private JTextField dateTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DisplaySchedules dialog = new DisplaySchedules();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DisplaySchedules() {
		setBounds(100, 100, 236, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Display Schedules");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(10, 11, 200, 17);
		contentPanel.add(titleLabel);
		
		JLabel startLocationNameLabel = new JLabel("Start Location Name");
		startLocationNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		startLocationNameLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(startLocationNameLabel);
		
		startLocationNameTextField = new JTextField();
		startLocationNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		startLocationNameTextField.setBounds(10, 60, 200, 25);
		contentPanel.add(startLocationNameTextField);
		startLocationNameTextField.setColumns(10);
		
		JLabel destinationNameLabel = new JLabel("Destination Name");
		destinationNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		destinationNameLabel.setBounds(10, 90, 150, 17);
		contentPanel.add(destinationNameLabel);
		
		destinationNameTextField = new JTextField();
		destinationNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		destinationNameTextField.setColumns(10);
		destinationNameTextField.setBounds(10, 110, 200, 25);
		contentPanel.add(destinationNameTextField);
		
		JLabel dateLabel = new JLabel("Date (YYYY-MM-DD)");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateLabel.setBounds(10, 140, 150, 17);
		contentPanel.add(dateLabel);
		
		dateTextField = new JTextField();
		dateTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateTextField.setColumns(10);
		dateTextField.setBounds(10, 160, 200, 25);
		contentPanel.add(dateTextField);
		
		JButton displayButton = new JButton("Display");
		displayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		displayButton.setBackground(SystemColor.textInactiveText);
		displayButton.setForeground(Color.WHITE);
		displayButton.setBounds(35, 200, 150, 30);
		displayButton.setFocusPainted(false);
		contentPanel.add(displayButton);
	}
}
