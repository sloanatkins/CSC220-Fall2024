package lab05;

/**
 * Represents a sorted set of doubles backed by a simple array. 
 * The set remains sorted at all times and does not allow duplicates.
 */
public class SortedBinarySet {
	/** The array that holds the list of values */
	public double[] theData;
	/** The capacity of the storage array */
	private int capacity;
	/** The current number of elements in the list */
	private int size;

	/** Constant for the initial storage array capacity */
	private static final int INITIAL_STORAGE_CAPACITY = 11;

	/** A flag to toggle between binary search and sequential search. */
	public boolean usesBinarySearch = false; //Keep as false for the Lab. You will need this in the assignment.

	/**
	 * Default constructor that initializes the set with the default capacity.
	 */
	public SortedBinarySet(){
		// TODO: Lab Part 2.1 - Initialize theData array with INITIAL_STORAGE_CAPACITY and other class variables
		this.theData = new double[INITIAL_STORAGE_CAPACITY];
		this.size = 0;
		this.capacity = INITIAL_STORAGE_CAPACITY;
	}

	/**
	 * Constructor that initializes the set with an input array.
	 * This is for the assignment, not for the lab.
	 * @param input The array to initialize the set with.
	 */
	public SortedBinarySet(double[] input){
		// TODO: Assignment Part 1.5 - Hint: think about whether you can reuse any of the methods you have implemented to make your job easier.
		this.theData = new double[INITIAL_STORAGE_CAPACITY];
	    this.size = 0;
	    this.capacity = INITIAL_STORAGE_CAPACITY;
	    
	    for (double element : input) {
	        insert(element);
	    }
	}

	/**
	 * Checks if the set is empty.
	 * @return true if the set is empty, false otherwise.
	 */
	public boolean empty(){
		// TODO: Lab Part 2.2 - Check if the SortedBinarySet contains no elements
		if(this.size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of elements in the set.
	 * @return The number of elements in the set.
	 */
	public int size(){
		// TODO: Lab Part 2.3 - Return the size of the SortedBinarySet
		return this.size; // placeholder
	}

	/**
	 * Grows the storage array by doubling its capacity.
	 */
	public void grow(){
		// TODO: Lab Part 2.4 - Double the size of theData and update capacity
		this.capacity *= 2;
		double[] newData = new double[this.capacity];
		
		for (int i = 0; i < this.size; i++) {
	        newData[i] = this.theData[i];
	    }
		
		this.theData = newData;
	}

	/**
	 * Converts the set to a string that includes its elements, capacity, and size.
	 * This is primarily used for testing purposes.
	 * @return A string representing the set.
	 */
	public String toString(){
		// TODO: Lab Part 2.5 - Return the list of elements, current capacity, and size as a string
		String output = "";
		
		for(int i = 0; i < this.size; i++) {
			output += this.theData[i] + " ";
		}
		
		output += "\n" + "Capacity: " + this.capacity + "\n" + "Size: " + this.size;
		
		return output; // placeholder
	}

	/**
	 * Clears all elements from the set.
	 * After this method is called, the set should be empty.
	 */
	public void clear() {
		// TODO: Lab Part 2.6 - Remove all elements from the SortedBinarySet and update class variables
		
		for(int i = 0; i < size; i ++) {
			this.theData[i] = 0;
		}
		
		this.size = 0;
	}

	/**
	 * Inserts a new value into the set in the correct position.
	 * The value is inserted only if it's not already in the set.
	 * @param newVal The value to insert.
	 * @return true if the value was successfully inserted, false if it already exists.
	 */
	public boolean insert(double newVal){
		// TODO: Lab Part 2.7 - Insert newVal in sorted order if it does not exist
		int index = sequentialFind(newVal);
		
		if (index < 0) {
	        index = (index * (-1)) - 1;
	        if (size == capacity) {
	            grow();
	        }

	        for (int i = size; i > index; i--) {
	            this.theData[i] = theData[i - 1];
	        }

	        theData[index] = newVal;
	        this.size++;
	        return true;
	    }
	    return false;

	}

	/**
	 * Sequentially searches for a target in the set.
	 * @param target The target value to search for.
	 * @return The index where the target is found, or -index - 1 if not found.
	 */
	private int sequentialFind(double target) {
		// TODO: Lab Part 2.8 - Implement sequential search for the target
		
		for(int i = 0; i < this.size; i++) {
			
			if(target == this.theData[i]) {
				return i;
			}
			
			else if (target < this.theData[i]) {
				return (-1) * i - 1;
			}
		}
		
		return (-1) * this.size - 1; // placeholder
	}

	//*********************************************************************
	// * you will implement the rest of the methods for your assignment    *
	// * don't touch them before finishing the lab portion                 *
	// *********************************************************************
	
	/**
	 * Removes a specified element from the set.
	 * @param element The element to remove.
	 * @return true if the element was removed, false if it did not exist.
	 */
	public boolean remove(double element){
		// TODO: Assignment part 1.1
		int index = findIndex(element);
	    if (index >= 0) {
	        for (int i = index; i < size - 1; i++) {
	            theData[i] = theData[i + 1];
	        }
	        theData[size - 1] = 0;
	        size--;
	        return true;
	    }
	    return false;
	}

	/**
	 * Uses binary search to find the target in the set (only if enabled).
	 * @param target The target value to search for.
	 * @return The index where the target is found, or -index - 1 if not found.
	 */
	private int binaryFind(double target) {
		// TODO: Assignment Part 1.2
		int low = 0;
	    int high = size - 1;
	    while (low <= high) {
	        int mid = (low + high) / 2;
	        if (theData[mid] == target) {
	            return mid;
	        } else if (theData[mid] < target) {
	            low = mid + 1;
	        } else {
	            high = mid - 1;
	        }
	    }
	    return -(low + 1); // not found
	}

	/**
	 * Checks if the set contains a specific element.
	 * @param element The element to check.
	 * @return true if the element is found, false otherwise.
	 */
	public boolean contains(double element){
		// TODO: Assignment Part 1.3
		return findIndex(element) >= 0;
	}

	/**
	 * Checks if the set contains all the elements of an input array.
	 * @param elements The array of elements to check.
	 * @return true if all elements are found in the set, false otherwise.
	 */
	public boolean containsAll(double[] elements){
		// TODO: Assignment Part 1.4
		for (double element : elements) {
	        if (!contains(element)) {
	            return false;
	        }
	    }
	    return true;
	}


	/**
	 * Finds the index of a target value using either sequential or binary search.
	 * @param target The target value to search for.
	 * @return The index where the target is found, or -index - 1 if not found.
	 */
	public int findIndex(double target) {		
		if (usesBinarySearch) {
			return binaryFind(target);
		} else {
			return sequentialFind(target);
		}
	}

	
}
