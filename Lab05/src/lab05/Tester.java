package lab05;

public class Tester {

    //TODO: You will need to write your own test for the Lab and the Assignment
    // Hint: You will need a main method to run your tests
    
    /*
     * Suggested Testing Strategy for Lab:
     *  1. Create an empty **SortedBinarySet**.
     *  2. Check its size and whether it is empty.
     *  3. Start adding a few numbers to your **SortedBinarySet** and ensure they are added properly (i.e., they are in the sorted order, and the other member variables reflect the changes).
     *  4. Continue adding values to your **SortedBinarySet** until you go beyond its original capacity. Ensure no errors occur in this case and that the **SortedBinarySet** does indeed grow.
     *  5. Test your `clear()` method to see if all elements are removed from the **SortedBinarySet**.
     */
	
	public static void main(String[] args) {
		SortedBinarySet set= new SortedBinarySet();
		
		System.out.println("Initial size: " + set.size());
		System.out.println("Is set empty? " + set.empty());
		
		set.insert(5.0);
		set.insert(1.0);
		set.insert (3.0);
		System.out.println("Set after inserting 5.0, 1.0, 3.0: " + set.toString());
		System.out.println("Size after insertion: " + set.size()); // Expected: 3
		System.out.println("Is set empty? " + set.empty()); // Expected: false

		// Step 4: Add more numbers and test if the set grows correctly
		set.insert (10.0); 
		set.insert(2.0); 
		set.insert(6.0); 
		set.insert(8.0); 
		set.insert(12.0); 
		set.insert(14.0); 
		set.insert(9.0);
		set.insert(4.0); // This should cause the set to grow in capacity System.out-println("Set after adding more elements: " + set. toString());
		System.out.println("Size after adding more elements: " + set.size());

		// Step 5: Test clear method
		set.clear();
		System.out.println("Set after clearing: " + set.toString());
		System.out.println("Size after clearing: " + set.size()); // Expected: 0
		System.out.println("Is set empty after clearing? " + set.empty()); // Expected: true
		
		double[] inputArray = {5.0, 3.0, 7.0, 1.0, 5.0, 2.0};
        SortedBinarySet setFromArray = new SortedBinarySet(inputArray);
        System.out.println("\nTest 2: Constructor with input array.");
        System.out.println("Set initialized with {5.0, 3.0, 7.0, 1.0, 5.0, 2.0}: " + setFromArray.toString());
        // Expected output: 1.0 2.0 3.0 5.0 7.0 (no duplicates, sorted order)
        System.out.println("Size of set from array: " + setFromArray.size()); // Expected: 5

        // Test 3: Contains and ContainsAll
        System.out.println("\nTest 3: Contains and ContainsAll.");
        System.out.println("Contains 7.0? " + setFromArray.contains(7.0)); // Expected: true
        System.out.println("Contains 4.0? " + setFromArray.contains(4.0)); // Expected: false
        
        double[] subset = {1.0, 2.0};
        double[] notSubset = {1.0, 8.0};
        System.out.println("Contains all {1.0, 2.0}? " + setFromArray.containsAll(subset)); // Expected: true
        System.out.println("Contains all {1.0, 8.0}? " + setFromArray.containsAll(notSubset)); // Expected: false
        
        // Test 4: Test growth by inserting beyond initial capacity
        System.out.println("\nTest 4: Test set growth.");
        for (double i = 8.0; i <= 20.0; i++) {
            setFromArray.insert(i);
        }
        System.out.println("Set after inserting values 8.0 to 20.0: " + setFromArray.toString());
        // Expected: set should grow and contain all values from 1.0 to 20.0 in sorted order.
        System.out.println("Final size after growth: " + setFromArray.size());
                
        System.out.println("\nInserting 100,000 elements into the set...");

        for (double i = 1.0; i <= 100000; i++) {
            set.insert(i);
        }

        // Sequential Search Test
        set.usesBinarySearch = false;  // Use sequential search
        long startTimeSequential = System.nanoTime();
        set.findIndex(99999.0);  // Search for an element near the end
        long endTimeSequential = System.nanoTime();
        long durationSequential = endTimeSequential - startTimeSequential;

        System.out.println("Time taken by sequential search: " + durationSequential + " nanoseconds");

        // Binary Search Test
        set.usesBinarySearch = true;  // Use binary search
        long startTimeBinary = System.nanoTime();
        set.findIndex(99999.0);  // Search for the same element
        long endTimeBinary = System.nanoTime();
        long durationBinary = endTimeBinary - startTimeBinary;

        System.out.println("Time taken by binary search: " + durationBinary + " nanoseconds");

        // Compare results
        System.out.println("Binary search is faster by: " + (durationSequential - durationBinary) + " nanoseconds");
		
	}

}
