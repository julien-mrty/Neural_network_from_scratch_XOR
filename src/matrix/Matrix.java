package matrix;

import java.util.ArrayList;
import java.util.List;

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
	
	public void add(double scalar) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.data[i][j] += scalar;
			}
		}
	}
	
	public void add(Matrix m) {
		if (columns != m.columns || rows != m.rows) {
			System.out.println("Shape mismatch : add(Matrix m)");
			return;
		}
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.data[i][j] += m.data[i][j];
			}
		}
	}
	
	public static Matrix subtract(Matrix a, Matrix b) {
        Matrix temp = new Matrix(a.rows, a.columns);
        
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.columns;j ++) {
                temp.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        
        return temp;
    }
	
	public static Matrix transpose(Matrix a) { // out of bouds sur matrice non carré ??
        Matrix temp = new Matrix(a.columns, a.rows);
        
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.columns; j++) {
                temp.data[j][i] = a.data[i][j];
            }
        }
        
        return temp;
    }
	
	public static Matrix multiply(Matrix a, Matrix b) {
		if (a.columns != b.rows) {
			System.out.println("Shape mismatch : multiply(Matrix a, Matrix b)");
			System.out.println("a.columns != b.rows");
			return null;
		}
		
		Matrix result = new Matrix(a.rows, b.columns);
		
		for (int i = 0; i < result.rows; i++) {
			for (int j = 0; j < result.columns; j++) {
				double sum = 0;
				
				for (int k = 0; k < a.columns; k++) {
					sum += a.data[i][k] * b.data[k][j];
				}
				
				result.data[i][j] = sum;
			}
		}
		
		return result;
	}
	
    public void multiply(Matrix a) { // Multiplication element-wise ??
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.columns;j++) {
                this.data[i][j] *= a.data[i][j];
            }
        }        
    }
    
    public void multiply(double a) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] *= a;
            }
        }
    }
    
    public void sigmoid() {
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                this.data[i][j] = 1 / (1 + Math.exp(-this.data[i][j]));             	
            }
        }
    }
    
    public Matrix dsigmoid() {
        Matrix temp = new Matrix(rows, columns);
        
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                temp.data[i][j] = this.data[i][j] * (1-this.data[i][j]);
            }
        }
        
        return temp;
    }
    
    public static Matrix fromArray(double[] x) {
        Matrix temp = new Matrix(x.length, 1);
        
        for (int i = 0; i < x.length; i++) {
            temp.data[i][0] = x[i];
        }
        
        return temp;
    }
    
    public List<Double> toArray() {
        List<Double> temp = new ArrayList<Double>()  ;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp.add(data[i][j]);
            }
        }
        
        return temp;
    }
}
