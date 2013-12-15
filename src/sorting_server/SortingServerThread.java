package sorting_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import protocol.DSPSortingServer;
import sorting_server.sorting_algorithms.SortingAlgorithms;

public class SortingServerThread implements Runnable {

	private Socket communicationSocket;
	private String services;
	private boolean connectionTerminated;
	
	public SortingServerThread(Socket communicationSocket, String services) {
		this.communicationSocket = communicationSocket;
		this.services = services;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			String clientMessage = "";
			String response = "";
			
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(this.communicationSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(this.communicationSocket.getOutputStream());
			
			DSPSortingServer protocol = new DSPSortingServer();
			
			while(!this.connectionTerminated) {
				
				clientMessage = inFromClient.readLine();
				int responseCode = protocol.parseProtocolMessage(clientMessage);
				
				switch(responseCode) {
					
					case DSPSortingServer.SORTING: {
						
						String dataString = protocol.getData();
						String sort = protocol.getSort();
						int iteration = protocol.getIteration();
						int[] dataArray = null;
						
//						System.out.println(sort+" "+iteration+" data: "+dataString);
						
						if(this.services.contains(sort)) {
						
							if(sort.equals("B")) {
								dataArray = SortingAlgorithms.bubbleSort(parseDataToArray(dataString), iteration);
							}
							if(sort.equals("S")) {
								dataArray = SortingAlgorithms.selectionSort(parseDataToArray(dataString), iteration);
							}
							if(sort.equals("I")) {
								dataArray = SortingAlgorithms.insertionSort(parseDataToArray(dataString), iteration);
							}
							
							protocol.setData(parseDataToString(dataArray));
							response = protocol.generateResponse();
						} else {
							protocol.setState(DSPSortingServer.NOT_SUPPORTED);
							response = protocol.generateResponse();
						}
					}
					break;
					
					case DSPSortingServer.NOT_SUPPORTED: {
						response = protocol.generateResponse();
					}
					break;
					
					case DSPSortingServer.ERROR: {
						this.connectionTerminated = true;
						response = protocol.generateResponse();
					}
					break;
					
					case DSPSortingServer.DISCONNECT: {
						this.connectionTerminated = true;
						response = protocol.generateResponse();
					}
					break;
				}
				
				outToClient.writeBytes(response);
			}
			inFromClient.close();
			outToClient.close();
			this.communicationSocket.close();
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
