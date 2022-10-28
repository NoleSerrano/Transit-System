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

public class WeeklySchedule extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField driverIDTextField;
	private JTextField dateTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WeeklySchedule dialog = new WeeklySchedule(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WeeklySchedule(Connection con) {
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		setBounds(100, 100, 236, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Weekly Schedule");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(10, 11, 200, 17);
		contentPanel.add(titleLabel);
		
		JLabel driverIDLabel = new JLabel("Driver ID");
		driverIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverIDLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(driverIDLabel);
		
		driverIDTextField = new JTextField();
		driverIDTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverIDTextField.setBounds(10, 60, 200, 25);
		contentPanel.add(driverIDTextField);
		driverIDTextField.setColumns(10);
		
		JLabel dateLabel = new JLabel("Date (YYYY-MM-DD)");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateLabel.setBounds(10, 90, 150, 17);
		contentPanel.add(dateLabel);
		
		dateTextField = new JTextField();
		dateTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateTextField.setColumns(10);
		dateTextField.setBounds(10, 110, 200, 25);
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
