package com.schlaepfer.interview.sudoku;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void matrixInitialization() {
		Matrix matrix = new Matrix(0, 0);
		
		assertThat(matrix).isNotNull();
		
		matrix.setCol(0);
		matrix.setRow(0);

		assertThat(matrix.getCol()).isEqualTo(0);
		assertThat(matrix.getRow()).isEqualTo(0);
	}

}
