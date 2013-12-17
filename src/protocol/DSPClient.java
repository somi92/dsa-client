package protocol;

public class DSPClient {
	
	public static final int WAITING = 0;
	public static final int ACCEPTED = 1;
	public static final int PEERS = 2;
	public static final int NOT_FOUND = 3;
	public static final int NOT_SUPPORTED = 4;
	public static final int ERROR = 5;
	public static final int FINISHED = 6;
	public static final int DISCONNECT = 7;
	
	private int state;
	private String[] addresses;
	private String services;
	private String requests;
	private String data;
	
	public DSPClient() {
		this.state = DSPClient.WAITING;
		this.services = "";
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String[] getAddresses() {
		return addresses;
	}

	public void setAddresses(String[] addresses) {
		this.addresses = addresses;
	}
	
	public String getServices() {
		return services;
	}
	
	public void setServices(String services) {
		this.services = services;
	}

	public String getRequests() {
		return requests;
	}

	public void setRequests(String requests) {
		this.requests = requests;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int parseProtocolMessage(String message) {
		// TODO Auto-generated method stub
		
		String[] messageParts = message.split(" ");
		String pMethod = "";
		String pOptions = "";
		
		if(messageParts.length>0 && messageParts.length<3) {
			
			if(messageParts.length == 1) {
				pMethod = messageParts[0];
				pOptions = "";
			}
			if(messageParts.length == 2) {
				pMethod = messageParts[0];
				pOptions = messageParts[1];
			}
			
		} else {
			setState(ERROR);
			return getState();
		}
		
		switch (pMethod) {
			
			case "WELCOME": {
				
				setState(DSPClient.ACCEPTED);
			}
			break;
			
			case "PEERS": {
				
				if(pOptions != null && pOptions.length()>0) {
					pOptions = pOptions.substring(1);
					setAddresses(pOptions.split("/"));
					setState(DSPClient.PEERS);
				} else {
					setAddresses(null);
					setState(DSPClient.ERROR);
				} 
			}
			break;
			
			case "NOT_FOUND": {
				
				setState(DSPClient.NOT_FOUND);
			}
			break;
			
			case "NOT_SUPPORTED": {
				
				setState(DSPClient.NOT_SUPPORTED);
			}
			
			case "FINISHED": {
				
				if(pOptions != null && pOptions.length()>0) {
					setData(pOptions);
					setState(DSPClient.FINISHED);
				} else {
					setState(DSPClient.ERROR);
				}
			}
			break;
			
			case "BYE": {
				
				setState(DSPClient.DISCONNECT);
			}
			break;
			
			default:
				setState(DSPClient.ERROR);
		}
		
		return getState();
	}

	
	public String connectToMainServer(String services, int port) {
		setServices(services);
		return "HELLO "+services+" "+port+'\n';
	}
	
	public String askForPeers(String request) {
		setRequests(request);
		return "PEERS "+request+'\n';
	}
	
	public String finishedSorting() {
		return "FINISHED"+'\n';
	}
	
	public String generateSortRequest(String data) {
		setData(data);
		return "SORT "+getRequests()+" "+data+'\n';
	}
	
	public String generateSortRequest(String data, int iteration) {
		setData(data);
		return "SORT "+getRequests()+"#"+iteration+" "+data+'\n';
	}
	
	public String sayGoodbye() {
		return "BYE"+'\n';
	}
}
