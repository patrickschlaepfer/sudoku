package com.schlaepfer.interview.sudoku;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import static com.schlaepfer.interview.sudoku.SolutionSudoku.FIELD_WIDTH;
import static com.schlaepfer.interview.sudoku.SolutionSudoku.BLOCK_WIDTH;

public class SolutionSudokuAdHocTest {

	private SolutionSudoku solutionSudoku;
	private Matrix matrix;

	@Before
	public void setUp() {
		solutionSudoku = new SolutionSudoku();
		matrix = new Matrix(0, 0);
	}

	@Test
	public void solveSudo() {
		int[][] field = { { 0, 0, 0, 0, 8, 2, 0, 7, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 2, 9, 7, 0, 0, 0, 0, 5 },

				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 7, 4, 0, 8, 5, 0, 0, 0, 0 },
				{ 0, 9, 0, 0, 0, 7, 3, 0, 8 },

				{ 6, 0, 2, 1, 0, 8, 9, 4, 0 }, { 0, 0, 4, 5, 0, 0, 0, 1, 2 },
				{ 1, 8, 0, 0, 0, 4, 5, 0, 0 }, };
		solutionSudoku.setFields(field);

		assertThat(solutionSudoku.solve(matrix)).isTrue();
		
		printResult(field);

	}

	@Test
	public void unsolveableSudoku() {
		int[][] field = { { 0, 0, 0, 4, 9, 2, 0, 7, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 2, 9, 7, 0, 0, 0, 0, 5 },

				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 7, 4, 0, 8, 5, 0, 0, 0, 0 },
				{ 0, 9, 0, 0, 0, 7, 3, 0, 8 },

				{ 6, 0, 2, 1, 0, 8, 9, 4, 0 }, { 0, 0, 4, 5, 0, 0, 0, 1, 2 },
				{ 1, 8, 0, 0, 0, 4, 5, 0, 0 }, };
		solutionSudoku.setFields(field);

		assertThat(solutionSudoku.solve(matrix)).isFalse();
	}
	
	private void printResult(int[][] field) {
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
