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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateBus extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField busIDTextField;
	private JTextField modelTextField;
	private JTextField yearTextField;
	private JLabel modelLabel;
	private JLabel yearLabel;
	private JButton btnSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateBus dialog = new UpdateBus();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateBus() {
		setBounds(100, 100, 346, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

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
