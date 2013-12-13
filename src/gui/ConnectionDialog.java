package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConnectionDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6695554088633959419L;
	
	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtServerIP;
	private JTextField txtServerPort;
	private JTextField txtListeningPort;
	
	private JCheckBox chbBubble = new JCheckBox("Bubble sort");
	private JCheckBox chbSelection = new JCheckBox("Selection sort");
	private JCheckBox chbInsertion = new JCheckBox("Insertion sort");
	
	private String ip = "";
	private int serverPort;
	private int listeningPort;
	private String services = "";
	
	private MainAppWindow main;

	public int getListeningPort() {
		return this.listeningPort;
	}
	
	public void setListeningPort(int listeningPort) {
		this.listeningPort = listeningPort;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConnectionDialog dialog = new ConnectionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] returnData() {
//		return ip+" "+serverPort+" "+listeningPort+" "+services;
		String[] data = {ip,serverPort+"",listeningPort+"",services};
		return data;
	}
	
	public void setParent(MainAppWindow main) {
		this.main = main;
	}
	/**
	 * Create the dialog.
	 */
	public ConnectionDialog() {
		setBounds(100, 100, 398, 275);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		
		
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.add(panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 166, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel label = new JLabel("IP adresa glavnog servera:");
				label.setToolTipText("IP adresa glavnog servera");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			{
				txtServerIP = new JTextField();
				txtServerIP.setToolTipText("IP adresa glavnog servera");
				txtServerIP.setColumns(10);
				GridBagConstraints gbc_txtServerIP = new GridBagConstraints();
				gbc_txtServerIP.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtServerIP.insets = new Insets(0, 0, 5, 0);
				gbc_txtServerIP.gridx = 1;
				gbc_txtServerIP.gridy = 0;
				panel.add(txtServerIP, gbc_txtServerIP);
			}
			{
				JLabel label = new JLabel("Port glavnog servera:");
				label.setToolTipText("Port glavnog servera potreban za prijavu");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 1;
				panel.add(label, gbc_label);
			}
			{
				txtServerPort = new JTextField();
				txtServerPort.setToolTipText("Port glavnog servera potreban za prijavu");
				txtServerPort.setColumns(10);
				GridBagConstraints gbc_txtServerPort = new GridBagConstraints();
				gbc_txtServerPort.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtServerPort.insets = new Insets(0, 0, 5, 0);
				gbc_txtServerPort.gridx = 1;
				gbc_txtServerPort.gridy = 1;
				panel.add(txtServerPort, gbc_txtServerPort);
			}
			{
				JLabel label = new JLabel("Port za osluskivanje:");
				label.setToolTipText("Port koji koriste drugi klijenti za zahtevanje usluga sortiranja - unesite nula ukoliko zelite da program automatski izabere slobodan port");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 2;
				panel.add(label, gbc_label);
			}
			{
				txtListeningPort = new JTextField();
				txtListeningPort.setToolTipText("Port koji koriste drugi klijenti za zahtevanje usluga sortiranja - unesite nula ukoliko zelite da program automatski izabere slobodan port");
				txtListeningPort.setColumns(10);
				GridBagConstraints gbc_txtListeningPort = new GridBagConstraints();
				gbc_txtListeningPort.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtListeningPort.insets = new Insets(0, 0, 5, 0);
				gbc_txtListeningPort.gridx = 1;
				gbc_txtListeningPort.gridy = 2;
				panel.add(txtListeningPort, gbc_txtListeningPort);
			}
			{
				JLabel label = new JLabel("Algoritmi sortiranja:");
				label.setToolTipText("Izaberite algoritme koje nudite ostalim klijentima");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 4;
				panel.add(label, gbc_label);
			}
			
//				JCheckBox chbBubble = new JCheckBox("Bubble sort");
				chbBubble.setToolTipText("Izaberite algoritme koje nudite ostalim klijentima");
				chbBubble.setIconTextGap(10);
				chbBubble.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_chbBubble = new GridBagConstraints();
				gbc_chbBubble.anchor = GridBagConstraints.WEST;
				gbc_chbBubble.insets = new Insets(0, 0, 5, 0);
				gbc_chbBubble.gridx = 1;
				gbc_chbBubble.gridy = 4;
				panel.add(chbBubble, gbc_chbBubble);
			
			
//				JCheckBox chbSelection = new JCheckBox("Selection sort");
				chbSelection.setToolTipText("Izaberite algoritme koje nudite ostalim klijentima");
				chbSelection.setIconTextGap(10);
				chbSelection.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_chbSelection = new GridBagConstraints();
				gbc_chbSelection.anchor = GridBagConstraints.WEST;
				gbc_chbSelection.insets = new Insets(0, 0, 5, 0);
				gbc_chbSelection.gridx = 1;
				gbc_chbSelection.gridy = 5;
				panel.add(chbSelection, gbc_chbSelection);
			
			
//				JCheckBox chbInsertion = new JCheckBox("Insertion sort");
				chbInsertion.setToolTipText("Izaberite algoritme koje nudite ostalim klijentima");
				chbInsertion.setIconTextGap(10);
				chbInsertion.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_chbInsertion = new GridBagConstraints();
				gbc_chbInsertion.anchor = GridBagConstraints.WEST;
				gbc_chbInsertion.gridx = 1;
				gbc_chbInsertion.gridy = 6;
				panel.add(chbInsertion, gbc_chbInsertion);
			
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(txtServerIP.getText()==null || txtServerIP.getText().length()==0) {
							JOptionPane.showMessageDialog(null, "Morate uneti IP adresu glavnog servera.", "Greska!", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							ip = txtServerIP.getText().trim();
						}
						
						if(txtServerPort.getText()==null || txtServerPort.getText().length()==0) {
							JOptionPane.showMessageDialog(null, "Morate uneti broj porta glavnog servera.", "Greska!", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							try {
								serverPort = Integer.parseInt(txtServerPort.getText().trim());
							} catch (NumberFormatException e2) {
								// TODO: handle exception
								JOptionPane.showMessageDialog(null, "Neispravno ste uneli broj porta glavnog servera.", "Greska!", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						
						if(txtListeningPort.getText()==null || txtListeningPort.getText().length()==0) {
							JOptionPane.showMessageDialog(null, "Morate uneti broj porta za osluskivanje.", "Greska!", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							try {
								listeningPort = Integer.parseInt(txtListeningPort.getText().trim());
							} catch (NumberFormatException e2) {
								// TODO: handle exception
								JOptionPane.showMessageDialog(null, "Neispravno ste uneli broj porta za osluskivanje.", "Greska!", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						
						if(!chbBubble.isSelected() && !chbSelection.isSelected() && !chbInsertion.isSelected()) {
							JOptionPane.showMessageDialog(null, "Morate izabrati bar jedan od ponudjenih algoritama.", "Greska!", JOptionPane.ERROR_MESSAGE);
							services = "";
							return;
						} else {
							services = "";
							if(chbBubble.isSelected()) {
								services = services + "B";
							}
							if(chbSelection.isSelected()) {
								services = services + "S";
							}
							if(chbInsertion.isSelected()) {
								services = services + "I";
							}
							services.trim();
//							System.out.println(services);
							int code = main.startSortingServer(getListeningPort());
							if(code==-1) {
								return;
							} else {
								setListeningPort(code);
							}
							main.setDataAndInitiateConnection(returnData());
							dispose();
						}
					}
				});
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCancel = new JButton("Ponisti");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String[] data = null;
						main.setDataAndInitiateConnection(data);
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}

}
