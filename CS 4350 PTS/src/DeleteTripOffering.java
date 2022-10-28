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
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class DeleteTripOffering extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tripNumberTextField;
	private JTextField dateTextField;
	private JTextField scheduledStartTimeTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DeleteTripOffering dialog = new DeleteTripOffering(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DeleteTripOffering(Connection con) {
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		setBounds(100, 100, 236, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Delete Trip Offering");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(10, 11, 200, 17);
		contentPanel.add(titleLabel);
		
		JLabel tripNumberLabel = new JLabel("Trip Number");
		tripNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tripNumberLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(tripNumberLabel);
		
		tripNumberTextField = new JTextField();
		tripNumberTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tripNumberTextField.setBounds(10, 60, 200, 25);
		contentPanel.add(tripNumberTextField);
		tripNumberTextField.setColumns(10);
		
		JLabel dateLabel = new JLabel("Date (YYYY-MM-DD)");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateLabel.setBounds(10, 90, 150, 17);
		contentPanel.add(dateLabel);
		
		dateTextField = new JTextField();
		dateTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateTextField.setColumns(10);
		dateTextField.setBounds(10, 110, 200, 25);
		contentPanel.add(dateTextField);
		
		JLabel scheduledStartTimeLabel = new JLabel("Scheduled Start Time");
		scheduledStartTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scheduledStartTimeLabel.setBounds(10, 140, 150, 17);
		contentPanel.add(scheduledStartTimeLabel);
		
		scheduledStartTimeTextField = new JTextField();
		scheduledStartTimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scheduledStartTimeTextField.setColumns(10);
		scheduledStartTimeTextField.setBounds(10, 160, 200, 25);
		contentPanel.add(scheduledStartTimeTextField);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteButton.setBackground(SystemColor.textInactiveText);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBounds(35, 200, 150, 30);
		deleteButton.setFocusPainted(false);
		contentPanel.add(deleteButton);
	}
}
