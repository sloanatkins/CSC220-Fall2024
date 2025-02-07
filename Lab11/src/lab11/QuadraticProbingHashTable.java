package lab11;

/**
 * Represents a hash table using quadratic probing for collision resolution.
 */
public class QuadraticProbingHashTable {

    /**
     * The array that holds the hash table entries.
     */
    public HashEntry[] HashTable;

    /**
     * The number of occupied cells in the hash table.
     */
    public int currentSize;

    /**
     * Constructor to create a hash table of the specified initial size.
     * Initializes all elements in the table to null.
     *
     * @param size the initial size of the hash table
     */
    public QuadraticProbingHashTable(int size) {
        //TODO Lab Part 2
    	HashTable = new HashEntry[size];
        currentSize = 0;
    }

    /**
     * Inserts an integer into the hash table. If the item is already present,
     * this method does nothing. If the load factor exceeds 0.75, the table is rehashed.
     * 
     * Note: Before inserting the item, the load factor is checked. If the load factor
     * is above the threshold, rehashing is performed first, followed by insertion. 
	 * Hint: first check the what the load factor would be after add - if the size is beyond rehash first!
     *
     * @param x the integer value to be inserted into the hash table
     */
    public void insert(int x) {
        //TODO: Lab Part 4
    	double score = ((double)currentSize + 1) / HashTable.length;
    	
    	if(score >= 0.75) {
    		rehash();
    	}
    	
    	int positionIndex = hash(x, HashTable.length);
    	
    	HashEntry entry = new HashEntry(x);
    	
    	if(HashTable[positionIndex] == null) {
    		HashTable[positionIndex] = entry;
    	} else if(HashTable[positionIndex].isActive == false) {
    		HashTable[positionIndex] = entry;
    	} else if (HashTable[positionIndex] != null) {
    		insertHelper(entry, positionIndex);
    	}
    	
    	currentSize++;
    }
    
    private void insertHelper(HashEntry entry, int positionIndex) {
    	int i = 1;
    	int hashKey = positionIndex;
    	
    	while (HashTable[positionIndex] != null) {
            if (HashTable[positionIndex].isActive == false) {
                break;
            }
            positionIndex = (int)(hashKey + Math.pow(i,2)) % HashTable.length;
            i++;
        }
    	HashTable[positionIndex] = entry;
    }

    /**
     * Increases the size of the hash table by a factor of two and rehashes
     * the current elements into the new, larger hash table.
     */
    public void rehash() {
        //TODO: Lab Part 5
    	HashEntry[] NewHashTable = new HashEntry[HashTable.length * 2];
    	int positionIndex;
    	
    	for (int i = 0; i < HashTable.length; i++) {
    		if (HashTable[i] != null && HashTable[i].isActive) {
    			int value = HashTable[i].element;
    			positionIndex = hash(value, NewHashTable.length);
    			int hashKey = positionIndex;
    			int n = 1; 

    			while (NewHashTable[positionIndex] != null) {
    				positionIndex = (int)(hashKey + Math.pow(n, 2)) % NewHashTable.length;
    				n++;
    			}

    			HashEntry newEntry = new HashEntry(value);
    			NewHashTable[positionIndex] = newEntry;
    		}
    	}
    	HashTable = NewHashTable;
    }

    /**
     * A simple hash function for integer values. This method returns a hash value
     * between 0 and `tableSize - 1` inclusive, using the modulo operator. The returned
     * hash value is computed to handle both positive and negative integers.
     *
     * @param value the integer to be hashed
     * @param tableSize the size of the hash table
     * @return the hash value for the input integer, constrained by the table size
     */
    public int hash(int value, int tableSize) {
        //TODO Lab Part 3
    	return (Math.abs(value)) % tableSize;
    }

    /**
     * Removes an element from the hash table by marking it as inactive.
     * The element is not physically removed from the table; instead, the active
     * status is simply set to false.
     *
     * @param x the integer value to be removed from the hash table
     */
    public void remove(int x) {
        //TODO: Assignment Part 2
    	int positionIndex = hash(x, HashTable.length);
    	int i = 1;
    	
    	while(HashTable[positionIndex] != null) {
    		if(HashTable[positionIndex].element == x && HashTable[positionIndex].isActive) {
    			HashTable[positionIndex].isActive = false;
    			currentSize--;
    			return;
    		}
    		positionIndex = (positionIndex + i * i) % HashTable.length;
    		i++;
    	}
    }

