package sorting_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

public class SortingServerMainThread implements Runnable {

	private int listeningPort;
	private String services;
	private ServerSocket mainServer;
	
	private ExecutorService mainServerExecutor;
	
	public SortingServerMainThread() {
		
	}
	
	public SortingServerMainThread(int listeningPort) {
		this.setListeningPort(listeningPort);
	}

	public int getListeningPort() {
		return listeningPort;
	}

	public void setListeningPort(int listeningPort) {
		this.listeningPort = listeningPort;
	}
	
	public String getServices() {
		return services;
	}
	
	public void setServices(String services) {
		this.services = services;
	}

	public ServerSocket getMainServer() {
		return mainServer;
	}

	public void setMainServer(ServerSocket mainServer) {
		this.mainServer = mainServer;
	}
	
	public int startMainSortingServer(int listeningPort) {
		try {
			this.mainServer = new ServerSocket(listeningPort);
			if(listeningPort==0) {
				setListeningPort(this.mainServer.getLocalPort());
			} else {
				setListeningPort(listeningPort);
			}
//			System.out.println("Sorting server osluskuje na portu: "+this.mainServer.getLocalPort());
			return getListeningPort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Aplikacija ne moze da rezervise izabrani port za osluskivanje! Port je verovatno zauzet. Izaberite novi broj porta i pokusajte ponovo.", "Greska!", JOptionPane.ERROR_MESSAGE);
			return -1;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Aplikacija ne moze da rezervise izabrani port za osluskivanje! Broj porta mora biti u rasponu 0-65535, ukljucno. Izaberite novi broj porta i pokusajte ponovo.", "Greska!", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
	}
	
	public void stopMainSortingServer() {
		try {
			this.mainServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainServerExecutor = Executors.newFixedThreadPool(10);
		while(true) {
			try {
				this.mainServerExecutor.execute(new SortingServerThread(this.mainServer.accept(), getServices()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				System.out.println("Catch in run!");
				return;
			}
		}
	}
	
	
}
