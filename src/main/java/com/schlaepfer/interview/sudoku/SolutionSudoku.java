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

	public boolean isValid(Matrix matrix, int value) {
		return (checkRow(matrix, value) && checkCol(matrix, value) && checkBox(
				matrix, value));
	}

	// Stub for JUnit testing
	protected void setFields(int[][] field) {
		SolutionSudoku.field = field;
	}

	public boolean checkBox(Matrix matrix, int value) {
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

	public boolean checkCol(Matrix matrix, int value) {
		for (int r = 0; r < FIELD_WIDTH; r++) {
			if (field[r][matrix.getCol()] == value)
				return false;
		}
		return true;
	}

	public boolean checkRow(Matrix matrix, int value) {
		for (int c = 0; c < FIELD_WIDTH; c++) {
			if (field[matrix.getRow()][c] == value)
				return false;
		}
		return true;
	}

	public Matrix getNextMatrix(Matrix cur) {
		int row = cur.getRow();
		int col = cur.getCol();
		col++;

		if (col > FIELD_WIDTH - 1) {
			col = 0;
			row++;
		}

		if (row > FIELD_WIDTH - 1)
			return null;

		Matrix next = new Matrix(row, col);
		return next;
	}

	public boolean solve(Matrix cur) {

		if (cur == null)
			return true;

		if (field[cur.getRow()][cur.getCol()] != 0) {
			return solve(getNextMatrix(cur));
		}

		for (int i = 1; i <= FIELD_WIDTH; i++) {
			boolean valid = isValid(cur, i);

			if (!valid)
				continue;

			field[cur.getRow()][cur.getCol()] = i;

			boolean solved = solve(getNextMatrix(cur));
			if (solved)
				return true;
			else
				field[cur.getRow()][cur.getCol()] = 0;
		}
		return false;
	}
}
