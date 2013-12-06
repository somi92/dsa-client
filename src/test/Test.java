package test;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String a = "123.212.323.212:8090/343.322.121.212:2132";
//		String a = "123.212.323.212:8090";
		String[] s = a.split("/");
		System.out.println("duzina niza: "+s.length);
		for(int i=0; i<s.length; i++) {
			System.out.println(s[i]+" - duzina: "+s[i].length());
		}
		
//		System.out.println("".length());
	}

}
