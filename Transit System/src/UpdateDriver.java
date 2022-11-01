import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class UpdateDriver extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField driverIDTextField;
	private JTextField driverNameTextField;
	private JTextField telephoneNumberTextField;
	private JLabel driverNameLabel;
	private JLabel telephoneNumberLabel;
	private JButton btnSelect;

	private boolean isSelected = false;
	private String[] driver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateDriver dialog = new UpdateDriver(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateDriver(Connection con) {

		JOptionPane message = new JOptionPane(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		DriverController dc = new DriverController(con);

		setBounds(100, 100, 346, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		setLocationRelativeTo(null); // center

		JLabel titleLabel = new JLabel("Update Driver");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		titleLabel.setBounds(10, 11, 310, 17);
		contentPanel.add(titleLabel);

		JLabel driverIDLabel = new JLabel("Driver ID");
		driverIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverIDLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(driverIDLabel);

		driverIDTextField = new JTextField();
		driverIDTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverIDTextField.setBounds(10, 60, 150, 25);
		contentPanel.add(driverIDTextField);
		driverIDTextField.setColumns(10);

		driverNameTextField = new JTextField();
		driverNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverNameTextField.setColumns(10);
		driverNameTextField.setBounds(170, 60, 150, 25);
		driverNameTextField.setEnabled(false);
		contentPanel.add(driverNameTextField);

		telephoneNumberTextField = new JTextField();
		telephoneNumberTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		telephoneNumberTextField.setColumns(10);
		telephoneNumberTextField.setBounds(170, 110, 150, 25);
		telephoneNumberTextField.setEnabled(false);
		contentPanel.add(telephoneNumberTextField);

		driverNameLabel = new JLabel("Driver Name");
		driverNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverNameLabel.setBounds(170, 40, 150, 17);
		contentPanel.add(driverNameLabel);

		telephoneNumberLabel = new JLabel("Telephone Number");
		telephoneNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		telephoneNumberLabel.setBounds(170, 90, 150, 17);
		contentPanel.add(telephoneNumberLabel);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int driverID = Integer.valueOf(driverIDTextField.getText());
				String driverName = emptyToNull(driverNameTextField.getText());
				String telephoneNumber = emptyToNull(telephoneNumberTextField.getText());
				dc.updateDriver(driverID, driverName, telephoneNumber);
				message.showMessageDialog(contentPanel, "Driver updated");
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBackground(SystemColor.textInactiveText);
		btnUpdate.setBounds(170, 200, 150, 30);
		btnUpdate.setEnabled(false);
		contentPanel.add(btnUpdate);

		telephoneNumberLabel.setForeground(Color.LIGHT_GRAY);
		driverNameLabel.setForeground(Color.LIGHT_GRAY);

		btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isSelected) { // item selected
					try {
						driver = dc.getDriver(Integer.valueOf(driverIDTextField.getText()));
						if (driver == null) {
							message.showMessageDialog(contentPanel, "No driver found");
						} else { // item found
							isSelected = true;
							btnUpdate.setEnabled(true);

							telephoneNumberTextField.setEnabled(true);
							telephoneNumberTextField.setText(driver[1]);
							telephoneNumberLabel.setForeground(Color.BLACK);

							driverNameTextField.setEnabled(true);
							driverNameTextField.setText(driver[0]);
							driverNameLabel.setForeground(Color.BLACK);

							driverIDTextField.setEnabled(false);
							driverIDLabel.setForeground(Color.LIGHT_GRAY);

							btnSelect.setText("Unselect");
						}
					} catch (Exception e2) {
						System.out.println(e2);
						message.showMessageDialog(contentPanel, "Invalid input");
					}
				} else { // item not selected
					isSelected = false;
					btnUpdate.setEnabled(false);

					telephoneNumberTextField.setEnabled(false);
					telephoneNumberTextField.setText("");
					telephoneNumberLabel.setForeground(Color.LIGHT_GRAY);

					driverNameTextField.setEnabled(false);
					driverNameTextField.setText("");
					driverNameLabel.setForeground(Color.LIGHT_GRAY);

					driverIDTextField.setEnabled(true);
					driverIDTextField.setText("");
					driverIDLabel.setForeground(Color.BLACK);

					btnSelect.setText("Select");
				}
			}
		});
		btnSelect.setForeground(Color.WHITE);
		btnSelect.setFocusPainted(false);
		btnSelect.setBackground(SystemColor.textInactiveText);
		btnSelect.setBounds(10, 200, 150, 30);
		contentPanel.add(btnSelect);
	}

	private String emptyToNull(String s) {
		if (s.isEmpty()) {
			return null;
		}
		return s;
	}
}
