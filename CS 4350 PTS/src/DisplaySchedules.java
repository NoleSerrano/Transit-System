import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DisplaySchedules extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField startLocationNameTextField;
	private JTextField destinationNameTextField;
	private JTextField dateTextField;

	private String[][] sch;
	private int flag = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DisplaySchedules dialog = new DisplaySchedules(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DisplaySchedules(Connection con) {

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
				try {
					String startLocationName = startLocationNameTextField.getText();
					String destinationName = destinationNameTextField.getText();
					Date date = Date.valueOf(dateTextField.getText());
					sch = mmc.getSchedules(startLocationName, destinationName, date);
					if (sch.length == 0) {
						message.showMessageDialog(contentPanel, "No schedules were found with the given input");
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

	public String[][] getSchedules() {
		return sch;
	}

	public int getFlag() {
		return flag;
	}
}
