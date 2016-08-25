import java.util.*;

public class MatrixArithmetic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// last working version: http://pastebin.com/4uULmWpm
		// http://pastebin.com/yC7YWPZq
		// damn that's fast: http://imgur.com/a/3DN6u
		Scanner s = new Scanner(System.in);
//ROW COUNTER STILL BUGGY
		System.out.println("Welcome, what would you like to do next (add, subtract, scalar, multiply)");
		String input = s.nextLine();
		String inputChars[] = input.split("");

		String scalar = "scalar";
		String add = "add";
		String subtract = "subtract";
		String multiply = "multiply";
		String rref = "rref";

		if (checkSpelling(inputChars, scalar)) {
			scalar();
		} else if (checkSpelling(inputChars, add)) {
			add();
		} else if (checkSpelling(inputChars, subtract)) {
			subtract();	
		} else if (checkSpelling(inputChars, multiply)) {
			multiply();
		} else if (checkSpelling(inputChars, rref)) {
			rref();
		} else if (input.toLowerCase().equals("stop") || input.toLowerCase().equals("close")) {

			System.out.println("exited");
			s.close();
			return;
		} else {
			System.out.println("unexpected input, exited");
			s.close();
			return;
		}
		s.close();
	}

	public static boolean checkSpelling(String[] userInput, String check) {
		boolean status = false;
		
		int validityCounter = 0;
		int checkLastChar = check.length()-1;
		int inputLastChar = userInput.length-1;	
		 
		String checkChar[] = check.split("");
		if (userInput[0].toLowerCase().contains(checkChar[0].toLowerCase()))
			validityCounter++;
		if (userInput[inputLastChar].toLowerCase().contains((checkChar[checkLastChar].toLowerCase())))
			validityCounter++;
		
		if (validityCounter >= 2) {
			status = true;
		}
		return status;
	}

	public static void printMatrix(ArrayList<Integer> matrix, int rows, int columns) {
		int totMatrix = rows * columns;
		int counter = 1;
		for (int i = 0; i < totMatrix; i++) {
			if ((counter) % columns == 0) {
				// System.out.print("| " + matrix.get(i) + " |" + "\n");
				System.out.printf("| %5s |" + "\n", matrix.get(i));
			} else {
				// System.out.print("| " + matrix.get(i) + " ");
				System.out.printf("| %5s ", matrix.get(i));

			}
			counter++;
		}

	
		System.out.println();
	}

	public static void scalar() {

		System.out.println("Multiply a matrix by a scalar\n");

			Scanner s = new Scanner(System.in);
		System.out.print("Matrix dimensions\n Matrix Rows:");
		int rows = Integer.parseInt(s.nextLine());
		System.out.print(" Matrix Columns:");
		int columns = Integer.parseInt(s.nextLine());
		int totMatrix = rows * columns;
		ArrayList<Integer> matrix = new ArrayList<Integer>();
		int rowNum = 1;
		for (int i = 1; i <= totMatrix; i++){
			matrix.add(getMatrix(rows, columns, i, rowNum));
			int mod = i % columns;
			if (mod == 0)rowNum++;  
		}
		
		/*int position = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				getMatrix(rows, columns, position, (i+1), (j+1));
				position++;
			}
		}*/
		
		printMatrix(matrix, rows, columns);

		Scanner a = new Scanner(System.in);
		System.out.println("Scalar factor:");
		int scalar = a.nextInt();

		int matrixCount = matrix.size();
		int newValue = 0;
		ArrayList<Integer> scalarMatrix = new ArrayList<Integer>();
		for (int i = 0; i < matrixCount; i++) {
			newValue = matrix.get(i) * scalar;
			scalarMatrix.add(newValue);
		}
		a.close();
		s.close();
		System.out.println("Result");
		printMatrix(scalarMatrix, rows, columns);
	}

	@SuppressWarnings("resource")
	public static void add() {

		System.out.println("Add two matricies\n");

		Scanner s = new Scanner(System.in);
		Scanner t = new Scanner(System.in);
		System.out.print("First matrix dimensions\n Matrix Rows:");
		int rows = Integer.parseInt(s.nextLine());
		System.out.print(" Matrix Columns:");
		int columns = Integer.parseInt(s.nextLine());
		int totMatrix = rows * columns;
		ArrayList<Integer> matrix = new ArrayList<Integer>();
		int rowNum = 1;
		for (int i = 1; i <= totMatrix; i++){
			matrix.add(getMatrix(rows, columns, i, rowNum));
			int mod = i % columns;
			if (mod == 0)rowNum++;  
		}
		System.out.print("Second matrix dimensions\n Matrix Rows:");
		int rows2 = Integer.parseInt(s.nextLine());
		System.out.print(" Matrix Columns:");
		int columns2 = Integer.parseInt(s.nextLine());
		int totMatrix2 = rows2 * columns2;
		ArrayList<Integer> matrix2 = new ArrayList<Integer>();
		if (totMatrix != totMatrix2) {
			System.out.println("Please enter two matricies with the same dimensions");
				System.exit(1);
		}

		int rowNum2 = 1;
		for (int i = 1; i <= totMatrix2; i++){
			matrix2.add(getMatrix(rows2, columns2, i, rowNum2));
			int mod = i % columns2;
			if (mod == 0)rowNum2++;  
		}
		System.out.println("First Matrix");
		printMatrix(matrix, rows, columns);
		System.out.println("Second Matrix");
		printMatrix(matrix2, rows2, columns2);

		int matrixCount = matrix.size();
		int newValue = 0;
		ArrayList<Integer> addMatrix = new ArrayList<Integer>();
		for (int i = 0; i < matrixCount; i++) {
			newValue = matrix.get(i) + matrix2.get(i);
			addMatrix.add(newValue);
		}
		s.close();
		System.out.println("Result");
		printMatrix(addMatrix, rows, columns);

	}

	@SuppressWarnings("resource")
	public static void multiply() {

		System.out.println("Multiply two matricies\n");
		System.out.println("Note: Columns in matrix A must equal rows in matrix B");

		Scanner s = new Scanner(System.in);
		Scanner t = new Scanner(System.in);

		System.out.print("First matrix dimensions\n Matrix Rows:");
		int rows = Integer.parseInt(s.nextLine());
		System.out.print(" Matrix Columns:");
		int columns = Integer.parseInt(s.nextLine());
		int totMatrix = rows * columns;
		ArrayList<Integer> matrix = new ArrayList<Integer>();
		int rowNum = 1;
		for (int i = 1; i <= totMatrix; i++){
			matrix.add(getMatrix(rows, columns, i, rowNum));
			int mod = i % columns;
			if (mod == 0)rowNum++;  
		}

		System.out.print("Second matrix dimensions\n Matrix Rows:");
		int rows2 = Integer.parseInt(s.nextLine());
		System.out.print(" Matrix Columns:");
		int columns2 = Integer.parseInt(s.nextLine());
		int totMatrix2 = rows2 * columns2;
		ArrayList<Integer> matrix2 = new ArrayList<Integer>();

		if (columns != rows2) {
			System.out.println("Columns in matrix A must equal rows in matrix B");
			System.out.println("Try again (yes, no)?");
			String input = t.nextLine();
			if (input.toLowerCase().equals("yes")) {
				System.out.print("Second matrix dimensions\n Matrix Rows:");
				rows2 = Integer.parseInt(s.nextLine());
				System.out.print(" Matrix Columns:");
				columns2 = Integer.parseInt(s.nextLine());
				totMatrix2 = rows2 * columns2;
				matrix2 = new ArrayList<Integer>();
			} else {
				System.exit(1);
			}
		}

		int rowNum2 = 1;
		for (int i = 1; i <= totMatrix2; i++){
			matrix2.add(getMatrix(rows2, columns2, i, rowNum2));
			int mod = i % columns2;
			if (mod == 0)rowNum2++;  
		}

		System.out.println("First Matrix");
		printMatrix(matrix, rows, columns);
		System.out.println("Second Matrix");
		printMatrix(matrix2, rows2, columns2);

		// start Matrix Multiplication calculations
		long startTime = System.nanoTime();
		int matrixOutputCount = rows * columns2;
		int matrix2Count = rows2 * columns2;
		int newValue = 0;
		int jeanCounter = 0;
		int b = 0;
		int jeanFactor = 0;
		int rowSwitchCount = 0;
		int iteration = 0;
		int jeanFactorLessThan = 0;
		boolean checkJeanFactor = rows2 < columns2;
		boolean checkRow2Equals1 = rows2 == 1;
		ArrayList<Integer> addMatrix = new ArrayList<Integer>();
		for (int i = 0; i < matrixOutputCount; i++) {
			if (rows > 1 && iteration % matrix2Count == 0 && iteration > 1) {
				rowSwitchCount++;// finished row 1
				b = columns * rowSwitchCount;
				jeanCounter = 0;
				jeanFactorLessThan = 0;
			} else if (rows > 1) {
				b = columns * rowSwitchCount; // row reset
			}

			newValue = 0;
			for (int a = 0; a < rows2; a++) { // matrix2Count
				if (!checkJeanFactor) {
					jeanFactor = (a % (rows2) != 0) ? (jeanFactor + (columns2)) : (jeanCounter);
					newValue += (matrix.get(b) * matrix2.get(jeanFactor));
					if (a != 0 && a % (rows2 - 1) == 0)
						jeanCounter++;
				} else if (checkRow2Equals1) {
					newValue += (matrix.get(b) * matrix2.get(jeanFactorLessThan));
					// jeanFactorLessThan = (jeanFactorLessThan <
					// matrix2.size()) ? (jeanFactorLessThan +=1) :
					// (jeanFactorLessThan = 0);
					jeanFactorLessThan += 1;
				} else if (checkJeanFactor) {
					newValue += (matrix.get(b) * matrix2.get(jeanFactorLessThan));
					jeanFactorLessThan = (iteration % 2 == 0) ? (jeanFactorLessThan + (columns2))
							: (jeanFactorLessThan - (columns2 - 1));
				}

				b++;
				iteration++;
			}
			addMatrix.add(newValue);
		}
		s.close();
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " nanoseconds");
		System.out.println("Result");
		printMatrix(addMatrix, rows, columns2);

	}

	public static void rref() {
		System.out.println("in development, see \"MatrixRREF\" class");
	}

	@SuppressWarnings("resource")
	public static void subtract() {

		System.out.println("Subtract two matricies\n");

		Scanner s = new Scanner(System.in);
		Scanner t = new Scanner(System.in);

		System.out.print("First matrix dimensions\n Matrix Rows:");
		int rows = Integer.parseInt(s.nextLine());
		System.out.print(" Matrix Columns:");
		int columns = Integer.parseInt(s.nextLine());
		int totMatrix = rows * columns;
		ArrayList<Integer> matrix = new ArrayList<Integer>();
		int rowNum = 1;
		for (int i = 1; i <= totMatrix; i++){
			matrix.add(getMatrix(rows, columns, i, rowNum));
			int mod = i % columns;
			if (mod == 0)rowNum++;  
		}
		System.out.print("Second matrix dimensions\n Matrix Rows:");
		int rows2 = Integer.parseInt(s.nextLine());
		System.out.print(" Matrix Columns:");
		int columns2 = Integer.parseInt(s.nextLine());
		int totMatrix2 = rows2 * columns2;
		ArrayList<Integer> matrix2 = new ArrayList<Integer>();
		if (totMatrix != totMatrix2) {
			System.out.println("Please enter two matricies with the same dimensions");
			System.exit(1);
		}

		int rowNum2 = 1;
		for (int i = 1; i <= totMatrix2; i++){
			matrix2.add(getMatrix(rows2, columns2, i, rowNum2));
			int mod = i % columns2;
			if (mod == 0)rowNum2++;  
		}

		System.out.println("First Matrix");
		printMatrix(matrix, rows, columns);
		System.out.println("Second Matrix");
		printMatrix(matrix2, rows2, columns2);

		int matrixCount = matrix.size();
		int newValue = 0;
		ArrayList<Integer> addMatrix = new ArrayList<Integer>();
		for (int i = 0; i < matrixCount; i++) {
			newValue = matrix.get(i) - matrix2.get(i);
			addMatrix.add(newValue);
		}
		s.close();
		System.out.println("Result");
		printMatrix(addMatrix, rows, columns);

	}

	@SuppressWarnings("resource")
	public static int getMatrix(int rows, int columns, int i, int rowNum) {
		int tempValue = 0;
		int columnNum = 0;
		//int rowNum = 1;
		Scanner a = new Scanner(System.in);
		columnNum = (i % columns == 0) ? (columns) : (i % columns);
		/*int mod = i % columns;
		if (mod == 0) {
			rowNum++;   // ROW COUNTER STILL BUGGY
		}*/
		/*for ( i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				 getNewMatrix(rows, columns, position, (i+1), (j+1));
				position++;
			}
		}*/
		System.out.print("Row " + rowNum + " Column " + columnNum + ": ");
		tempValue = a.nextInt();
		// DONT CLOSE THE SCANNER OTHERWISE IT WILL THROW A
		// NoSuchElementException error, source:
		// http://stackoverflow.com/questions/15423519/issue-with-scanners-and-java-util-nosuchelementexception-no-line-found-at-jav
		return tempValue;
	}
	

}