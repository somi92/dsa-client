package sorting_server.sorting_algorithms;

public class SortingAlgorithms {

	public static int[] bubbleSort(int[] array, int iteration) {
		int in, temp;
		for (in = 0; in<array.length-1-iteration; in++) {
			if (array[in] > array[in+1]) {
				temp = array[in];
				array[in] = array[in+1];
				array[in+1] = temp;
			}
		}
		return array;
	}
	
	public static int[] selectionSort(int[] array, int iteration) {
		int in;
		int min, temp;
		min = iteration;
		for (in = iteration+1; in<array.length; in++) {
			if (array[in] < array[min]) {
				min = in;
			}
		}
		temp = array[iteration];
		array[iteration] = array[min];
		array[min] = temp;	
		return array;
	}
	
	public static int[] insertionSort(int[] array, int iteration) {
		int in, temp;
		iteration++;
		temp = array[iteration];
		in = iteration;
		while (in>0 && array[in-1] > temp) {
			array[in] = array[in-1];
			--in;
		}
		array[in] = temp;
		return array;
	}
}
