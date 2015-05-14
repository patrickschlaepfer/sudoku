package com.schlaepfer.interview.sudoku;

public class Sudoku {

	// mittel
	// private static int[][] field = {
	// {0,0,0, 8,9,0, 0,0,0},
	// {0,6,9, 0,0,4, 3,2,0},
	// {8,2,0, 0,0,5, 4,0,7},
	//
	// {3,0,0, 9,0,0, 1,0,2},
	// {0,4,0, 5,0,0, 0,7,0},
	// {0,0,0, 0,0,0, 5,3,0},
	//
	// {7,1,0, 0,0,0, 0,0,0},
	// {6,0,0, 0,2,0, 8,0,0},
	// {0,8,4, 0,6,0, 0,1,0}
	// };

	// mittel
	// private static int[][] field = {
	// {0,5,7, 8,0,6, 4,0,0},
	// {0,0,0, 0,0,0, 0,3,8},
	// {1,4,0, 0,3,7, 9,0,0},
	//
	// {0,0,0, 0,1,2, 0,0,0},
	// {0,0,2, 4,0,3, 0,0,6},
	// {0,3,0, 0,6,0, 0,0,7},
	//
	// {0,9,5, 0,0,0, 0,0,0},
	// {7,2,0, 0,5,0, 0,8,0},
	// {0,0,1, 0,0,4, 0,9,0}
	// };

	// mittel
	// private static int[][] field = {
	// {0,4,7, 0,0,8, 0,5,0},
	// {9,8,0, 0,0,0, 0,0,0},
	// {5,0,0, 4,0,0, 0,3,0},
	//
	// {4,3,0, 7,9,0, 0,0,1},
	// {0,0,0, 0,0,0, 4,6,0},
	// {0,5,6, 2,0,3, 0,0,7},
	//
	// {0,0,0, 1,0,2, 0,0,0},
	// {0,7,0, 0,0,9, 1,0,0},
	// {2,0,0, 8,3,0, 6,0,0},
	// };

	// schwer
	// private static int[][] field = {
	// {0,0,8, 0,3,0, 0,9,1},
	// {0,2,9, 7,1,0, 6,0,8},
	// {7,1,0, 8,0,0, 3,0,0},
	//
	// {0,0,0, 9,0,7, 0,1,5},
	// {9,0,6, 0,5,0, 0,3,0},
	// {0,0,0, 0,0,0, 0,0,0},
	//
	// {6,0,0, 5,0,0, 4,7,0},
	// {8,5,0, 0,7,3, 0,0,0},
	// {0,0,0, 0,0,0, 0,0,0},
	// };

	// schwer
	private static int[][] field = { { 0, 0, 0, 0, 8, 2, 0, 7, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 2, 9, 7, 0, 0, 0, 0, 5 },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 7, 4, 0, 8, 5, 0, 0, 0, 0 },
			{ 0, 9, 0, 0, 0, 7, 3, 0, 8 },

			{ 6, 0, 2, 1, 0, 8, 9, 4, 0 }, { 0, 0, 4, 5, 0, 0, 0, 1, 2 },
			{ 1, 8, 0, 0, 0, 4, 5, 0, 0 }, };

	public static final int BLOCK_WIDTH = 3;
	public static final int FIELD_WIDTH = BLOCK_WIDTH * BLOCK_WIDTH;
	
	
	public static void main(String[] args) {
		
		Sudoku sudoku = new Sudoku();
		if(sudoku.solve(field)) {
			printResult(field);
		} else {
			printNoResult();
		}
		
	}
	
	private static void printNoResult() {
		System.out.println("There is no solution");
	}

	public boolean solve(int[][] field) {
		
		if(isCompleted(field)) {
			return true;
		}
		
		for(int i=1; i<= FIELD_WIDTH; i++) {
			if(isSave(field, row, col, i)) {
				field2[row][col] = i;
				if(solve(field2)) {
					return true;
				}
				field2[row][col] = 0;
			}
		}
		return false;
	}
	
	public boolean isCompleted(int[][] field) {
		for(int row=0; row <= FIELD_WIDTH; row++) {
			for(int col=0; col <= FIELD_WIDTH; col++) {
				if(field[row][col] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean usedInRow(int[][] field, int row, int num) {
		for(int col=0; col < FIELD_WIDTH; col++) {
			if(field[col][row] == num) {
				return true;
			}
		}
		return false;
	}
	
	public boolean usedInCol(int[][] field, int col, int num) {
		for(int row=0; row < FIELD_WIDTH; row++) {
			if(field[col][row] == num) {
				return true;
			}
		}
		return false;
	}
	
	public boolean usedInBox(int[][] field, int boxStartRow, int boxStartCol, int num) {
		for (int row = 0; row < BLOCK_WIDTH; row++)
	        for (int col = 0; col < BLOCK_WIDTH; col++)
	            if (field[row+boxStartRow][col+boxStartCol] == num)
	                return true;
	    return false;
	}
	
	public boolean isSave(int[][] field, int row, int col, int num) {
		
		System.out.println("testing ["+row+"]["+col+"]");
		
		return !usedInRow(field, row, num) &&
		           !usedInCol(field, col, num) &&
		           !usedInBox(field, row - row%BLOCK_WIDTH , col - col%BLOCK_WIDTH, num);
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
