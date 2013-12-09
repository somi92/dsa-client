package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MainWindow {

	private JFrame frmMain;
	private JTextField txtArray;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMain.setLocationRelativeTo(null);
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setTitle("Aplikacija za distribuirano sortiranje");
		frmMain.setResizable(false);
		frmMain.setBounds(100, 100, 696, 626);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelServer = new JPanel();
		panelServer.setBackground(Color.LIGHT_GRAY);
		frmMain.getContentPane().add(panelServer, BorderLayout.NORTH);
		GridBagLayout gbl_panelServer = new GridBagLayout();
		gbl_panelServer.columnWidths = new int[]{59, 111, 264, 285, 264, 0};
		gbl_panelServer.rowHeights = new int[]{0, 30, 35, 35, 35, 0};
		gbl_panelServer.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelServer.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panelServer.setLayout(gbl_panelServer);
		
		JButton btnConnect = new JButton("Prijavi se na glavni server");
		btnConnect.setPreferredSize(new Dimension(240, 35));
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainServerDialog dialog = new MainServerDialog();
				dialog.setVisible(true);
				frmMain.setEnabled(false);
			}
		});
		
		JLabel lblAplikacijaZaDistribuirano = new JLabel("Aplikacija za distribuirano sortiranje");
		lblAplikacijaZaDistribuirano.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblAplikacijaZaDistribuirano = new GridBagConstraints();
		gbc_lblAplikacijaZaDistribuirano.insets = new Insets(0, 0, 5, 5);
		gbc_lblAplikacijaZaDistribuirano.gridx = 2;
		gbc_lblAplikacijaZaDistribuirano.gridy = 0;
		panelServer.add(lblAplikacijaZaDistribuirano, gbc_lblAplikacijaZaDistribuirano);
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.fill = GridBagConstraints.BOTH;
		gbc_btnConnect.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnect.gridx = 2;
		gbc_btnConnect.gridy = 2;
		panelServer.add(btnConnect, gbc_btnConnect);
		
		JLabel lblStatus = new JLabel("Niste prijavljeni na glavni server.");
		lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.fill = GridBagConstraints.VERTICAL;
		gbc_lblStatus.gridx = 3;
		gbc_lblStatus.gridy = 2;
		panelServer.add(lblStatus, gbc_lblStatus);
		
		JButton btnDisconnect = new JButton("Odjavi se sa glavnog servera");
		btnDisconnect.setEnabled(false);
		btnDisconnect.setPreferredSize(new Dimension(240, 35));
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnDisconnect = new GridBagConstraints();
		gbc_btnDisconnect.fill = GridBagConstraints.BOTH;
		gbc_btnDisconnect.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisconnect.gridx = 2;
		gbc_btnDisconnect.gridy = 3;
		panelServer.add(btnDisconnect, gbc_btnDisconnect);
		
		JPanel panelClient = new JPanel();
		panelClient.setAlignmentY(Component.TOP_ALIGNMENT);
		panelClient.setAlignmentX(Component.LEFT_ALIGNMENT);
		frmMain.getContentPane().add(panelClient, BorderLayout.CENTER);
		GridBagLayout gbl_panelClient = new GridBagLayout();
		gbl_panelClient.columnWidths = new int[]{39, 539, 114, 0};
		gbl_panelClient.rowHeights = new int[]{30, 0, 0, 0, 34, 174, 0};
		gbl_panelClient.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelClient.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelClient.setLayout(gbl_panelClient);
		
		txtArray = new JTextField();
		txtArray.setToolTipText("Unesite niz koji zelite da sortirate");
		txtArray.setEnabled(false);
		txtArray.setPreferredSize(new Dimension(200, 30));
		GridBagConstraints gbc_txtArray = new GridBagConstraints();
		gbc_txtArray.fill = GridBagConstraints.BOTH;
		gbc_txtArray.insets = new Insets(0, 0, 5, 5);
		gbc_txtArray.gridx = 1;
		gbc_txtArray.gridy = 1;
		panelClient.add(txtArray, gbc_txtArray);
		txtArray.setColumns(10);
		
		JButton btnSort = new JButton("Sortiraj");
		btnSort.setToolTipText("Unesite niz koji zelite da sortirate");
		btnSort.setEnabled(false);
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnSort = new GridBagConstraints();
		gbc_btnSort.insets = new Insets(0, 0, 5, 0);
		gbc_btnSort.anchor = GridBagConstraints.WEST;
		gbc_btnSort.gridx = 2;
		gbc_btnSort.gridy = 1;
		panelClient.add(btnSort, gbc_btnSort);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		panelClient.add(panel, gbc_panel);
		
		JLabel lblAlgoritamZaSortiranje = new JLabel("Algoritam za sortiranje:");
		lblAlgoritamZaSortiranje.setToolTipText("Izaberite algoritam kojim zelite da sortirate niz");
		panel.add(lblAlgoritamZaSortiranje);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setToolTipText("Izaberite algoritam kojim zelite da sortirate niz");
		panel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bubble sort", "Selection sort", "Insertion sort"}));
		
		JLabel lblDnevnik = new JLabel("Dnevnik:");
		lblDnevnik.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblDnevnik = new GridBagConstraints();
		gbc_lblDnevnik.anchor = GridBagConstraints.WEST;
		gbc_lblDnevnik.insets = new Insets(0, 0, 5, 5);
		gbc_lblDnevnik.gridx = 1;
		gbc_lblDnevnik.gridy = 4;
		panelClient.add(lblDnevnik, gbc_lblDnevnik);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		panelClient.add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setToolTipText("Dnevnik prikazuje komunikaciju na mrezi");
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		lblDnevnik.setLabelFor(textArea);
		textArea.setEditable(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 50));
		frmMain.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New button");
		panel_2.add(btnNewButton);
	}

}
