package sorting_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

public class SortingServerMainThread implements Runnable {

	private int listeningPort;
	private ServerSocket mainServer;
	
	private Executor mainServerExecutor;
	
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

	public ServerSocket getMainServer() {
		return mainServer;
	}

	public void setMainServer(ServerSocket mainServer) {
		this.mainServer = mainServer;
	}
	
	public int startMainServer(int listeningPort) {
		try {
			this.mainServer = new ServerSocket(listeningPort);
			if(listeningPort==0) {
				setListeningPort(this.mainServer.getLocalPort());
			} else {
				setListeningPort(listeningPort);
			}
			return getListeningPort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(null, "Aplikacija ne moze da rezervise izabrani port! Pokusajte ponovo.", "Greska!", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainServerExecutor = Executors.newFixedThreadPool(10);
		while(true) {
			try {
				this.mainServerExecutor.execute(new SortingServerThread(this.mainServer.accept()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
