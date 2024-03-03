package matrix;

public class Matrix {
	double [][] data;
	int rows, columns;
	
	public Matrix(int rows, int columns) {
		data = new double [rows][columns];
		this.rows = rows;
		this.columns = columns;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				data[i][j] = Math.random() * 2 - 1;
			}
		}
	}
}
