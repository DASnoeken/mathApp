package nodatabase.test.domain;

import java.math.BigDecimal;
import java.util.Vector;

public class MatrixD {
	private int rowsCount;
	private int columnsCount;
	private Vector<Vector<Double>> matrix;
	private String errormessage;
	private int id;
	private String inputString;
	private double rrMult; // help for determinant
	private final double thresh = 1e-10;

	public double getRrMult() {
		return rrMult;
	}

	public String getInputString() {
		return inputString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public MatrixD(int n, int m) {
		this.errormessage = "None";
		this.rowsCount = n;
		this.columnsCount = m;
		this.matrix = new Vector<Vector<Double>>();
		Vector<Double> r = new Vector<>();
		for (int i = 0; i < m; i++) {
			r.add(0.0);
		}
		for (int i = 0; i < n; i++) {
			this.matrix.add(r);
		}
	}

	public void stringToMatrix(String s) {
		this.inputString = s;
		String[] rows = s.split(";");
		for (int i = 0; i < rows.length; i++) {
			String[] elements = rows[i].split(",");
			for (int j = 0; j < elements.length; j++) {
				setMatrixElement(i, j, Double.parseDouble(elements[j]));
			}
		}
	}

	public void setMatrixElement(int n, int m, double value) {
		Vector<Double> row = new Vector<>();// this.matrix.get(n);
		
		//First part deals with precision problems, e.g. 0.99999999999999 would show up instead of 1.0
		if(Math.abs(value)<=thresh) {
			value=0.0;
		}
		if(BigDecimal.valueOf(value).scale() > 5) {
			Double tmp = value;
			String tmpString = Double.toString(tmp);
			if(tmpString.matches("\\d{1,}\\.\\d{0,}0{3,}\\d{0,}")) {
				tmpString = tmpString.replaceAll("0{3}[1-9]{0,}$", "");
				value = Double.valueOf(tmpString);
			}else if(tmpString.matches("\\d{1,}\\.9{5,}\\d{0,}")) {
				value = Math.round(value);
			}
		}
		
		//Filling matrix element
		for (int i = 0; i < columnsCount; i++) {
			row.add(matrix.get(n).get(i));
		}
		row.set(m, value);
		this.matrix.set(n, row);
	}

	public void printElements() {
		for (int i = 0; i < rowsCount; i++) {
			for (int j = 0; j < columnsCount; j++) {
				System.out.print(this.matrix.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	public MatrixD multiply(MatrixD m) throws MatrixDimensionException {
		if (columnsCount != m.getRowsCount()) {
			System.out.println("Inner dimension mismatch");
			this.errormessage = "Inner dimension mismatch";
			throw new MatrixDimensionException(errormessage);
		}
		MatrixD ans = new MatrixD(rowsCount, m.getColumnsCount());
		Vector<Double> row = null;
		Vector<Double> col = null;
		for (int r = 0; r < rowsCount; r++) {
			row = this.matrix.get(r);
			for (int c = 0; c < m.getColumnsCount(); c++) {
				col = m.getColumn(c);
				double value = 0.0;
				for (int i = 0; i < col.size(); i++) {
					value += row.get(i) * col.get(i);
				}
				ans.setMatrixElement(r, c, value);
			}
		}
		return ans;
	}

	public MatrixD add(MatrixD m) throws MatrixDimensionException {
		if (rowsCount != m.getRowsCount() || columnsCount != m.getColumnsCount()) {
			System.out.println("Matrices need same dimensions");
			this.errormessage = "Matrices need same dimensions";
			throw new MatrixDimensionException("Matrices need same dimensions");
		}
		MatrixD ans = new MatrixD(rowsCount, columnsCount);
		for (int i = 0; i < rowsCount; i++) {
			for (int j = 0; j < columnsCount; j++) {
				double value = this.matrix.get(i).get(j) + m.getMatrix().get(i).get(j);
				ans.setMatrixElement(i, j, value);
			}
		}
		return ans;
	}

	public MatrixD subtract(MatrixD m) throws MatrixDimensionException {
		if (rowsCount != m.getRowsCount() || columnsCount != m.getColumnsCount()) {
			System.out.println("Matrices need same dimensions");
			this.errormessage = "Matrices need same dimensions";
			throw new MatrixDimensionException("Matrices need same dimensions");
		}
		MatrixD ans = new MatrixD(rowsCount, columnsCount);
		for (int i = 0; i < rowsCount; i++) {
			for (int j = 0; j < columnsCount; j++) {
				double value = this.matrix.get(i).get(j) - m.getMatrix().get(i).get(j);
				ans.setMatrixElement(i, j, value);
			}
		}
		return ans;
	}

	public MatrixD transposeMatrix() {
		MatrixD m = new MatrixD(this.columnsCount, this.rowsCount);
		for (int i = 0; i < getRowsCount(); i++) {
			for (int j = 0; j < getColumnsCount(); j++) {
				m.setMatrixElement(j, i, this.matrix.get(i).get(j));
			}
		}
		return m;
	}

	public MatrixD rref() { // reduced row echelon form
		MatrixD m = new MatrixD(this.rowsCount, this.columnsCount);
		for (int i = 0; i < getRowsCount(); i++) {
			for (int j = 0; j < getColumnsCount(); j++) {
				m.setMatrixElement(i, j, this.matrix.get(i).get(j));
			}
		}
		for (int rowIndex = 0; rowIndex < getRowsCount(); rowIndex++) {
			if (m.getMatrix().get(rowIndex).get(rowIndex) == 0) {
				if (rowIndex < getRowsCount() - 1) {
					m = MatrixD.swapRows(m, rowIndex, rowIndex + 1);
				} else {
					m = MatrixD.swapRows(m, rowIndex, rowIndex - 1);
				}
			}
			Vector<Double> currentRow = m.getMatrix().get(rowIndex);
			Double divisor = currentRow.get(rowIndex);
			for (int j = 0; j < getColumnsCount(); j++) {
				m.setMatrixElement(rowIndex, j, currentRow.get(j) / divisor); // gets a 1 on diagonal
			}

			for (int i = 0; i < getRowsCount(); i++) {
				if (i == rowIndex) {
					continue;
				}
				Double rowMultiplicant = m.getMatrix().get(i).get(rowIndex);
				Vector<Double> subtracter = new Vector<Double>();// m.getMatrix().get(rowIndex);
				subtracter.setSize(getColumnsCount());
				for (int j = 0; j < getColumnsCount(); j++) {
					subtracter.set(j, rowMultiplicant * m.getMatrix().get(rowIndex).get(j));
					m.setMatrixElement(i, j, m.getMatrix().get(i).get(j) - subtracter.get(j));
				}
			}
		}
		for (int rowIndex = 0; rowIndex < getRowsCount(); rowIndex++) {
			if (m.getMatrix().get(rowIndex).get(rowIndex) == 0) {
				if (rowIndex < getRowsCount() - 1) {
					m = MatrixD.swapRows(m, rowIndex, rowIndex + 1);
				}
			}
		}
		for (int rowIndex = 0; rowIndex < getRowsCount(); rowIndex++) {
			if (m.getMatrix().get(rowIndex).get(rowIndex) != 1 && m.getMatrix().get(rowIndex).get(rowIndex) != 0) {
				Vector<Double> currentRow = m.getMatrix().get(rowIndex);
				Double divisor = currentRow.get(rowIndex);
				for (int j = 0; j < getColumnsCount(); j++) {
					m.setMatrixElement(rowIndex, j, currentRow.get(j) / divisor); // gets a 1 on diagonal
				}

				for (int i = 0; i < getRowsCount(); i++) {
					if (i == rowIndex) {
						continue;
					}
					Double rowMultiplicant = m.getMatrix().get(i).get(rowIndex);
					Vector<Double> subtracter = new Vector<Double>();// m.getMatrix().get(rowIndex);
					subtracter.setSize(getColumnsCount());
					for (int j = 0; j < getColumnsCount(); j++) {
						subtracter.set(j, rowMultiplicant * m.getMatrix().get(rowIndex).get(j));
						m.setMatrixElement(i, j, m.getMatrix().get(i).get(j) - subtracter.get(j));
					}
				}
			}
		}
		return m;
	}

	public static MatrixD swapRows(MatrixD m, int row1, int row2) {
		Vector<Double> first = new Vector<Double>();
		Vector<Double> second = new Vector<Double>();
		first.setSize(m.getColumnsCount());
		second.setSize(m.getColumnsCount());
		for (int i = 0; i < m.getColumnsCount(); i++) {
			first.set(i, m.getMatrix().get(row1).get(i));
			second.set(i, m.getMatrix().get(row2).get(i));
		}
		for (int i = 0; i < m.getColumnsCount(); i++) {
			m.setMatrixElement(row1, i, second.get(i));
			m.setMatrixElement(row2, i, first.get(i));
		}
		return m;
	}

	public MatrixD scale(Double scalar) {
		MatrixD m = new MatrixD(this.rowsCount, this.columnsCount);
		for (int rows = 0; rows < this.rowsCount; rows++) {
			for (int cols = 0; cols < this.columnsCount; cols++) {
				m.setMatrixElement(rows, cols, this.matrix.get(rows).get(cols) * scalar);
			}
		}
		return m;
	}

	public MatrixD ref() {
		this.rrMult = 1.0;
		MatrixD m = new MatrixD(this.rowsCount, this.columnsCount);
		for (int i = 0; i < getRowsCount(); i++) {
			for (int j = 0; j < getColumnsCount(); j++) {
				m.setMatrixElement(i, j, this.matrix.get(i).get(j));
			}
		}
		for (int rowIndex = 0; rowIndex < getRowsCount(); rowIndex++) {
			if (m.getMatrix().get(rowIndex).get(rowIndex) == 0) {
				if (rowIndex < getRowsCount() - 1) {
					m = MatrixD.swapRows(m, rowIndex, rowIndex + 1);
					this.rrMult *= -1;
				} else {
					m = MatrixD.swapRows(m, rowIndex, rowIndex - 1);
					this.rrMult *= -1;
				}
			}
			Vector<Double> currentRow = m.getMatrix().get(rowIndex);
			Double multrow = currentRow.get(rowIndex);
			if (rowIndex < getRowsCount() - 1) {
				for (int row = rowIndex + 1; row < getRowsCount(); row++) {
					double multcol = m.getMatrix().get(row).get(rowIndex);
					for (int col = 0; col < getColumnsCount(); col++) {
						m.getMatrix().get(row).set(col, m.getMatrix().get(row).get(col)
								- m.getMatrix().get(rowIndex).get(col) * multcol / multrow);
					}
				}
			}
		}
		return m;
	}

	public static double determinant(MatrixD m) throws MatrixDimensionException {
		if (m.getRowsCount() != m.getColumnsCount()) {
			throw new MatrixDimensionException("Matrix must be a square!");
		}
		double ans = 1.0;
		MatrixD tmp = m.ref();
		ans *= m.getRrMult();
		for (int i = 0; i < tmp.getRowsCount(); i++) {
			ans *= tmp.getMatrix().get(i).get(i);
		}
		return ans;
	}

	public MatrixD inverse() throws MatrixDimensionException, MatrixException {
		if (getRowsCount() != getColumnsCount() || MatrixD.determinant(this) == 0) {
			throw new MatrixException("Non-invertible matrix");
		}
		MatrixD m = new MatrixD(getRowsCount(), 2 * getColumnsCount());
		for (int i = 0; i < getRowsCount(); i++) {
			for (int j = 0; j < getColumnsCount(); j++) {
				m.setMatrixElement(i, j, this.getMatrix().get(i).get(j));
			}
			for (int j = getColumnsCount(); j < 2 * getColumnsCount(); j++) {
				double value;
				if (i == j-getColumnsCount()) {
					value = 1.0;
				} else {
					value = 0.0;
				}
				m.setMatrixElement(i, j, value);
			}
		}
		m = m.rref();
		MatrixD m2 = new MatrixD(getRowsCount(), getColumnsCount());
		for (int i = 0; i < getRowsCount(); i++) {
			for (int j = 0; j < getColumnsCount(); j++) {
				m2.setMatrixElement(i, j, m.getMatrix().get(i).get(getColumnsCount()+j));
			}
		}
		return m2;
	}

	public Vector<Vector<Double>> getMatrix() {
		return matrix;
	}

	public Vector<Double> getColumn(int n) {
		if (n > columnsCount) {
			throw new IllegalArgumentException();
		}
		Vector<Double> ans = new Vector<>();
		for (int i = 0; i < rowsCount; i++) {
			ans.add(matrix.get(i).elementAt(n));
		}
		return ans;
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public int getColumnsCount() {
		return columnsCount;
	}

	@Override
	public String toString() {
		String ans = new String();
		for (int i = 0; i < rowsCount; i++) {
			for (int j = 0; j < columnsCount; j++) {
				if (j == columnsCount - 1) {
					ans += this.matrix.get(i).get(j);
				} else {
					ans += (this.matrix.get(i).get(j) + ", ");
				}
			}
			ans += "; ";
		}
		return ans;
	}

	public String toHTMLString() {
		String ans = new String();
		ans += "<table class=matrix>";
		for (int i = 0; i < rowsCount; i++) {
			ans += "<tr>";
			for (int j = 0; j < columnsCount; j++) {
				ans += ("<td class=matrixColumn>" + this.matrix.get(i).get(j) + "</td>");
			}
			ans += "</tr>";
		}
		ans += "</table>";
		return ans;
	}
}
