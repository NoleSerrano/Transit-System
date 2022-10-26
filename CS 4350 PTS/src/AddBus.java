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

public class AddBus extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField modelTextField;
	private JTextField yearTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddBus dialog = new AddBus();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddBus() {
		setBounds(100, 100, 236, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Add Bus");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(10, 11, 200, 17);
		contentPanel.add(titleLabel);
		
		JLabel modelLabel = new JLabel("Model");
		modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		modelLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(modelLabel);
		
		modelTextField = new JTextField();
		modelTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		modelTextField.setBounds(10, 60, 200, 25);
		contentPanel.add(modelTextField);
		modelTextField.setColumns(10);
		
		JLabel yearLabel = new JLabel("Year");
		yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		yearLabel.setBounds(10, 90, 150, 17);
		contentPanel.add(yearLabel);
		
		yearTextField = new JTextField();
		yearTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		yearTextField.setColumns(10);
		yearTextField.setBounds(10, 110, 200, 25);
		contentPanel.add(yearTextField);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setBackground(SystemColor.textInactiveText);
		addButton.setForeground(Color.WHITE);
		addButton.setBounds(35, 200, 150, 30);
		addButton.setFocusPainted(false);
		contentPanel.add(addButton);
	}
}
