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

public class DeleteBus extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField busIDTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DeleteBus dialog = new DeleteBus(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DeleteBus(Connection con) {

		JOptionPane message = new JOptionPane(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		BusController bc = new BusController(con);

		setBounds(100, 100, 236, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		setLocationRelativeTo(null); // center

		JLabel titleLabel = new JLabel("Delete Bus");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(10, 11, 200, 17);
		contentPanel.add(titleLabel);

		JLabel busIDLabel = new JLabel("Bus ID");
		busIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		busIDLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(busIDLabel);

		busIDTextField = new JTextField();
		busIDTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		busIDTextField.setBounds(10, 60, 200, 25);
		contentPanel.add(busIDTextField);
		busIDTextField.setColumns(10);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String busID = busIDTextField.getText();
				try {
					int flag = bc.deleteBus(Integer.valueOf(busID));
					if (flag == 0) {
						message.showMessageDialog(contentPanel, "Bus not found");
					} else {
						message.showMessageDialog(contentPanel, "Bus deleted");
						busIDTextField.setText("");
					}
				} catch (Exception e2) {
					System.out.println(e2);
					message.showMessageDialog(contentPanel, "Invalid input");
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
