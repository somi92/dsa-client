package protocol;

public class DSPClient implements DistributedSortingProtocol {
	
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
	private int port;
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

	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
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

	@Override
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
			
			case "SORT": {
				
			}
			
			case "NOT_FOUND": {
				
				setState(DSPClient.NOT_FOUND);
			}
			break;
			
			case "NOT_SUPPORTED": {
				
				setState(DSPClient.NOT_SUPPORTED);
			}
			
			case "FINISHED": {
				
				setState(DSPClient.FINISHED);
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

	@Override
	public String generateResponse() {
		// TODO Auto-generated method stub
		String response = new String();
		
		if(getState() == DSPClient.ERROR) {
			response = "ERROR";
		}
		if(getState() == DSPClient.NOT_FOUND) {
			response = "NOT_FOUND";
		}
		if(getState() == DSPClient.FINISHED) {
			response = "OK";
		}
		if(getState() == DSPClient.DISCONNECT) {
			response = "BYE";
		}
		
		return response+'\n';
	}
	
	public String generateResponse(String message) {
		// TODO Auto-generated method stub
		String response = new String();
		
		if(getState() == DSPClient.PEERS) {
			response = "PEERS "+message;
		} else if(getState() == DSPClient.ERROR){
			response = "ERROR";
		} else {
			throw new RuntimeException("This method is used only when responding to PEERS request.");
		}
		
		return response+'\n';
	}
	
	private boolean isInteger(String s) {
		try {
			@SuppressWarnings("unused")
			int i = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

}
