package lab01;

public class SumExperiment {
	
	/**
	 * This method checks an array for any pair of values that add up to 20.
	 * If such a pair is found, it returns the index of the smallest value in that pair.
	 * If no such pair is found, it returns -1.
	 *
	 * @param array an array of integers to be inspected
	 * @return the index of the smallest value in the pair that sums to 20, or -1 if no such pair exists
	 */
	public static int checkSum(int[] array){
		// if it find such a pair, it will return the *index* of the smallest value
		// if it does not find such as pair, it will return -1;
		// remove the following line after you are done writing the function
		int value = 0;
		
		int smallest = array[0];
		int largest = array[array.length - 1];
		int smallestIndex = 0;
		int largestIndex = array.length - 1;
		
		for(int i = 0; i < array.length - 1; i++) {
			value = smallest + largest;
			if(value != 20) {
				if(value > 20) {
					largest = array[largestIndex - 1];
					--largestIndex;
				}
				else {
					smallest = array[smallestIndex + 1];
					++smallestIndex;		
				}
			}
			else {
				break;
			}
			
		}
		if(value != 20) {
			smallestIndex = -1;
		}
		return smallestIndex;
				
	}
	
	
	public static void main(String[] args) {
		int[] array1 = new int[]{5, 7, 8, 9, 10, 15, 16};
		if (checkSum(array1) != 0)
			System.err.println("TEST1 FAILED");
		
		int[] array2 = new int[]{3, 5, 8, 9, 10, 15, 16};
		if (checkSum(array2) != 1)
			System.err.println("TEST2 FAILED");
		
		int[] array3 = new int[]{3, 4, 6, 9, 10, 14, 15};
		if (checkSum(array3) != 2)
			System.err.println("TEST3 FAILED");
		
		int[] array4 = new int[]{6, 7, 8, 9, 10, 15, 16};
		if (checkSum(array4) != -1)
			System.err.println("TEST4 FAILED");
		
		System.out.println("Done!!!");
	}
}
