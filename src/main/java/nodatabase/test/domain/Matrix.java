package nodatabase.test.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Vector;

public class Matrix {
	private int rowsCount;
	private int columnsCount;
	private Vector<Vector<BigDecimal>> matrix;
	private String errormessage;
	private int id;
	private String inputString;
	private BigDecimal rrMult; // help for determinant
	private final static double thresh = 1e-6;
	private final MathContext mc = MathContext.UNLIMITED ;
	private final MathContext mc2 = new MathContext(8,RoundingMode.HALF_UP);

	public Matrix(int n, int m) {
		this.errormessage = "None";
		this.rowsCount = n;
		this.columnsCount = m;
		this.matrix = new Vector<Vector<BigDecimal>>();
		Vector<BigDecimal> r = new Vector<>();
		for (int i = 0; i < m; i++) {
			r.add(BigDecimal.ZERO);
		}
		for (int i = 0; i < n; i++) {
			this.matrix.add(r);
		}
	}
	
	public Matrix(String input) {
		this.errormessage = "None";
		this.rowsCount = countCharOcc(input, ';');
		String singleRow = input.split(";")[0];
		this.columnsCount = countCharOcc(singleRow, ',')+1;
		this.matrix = new Vector<Vector<BigDecimal>>();
		Vector<BigDecimal> r = new Vector<>();
		for (int i = 0; i < this.columnsCount; i++) {
			r.add(BigDecimal.ZERO);
		}
		for (int i = 0; i < this.rowsCount; i++) {
			this.matrix.add(r);
		}
		stringToMatrix(input);
	}
	
