package com.schlaepfer.interview.sudoku;

public class SolutionSudoku {

	private static int[][] field = { { 0, 0, 0, 0, 8, 2, 0, 7, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 2, 9, 7, 0, 0, 0, 0, 5 },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 7, 4, 0, 8, 5, 0, 0, 0, 0 },
			{ 0, 9, 0, 0, 0, 7, 3, 0, 8 },

			{ 6, 0, 2, 1, 0, 8, 9, 4, 0 }, { 0, 0, 4, 5, 0, 0, 0, 1, 2 },
			{ 1, 8, 0, 0, 0, 4, 5, 0, 0 }, };

	public static final int BLOCK_WIDTH = 3;
	public static final int FIELD_WIDTH = BLOCK_WIDTH * BLOCK_WIDTH;

	public static void main(String[] args) {
		SolutionSudoku solutionSudoku = new SolutionSudoku();
		solutionSudoku.start();
	}

	public void start() {
		boolean solved = solve(new Matrix(0, 0));
		if (!solved) {
			System.out.println("SUDOKU cannot be solved.");
			return;
		}
		System.out.println("SOLUTION\n");
		printResult(field);
	}

	boolean isValid(Matrix matrix, int value) {

		if (field[matrix.getRow()][matrix.getCol()] != 0) {
			throw new RuntimeException(
					"Cannot call for cell which already has a value");
		}

		return (checkRow(matrix, value) && checkCol(matrix, value) && checkBox(
				matrix, value));

	}
	
	// Stub for JUnit testing
	protected void setFields(int [][] field) {
		SolutionSudoku.field = field;
	}

	boolean checkBox(Matrix matrix, int value) {
		int boxStartRow = matrix.getRow() - matrix.getRow() % BLOCK_WIDTH;
		int boxStartCol = matrix.getCol() - matrix.getCol() % BLOCK_WIDTH;
		for (int row = 0; row < BLOCK_WIDTH; row++) {
			for (int col = 0; col < BLOCK_WIDTH; col++) {
				if (field[row + boxStartRow][col + boxStartCol] == value)
					return false;
			}
		}
		return true;
	}

	boolean checkCol(Matrix matrix, int value) {
		for (int r = 0; r < FIELD_WIDTH; r++) {
			if (field[r][matrix.getCol()] == value)
				return false;
		}
		return true;
	}

	boolean checkRow(Matrix matrix, int value) {
		for (int c = 0; c < FIELD_WIDTH; c++) {
			if (field[matrix.getRow()][c] == value)
				return false;
		}
		return true;
	}

	Matrix getNextMatrix(Matrix cur) {
		int row = cur.getRow();
		int col = cur.getCol();
		col++;

		if (col > FIELD_WIDTH - 1) {
			col = 0;
			row++;
		}

		if (row > FIELD_WIDTH - 1)
			return null; // reached end

		Matrix next = new Matrix(row, col);
		return next;
	}

	// everything is put together here
	// very simple solution
	// must return true, if the soduku is solved, return false otherwise
	boolean solve(Matrix cur) {

		// if the cell is null, we have reached the end
		if (cur == null)
			return true;

		// if grid[cur] already has a value, there is nothing to solve here,
		// continue on to next cell
		if (field[cur.getRow()][cur.getCol()] != 0) {
			// return whatever is being returned by solve(next)
			// i.e the state of soduku's solution is not being determined by
			// this cell, but by other cells
			return solve(getNextMatrix(cur));
		}

		// this is where each possible value is being assigned to the cell, and
		// checked if a solutions could be arrived at.

		// if grid[cur] doesn't have a value
		// try each possible value
		for (int i = 1; i <= 9; i++) {
			// check if valid, if valid, then update
			boolean valid = isValid(cur, i);

			if (!valid) // i not valid for this cell, try other values
				continue;

			// assign here
			field[cur.getRow()][cur.getCol()] = i;

			// continue with next cell
			boolean solved = solve(getNextMatrix(cur));
			// if solved, return, else try other values
			if (solved)
				return true;
			else
				field[cur.getRow()][cur.getCol()] = 0; // reset
			// continue with other possible values
		}

		// if you reach here, then no value from 1 - 9 for this cell can solve
		// return false
		return false;
	}

	private static void printResult(int[][] field) {
		for (int y = 0; y < FIELD_WIDTH; y++) {
			for (int x = 0; x < FIELD_WIDTH; x++) {
				System.out.print(field[x][y] + " ");
				if (x % BLOCK_WIDTH == BLOCK_WIDTH - 1) {
					System.out.print(" ");
				}
			}
			if (y % BLOCK_WIDTH == BLOCK_WIDTH - 1) {
				System.out.println();
			}
			System.out.println();
		}
	}

}
