package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainServerDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204281886943918433L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIP;
	private JTextField txtPort;
	private JTextField txtListeningPort;
	private JLabel lblIP;
	private JLabel lblPort;
	private JLabel lblPortListening;
	
	private String serverIP;
	private int serverPort;
	private int listeningPort;
	private String services;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MainServerDialog dialog = new MainServerDialog();
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MainServerDialog() {
		setTitle("Prijava na glavni server");
		setBounds(100, 100, 390, 274);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 166, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblIP = new JLabel("IP adresa glavnog servera:");
			lblIP.setToolTipText("IP adresa glavnog servera");
			lblIP.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblIP = new GridBagConstraints();
			gbc_lblIP.insets = new Insets(0, 0, 5, 5);
			gbc_lblIP.anchor = GridBagConstraints.EAST;
			gbc_lblIP.gridx = 0;
			gbc_lblIP.gridy = 0;
			contentPanel.add(lblIP, gbc_lblIP);
		}
		{
			txtIP = new JTextField();
			lblIP.setLabelFor(txtIP);
			txtIP.setToolTipText("IP adresa glavnog servera");
			GridBagConstraints gbc_txtIP = new GridBagConstraints();
			gbc_txtIP.insets = new Insets(0, 0, 5, 0);
			gbc_txtIP.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtIP.gridx = 1;
			gbc_txtIP.gridy = 0;
			contentPanel.add(txtIP, gbc_txtIP);
			txtIP.setColumns(10);
		}
		{
			lblPort = new JLabel("Port glavnog servera:");
			lblPort.setToolTipText("Port glavnog servera potreban za prijavu");
			lblPort.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblPort = new GridBagConstraints();
			gbc_lblPort.anchor = GridBagConstraints.EAST;
			gbc_lblPort.insets = new Insets(0, 0, 5, 5);
			gbc_lblPort.gridx = 0;
			gbc_lblPort.gridy = 1;
			contentPanel.add(lblPort, gbc_lblPort);
		}
		{
			txtPort = new JTextField();
			lblPort.setLabelFor(txtPort);
			txtPort.setToolTipText("Port glavnog servera potreban za prijavu");
			GridBagConstraints gbc_txtPort = new GridBagConstraints();
			gbc_txtPort.insets = new Insets(0, 0, 5, 0);
			gbc_txtPort.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPort.gridx = 1;
			gbc_txtPort.gridy = 1;
			contentPanel.add(txtPort, gbc_txtPort);
			txtPort.setColumns(10);
		}
		{
			lblPortListening = new JLabel("Port za osluskivanje:");
			lblPortListening.setToolTipText("Port koji koriste drugi klijenti za zahtevanje usluga sortiranja - unesite nula ukoliko zelite da program automatski izabere slobodan port");
			GridBagConstraints gbc_lblPortListening = new GridBagConstraints();
			gbc_lblPortListening.anchor = GridBagConstraints.EAST;
			gbc_lblPortListening.insets = new Insets(0, 0, 5, 5);
			gbc_lblPortListening.gridx = 0;
			gbc_lblPortListening.gridy = 2;
			contentPanel.add(lblPortListening, gbc_lblPortListening);
		}
		{
			txtListeningPort = new JTextField();
			lblPortListening.setLabelFor(txtListeningPort);
			txtListeningPort.setToolTipText("Port koji koriste drugi klijenti za zahtevanje usluga sortiranja - unesite nula ukoliko zelite da program automatski izabere slobodan port");
			GridBagConstraints gbc_txtListeningPort = new GridBagConstraints();
			gbc_txtListeningPort.insets = new Insets(0, 0, 5, 0);
			gbc_txtListeningPort.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtListeningPort.gridx = 1;
			gbc_txtListeningPort.gridy = 2;
			contentPanel.add(txtListeningPort, gbc_txtListeningPort);
			txtListeningPort.setColumns(10);
		}
		{
			JLabel lblIzaberiteAlgoritmeSortiranja = new JLabel("Algoritmi sortiranja:");
			lblIzaberiteAlgoritmeSortiranja.setToolTipText("Izaberite algoritme koje nudite ostalim klijentima");
			GridBagConstraints gbc_lblIzaberiteAlgoritmeSortiranja = new GridBagConstraints();
			gbc_lblIzaberiteAlgoritmeSortiranja.insets = new Insets(0, 0, 5, 5);
			gbc_lblIzaberiteAlgoritmeSortiranja.gridx = 0;
			gbc_lblIzaberiteAlgoritmeSortiranja.gridy = 4;
			contentPanel.add(lblIzaberiteAlgoritmeSortiranja, gbc_lblIzaberiteAlgoritmeSortiranja);
		}
		{
			JCheckBox chckbxBubbleSort = new JCheckBox("Bubble sort");
			chckbxBubbleSort.setToolTipText("Izaberite algoritme koje nudite ostalim klijentima");
			chckbxBubbleSort.setIconTextGap(10);
			chckbxBubbleSort.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxBubbleSort = new GridBagConstraints();
			gbc_chckbxBubbleSort.anchor = GridBagConstraints.WEST;
			gbc_chckbxBubbleSort.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxBubbleSort.gridx = 1;
			gbc_chckbxBubbleSort.gridy = 4;
			contentPanel.add(chckbxBubbleSort, gbc_chckbxBubbleSort);
		}
		{
			JCheckBox chckbxSelectionSort = new JCheckBox("Selection sort");
			chckbxSelectionSort.setToolTipText("Izaberite algoritme koje nudite ostalim klijentima");
			chckbxSelectionSort.setIconTextGap(10);
			chckbxSelectionSort.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxSelectionSort = new GridBagConstraints();
			gbc_chckbxSelectionSort.anchor = GridBagConstraints.WEST;
			gbc_chckbxSelectionSort.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxSelectionSort.gridx = 1;
			gbc_chckbxSelectionSort.gridy = 5;
			contentPanel.add(chckbxSelectionSort, gbc_chckbxSelectionSort);
		}
		{
			JCheckBox chckbxInsertionSort = new JCheckBox("Insertion sort");
			chckbxInsertionSort.setToolTipText("Izaberite algoritme koje nudite ostalim klijentima");
			chckbxInsertionSort.setIconTextGap(10);
			chckbxInsertionSort.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxInsertionSort = new GridBagConstraints();
			gbc_chckbxInsertionSort.anchor = GridBagConstraints.WEST;
			gbc_chckbxInsertionSort.gridx = 1;
			gbc_chckbxInsertionSort.gridy = 6;
			contentPanel.add(chckbxInsertionSort, gbc_chckbxInsertionSort);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
//						if(txtIP.getText().length()==0 || txtPort.getText().length()==0 || txtListeningPort.getText().length()==0 ||
//								(contentPanel.)) {
//							
//						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
