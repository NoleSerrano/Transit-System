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
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class DeleteDriver extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField driverIDTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DeleteDriver dialog = new DeleteDriver(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DeleteDriver(Connection con) {

		JOptionPane message = new JOptionPane(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		DriverController dc = new DriverController(con);

		setBounds(100, 100, 236, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		setLocationRelativeTo(null); // center

		JLabel titleLabel = new JLabel("Delete Driver");
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

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String driverID = driverIDTextField.getText();
				try {
					boolean driverFound = dc.deleteDriver(Integer.valueOf(driverID));
					if (!driverFound) {
						message.showMessageDialog(null, "Driver not found");
					} else {
						message.showMessageDialog(null, "Driver deleted");
					}
				} catch (Exception e2) {
					System.out.println(e2);
					message.showMessageDialog(null, "Invalid input");
				}

			}
		});
		deleteButton.setBackground(SystemColor.textInactiveText);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBounds(35, 200, 150, 30);
		deleteButton.setFocusPainted(false);
		contentPanel.add(deleteButton);
	}
}
