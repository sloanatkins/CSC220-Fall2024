package lab11;

/**
 * Represents an entry in a hash table. Each entry contains an element and a flag
 * indicating if the entry is active (i.e., not deleted).
 */
public class HashEntry {
    

    //The value stored in the hash table.
    public int element;
    
    //A flag indicating if the entry is active (true if active, false if deleted).
    public boolean isActive;
    
    /**
     * Default constructor that initializes the element to 0 and the entry as active.
     */
    public HashEntry() {
        element = 0;
        isActive = true;
    }
    
    /**
     * Constructor that initializes the element with the given value and sets the entry as active.
     *
     * @param v the value to be stored in the hash table entry
     */
    public HashEntry(int v) {
        element = v;
        isActive = true;
    }
    
    /**
     * Constructor that initializes the element with the given value and the active flag with the given status.
     *
     * @param v the value to be stored in the hash table entry
     * @param b the status of the entry (true if active, false if deleted)
     */
    public HashEntry(int v, boolean b) {
        element = v;
        isActive = b;
    }
}
