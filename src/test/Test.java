package test;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String a = "123.212.323.212:8090/343.322.121.212:2132/";
		System.out.println(a.substring(0, a.length()-1));
//		String a = "123.212.323.212:8090";
		String[] s = a.split("/");
		System.out.println("duzina niza: "+s.length);
		for(int i=0; i<s.length; i++) {
			System.out.println(s[i]+" - duzina: "+s[i].length());
		}
		
		System.out.println('\n');
		
		int[] data = {1,2,3,4,2};
		boolean sorted = true;
		for(int i=0; i<data.length-1; i++) {
			if(data[i] > data[i+1]) {
				sorted = false;
				break;
			}
		}
		System.out.println("Sorted: "+sorted);
	}

}
