import java.util.Arrays;

public class Quicksort {
	
	public static void main(String[] args) {
		int[] input = { 35, 98, 86, 6, 23, 840, 11, 93, 95, 8, 3, 7, 36, 361, 5, 52, 53, 83, 80, 390, 620, 1, 40, 872, 40, 768, 30, 4, 99, 9 };
		quicksort(input);
		System.out.print(Arrays.toString(input));
	}
	
	public static void quicksort(int[] input) {
		quicksort(input, 0, input.length - 1);
	}
	
	private static void quicksort(int[] input, int left, int right) {
		int partition = partition(input, left, right);
		if (left < partition - 1) {
			quicksort(input, left, partition - 1);
		}
		if (right > partition) {
			quicksort(input, partition, right);
		}
	}
	
	private static int median(int[] input, int left, int right) {
		return Math.max(Math.min(input[left], input[right]), Math.min(Math.max(input[left], input[right]), input[(left + right) / 2]));
		
	}
	
	private static int partition(int[] input, int left, int right) {
		int pivot = median(input, left, right);
		
		while (left <= right) {
			while (input[left] < pivot) {
				left++;
			}
			while (input[right] > pivot) {
				right--;
			}
			if (left <= right) {
				int temp = input[left];
				input[left] = input[right];
				input[right] = temp;
				left++;
				right--;
			}
		}
		return left;
	}
}
