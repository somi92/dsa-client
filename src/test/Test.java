package test;

import java.io.IOException;
import java.net.ServerSocket;

import client.ClientThread;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String a = "123.212.323.212:8090/343.322.121.212:2132/";
//		System.out.println(a.substring(0, a.length()-1));
////		String a = "123.212.323.212:8090";
//		String[] s = a.split("/");
//		System.out.println("duzina niza: "+s.length);
//		for(int i=0; i<s.length; i++) {
//			System.out.println(s[i]+" - duzina: "+s[i].length());
//		}
//		
//		System.out.println('\n');
//		
//		int[] data = {1,2,3,4,2};
//		boolean sorted = true;
//		for(int i=0; i<data.length-1; i++) {
//			if(data[i] > data[i+1]) {
//				sorted = false;
//				break;
//			}
//		}
//		System.out.println("Sorted: "+sorted);
		
		ClientThread c = new ClientThread("localhost", 8090);
		c.setServices("BSI");
		
		try {
			ServerSocket ss = new ServerSocket(0);
			c.setServerSidePort(ss.getLocalPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread t = new Thread(c);
		
		t.start();
	}

}