	public BigDecimal getRrMult() {
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

	public void stringToMatrix(String s) {
		this.inputString = s;
		String[] rows = s.split(";");
		for (int i = 0; i < rows.length; i++) {
			String[] elements = rows[i].split(",");
			for (int j = 0; j < elements.length; j++) {
				setMatrixElement(i, j, new BigDecimal(elements[j]));
			}
		}
	}

	public void setMatrixElement(int n, int m, BigDecimal value) {
		Vector<BigDecimal> row = new Vector<>();// this.matrix.get(n);

		// First part deals with precision problems, e.g. 0.99999999999999 would show up
		// instead of 1.0
		if (Math.abs(value.doubleValue()) <= thresh) {
			value = BigDecimal.ZERO;
		}
		if (value.scale() > 5) {
			BigDecimal tmp = value;
			String tmpString = tmp.toPlainString();
			if (tmpString.matches("\\d{1,}\\.\\d{0,}0{3,}\\d{0,}")) {
				tmpString = tmpString.replaceAll("0{1,}[1-9]{0,}0{0,}$", "");
				value = new BigDecimal(tmpString);
			} else if (tmpString.matches("\\d{1,}\\.9{5,}\\d{0,}")) {
				value = new BigDecimal(Double.toString(Math.round(value.doubleValue())));
			}
		}

		// Filling matrix element
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

	public Matrix multiply(Matrix m) throws MatrixDimensionException {
		if (columnsCount != m.getRowsCount()) {
			System.out.println("Inner dimension mismatch");
			this.errormessage = "Inner dimension mismatch";
			throw new MatrixDimensionException(errormessage);
		}
		Matrix ans = new Matrix(rowsCount, m.getColumnsCount());
		Vector<BigDecimal> row = null;
		Vector<BigDecimal> col = null;
		for (int r = 0; r < rowsCount; r++) {
			row = this.matrix.get(r);
			for (int c = 0; c < m.getColumnsCount(); c++) {
				col = m.getColumn(c);
				BigDecimal value = BigDecimal.ZERO;
				for (int i = 0; i < col.size(); i++) {
					value = value.add(row.get(i).multiply(col.get(i)));
				}
				ans.setMatrixElement(r, c, value);
			}
		}
		return ans;
	}

	public Matrix add(Matrix m) throws MatrixDimensionException {
		if (rowsCount != m.getRowsCount() || columnsCount != m.getColumnsCount()) {
			System.out.println("Matrices need same dimensions");
			this.errormessage = "Matrices need same dimensions";
			throw new MatrixDimensionException("Matrices need same dimensions");
		}
		Matrix ans = new Matrix(rowsCount, columnsCount);
		for (int i = 0; i < rowsCount; i++) {
			for (int j = 0; j < columnsCount; j++) {
				BigDecimal value = this.matrix.get(i).get(j).add(m.getMatrix().get(i).get(j));
				ans.setMatrixElement(i, j, value);
			}
		}
		return ans;
	}

	public Matrix subtract(Matrix m) throws MatrixDimensionException {
		if (rowsCount != m.getRowsCount() || columnsCount != m.getColumnsCount()) {
			System.out.println("Matrices need same dimensions");
			this.errormessage = "Matrices need same dimensions";
			throw new MatrixDimensionException("Matrices need same dimensions");
		}
		Matrix ans = new Matrix(rowsCount, columnsCount);
		for (int i = 0; i < rowsCount; i++) {
			for (int j = 0; j < columnsCount; j++) {
				BigDecimal value = this.matrix.get(i).get(j).subtract(m.getMatrix().get(i).get(j));
				ans.setMatrixElement(i, j, value);
			}
		}
		return ans;
	}

	public Matrix transposeMatrix() {
		Matrix m = new Matrix(this.columnsCount, this.rowsCount);
		for (int i = 0; i < getRowsCount(); i++) {
			for (int j = 0; j < getColumnsCount(); j++) {
				m.setMatrixElement(j, i, this.matrix.get(i).get(j));
			}
		}
		return m;
	}

	public Matrix rref() { // reduced row echelon form
		Matrix m = new Matrix(this.rowsCount, this.columnsCount);
		for (int i = 0; i < getRowsCount(); i++) {
			for (int j = 0; j < getColumnsCount(); j++) {
				m.setMatrixElement(i, j, this.matrix.get(i).get(j));
			}
		}
		for (int rowIndex = 0; rowIndex < getRowsCount(); rowIndex++) {
			if (m.getMatrix().get(rowIndex).get(rowIndex).equals(BigDecimal.ZERO)) {
				if (rowIndex < getRowsCount() - 1) {
					m = Matrix.swapRows(m, rowIndex, rowIndex + 1);
				} else {
					m = Matrix.swapRows(m, rowIndex, rowIndex - 1);
				}
			}
			Vector<BigDecimal> currentRow = m.getMatrix().get(rowIndex);
			BigDecimal divisor = currentRow.get(rowIndex);
			int count=0;
			for (int j = 0; j < getColumnsCount(); j++) {
				try {
					if(!divisor.equals(BigDecimal.ZERO)) {
						m.setMatrixElement(rowIndex, j, currentRow.get(j).divide(divisor,mc)); // gets a 1 on diagonal
					}else {
						count++;
						if(rowIndex+count>=getColumnsCount()) {
							continue;
						}else {
							m.setMatrixElement(rowIndex, j, currentRow.get(rowIndex+count));
						}
					}
				}catch(ArithmeticException ae) {
					m.setMatrixElement(rowIndex, j, currentRow.get(j).divide(divisor,mc2));
				}
			}

			for (int i = 0; i < getRowsCount(); i++) {
				if (i == rowIndex) {
					continue;
				}
				BigDecimal rowMultiplicant = m.getMatrix().get(i).get(rowIndex);
				Vector<BigDecimal> subtracter = new Vector<>();// m.getMatrix().get(rowIndex);
				subtracter.setSize(getColumnsCount());
				for (int j = 0; j < getColumnsCount(); j++) {
					subtracter.set(j, rowMultiplicant.multiply(m.getMatrix().get(rowIndex).get(j)));
					m.setMatrixElement(i, j, m.getMatrix().get(i).get(j).subtract(subtracter.get(j)));
				}
			}
		}
		for (int rowIndex = 0; rowIndex < getRowsCount(); rowIndex++) {
			if (m.getMatrix().get(rowIndex).get(rowIndex).equals(BigDecimal.ZERO)) {
				if (rowIndex < getRowsCount() - 1) {
					m = Matrix.swapRows(m, rowIndex, rowIndex + 1);
				}
			}
		}
		for (int rowIndex = 0; rowIndex < getRowsCount(); rowIndex++) {
			if (!m.getMatrix().get(rowIndex).get(rowIndex).equals(BigDecimal.ONE)
					&& !m.getMatrix().get(rowIndex).get(rowIndex).equals(BigDecimal.ZERO)) {
				Vector<BigDecimal> currentRow = m.getMatrix().get(rowIndex);
				BigDecimal divisor = currentRow.get(rowIndex);
				for (int j = 0; j < getColumnsCount(); j++) {
					m.setMatrixElement(rowIndex, j, currentRow.get(j).divide(divisor,mc)); // gets a 1 on diagonal
				}

				for (int i = 0; i < getRowsCount(); i++) {
					if (i == rowIndex) {
						continue;
					}
					BigDecimal rowMultiplicant = m.getMatrix().get(i).get(rowIndex);
					Vector<BigDecimal> subtracter = new Vector<>();// m.getMatrix().get(rowIndex);
					subtracter.setSize(getColumnsCount());
					for (int j = 0; j < getColumnsCount(); j++) {
						subtracter.set(j, rowMultiplicant.multiply(m.getMatrix().get(rowIndex).get(j)));
						m.setMatrixElement(i, j, m.getMatrix().get(i).get(j).subtract(subtracter.get(j)));
					}
				}
			}
		}
		return m;
	}

	public static Matrix swapRows(Matrix m, int row1, int row2) {
		Vector<BigDecimal> first = new Vector<>();
		Vector<BigDecimal> second = new Vector<>();
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

	public Matrix scale(BigDecimal scalar) {
		Matrix m = new Matrix(this.rowsCount, this.columnsCount);
		for (int rows = 0; rows < this.rowsCount; rows++) {
			for (int cols = 0; cols < this.columnsCount; cols++) {
				m.setMatrixElement(rows, cols, this.matrix.get(rows).get(cols).multiply(scalar));
			}
		}
		return m;
	}
	
	public BigDecimal getElement(int i,int j) {
		return this.getMatrix().get(i-1).get(j-1);
	}

	public Matrix ref() {
		this.rrMult = BigDecimal.ONE;
		Matrix m = new Matrix(this.rowsCount, this.columnsCount);
		for (int i = 0; i < getRowsCount(); i++) {
			for (int j = 0; j < getColumnsCount(); j++) {
				m.setMatrixElement(i, j, this.matrix.get(i).get(j));
			}
		}
		for (int rowIndex = 0; rowIndex < getRowsCount(); rowIndex++) {
			if(m.getRowsCount()<=m.getColumnsCount()) {
				if (m.getMatrix().get(rowIndex).get(rowIndex).equals(BigDecimal.ZERO)) {
					if (rowIndex < getRowsCount() - 1) {
						m = Matrix.swapRows(m, rowIndex, rowIndex + 1);
						this.rrMult = rrMult.multiply(new BigDecimal(-1.0));
					} else {
						m = Matrix.swapRows(m, rowIndex, rowIndex - 1);
						this.rrMult = rrMult.multiply(new BigDecimal(-1.0));
					}
				}
				Vector<BigDecimal> currentRow = m.getMatrix().get(rowIndex);
				BigDecimal multrow = currentRow.get(rowIndex);
				if (rowIndex < getRowsCount() - 1) {
					for (int row = rowIndex + 1; row < getRowsCount(); row++) {
						BigDecimal multcol = m.getMatrix().get(row).get(rowIndex);
						int count = 0;
						for (int col = 0; col < getColumnsCount(); col++) {
							if(!multrow.equals(BigDecimal.ZERO)) {
								try {
									m.getMatrix().get(row).set(col, m.getMatrix().get(row).get(col)
											.subtract(m.getMatrix().get(rowIndex).get(col).multiply(multcol).divide(multrow,mc)));
								}catch(ArithmeticException ae) {
									m.getMatrix().get(row).set(col, m.getMatrix().get(row).get(col)
											.subtract(m.getMatrix().get(rowIndex).get(col).multiply(multcol).divide(multrow,mc2)));
								}
							}else {
								count++;
								if(rowIndex+count>=getColumnsCount()) {
									continue;
								}else {
									multcol = m.getMatrix().get(row).get(rowIndex+count);
								}
							}
						}
					}
				}
			}else{
				for(int col=0;col<m.getColumnsCount();col++) {
					if (m.getMatrix().get(col).get(col).equals(BigDecimal.ZERO)) {
						if (rowIndex < getRowsCount() - 1) {
							m = Matrix.swapRows(m, rowIndex, rowIndex + 1);
							this.rrMult = rrMult.multiply(new BigDecimal(-1.0));
						} else {
							m = Matrix.swapRows(m, rowIndex, rowIndex - 1);
							this.rrMult = rrMult.multiply(new BigDecimal(-1.0));
						}
					}
					Vector<BigDecimal> currentRow = m.getMatrix().get(rowIndex);
					BigDecimal multrow = currentRow.get(col);
					if (rowIndex < getRowsCount() - 1) {
						for (int row = rowIndex + 1; row < getRowsCount(); row++) {
							BigDecimal multcol = m.getMatrix().get(row).get(rowIndex);
							int count = 0;
							for (int col2 = 0; col2 < getColumnsCount(); col2++) {
								if(!multrow.equals(BigDecimal.ZERO)) {
									try {
										m.getMatrix().get(row).set(col2, m.getMatrix().get(row).get(col2)
												.subtract(m.getMatrix().get(rowIndex).get(col2).multiply(multcol).divide(multrow,mc)));
									}catch(ArithmeticException ae) {
										m.getMatrix().get(row).set(col2, m.getMatrix().get(row).get(col2)
												.subtract(m.getMatrix().get(rowIndex).get(col2).multiply(multcol).divide(multrow,mc2)));
									}
								}else {
									count++;
									if(rowIndex+count>=getColumnsCount()) {
										continue;
									}else {
										multcol = m.getMatrix().get(row).get(rowIndex+count);
									}
								}
							}
						}
					}
				}
			}			
		}
		return m;
	}

	public static BigDecimal determinant(Matrix m) throws MatrixDimensionException {
		if (m.getRowsCount() != m.getColumnsCount()) {
			throw new MatrixDimensionException("Matrix must be a square!");
		}
		BigDecimal ans = new BigDecimal(1.0);
		Matrix tmp = m.ref();
		ans = ans.multiply(m.getRrMult());
		for (int i = 0; i < tmp.getRowsCount(); i++) {
			ans = ans.multiply(tmp.getMatrix().get(i).get(i));
		}
		if(ans.abs().compareTo(new BigDecimal(Double.toString(thresh)))==-1) {
			ans = BigDecimal.ZERO;
		}
		return ans;
	}
	
	public static BigDecimal trace(Matrix m) throws MatrixDimensionException{
		if(m.getRowsCount() != m.getColumnsCount()) {
			throw new MatrixDimensionException("Matrix must be a square!");
		}
		BigDecimal ans = BigDecimal.ZERO;
		for(int i = 0; i<m.getRowsCount();i++) {
			ans = ans.add(m.getMatrix().get(i).get(i));
		}
		return ans;
	}
	
	public Matrix power(double power) {
		Matrix m = this;
		for(int i = 0; i<power;i++) {
			try {
				m = m.multiply(this);
			} catch (MatrixDimensionException e) {e.printStackTrace();}
		}
		return m;
	}
	
	public Matrix inverse() throws MatrixDimensionException, MatrixException {
		if (getRowsCount() != getColumnsCount() || Matrix.determinant(this).equals(BigDecimal.ZERO)) {
			throw new MatrixException("Non-invertible matrix");
		}
		Matrix m = new Matrix(getRowsCount(), 2 * getColumnsCount());
		for (int i = 0; i < getRowsCount(); i++) {
			for (int j = 0; j < getColumnsCount(); j++) {
				m.setMatrixElement(i, j, this.getMatrix().get(i).get(j));
			}
			for (int j = getColumnsCount(); j < 2 * getColumnsCount(); j++) {
				BigDecimal value;
				if (i == j - getColumnsCount()) {
					value = new BigDecimal(1.0);
				} else {
					value = new BigDecimal(0.0);
				}
				m.setMatrixElement(i, j, value);
			}
		}
		m = m.rref();
		Matrix m2 = new Matrix(getRowsCount(), getColumnsCount());
		for (int i = 0; i < getRowsCount(); i++) {
			for (int j = 0; j < getColumnsCount(); j++) {
				m2.setMatrixElement(i, j, m.getMatrix().get(i).get(getColumnsCount() + j));
			}
		}
		return m2;
	}
	
	public static Matrix crossProduct(Matrix n,Matrix m) throws MatrixDimensionException { //Calculates n x m, the cross product between vectors n and m
		Matrix ans = new Matrix(1, 3);	//Final answer is a vector of length 3
		if(n.getRowsCount()>n.getColumnsCount()) {
			n=n.transposeMatrix();
		}
		if(m.getRowsCount()>m.getColumnsCount())
			m=m.transposeMatrix();
		if(!(n.getRowsCount()==1 && n.getColumnsCount()==3) && !(m.getRowsCount()==1 && m.getColumnsCount()==3)) {
			throw new MatrixDimensionException("Only vectors of length 3 allowed!");
		}
		BigDecimal ex = new BigDecimal("1");
		ex=ex.multiply(n.getMatrix().get(0).get(1)).multiply(m.getMatrix().get(0).get(2));
		ex=ex.subtract(m.getMatrix().get(0).get(1).multiply(n.getMatrix().get(0).get(2)));
		ans.setMatrixElement(0, 0, ex);
		
		BigDecimal ey = new BigDecimal("1");
		//Note that the order here is flipped because the sign of this element will technically be -1
		ey=ey.multiply(m.getMatrix().get(0).get(0).multiply(n.getMatrix().get(0).get(2)));
		ey=ey.subtract(n.getMatrix().get(0).get(0).multiply(m.getMatrix().get(0).get(2)));
		ans.setMatrixElement(0, 1, ey);
		
		BigDecimal ez = new BigDecimal("1");
		ez=ez.multiply(n.getMatrix().get(0).get(0)).multiply(m.getMatrix().get(0).get(1));
		ez=ez.subtract(m.getMatrix().get(0).get(0).multiply(n.getMatrix().get(0).get(1)));
		ans.setMatrixElement(0, 2, ez);
		return ans;
	}

	public Vector<Vector<BigDecimal>> getMatrix() {
		return matrix;
	}

	public Vector<BigDecimal> getColumn(int n) {
		if (n > columnsCount) {
			throw new IllegalArgumentException();
		}
		Vector<BigDecimal> ans = new Vector<>();
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
	
	public String toTexString() {
		String ans = new String();
		ans+="\\begin{pmatrix}";
		for(int i = 0;i<this.rowsCount;i++) {
			for(int j = 0;j<this.columnsCount;j++) {
				if(j<this.columnsCount-1)
					ans+=this.matrix.get(i).get(j) + " & ";
				else
					ans+=this.matrix.get(i).get(j);
			}
			if(i<this.rowsCount-1)
				ans+="\\\\";
		}
		ans+="\\end{pmatrix}";
		return ans;
	}
	
	private int countCharOcc(String s, char c) {
		char[] scarr = s.toCharArray();
		int ans = 0;
		for (int i = 0; i < scarr.length; i++) {
			if (scarr[i] == c) {
				ans++;
			}
		}
		return ans;
	}
}
