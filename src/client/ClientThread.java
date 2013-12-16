package client;

import gui.MainAppWindow;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

import protocol.DSPClient;

public class ClientThread implements Runnable {

	private Socket mainServer;
	private Socket sortingServer1;
	private Socket sortingServer2;
//	private Socket sServer1;
//	private Socket sServer2;
	private DSPClient protocol;
	
	private MainAppWindow parent;
	
	public static final int CONNECT = 1;
	public static final int SORT = 2;
	public static final int FINISHED = 3;
	public static final int DISCONNECT = 4;
	private int task = 0;
	
	private int mainServerPort;
	private String mainServerIP;
	private String services;
	private int serverSidePort;
	
	private StringBuffer log = new StringBuffer("");
	
	private boolean connectionTerminated;
	
	public ClientThread() {
		this.connectionTerminated = false;
	}
	
	public ClientThread(MainAppWindow parent) {
		this.parent = parent;
	}
	
	public ClientThread(String mainServerIP,int mainServerPort) {
		this.connectionTerminated = false;
		this.mainServerIP = mainServerIP;
		this.mainServerPort = mainServerPort;
	}

	public int getTask() {
		return task;
	}

	public void setTask(int task) {
		this.task = task;
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
		
//		try {
//			
//			this.mainServer = new Socket(getMainServerIP(), getMainServerPort());
//			DSPClient protocol = new DSPClient();
//			
//			BufferedReader mainServerInput = new BufferedReader(new InputStreamReader(this.mainServer.getInputStream()));
//			DataOutputStream mainServerOutput = new DataOutputStream(this.mainServer.getOutputStream());
//			
////			while(!this.connectionTerminated) {
//				
//				String request = protocol.connectToMainServer(getServices(), getServerSidePort());
//				mainServerOutput.writeBytes(request);
//				System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Poslat zahtev za prijavu glavnom serveru: "+request);
//				
//				String serverResponse = mainServerInput.readLine();
//				int responseCode = protocol.parseProtocolMessage(serverResponse);
//				
//				switch(responseCode) {
//				
//					case DSPClient.ACCEPTED: {
//						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Uspesno ste prijavljeni! Server: "+serverResponse);
//					}
//					break;
//					
//					case DSPClient.PEERS: {
//						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse);
//					}	
//					break;
//					
//					case DSPClient.ERROR: {
//						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server prijavljuje gresku: "+serverResponse);
//					}
//					break;
//					
//					case DSPClient.NOT_FOUND: {
//						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server nije pronasao odgovarajuce klijente: "+serverResponse);
//					}
//					
//					default: {
//						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse);
//					}
//				}
//				
//				
////			}
////			mainServerInput.close();
////			mainServerOutput.close();
////			mainServer.close();
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		switch(getTask()) {
			
			case ClientThread.CONNECT: {
				connectToMainServer();
			}
			break;
			
			case ClientThread.DISCONNECT: {
				disconnectFromMainServer();
			}
		}
		
	}
	
