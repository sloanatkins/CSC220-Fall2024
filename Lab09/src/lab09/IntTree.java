package lab09;

/**
 * The IntTree class represents a simple binary tree structure for integer values. 
 * It includes methods to construct a tree of integers, print the data using an inorder traversal,
 * and other utility functions for manipulating and querying the tree.
 * 
 * Chapter 17.2 - page 1035 (modified)
 */
public class IntTree {
    
    /**
     * The root node of the binary tree.
     */
    private IntNode root;

    /**
     * Constructs a binary tree based on an array of integers.
     * If the array contains -1 at any index, that position will be treated as a null node.
     * 
     * @param arr an array of integers to be used for constructing the binary tree
     * @throws IllegalArgumentException if the array is empty
     */
    public IntTree(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("empty array");
        }
        root = builTreeArray(1, arr);
    }

    /**
     * Builds a binary tree from an array of integers. The array is used to fill the tree level by level.
     * 
     * @param n the index in the array for the current node
     * @param arr the array of integers used to build the tree
     * @return the constructed IntNode or null if the index exceeds the array length
     */
    private IntNode builTreeArray(int n, int[] arr) {
        if (n > arr.length) {
            return null;
        } else {
            if (arr[n - 1] != -1) {
                return new IntNode(arr[n - 1], builTreeArray(2 * n, arr), builTreeArray(2 * n + 1, arr));
            } else {
                return null;
            }
        }
    }

    /**
     * Constructs a binary tree with nodes numbered sequentially from 1 to the given max value.
     * 
     * @param max the maximum number of nodes to be created
     * @throws IllegalArgumentException if max is less than 0
     */
    public IntTree(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        root = buildTree(1, max);
    }

    /**
     * Recursively builds a sequential binary tree with n as its root, unless n exceeds max.
     * 
     * @param n the value to be assigned to the current node
     * @param max the maximum number of nodes to be created
     * @return the constructed IntNode or null if n exceeds max
     */
    private IntNode buildTree(int n, int max) {
        if (n > max) {
            return null;
        } else {
            return new IntNode(n, buildTree(2 * n, max), buildTree(2 * n + 1, max));
        }
    }

    /**
     * Returns a string representing the inorder traversal of the binary tree.
     * 
     * @return a string of tree data in inorder traversal
     */
    public String getInorder() {
        return getInorder(root);
    }

    /**
     * Recursively builds the inorder traversal of the tree starting from the given root.
     * 
     * @param root the current node of the tree
     * @return a string of tree data in inorder traversal
     */
    private String getInorder(IntNode root) {
        if (root != null) {
            return (getInorder(root.left) + " " + root.data + " " + getInorder(root.right));
        } else {
            return "";
        }
    }

    /**
     * Prints the contents of the tree using an inorder traversal.
     * The data is printed on a single line.
     */
    public void printInorder() {
        System.out.print("inorder:");
        printInorder(root);
        System.out.println();
    }

    /**
     * Recursively prints the contents of the tree in inorder starting from the given root.
     * 
     * @param root the current node of the tree
     */
    private void printInorder(IntNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    /**
     * Prints the contents of the tree in a sideways manner using indentation
     * to indicate node depth, starting from the rightmost node and working left.
     * The output is rotated so it visually represents the tree structure when viewed sideways.
     */
    public void printSideways() {
        printSideways(root, 0);
    }

    /**
     * Recursively prints the tree in a reversed preorder manner, indenting each line
     * according to the node's depth in the tree.
     * 
     * @param root the current node of the tree
     * @param level the depth of the current node
     */
    private void printSideways(IntNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("       ");
            }
            System.out.println(root.data);
            printSideways(root.left, level + 1);
        }
    }

	//==================================================	

	/**
     * Returns the number of empty branches in the tree. 
	 * A leaf node has two empty branches,
     * A node with one nonempty child has one empty branch, 
	 * A node with two nonempty children has no empty branches.
     * An empty tree is considered to have one empty branch (the tree itself).
	 * 
     * @return the number of empty branches in the tree
     */
    public int numEmpty() {
		//TODO: Lab Part 2
        return numEmptyHelper(root); //Placeholder
    }
    
    private int numEmptyHelper(IntNode root) {
    	if(root == null){
    		return 1;
    	}
    	return (numEmptyHelper(root.left) + numEmptyHelper(root.right));
    }

	/**
     * Returns the values at the specified level in the tree, one per line, from left to right. 
	 * The root node is considered to be at level 1.
     * 
     * @param level the level of the tree to retrieve values from
     * @return a string containing the values at the specified level, or an empty string if no values are present
     */
    public String printLevel(int level) {
        //TODO: Lab Part 3
    	return printLevelHelper(root, level); //Placeholder
    }
    
    private String printLevelHelper(IntNode root, int level) {
    	if(root == null) {
    		return "";
    	}
    	else if(level == 1) {
    		return root.data + "\n";
    	}
    	else {
    		return (printLevelHelper(root.left, level-1) + printLevelHelper(root.right, level-1)); 
    	}
    }

	/**
     * Returns the depth of the binary tree. 
	 * The root node is considered to be at depth 1,
     * its children at depth 2, and so on.
	 * 
     * 
     * @return the depth of the tree, or 0 if the tree is empty 
     */
    public int getDepth() {
		//TODO: Lab part 4
    	return getDepthHelper(root);
    }
    
    private int getDepthHelper(IntNode root) {
    	if (root == null) {
            return 0;  
        }

        return 1 + Math.max(getDepthHelper(root.left), getDepthHelper(root.right));
    }

	//==================================================	

    /**
     * Checks if the binary tree contains at least three occurrences of the given value.
     * If it does, the tree is considered "lucky".
     * 
     * @param value the value to search for in the tree
     * @return true if the value occurs at least three times, false otherwise
     */
    public boolean luckyTree(int value) {
        //TODO: Assignment Part 2
        return luckyTreeHelper(root, value, 0) >= 3;
    }
    
    private int luckyTreeHelper(IntNode root, int value, int count) {
    	if (root == null) {
            return count;
        }
        if (root.data == value) {
            count++;
        }
        if (count >= 3) {
            return count;
        }
        count = luckyTreeHelper(root.left, value, count);
        return luckyTreeHelper(root.right, value, count);
    }

    /**
     * Converts the binary tree into a perfect tree by adding nodes with the value -1.
     * A perfect binary tree is one where all leaves are at the same level and every branch node has two children.
     * Another way of thinking of it is that you are adding dummy nodes to the tree until every path from the root to a leaf is the same length.
	 * 
	 **/
    public void perfectify() {
        //TODO: Assignment Part 3
    	int depth = getDepth();
        root = perfectifyHelper(root, 1, depth);
    }

    private IntNode perfectifyHelper(IntNode root, int currentDepth, int maxDepth) {
        if (currentDepth > maxDepth) {
            return null;
        }
        if (root == null) {
            root = new IntNode(-1);
        }
        root.left = perfectifyHelper(root.left, currentDepth + 1, maxDepth);
        root.right = perfectifyHelper(root.right, currentDepth + 1, maxDepth);
        return root;
    }

    /**
     * Returns a string representation of the binary tree. If the tree is empty, the method returns "empty".
     * For a leaf node, it returns the node's data. For a branch node, it returns a parenthesized string 
     * containing the data at the root, followed by the left subtree and the right subtree.
     * 
     * @return a string representation of the tree
     */
    public String toString() {
        //TODO: Assignment Part 1
    	if (root == null) {
            return "";
        }
        return toStringHelper(root);
    }

    private String toStringHelper(IntNode root) {
        if (root == null) {
            return "empty";
        }
        if (root.left == null && root.right == null) {
            return String.valueOf(root.data); 
        }
        return "(" + root.data + ", " + toStringHelper(root.left) + ", " + toStringHelper(root.right) + ")";
    }

}
