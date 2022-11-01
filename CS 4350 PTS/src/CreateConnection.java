import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CreateConnection extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField urlTextField;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateConnection dialog = new CreateConnection();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateConnection() {

		JOptionPane message = new JOptionPane(null);
		setModalityType(ModalityType.APPLICATION_MODAL);

		setBounds(100, 100, 236, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(246, 249, 250));
		setResizable(false);
		contentPanel.setLayout(null);

		setLocationRelativeTo(null); // center

		JLabel titleLabel = new JLabel("Create Connection");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		titleLabel.setBounds(10, 11, 200, 17);
		contentPanel.add(titleLabel);

		JLabel urlLabel = new JLabel("Database URL");
		urlLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		urlLabel.setBounds(10, 40, 150, 17);
		contentPanel.add(urlLabel);

		urlTextField = new JTextField();
		urlTextField.setToolTipText("example: \"localhost:3306/pts\"");
		urlTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		urlTextField.setBounds(10, 60, 200, 25);
		contentPanel.add(urlTextField);
		urlTextField.setColumns(10);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameLabel.setBounds(10, 90, 150, 17);
		contentPanel.add(usernameLabel);

		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(10, 110, 200, 25);
		contentPanel.add(usernameTextField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordLabel.setBounds(10, 140, 150, 17);
		contentPanel.add(passwordLabel);

		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String driver = "com.mysql.cj.jdbc.Driver";
//					String url = "jdbc:mysql://localhost:3306/pts";
//					String username = "root";
//					String password = "DBpassword1";
					String url = urlTextField.getText();
					if (!url.substring(0, 8).equals("mysql://")) {
						url = "mysql://" + url;
					}
					url = "jdbc:" + url;
					String username = usernameTextField.getText();
					String password = String.valueOf(passwordTextField.getPassword());
					Class.forName(driver);

					Connection con = DriverManager.getConnection(url, username, password);
					System.out.println("Connected");
					dispose(); // rid of this frame
					MainMenu mm = new MainMenu(con);
					mm.setLocationRelativeTo(contentPanel); // puts it in middle of this frame
					mm.setVisible(true);
				} catch (SQLException | ClassNotFoundException e2) {
					System.out.println(e2);
					message.showMessageDialog(contentPanel, e2);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		connectButton.setBackground(SystemColor.textInactiveText);
		connectButton.setForeground(Color.WHITE);
		connectButton.setBounds(35, 200, 150, 30);
		connectButton.setFocusPainted(false);
		contentPanel.add(connectButton);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(10, 160, 200, 25);
		contentPanel.add(passwordTextField);
	}
}
