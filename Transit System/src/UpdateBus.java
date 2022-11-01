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

public class UpdateBus extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField busIDTextField;
	private JTextField modelTextField;
	private JTextField yearTextField;
	private JLabel modelLabel;
	private JLabel yearLabel;
	private JButton btnSelect;

	private boolean isSelected = false;
	private String[] bus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateBus dialog = new UpdateBus(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateBus(Connection con) {

		JOptionPane message = new JOptionPane(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		BusController bc = new BusController(con);

		setBounds(100, 100, 346, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		setLocationRelativeTo(null); // center

		JLabel titleLabel = new JLabel("Update Bus");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		titleLabel.setBounds(10, 11, 310, 17);
		contentPanel.add(titleLabel);

		JLabel busIDLabel = new JLabel("Bus ID");
		busIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		busIDLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(busIDLabel);

		busIDTextField = new JTextField();
		busIDTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		busIDTextField.setBounds(10, 60, 150, 25);
		contentPanel.add(busIDTextField);
		busIDTextField.setColumns(10);

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

		modelTextField = new JTextField();
		modelTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		modelTextField.setColumns(10);
		modelTextField.setBounds(170, 60, 150, 25);
		contentPanel.add(modelTextField);

		yearTextField = new JTextField();
		yearTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		yearTextField.setColumns(10);
		yearTextField.setBounds(170, 110, 150, 25);
		contentPanel.add(yearTextField);

		modelLabel = new JLabel("Model");
		modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		modelLabel.setBounds(170, 40, 150, 17);
		contentPanel.add(modelLabel);

		yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		yearLabel.setBounds(170, 90, 150, 17);
		contentPanel.add(yearLabel);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int busID = Integer.valueOf(busIDTextField.getText());
				String model = emptyToNull(modelTextField.getText());
				String year = emptyToNull(yearTextField.getText());
				int flag = bc.updateBus(busID, model, year);
				if (flag == 0) {
					message.showMessageDialog(contentPanel, "Invalid year");
				} else {
					message.showMessageDialog(contentPanel, "Bus updated");
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBackground(SystemColor.textInactiveText);
		btnUpdate.setBounds(170, 200, 150, 30);
		contentPanel.add(btnUpdate);

		btnUpdate.setEnabled(false);
		yearTextField.setEnabled(false);
		modelTextField.setEnabled(false);
		yearLabel.setForeground(Color.LIGHT_GRAY);
		modelLabel.setForeground(Color.LIGHT_GRAY);

		btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isSelected) { // item selected
					try {
						bus = bc.getBus(Integer.valueOf(busIDTextField.getText()));
						if (bus == null) {
							message.showMessageDialog(contentPanel, "No bus found");
						} else { // item found
							isSelected = true;
							btnUpdate.setEnabled(true);
							
							yearTextField.setEnabled(true);
							yearTextField.setText(stringNullToEmpty(bus[1]));
							yearLabel.setForeground(Color.BLACK);
							
							modelTextField.setEnabled(true);
							modelTextField.setText(bus[0]);
							modelLabel.setForeground(Color.BLACK);
							
							busIDTextField.setEnabled(false);
							busIDLabel.setForeground(Color.LIGHT_GRAY);
							
							btnSelect.setText("Unselect");
							
						}
					} catch (Exception e2) {
						System.out.println(e2);
						message.showMessageDialog(contentPanel, "Invalid input");
					}
				} else { // item not selected
					isSelected = false;
					btnUpdate.setEnabled(false);
					
					yearTextField.setEnabled(false);
					yearTextField.setText("");
					yearLabel.setForeground(Color.LIGHT_GRAY);
					
					modelTextField.setEnabled(false);
					modelTextField.setText("");
					modelLabel.setForeground(Color.LIGHT_GRAY);
					
					busIDTextField.setEnabled(true);
					busIDTextField.setText("");
					busIDLabel.setForeground(Color.BLACK);
					
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

	private String stringNullToEmpty(String s) { // should only be used for data types ints
		if (s == "null") {
			return "";
		}
		return s;
	}
}
