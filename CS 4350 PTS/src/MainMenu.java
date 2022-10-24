import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.Choice;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Dimension;

public class MainMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MainMenu dialog = new MainMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MainMenu() {
		setBounds(100, 100, 666, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Transit System");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setBounds(30, 11, 590, 17);
		contentPanel.add(titleLabel);

		JButton recordData = new JButton("Record Data");
		recordData.setBackground(SystemColor.controlHighlight);
		recordData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		recordData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		recordData.setBounds(30, 39, 140, 30);
		recordData.setFocusPainted(false); // removes ugly focus
		contentPanel.add(recordData);

		JButton weeklySchedule = new JButton("Weekly Schedule");
		weeklySchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		weeklySchedule.setFont(new Font("Tahoma", Font.PLAIN, 12));
		weeklySchedule.setFocusPainted(false);
		weeklySchedule.setBackground(SystemColor.controlHighlight);
		weeklySchedule.setBounds(180, 39, 140, 30);
		contentPanel.add(weeklySchedule);

		JButton displaySchedules = new JButton("Display Schedules");
		displaySchedules.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displaySchedules.setFocusPainted(false);
		displaySchedules.setBackground(SystemColor.controlHighlight);
		displaySchedules.setBounds(330, 39, 140, 30);
		contentPanel.add(displaySchedules);

		JButton displayStops = new JButton("Display Stops");
		displayStops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		displayStops.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displayStops.setFocusPainted(false);
		displayStops.setBackground(SystemColor.controlHighlight);
		displayStops.setBounds(480, 39, 140, 30);
		contentPanel.add(displayStops);

		JButton driverButton = new JButton("Driver");
		driverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		driverButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		driverButton.setBackground(SystemColor.controlHighlight);
		driverButton.setBounds(230, 80, 190, 30);
		driverButton.setFocusPainted(false);
		contentPanel.add(driverButton);

		JPopupMenu driverPM = new JPopupMenu();
		driverPM.setLabel("");
		addPopup(driverButton, driverPM);

		JMenuItem addDr = new JMenuItem("Add");
		driverPM.add(addDr);

		JMenuItem deleteDr = new JMenuItem("Delete");
		driverPM.add(deleteDr);

		JMenuItem updateDr = new JMenuItem("Update");
		driverPM.add(updateDr);

		JMenuItem displayDr = new JMenuItem("Display");
		driverPM.add(displayDr);

		JButton tripOfferingButton = new JButton("Trip Offering");
		tripOfferingButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tripOfferingButton.setFocusPainted(false);
		tripOfferingButton.setBackground(SystemColor.controlHighlight);
		tripOfferingButton.setBounds(30, 80, 190, 30);
		contentPanel.add(tripOfferingButton);

		JPopupMenu tripOfferingPM = new JPopupMenu();
		addPopup(tripOfferingButton, tripOfferingPM);

		JMenuItem addTO = new JMenuItem("Add");
		tripOfferingPM.add(addTO);

		JMenuItem deleteTO = new JMenuItem("Delete");
		tripOfferingPM.add(deleteTO);

		JMenuItem updateTO = new JMenuItem("Update");
		tripOfferingPM.add(updateTO);

		JMenuItem displayTO = new JMenuItem("Display");
		tripOfferingPM.add(displayTO);

		JButton busButton = new JButton("Bus");
		busButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		busButton.setFocusPainted(false);
		busButton.setBackground(SystemColor.controlHighlight);
		busButton.setBounds(430, 80, 190, 30);
		contentPanel.add(busButton);

		JPopupMenu busPM = new JPopupMenu();
		addPopup(busButton, busPM);

		JMenuItem addBu = new JMenuItem("Add");
		busPM.add(addBu);

		JMenuItem deleteBu = new JMenuItem("Delete");
		busPM.add(deleteBu);

		JMenuItem updateBu = new JMenuItem("Update");
		busPM.add(updateBu);

		JMenuItem displayBu = new JMenuItem("Display");
		busPM.add(displayBu);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), popup.getX(), popup.getY() + 30);
			}
		});
	}
}
