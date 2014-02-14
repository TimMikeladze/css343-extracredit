import java.util.Arrays;

public class Quicksort {
	
	public static void main(String[] args) {
		int[] array = { 35, 98, 86, 6, 23, 840, 11, 93, 95, 8, 3, 7, 36, 361, 5, 52, 53, 83, 80, 390, 620, 1, 40, 872, 40, 768, 30, 4, 99, 9 };
		quickSort(array);
		System.out.print(Arrays.toString(array));
	}
	
	public static void quickSort(int[] input) {
		quickSort(input, 0, input.length - 1);
	}
	
	private static void quickSort(int[] array, int l, int r) {
		// Insertion sort is faster than Quick sort on smaller data sets
		// Here we use insertion sort if array is 10 elements or less
		if (l + 10 <= r) {
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
		// Find the pivot which is the median of 0, n/2 and n - 1
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
}