    /**
     * Searches for an element in the hash table.
     * If the element is found, this method returns the index at which the element resides.
     * If the element is not in the hash table, this method returns -1.
     *
     * @param x the integer value to search for in the hash table
     * @return the index of the element if found; -1 otherwise
     */
    public int find(int x) {
        //TODO: Assignment Part 3
    	int positionIndex = hash(x, HashTable.length); 
        int i = 1; 

        while (HashTable[positionIndex] != null) {
            if (HashTable[positionIndex].element == x && HashTable[positionIndex].isActive) {
                return positionIndex; 
            }
            positionIndex = (positionIndex + i * i) % HashTable.length;
            i++;
       
        }
    	positionIndex = -1;
    	
    	return positionIndex;
    
    }

    /**
     * Provides a string representation of the hash table.
     * Each element's value is displayed along with its active status ('T' for active, 'F' for inactive).
     * Empty slots are represented by "eF".
     *
     * @return a string representation of the hash table
     */
    public String toString() {
        String toReturn = "";
        for (int i = 0; i < HashTable.length; i++) {
            if (HashTable[i] == null) {
                toReturn += ("eF ");
            } else {
                if (HashTable[i].isActive)
                    toReturn += (HashTable[i].element + "T ");
                else
                    toReturn += (HashTable[i].element + "F ");
            }
        }
        return toReturn;
    }
    
    public static void main(String[] args){
    	
    	
    	// ********************* TESTS FOR LAB ****************************//
    	
    	QuadraticProbingHashTable h1 = new QuadraticProbingHashTable(10);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF eF eF eF eF "))
    		System.err.print("TEST FAILED: constructor ( 0 )");
    	    	
    	h1.insert(89);
    	h1.insert(58);
    	h1.insert(6);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF 6T eF 58T 89T "))
    		System.err.println("TEST FAILED: insert ( 1 )");
    	    	
    	h1.insert(16);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF 6T 16T 58T 89T "))
    			System.err.println("TEST FAILED: insert ( 2 )");
    	
    	h1.insert(9);
    	if (!h1.toString().equals("9T eF eF eF eF eF 6T 16T 58T 89T "))
			System.err.println("TEST FAILED: insert ( 3 )");   
    	
    	QuadraticProbingHashTable h2 = new QuadraticProbingHashTable(7);
    	
    	h2.insert(0);
    	h2.insert(1);
    	h2.insert(2);
    	h2.insert(3);
    	h2.insert(4);
    	h2.insert(5);
    	
    	if (!h2.toString().equals("0T 1T 2T 3T 4T 5T eF eF eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 4 )"); 
    	
    	System.out.println("Lab Testing Done!!!");
    	
    	
    	// ********************* TESTS FOR ASSIGNMENT ****************************//
    	
    	QuadraticProbingHashTable h3 = new QuadraticProbingHashTable(11);
    	
    	if (!h3.toString().equals("eF eF eF eF eF eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 5 )");    	    	
    	
    	h3.insert(44);    	
    	h3.insert(4);
    	h3.remove(44);
    	
    	if (!h3.toString().equals("44F eF eF eF 4T eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: remove ( 6 )");    	    	
    	
    	h3.insert(77);
    	
    	if (!h3.toString().equals("77T eF eF eF 4T eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 7 )");    	    	    	
    	
    	h3.insert(16);    	
    	h3.insert(28);
    	h3.insert(21);    	
    	h3.insert(11);    	
    	h3.insert(22);
    	h3.insert(33);  
    	
    	if (!h3.toString().equals("77T 11T eF 33T 4T 16T 28T eF eF 22T 21T "))
			System.err.println("TEST FAILED: insert ( 8 )");    	    	

    	h3.insert(55);
    	
    	if (!h3.toString().equals("22T eF eF eF 4T eF 28T eF eF eF eF 77T 11T eF eF 33T 16T eF eF eF 55T 21T "))
			System.err.println("TEST FAILED: insert ( 9 )");    	    	    	
    	
    	if (h3.find(4) != 4)
    		System.err.print("TEST FAILED: find ( 10 )");
    	
    	if (h3.find(44) != -1)
    		System.err.print("TEST FAILED: find ( 11 )");
    	
    	if (h3.find(77) != 11)
    		System.err.print("TEST FAILED: find ( 12 )");    
    	
    	System.out.println("Assignment Testing Done!!!");
    	
    }

}