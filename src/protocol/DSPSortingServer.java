package protocol;

public class DSPSortingServer {

	public static final int WAITING = 0;
	public static final int SORTING = 1;
	public static final int NOT_SUPPORTED = 2;
	public static final int ERROR = 3;
	public static final int DISCONNECT = 4;
	
	private int state;
	private String data;
	private String sort;
	private int iteration;
	
	public DSPSortingServer() {
		this.state = DSPSortingServer.WAITING;
		this.data = "";
		this.sort = "";
		this.iteration = 0;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getSort() {
		return sort;
	}
	
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public int getIteration() {
		return iteration;
	}
	
	public void setIteration(int iteration) {
		this.iteration = iteration;
	}
	
	public int parseProtocolMessage(String message) {
		
		String[] messageParts = message.split(" ");
		String pMethod = "";
		String pOptions = "";
		String pData = "";
		
		if(messageParts.length == 1 || messageParts.length == 3) {
			
			if(messageParts.length == 1) {
				pMethod = messageParts[0];
				pOptions = "";
				pData = "";
			}
			
			if(messageParts.length == 3) {
				pMethod = messageParts[0];
				pOptions = messageParts[1];
				pData = messageParts[2];
			}
			
		} else {
			setState(ERROR);
			return getState();
		}
		
		switch (pMethod) {
			
			case "SORT": {
				
				if(pOptions.startsWith("B") || pOptions.startsWith("S") || pOptions.startsWith("I")) {
					String[] sortParts = pOptions.split("#");
					setIteration(Integer.parseInt(sortParts[1]));
					setSort(sortParts[0]);
					setData(pData);
					setState(DSPSortingServer.SORTING);
					
				} else {
					setIteration(0);
					setSort("");
					setData("");
					setState(DSPSortingServer.ERROR);
				}
				
			}
			break;
			
			case "BYE": {
				
				setState(DSPSortingServer.DISCONNECT);
			}
			break;
			
			default:
				setState(DSPSortingServer.ERROR);
		}
		
		return getState();
	}
	
	public String generateResponse() {
		
		String response = "";
		
		if(getState() == DSPSortingServer.SORTING) {
			response = "FINISHED "+getData();
		}
		
		if(getState() == DSPSortingServer.NOT_SUPPORTED) {
			response = "NOT_SUPPORTED";
		}
		
		if(getState() == DSPSortingServer.ERROR) {
			response = "ERROR";
		}
		
		if(getState() == DSPSortingServer.DISCONNECT) {
			response = "BYE";
		}
		
		return response+'\n';
	}
	
}
