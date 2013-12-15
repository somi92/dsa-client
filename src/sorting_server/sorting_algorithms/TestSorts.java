package sorting_server.sorting_algorithms;

public class TestSorts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] p = {3,2,4,1,5};
		int[] n = SortingAlgorithms.bubbleSort(p, 0);
		
		for(int i=0; i<n.length; i++) {
			System.out.print(n[i]+" ");
		}
	}

}
