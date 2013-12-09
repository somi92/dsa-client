package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.GregorianCalendar;

import protocol.DSPClient;

public class ClientThread implements Runnable {

	private Socket mainServer;
//	private Socket sServer1;
//	private Socket sServer2;
	
	private int mainServerPort;
	private String mainServerIP;
	private String services;
	private int serverSidePort;
	
	private boolean connectionTerminated;
	
	public ClientThread() {
		this.connectionTerminated = false;
	}
	
	public ClientThread(String mainServerIP,int mainServerPort) {
		this.connectionTerminated = false;
		this.mainServerIP = mainServerIP;
		this.mainServerPort = mainServerPort;
	}
	
	

	public int getMainServerPort() {
		return mainServerPort;
	}

	public void setMainServerPort(int mainServerPort) {
		this.mainServerPort = mainServerPort;
	}

	public String getMainServerIP() {
		return mainServerIP;
	}

	public void setMainServerIP(String mainServerIP) {
		this.mainServerIP = mainServerIP;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public int getServerSidePort() {
		return serverSidePort;
	}

	public void setServerSidePort(int serverSidePort) {
		this.serverSidePort = serverSidePort;
	}

	public boolean isConnectionTerminated() {
		return connectionTerminated;
	}

	public void setConnectionTerminated(boolean connectionTerminated) {
		this.connectionTerminated = connectionTerminated;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			this.mainServer = new Socket(getMainServerIP(), getMainServerPort());
			DSPClient protocol = new DSPClient();
			
			BufferedReader mainServerInput = new BufferedReader(new InputStreamReader(this.mainServer.getInputStream()));
			DataOutputStream mainServerOutput = new DataOutputStream(this.mainServer.getOutputStream());
			
//			while(!this.connectionTerminated) {
				
				String request = protocol.connectToMainServer(getServices(), getServerSidePort());
				mainServerOutput.writeBytes(request);
				System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Poslat zahtev za prijavu glavnom serveru: "+request);
				
				String serverResponse = mainServerInput.readLine();
				int responseCode = protocol.parseProtocolMessage(serverResponse);
				
				switch(responseCode) {
				
					case DSPClient.ACCEPTED: {
						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Uspesno ste prijavljeni! Server: "+serverResponse);
					}
					break;
					
					case DSPClient.PEERS: {
						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse);
					}	
					break;
					
					case DSPClient.ERROR: {
						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server prijavljuje gresku: "+serverResponse);
					}
					break;
					
					case DSPClient.NOT_FOUND: {
						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server nije pronasao odgovarajuce klijente: "+serverResponse);
					}
					
					default: {
						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse);
					}
				}
				
				
//			}
			mainServer.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private int[] parseDataToArray(String dataString) {
		int[] data;
		String[] dArray = dataString.split(",");
		data = new int[dArray.length];
		for(int i=0; i<data.length; i++) {
			data[i] = Integer.parseInt(dArray[i]);
		}
		return data;
	}
	
	private String parseDataToString(int[] dataArray) {
		String data = "";
		for(int i=0; i<dataArray.length; i++) {
			data = data + dataArray[i] + ",";
		}
		return data.substring(0, data.length()-1);
	}
	
	private boolean isSorted(int[] data) {
		for(int i=0; i<data.length-1; i++) {
			if(data[i] > data[i+1]) {
				return false;
			}
		}
		return true;
	}

}
