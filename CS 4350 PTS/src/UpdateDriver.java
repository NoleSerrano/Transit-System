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

public class UpdateDriver extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField driverIDTextField;
	private JTextField driverNameTextField;
	private JTextField telephoneNumberTextField;
	private JLabel driverNameLabel;
	private JLabel telephoneNumberLabel;
	private JButton btnSelect;

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
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		
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

		JButton recordButton = new JButton("Select");
		recordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		recordButton.setBackground(SystemColor.textInactiveText);
		recordButton.setForeground(Color.WHITE);
		recordButton.setBounds(90, 250, 150, 30);
		recordButton.setFocusPainted(false);
		contentPanel.add(recordButton);

		driverNameTextField = new JTextField();
		driverNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverNameTextField.setColumns(10);
		driverNameTextField.setBounds(170, 60, 150, 25);
		contentPanel.add(driverNameTextField);

		telephoneNumberTextField = new JTextField();
		telephoneNumberTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		telephoneNumberTextField.setColumns(10);
		telephoneNumberTextField.setBounds(170, 110, 150, 25);
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
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBackground(SystemColor.textInactiveText);
		btnUpdate.setBounds(170, 200, 150, 30);
		contentPanel.add(btnUpdate);

		btnSelect = new JButton("Select");
		btnSelect.setForeground(Color.WHITE);
		btnSelect.setFocusPainted(false);
		btnSelect.setBackground(SystemColor.textInactiveText);
		btnSelect.setBounds(10, 200, 150, 30);
		contentPanel.add(btnSelect);
	}
}
