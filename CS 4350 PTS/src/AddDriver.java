import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class AddDriver extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField driverNameTextField;
	private JTextField telephoneNumberTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddDriver dialog = new AddDriver(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddDriver(Connection con) {
		
		setModalityType(ModalityType.APPLICATION_MODAL); // stops main menu from being used
		DriverController dc = new DriverController(con);
		
		setBounds(100, 100, 236, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Add Driver");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(10, 11, 200, 17);
		contentPanel.add(titleLabel);
		
		JLabel driverNameLabel = new JLabel("Driver Name");
		driverNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverNameLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(driverNameLabel);
		
		driverNameTextField = new JTextField();
		driverNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverNameTextField.setBounds(10, 60, 200, 25);
		contentPanel.add(driverNameTextField);
		driverNameTextField.setColumns(10);
		
		JLabel telephoneNumberLabel = new JLabel("Telephone Number");
		telephoneNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		telephoneNumberLabel.setBounds(10, 90, 150, 17);
		contentPanel.add(telephoneNumberLabel);
		
		telephoneNumberTextField = new JTextField();
		telephoneNumberTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		telephoneNumberTextField.setColumns(10);
		telephoneNumberTextField.setBounds(10, 110, 200, 25);
		contentPanel.add(telephoneNumberTextField);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String driverName = driverNameTextField.getText();
				String telephoneNumber = telephoneNumberTextField.getText();
				dc.addDriver(driverName, telephoneNumber);
				JOptionPane message = new JOptionPane("TEST");
				message.showMessageDialog(null, "Driver added");
			}
		});
		addButton.setBackground(SystemColor.textInactiveText);
		addButton.setForeground(Color.WHITE);
		addButton.setBounds(35, 200, 150, 30);
		addButton.setFocusPainted(false);
		contentPanel.add(addButton);
	}
}
