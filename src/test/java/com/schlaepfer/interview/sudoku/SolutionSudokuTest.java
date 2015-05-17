package com.schlaepfer.interview.sudoku;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.*;

public class SolutionSudokuTest {

	private static final int NUMBER_TO_START_WITH = 1;
	private SolutionSudoku solutionSudoku;
	private Matrix matrix;
	
	@Before
	public void setUp() {
		solutionSudoku = new SolutionSudoku();
		matrix = new Matrix(0,0);
	}

	@Test
	public void solutionSudokuIsNotNull() {
		assertThat(solutionSudoku).isNotNull();
	}
	
	@Test
	public void isUniqueInRow() {
		int[][] field = { { 0, 2, 3, 4, 5, 6, 7, 8, 9 }};
		solutionSudoku.setFields(field);
		assertThat(solutionSudoku.checkRow(matrix, 1)).isTrue();
	}
	
	@Test
	public void isNotUniqueInRow() {
		int[][] field = { { 0, 1, 3, 4, 5, 6, 7, 8, 9 }};
		solutionSudoku.setFields(field);
		assertThat(solutionSudoku.checkRow(matrix, NUMBER_TO_START_WITH)).isFalse();
	}
	
	@Test 
	public void isUniqueInCol() {
		
		int[][] field = { 
			{ 0 },
			{ 2 }, 
			{ 3 },
			{ 4 }, 
			{ 5 },
			{ 6 },
			{ 7 }, 
			{ 8 },
			{ 9 }, };
		solutionSudoku.setFields(field);
		assertThat(solutionSudoku.checkCol(matrix, NUMBER_TO_START_WITH)).isTrue();
	}
	
	@Test 
	public void isNotUniqueInCol() {
		
		int[][] field = { 
			{ 0 },
			{ 1 }, 
			{ 3 },
			{ 4 }, 
			{ 5 },
			{ 6 },
			{ 7 }, 
			{ 8 },
			{ 9 }, };
		solutionSudoku.setFields(field);
		assertThat(solutionSudoku.checkCol(matrix, NUMBER_TO_START_WITH)).isFalse();
	}
	
	@Test
	public void isUniqueInBox() {
		int[][] field = { 
				{ 0, 2, 3 },
				{ 4, 5, 6 }, 
				{ 7, 8, 9 },
				 };
		solutionSudoku.setFields(field);
		assertThat(solutionSudoku.checkBox(matrix, NUMBER_TO_START_WITH)).isTrue();
	}
	
	@Test
	public void isNotUniqueInBox() {
		int[][] field = { 
				{ 0, 1, 3 },
				{ 4, 5, 6 }, 
				{ 7, 8, 9 },
				 };
		solutionSudoku.setFields(field);
		assertThat(solutionSudoku.checkBox(matrix, NUMBER_TO_START_WITH)).isFalse();
	}
	
	@Test
	public void getNextMatrix() {
		Matrix cur = new Matrix(0,0);
		Matrix expectedMatrix = new Matrix(0,1);
		
		assertThat(solutionSudoku.getNextMatrix(cur)).isEqualTo(expectedMatrix);
		
	}
	
	@Test
	public void getWrongNextMatrix() {
		Matrix cur = new Matrix(0,0);
		Matrix expectedMatrix = new Matrix(0,4);
		
		assertThat(solutionSudoku.getNextMatrix(cur)).isNotEqualTo(expectedMatrix);
		
	}

}
