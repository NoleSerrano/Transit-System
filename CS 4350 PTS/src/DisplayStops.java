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

public class DisplayStops extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tripNumberTextField;

	private String[][] stops;
	private int flag = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DisplayStops dialog = new DisplayStops(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DisplayStops(Connection con) {

		MainMenuController mmc = new MainMenuController(con);
		setModalityType(ModalityType.APPLICATION_MODAL);
		JOptionPane message = new JOptionPane(null);

		setBounds(100, 100, 236, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		setLocationRelativeTo(null); // center

		JLabel titleLabel = new JLabel("Display Stops");
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

		JButton displayButton = new JButton("Display");
		displayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int tripNumber = Integer.valueOf(tripNumberTextField.getText());
					stops = mmc.getStops(tripNumber);
					if (stops.length == 0) {
						message.showMessageDialog(contentPanel, "No stops found with the trip number");
					} else { // success
						flag = 1;
						dispose();
					}
				} catch (Exception e2) {
					System.out.println(e2);
					message.showMessageDialog(contentPanel, "Invalid input");
				}
			}
		});
		displayButton.setBackground(SystemColor.textInactiveText);
		displayButton.setForeground(Color.WHITE);
		displayButton.setBounds(35, 200, 150, 30);
		displayButton.setFocusPainted(false);
		contentPanel.add(displayButton);
	}

	public String[][] getStops() {
		return stops;
	}

	public int getFlag() {
		return flag;
	}
}
