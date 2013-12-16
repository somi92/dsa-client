package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import sorting_server.SortingServerMainThread;

import client.ClientThread;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainAppWindow {

	private JFrame frmMainAppWindow;
	private JPanel panelServer;
	private JLabel label;
	private JButton btnConnect;
	private JLabel lblStatus;
	private JButton btnDisconnect;
	private JPanel panelClient;
	private JTextField txtArray;
	private JButton btnSort;
	private JPanel panelButtons;
	private JLabel label_2;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbSort;
	private JLabel label_3;
	private JScrollPane scrollPane;
	private JPanel panel_3;
	private JButton button_3;
	private JTextArea txtLog;

//	private Thread client;
//	private Executor clientExecutor = Executors.newSingleThreadExecutor();
	private ExecutorService threadsExecutor;
	private ClientThread client;
	private SortingServerMainThread sortingServer;
	
	private String data = "";
	private JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppWindow window = new MainAppWindow();
					window.frmMainAppWindow.setLocationRelativeTo(null);
					window.frmMainAppWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainAppWindow() {
		initialize();
	}
	
	public void setDataAndInitiateConnection(String[] data) {
		MainAppWindow.this.frmMainAppWindow.setEnabled(true);
		if(data==null || data.length<4) {
			return;
		}
		this.threadsExecutor = Executors.newFixedThreadPool(2);
		this.threadsExecutor.execute(sortingServer);
//		this.data = data[0]+data[1]+data[2]+data[3];
//		String[] dataArray = data.split(" ");
		this.client = new ClientThread(MainAppWindow.this);
//		this.client.setMainServerIP(dataArray[0]);
//		this.client.setMainServerPort(Integer.parseInt(dataArray[1]));
//		this.client.setServerSidePort(Integer.parseInt(dataArray[2]));
//		this.client.setServices(dataArray[3]);
		
		this.client.setMainServerIP(data[0]);
		this.client.setMainServerPort(Integer.parseInt(data[1]));
		this.client.setServerSidePort(Integer.parseInt(data[2]));
		this.client.setServices(data[3]);
		this.sortingServer.setServices(data[3]);
		
//		this.sortingServerListeningPort = Integer.parseInt(data[2]);
		
		this.client.setTask(ClientThread.CONNECT);
//		this.client = new Thread(c);
//		this.client.start();
		this.threadsExecutor.execute(client);
//		btnConnect.setEnabled(false);
//		btnDisconnect.setEnabled(true);
	}
	
	public void manageButtons() {
		btnConnect.setEnabled(!btnConnect.isEnabled());
		btnDisconnect.setEnabled(!btnDisconnect.isEnabled());
		btnSort.setEnabled(!btnSort.isEnabled());
		txtArray.setEnabled(!txtArray.isEnabled());
		cmbSort.setEnabled(!cmbSort.isEnabled());
		if(btnConnect.isEnabled()) {
			lblStatus.setText("Niste prijavljeni na glavni server.");
		} else {
			lblStatus.setText("Prijavljeni ste na glavni server.");
		}
	}
	
	public String getData() {
		return data;
	}
	
	public void closeConnection() {
		this.client.setTask(ClientThread.DISCONNECT);
		this.threadsExecutor.execute(client);
		this.threadsExecutor.shutdown();
		this.sortingServer.stopMainSortingServer();
		try {
			boolean r = this.threadsExecutor.awaitTermination(1000, TimeUnit.MILLISECONDS);
			System.out.println(r);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateLog(StringBuffer data) {
		txtLog.append(data.toString());
		txtLog.setCaretPosition(txtLog.getText().length());
	}
	
	public int startSortingServer(int sortingServerListeningPort) {
		this.sortingServer = new SortingServerMainThread();
		int code = this.sortingServer.startMainSortingServer(sortingServerListeningPort);
		return code;
	}
	
	public void startSorting(String algorithm, String data) {
		this.client.setTask(ClientThread.SORT);
		this.client.setAlgorithm(algorithm);
		this.client.setData(data);
		this.threadsExecutor.execute(client);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainAppWindow = new JFrame();
		frmMainAppWindow.setResizable(false);
		frmMainAppWindow.setTitle("Aplikacija za distribuirano sortiranje");
		frmMainAppWindow.setBounds(100, 100, 812, 571);
		frmMainAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainAppWindow.getContentPane().add(getPanelServer(), BorderLayout.NORTH);
		frmMainAppWindow.getContentPane().add(getPanelClient(), BorderLayout.CENTER);
		frmMainAppWindow.getContentPane().add(getPanel_3(), BorderLayout.SOUTH);
	}

	private JPanel getPanelServer() {
		if (panelServer == null) {
			panelServer = new JPanel();
			panelServer.setBackground(Color.LIGHT_GRAY);
			GridBagLayout gbl_panelServer = new GridBagLayout();
			gbl_panelServer.columnWidths = new int[]{59, 111, 264, 285, 264, 0};
			gbl_panelServer.rowHeights = new int[]{0, 30, 35, 35, 35, 0};
			gbl_panelServer.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panelServer.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			panelServer.setLayout(gbl_panelServer);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 0;
			panelServer.add(getLabel(), gbc_label);
			GridBagConstraints gbc_btnConnect = new GridBagConstraints();
			gbc_btnConnect.fill = GridBagConstraints.BOTH;
			gbc_btnConnect.insets = new Insets(0, 0, 5, 5);
			gbc_btnConnect.gridx = 2;
			gbc_btnConnect.gridy = 2;
			panelServer.add(getBtnConnect(), gbc_btnConnect);
			GridBagConstraints gbc_lblStatus = new GridBagConstraints();
			gbc_lblStatus.fill = GridBagConstraints.VERTICAL;
			gbc_lblStatus.anchor = GridBagConstraints.EAST;
			gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
			gbc_lblStatus.gridx = 3;
			gbc_lblStatus.gridy = 2;
			panelServer.add(getLblStatus(), gbc_lblStatus);
			GridBagConstraints gbc_btnDisconnect = new GridBagConstraints();
			gbc_btnDisconnect.fill = GridBagConstraints.BOTH;
			gbc_btnDisconnect.insets = new Insets(0, 0, 5, 5);
			gbc_btnDisconnect.gridx = 2;
			gbc_btnDisconnect.gridy = 3;
			panelServer.add(getBtnDisconnect(), gbc_btnDisconnect);
		}
		return panelServer;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Aplikacija za distribuirano sortiranje");
			label.setFont(new Font("Dialog", Font.BOLD, 15));
		}
		return label;
	}
	private JButton getBtnConnect() {
		if (btnConnect == null) {
			btnConnect = new JButton("Prijavi se na glavni server");
			btnConnect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ConnectionDialog dialog = new ConnectionDialog();
					dialog.setVisible(true);
					dialog.setParent(MainAppWindow.this);
					MainAppWindow.this.frmMainAppWindow.setEnabled(false);
				}
			});
			btnConnect.setPreferredSize(new Dimension(240, 35));
		}
		return btnConnect;
	}
	private JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("Niste prijavljeni na glavni server.");
			lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblStatus;
	}
	private JButton getBtnDisconnect() {
		if (btnDisconnect == null) {
			btnDisconnect = new JButton("Odjavi se sa glavnog servera");
			btnDisconnect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					closeConnection();
				}
			});
			btnDisconnect.setPreferredSize(new Dimension(240, 35));
			btnDisconnect.setEnabled(false);
		}
		return btnDisconnect;
	}
	private JPanel getPanelClient() {
		if (panelClient == null) {
			panelClient = new JPanel();
			panelClient.setAlignmentY(0.0f);
			panelClient.setAlignmentX(0.0f);
			GridBagLayout gbl_panelClient = new GridBagLayout();
			gbl_panelClient.columnWidths = new int[]{39, 701, 35, 0};
			gbl_panelClient.rowHeights = new int[]{30, 0, 0, 0, 34, 174, 0};
			gbl_panelClient.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panelClient.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panelClient.setLayout(gbl_panelClient);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 1;
			panelClient.add(getPanel(), gbc_panel);
			GridBagConstraints gbc_panelButtons = new GridBagConstraints();
			gbc_panelButtons.fill = GridBagConstraints.VERTICAL;
			gbc_panelButtons.anchor = GridBagConstraints.WEST;
			gbc_panelButtons.insets = new Insets(0, 0, 5, 5);
			gbc_panelButtons.gridx = 1;
			gbc_panelButtons.gridy = 2;
			panelClient.add(getPanelButtons(), gbc_panelButtons);
			GridBagConstraints gbc_label_3 = new GridBagConstraints();
			gbc_label_3.anchor = GridBagConstraints.WEST;
			gbc_label_3.insets = new Insets(0, 0, 5, 5);
			gbc_label_3.gridx = 1;
			gbc_label_3.gridy = 4;
			panelClient.add(getLabel_3(), gbc_label_3);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 5;
			panelClient.add(getScrollPane(), gbc_scrollPane);
		}
		return panelClient;
	}
	private JTextField getTxtArray() {
		if (txtArray == null) {
			txtArray = new JTextField();
			txtArray.setSize(new Dimension(400, 0));
			txtArray.setToolTipText("Unesite niz koji zelite da sortirate, elemente odvojite zarezima");
			txtArray.setPreferredSize(new Dimension(500, 30));
			txtArray.setEnabled(false);
			txtArray.setColumns(10);
		}
		return txtArray;
	}
	private JButton getBtnSort() {
		if (btnSort == null) {
			btnSort = new JButton("Sortiraj");
			btnSort.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String algorithm = "";
					if(cmbSort.getSelectedIndex() == 0) {
						algorithm = "B";
					}
					if(cmbSort.getSelectedIndex() == 1) {
						algorithm = "S";
					}
					if(cmbSort.getSelectedIndex() == 2) {
						algorithm = "I";
					}
					startSorting(algorithm, txtArray.getText().trim());
				}
			});
			btnSort.setToolTipText("Unesite niz koji zelite da sortirate");
			btnSort.setEnabled(false);
		}
		return btnSort;
	}
	private JPanel getPanelButtons() {
		if (panelButtons == null) {
			panelButtons = new JPanel();
			panelButtons.add(getLabel_2());
			panelButtons.add(getCmbSort());
		}
		return panelButtons;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Algoritam za sortiranje:");
			label_2.setToolTipText("Izaberite algoritam kojim zelite da sortirate niz");
		}
		return label_2;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getCmbSort() {
		if (cmbSort == null) {
			cmbSort = new JComboBox();
			cmbSort.setToolTipText("Izaberite algoritam kojim zelite da sortirate niz");
			cmbSort.setEnabled(false);
			cmbSort.setModel(new DefaultComboBoxModel(new String[] {"Bubble sort", "Selection sort", "Insertion sort"}));
		}
		return cmbSort;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Dnevnik:");
			label_3.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return label_3;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTxtLog());
		}
		return scrollPane;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setPreferredSize(new Dimension(10, 50));
			panel_3.add(getButton_3());
		}
		return panel_3;
	}
	private JButton getButton_3() {
		if (button_3 == null) {
			button_3 = new JButton("New button");
			button_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
//					System.out.println(data);
//					System.out.println(client.isAlive());
//					System.out.println(client.getState());
//					client.start();
//					client.interrupt();
//					System.out.println(client.toString());
				}
			});
		}
		return button_3;
	}
	private JTextArea getTxtLog() {
		if (txtLog == null) {
			txtLog = new JTextArea();
			txtLog.setToolTipText("Dnevnik prikazuje komunikaciju na mrezi");
			txtLog.setEditable(false);
		}
		return txtLog;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{588, 128, 0, 0};
			gbl_panel.rowHeights = new int[]{30, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			GridBagConstraints gbc_txtArray = new GridBagConstraints();
			gbc_txtArray.fill = GridBagConstraints.BOTH;
			gbc_txtArray.insets = new Insets(0, 0, 0, 5);
			gbc_txtArray.gridx = 0;
			gbc_txtArray.gridy = 0;
			panel.add(getTxtArray(), gbc_txtArray);
			GridBagConstraints gbc_btnSort = new GridBagConstraints();
			gbc_btnSort.insets = new Insets(0, 0, 0, 5);
			gbc_btnSort.fill = GridBagConstraints.BOTH;
			gbc_btnSort.gridx = 1;
			gbc_btnSort.gridy = 0;
			panel.add(getBtnSort(), gbc_btnSort);
		}
		return panel;
	}
}
