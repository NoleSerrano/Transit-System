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

public class DisplayStops extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tripNumberTextField;
	private JTextField stopNumberTextField;

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
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		setBounds(100, 100, 236, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

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
		
		JLabel stopNumberLabel = new JLabel("Stop Number");
		stopNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		stopNumberLabel.setBounds(10, 90, 150, 17);
		contentPanel.add(stopNumberLabel);
		
		stopNumberTextField = new JTextField();
		stopNumberTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		stopNumberTextField.setColumns(10);
		stopNumberTextField.setBounds(10, 110, 200, 25);
		contentPanel.add(stopNumberTextField);
		
		JButton displayButton = new JButton("Display");
		displayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		displayButton.setBackground(SystemColor.textInactiveText);
		displayButton.setForeground(Color.WHITE);
		displayButton.setBounds(35, 200, 150, 30);
		displayButton.setFocusPainted(false);
		contentPanel.add(displayButton);
	}
}