	private void connectToMainServer() {
		
		try {
			
			this.mainServer = new Socket(getMainServerIP(), getMainServerPort());
			this.protocol = new DSPClient();
			
			BufferedReader mainServerInput = new BufferedReader(new InputStreamReader(this.mainServer.getInputStream()));
			DataOutputStream mainServerOutput = new DataOutputStream(this.mainServer.getOutputStream());
			
			String request = this.protocol.connectToMainServer(getServices(), getServerSidePort());
			mainServerOutput.writeBytes(request);
//			System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Poslat zahtev za prijavu glavnom serveru: "+request);
			this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Poslat zahtev za prijavu glavnom serveru: "+request);
			this.parent.updateLog(this.log);
			
			String serverResponse = mainServerInput.readLine();
			int responseCode = this.protocol.parseProtocolMessage(serverResponse);
			
			switch(responseCode) {
			
			case DSPClient.ACCEPTED: {
//				System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Uspesno ste prijavljeni! Server: "+serverResponse);
				this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Uspesno ste prijavljeni! Server: "+serverResponse+'\n');
				this.parent.updateLog(this.log);
				this.parent.manageButtons();
			}
			break;
			
//			case DSPClient.PEERS: {
//				System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse);
//			}	
//			break;
			
			case DSPClient.ERROR: {
//				System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server prijavljuje gresku: "+serverResponse);
				this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server prijavljuje gresku: "+serverResponse+'\n');
				this.parent.updateLog(this.log);
			}
			break;
			
//			case DSPClient.NOT_FOUND: {
//				System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server nije pronasao odgovarajuce klijente: "+serverResponse);
//			}
//			break;
			
			default: {
//				System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse);
				this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse+'\n');
				this.parent.updateLog(this.log);
			}
		}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void disconnectFromMainServer() {
		
		try {
			
			BufferedReader mainServerInput = new BufferedReader(new InputStreamReader(this.mainServer.getInputStream()));
			DataOutputStream mainServerOutput = new DataOutputStream(this.mainServer.getOutputStream());
			
			String request = this.protocol.sayGoodbye();
			mainServerOutput.writeBytes(request);
//			System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Poslat zahtev za prijavu glavnom serveru: "+request);
			this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Poslat zahtev za odjavu glavnom serveru: "+request);
			this.parent.updateLog(this.log);
			
			String serverResponse = mainServerInput.readLine();
			int responseCode = this.protocol.parseProtocolMessage(serverResponse);
			
			switch(responseCode) {
						
						case DSPClient.DISCONNECT: {
//							System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Uspesno ste odjavljeni! Server: "+serverResponse);
							this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Uspesno ste odjavljeni! Server: "+serverResponse+'\n');
							mainServerInput.close();
							mainServerOutput.close();
							this.mainServer.close();
							this.parent.updateLog(this.log);
							this.parent.manageButtons();
						}
						break;
						
			//			case DSPClient.PEERS: {
			//				System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse);
			//			}	
			//			break;
						
						case DSPClient.ERROR: {
//							System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server prijavljuje gresku: "+serverResponse);
							this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server prijavljuje gresku: "+serverResponse+'\n');
							this.parent.updateLog(this.log);
						}
						break;
						
			//			case DSPClient.NOT_FOUND: {
			//				System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server nije pronasao odgovarajuce klijente: "+serverResponse);
			//			}
			//			break;
						
						default: {
//							System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse);
							this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse+'\n');
							this.parent.updateLog(this.log);
							
						}
					}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void startSortingSession(String algorithm, String data) {
		
		try {
			BufferedReader mainServerInput = new BufferedReader(new InputStreamReader(this.mainServer.getInputStream()));
			DataOutputStream mainServerOutput = new DataOutputStream(this.mainServer.getOutputStream());
			
			String request = this.protocol.askForPeers(algorithm);
			mainServerOutput.writeBytes(request);
	//		System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Poslat zahtev za prijavu glavnom serveru: "+request);
			this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Poslat upit za sorting servere glavnom serveru, : "+request);
			this.parent.updateLog(this.log);
			
			String serverResponse = mainServerInput.readLine();
			int responseCode = this.protocol.parseProtocolMessage(serverResponse);
			
		
			switch(responseCode) {
						
//						case DSPClient.DISCONNECT: {
//	//						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Uspesno ste odjavljeni! Server: "+serverResponse);
//							this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Uspesno ste odjavljeni! Server: "+serverResponse+'\n');
//							mainServerInput.close();
//							mainServerOutput.close();
//							this.mainServer.close();
//							this.parent.updateLog(this.log);
//							this.parent.manageButtons();
//						}
//						break;
						
						case DSPClient.PEERS: {
//							System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server je pronasao odgovarajuce sorting servere: "+serverResponse);
							this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server je pronasao odgovarajuce sorting servere: "+serverResponse+'\n');
							this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Zapocinje proces sortiranja, povezivanje sa sorting serverima..."+'\n');
							this.parent.updateLog(this.log);
							
							String[] adresses = protocol.getAddresses();
							String server1;
							int server1Port;
							String server2;
							int server2Port;
							
							if(adresses.length == 1) {
								// pronadjen samo jedan klijent
								server1 = adresses[0].substring(0, adresses[0].indexOf(":"));
								server1Port = Integer.parseInt(adresses[0].substring(adresses[0].indexOf(":")+1));
								
								this.sortingServer1 = new Socket(server1, server1Port);
							} else {
								server1 = adresses[0].substring(0, adresses[0].indexOf(":"));
								server1Port = Integer.parseInt(adresses[0].substring(adresses[0].indexOf(":")+1));
								
								server2 = adresses[1].substring(0, adresses[1].indexOf(":"));
								server2Port = Integer.parseInt(adresses[1].substring(adresses[1].indexOf(":")+1));
								
								this.sortingServer1 = new Socket(server1, server1Port);
								this.sortingServer2 = new Socket(server2, server2Port);
							}
							
							
						}	
						break;
						
						case DSPClient.ERROR: {
	//						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server prijavljuje gresku: "+serverResponse);
							this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server prijavljuje gresku: "+serverResponse+'\n');
							this.parent.updateLog(this.log);
							return;
						}
//						break;
						
						case DSPClient.NOT_FOUND: {
	//						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server nije pronasao odgovarajuce sorting servere: "+serverResponse);
							this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server nije pronasao odgovarajuce sorting servere: "+serverResponse+'\n');
							this.parent.updateLog(this.log);
							return;
						}
//						break;
						
						default: {
	//						System.out.println("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse);
							this.log = new StringBuffer("(vreme: "+(new GregorianCalendar()).getTime()+") "+" Server: "+serverResponse+'\n');
							this.parent.updateLog(this.log);
							return;
						}
						
					}
			} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
				}
	}

	
//	private void startSorting(String) {
//		
//	}
	
//	private int[] parseDataToArray(String dataString) {
//		int[] data;
//		String[] dArray = dataString.split(",");
//		data = new int[dArray.length];
//		for(int i=0; i<data.length; i++) {
//			data[i] = Integer.parseInt(dArray[i]);
//		}
//		return data;
//	}
//	
//	private String parseDataToString(int[] dataArray) {
//		String data = "";
//		for(int i=0; i<dataArray.length; i++) {
//			data = data + dataArray[i] + ",";
//		}
//		return data.substring(0, data.length()-1);
//	}
//	
//	private boolean isSorted(int[] data) {
//		for(int i=0; i<data.length-1; i++) {
//			if(data[i] > data[i+1]) {
//				return false;
//			}
//		}
//		return true;
//	}

}
