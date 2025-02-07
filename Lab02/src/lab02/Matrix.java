package lab02;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	
	//	default constructor
	public Matrix(){}
	
	// constructor 1 - Constructor for new zero matrix
	public Matrix(int rowDim, int colDim){
		/*  TODO: Lab Part 1 */
			/* write a constructor that would create a matrix
			 * of correct size and initialize it to 0. 
		*/
		numRows = rowDim;
		numColumns = colDim;
		data = new int[rowDim][colDim];
	}
	
	
	// constructor 2 - Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int d[][])
	{
		/*  TODO: Lab Experiment 1.3
			*  1) put the numRows to be the number of 1D arrays in the 2D array
			*  2) specify the numColumns and set it
			*  3) be careful of special cases you are supposed to handle them properly
			*  4) create a new matrix to hold the data
			*  5) copy the data over
		*/
		
		data = d;
		numRows = d.length;
		
		if(numRows == 0) {
			numRows = 0;
			data = new int[0][0];
		}else {
			numColumns = d[0].length;
		}
		
	}	
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		/* TODO: Lab part 1
		 	* replace the below return statement with the correct code, 
			* you must return a String that represents this matrix, as 
			* specified in the instruction for M1
			* anything else IS NOT acceptable
		 */
		
		String Matrix = "";
		
		for(int row = 0; row < numRows; row++) {
			for(int column = 0; column < numColumns; column++) {
				Matrix += data[row][column] + " ";
			}
			Matrix += "\n";
		}
		return Matrix; // placeholder		
	}
	
	/**
	 * @Override
	 * This method overrides the `equals` method of the `Object` class.
	 * 
	 * Compares this Matrix to another Object for equality. If the Object 
	 * is not an instance of the Matrix class, the method returns false. 
	 * If it is a Matrix, the method checks whether the two matrices have 
	 * the same dimensions and elements in the same order.
	 *
	 * @param o The Object to compare to this Matrix.
	 * @return true if the input Object is a Matrix with the same dimensions 
	 *         and elements as this Matrix; false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		
		Matrix m = (Matrix) o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		/* TODO: Assignment part 1
			* Replace the below return statement with the correct code.
			* You must return the correct value after determining if 
			* this matrix is equal to the input matrix.
		*/
		
		
		if (this.numRows != m.numRows || this.numColumns != m.numColumns) {
            return false;
        }

        // Check values
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                if (this.data[i][j] != m.data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


	/**
	 * Computes the transpose of this Matrix.
	 * 
	 * This method creates a new Matrix that is the transpose of this Matrix.
	 * The transpose of a matrix is obtained by flipping the matrix over its diagonal, 
	 * switching the row and column indices of the matrix.
	 *
	 * @return A new Matrix that is the transpose of this Matrix.
	 */
	public Matrix transpose() {
		/* TODO: Assignment Part 2
		* Replace the below return statement with the correct code.
		* Implement the logic to compute the transpose of this matrix.
		*/
		
		Matrix transposedMatrix = new Matrix(this.numColumns, this.numRows);

        for (int i = 0; i < transposedMatrix.numRows; i++) {
            for (int j = 0; j < transposedMatrix.numColumns; j++) {
            	transposedMatrix.data[i][j] = this.data[j][i];
            }
        }

        return transposedMatrix;
	}

	/**
	 * Adds this Matrix to another Matrix.
	 * 
	 * This method checks if the two matrices are compatible for addition, i.e., 
	 * they have the same dimensions. If they are not compatible, the method returns null. 
	 * If they are compatible, the method computes the sum of the two matrices and returns 
	 * the resulting Matrix.
	 *
	 * @param m The Matrix to add to this Matrix.
	 * @return A new Matrix that is the sum of this Matrix and the input Matrix, or 
	 *         null if the matrices are not compatible for addition.
	 */
	public Matrix add(Matrix m) {
		/* TODO: Assignment part 3
			* Replace the below return statement with the correct code.
			* This function must check if the two matrices are compatible for addition.
			* If not, return null. If they are compatible, create a new matrix and fill it in with
			* the correct values for matrix addition.
		*/
		
		if (this.numRows != m.numRows || this.numColumns != m.numColumns) {
            return null; // Matrices must be the same size
        }
		
		Matrix addition = new Matrix(this.numRows, this.numColumns);

        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
            	addition.data[i][j] = this.data[i][j] + m.data[i][j];
            }
        }

        return addition;
		
	}

	/**
	 * Multiplies this Matrix by another Matrix.
	 * 
	 * This method checks if the two matrices are compatible for multiplication, i.e., 
	 * the number of columns in this matrix is equal to the number of rows in the input Matrix.
	 * If they are not compatible, the method returns null. If they are compatible, the method 
	 * computes the product of the two matrices and returns the resulting Matrix.
	 *
	 * @param m The Matrix to multiply with this Matrix.
	 * @return A new Matrix that is the product of this Matrix and the input Matrix, or 
	 *         null if the matrices are not compatible for multiplication.
	 */
	public Matrix mult(Matrix m) {
		/* TODO: Assignment part 4
			* Replace the below return statement with the correct code.
			* This function must check if the two matrices are compatible for multiplication.
			* If not, return null. If they are compatible, determine the dimensions of 
			* the resulting matrix and fill it in with the correct values for matrix multiplication.
		*/
		
		if (this.numColumns != m.numRows) {
            return null; // Matrices cannot be multiplied
        }
		
		Matrix multiplication = new Matrix(this.numRows, m.numColumns);
        
		for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < m.numColumns; j++) {
            	multiplication.data[i][j] = 0;
                for (int k = 0; k < this.numColumns; k++) {
                	multiplication.data[i][j] += this.data[i][k] * m.data[k][j];
                }
            }
        }
		return multiplication;
        
	}

}

