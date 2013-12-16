package sorting_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestSS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		try {
//			Socket s = new Socket("localhost", 9090);
//			
//			BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
//			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//			DataOutputStream out = new DataOutputStream(s.getOutputStream());
//			
//			
//			
////			String m = c.readLine();
//////			String m = "HELLO BSI"+'\n';
////			m = m + '\n';
////			out.writeBytes(m);
////			System.out.println("Poslato serveru: "+m);
////			
////			String i = in.readLine();
////			System.out.println("Primljeno od servera: "+i);
//			
//			while(true) {
//				String m = c.readLine();
//				m = m + '\n';
//				out.writeBytes(m);
//				System.out.println("Poslato serveru: "+m);
//				
//				String i = in.readLine();
//				System.out.println("Primljeno od servera: "+i);
//			}
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String s = "127.0.0.1:8980/127.0.0.1:8322";
		String[] sa = s.split("/");
		System.out.println(sa[0]);
		if(sa.length != 1) {
			System.out.println(sa[1]);
		} else {
			System.out.println("sa[1] = null");
		}
//		System.out.println(s.indexOf("/"));
		System.out.println(sa[0].substring(0, sa[0].indexOf(":")));
		System.out.println(sa[0].substring(sa[0].indexOf(":")+1));
	}

}
