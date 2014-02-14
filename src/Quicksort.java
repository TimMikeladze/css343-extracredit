/**
 * @author Tim Mikeladze
 * 
 * 
 */
public class Quicksort {
	
	private static final int SIZE = 5000000;
	private static final int LIMIT = 15;
	private static final int TESTS = 5;
	private static boolean useInsertionSort = false;
	
	public static void main(String[] args) {
		// Runs speed tests comparing a Quick sort with a median pivot and a Quick sort with a median pivot combined with an Insertion sort for smaller subarrays
		
		double average = 0;
		for (int i = 1; i <= TESTS; i++) {
			System.out.println("Test " + i);
			int[] array1 = generateRandomArray(SIZE);
			int[] array2 = new int[array1.length];
			
			System.arraycopy(array1, 0, array2, 0, array1.length);
			
			long start = 0;
			long speed1 = 0;
			long speed2 = 0;
			
			useInsertionSort = false;
			start = System.currentTimeMillis();
			
			quickSort(array1);
			
			speed1 = System.currentTimeMillis() - start;
			System.out.println("Quick sort with median pivot: " + speed1 + " ms");
			
			useInsertionSort = true;
			start = System.currentTimeMillis();
			
			quickSort(array2);
			
			speed2 = System.currentTimeMillis() - start;
			System.out.println("Quick sort with median pivot and insertion sort: " + speed2 + " ms");
			
			long diff = speed1 - speed2;
			double percentage;
			if (speed1 != 0) {
				percentage = 100 - (100 * speed2 / speed1);
			} else {
				percentage = 0;
			}
			System.out.println("Speed increase: " + diff + " ms");
			System.out.println("Speed increase: " + percentage + "%");
			
			average += percentage;
		}
		
		System.out.println("\nAverage speed increase: " + (average / TESTS) + "%");
	}
	
	public static void quickSort(int[] input) {
		quickSort(input, 0, input.length - 1);
	}
	
	private static void quickSort(int[] array, int l, int r) {
		// Insertion sort is faster than Quick sort on smaller data sets
		if ((l + LIMIT <= r && useInsertionSort) || !useInsertionSort) {
			// Partition the array into sub arrays and recursively sort each one
			int partition = partition(array, l, r);
			if (l < partition - 1) {
				quickSort(array, l, partition - 1);
			}
			if (r > partition) {
				quickSort(array, partition, r);
			}
		} else {
			// Insertion sort is only used for small data sets
			insertionSort(array, l, r);
		}
		
	}
	
	private static int partition(int[] array, int l, int r) {
		// Find the pivot which is the median of max(0, n/2, n - 1)
		int pivot = median(array, l, r);
		
		// Sort the sub array
		while (l <= r) {
			while (array[l] < pivot) {
				l++;
			}
			while (array[r] > pivot) {
				r--;
			}
			if (l <= r) {
				swap(array, l, r);
				l++;
				r--;
			}
		}
		return l;
	}
	
	private static void swap(int[] array, int l, int r) {
		// Swaps two array elements
		int temp = array[l];
		array[l] = array[r];
		array[r] = temp;
	}
	
	private static int median(int[] input, int l, int r) {
		return Math.max(Math.min(input[l], input[r]), Math.min(Math.max(input[l], input[r]), input[(l + r) / 2]));
		
	}
	
	private static void insertionSort(int array[], int l, int r) {
		while (l < r) {
			int value = array[l];
			int i = l - 1;
			while (i >= 0 && array[i] > value) {
				array[i + 1] = array[i];
				i = i - 1;
			}
			array[i + 1] = value;
			l++;
		}
	}
	
	private static int[] generateRandomArray(int size) {
		int array[] = new int[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * (size));
		}
		return array;
	}
}
