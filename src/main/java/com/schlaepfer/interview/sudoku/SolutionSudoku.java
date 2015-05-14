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
	
	/**
	 * Class to abstract the representation of a cell. Cell => (x, y)
	 */
	static class Cell {

		int row, col;

		public Cell(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Cell [row=" + row + ", col=" + col + "]";
		}
	};

	static boolean isValid(Cell cell, int value) {

		if (field[cell.row][cell.col] != 0) {
			throw new RuntimeException(
					"Cannot call for cell which already has a value");
		}

		checkRow(cell, value);

		// if v present in col, return false
		for (int r = 0; r < 9; r++) {
			if (field[r][cell.col] == value)
				return false;
		}

		// if v present in grid, return false

		// to get the grid we should calculate (x1,y1) (x2,y2)
		int x1 = 3 * (cell.row / 3);
		int y1 = 3 * (cell.col / 3);
		int x2 = x1 + 2;
		int y2 = y1 + 2;

		for (int x = x1; x <= x2; x++)
			for (int y = y1; y <= y2; y++)
				if (field[x][y] == value)
					return false;

		// if value not present in row, col and bounding box, return true
		return true;
	}

	private static boolean checkRow(Cell cell, int value) {
		// if v present row, return false
		for (int c = 0; c < 9; c++) {
			if (field[cell.row][c] == value)
				return false;
		}
		return true;
	}

	// simple function to get the next cell
	// read for yourself, very simple and straight forward
	static Cell getNextCell(Cell cur) {

		int row = cur.row;
		int col = cur.col;

		// next cell => col++
		col++;

		// if col > 8, then col = 0, row++
		// reached end of row, got to next row
		if (col > 8) {
			// goto next line
			col = 0;
			row++;
		}

		// reached end of matrix, return null
		if (row > 8)
			return null; // reached end

		Cell next = new Cell(row, col);
		return next;
	}

	// everything is put together here
	// very simple solution
	// must return true, if the soduku is solved, return false otherwise
	static boolean solve(Cell cur) {

		// if the cell is null, we have reached the end
		if (cur == null)
			return true;

		// if grid[cur] already has a value, there is nothing to solve here,
		// continue on to next cell
		if (field[cur.row][cur.col] != 0) {
			// return whatever is being returned by solve(next)
			// i.e the state of soduku's solution is not being determined by
			// this cell, but by other cells
			return solve(getNextCell(cur));
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
			field[cur.row][cur.col] = i;

			// continue with next cell
			boolean solved = solve(getNextCell(cur));
			// if solved, return, else try other values
			if (solved)
				return true;
			else
				field[cur.row][cur.col] = 0; // reset
			// continue with other possible values
		}

		// if you reach here, then no value from 1 - 9 for this cell can solve
		// return false
		return false;
	}

	public static void main(String[] args) {
		boolean solved = solve(new Cell(0, 0));
		if (!solved) {
			System.out.println("SUDOKU cannot be solved.");
			return;
		}
		System.out.println("SOLUTION\n");
		printResult(field);
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
