package sorting_server;

import java.net.Socket;

public class SortingServerThread implements Runnable {

	private Socket communicationSocket;
	
	public SortingServerThread(Socket communicationSocket) {
		this.communicationSocket = communicationSocket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
