package app.math.api;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.math.domain.Matrix;
import app.math.domain.MatrixDimensionException;
import app.math.domain.MatrixException;

@RestController
public class LinearAlgebraEndpoint {
	private ArrayList<Matrix> matrices;
	private String errormessage;
	private int lastId;

	public LinearAlgebraEndpoint() {
		this.matrices = new ArrayList<Matrix>();
		this.errormessage = "None";
		lastId = 0;
	}

	@DeleteMapping("/LinAlg/clearMatrices")
	public void clearMatrices() {
		this.matrices.clear();
		lastId = 0;
	}

	@DeleteMapping("/LinAlg/deleteById/{id}")
	public void deleteMatrixById(@PathVariable int id) {
		this.matrices.remove(id);
		this.lastId--;
		for (int i = 0; i < this.matrices.size(); i++) {
			this.matrices.get(i).setId(i);
		}
	}

	@GetMapping("/LinAlg/getMatrix/{id}")
	public Matrix getMatrix(@PathVariable int id) {
		try {
			return matrices.get(id);
		} catch (IndexOutOfBoundsException ioobe) {
			return null;
		}
	}

	@GetMapping("/LinAlg/getMatrixString/{id}")
	public String getMatrixString(@PathVariable int id) {
		try {
			return "$$" + matrices.get(id).toTexString() + "$$";
		} catch (IndexOutOfBoundsException ioobe) {
			return "Matrix not found!";
		}
	}

	@GetMapping("/LinAlg/getAllMatrixStrings")
	public String getAllMatrixStrings() {
		if (matrices.size() == 0) {
			return "No matrices found!";
		}
		String ans = "<table class=\"matrixEquation\">\r\n";
		for (Matrix m : this.matrices) {
			ans += "<tr class=\"MatrixEquationRow\">\r\n" + "<td class=\"MatrixEquationColumn\">" + "Matrix(id = "
					+ m.getId()
					+ ")</td><td class=\"MatrixEquationColumn\"> = </td><td class=\"MatrixEquationColumn\">";
			ans += "$$" + m.toTexString() + "$$" + "</td></tr>";
		}
		ans += "</td>\r\n" + "</tr>\r\n" + "</table>";
		return ans;
	}

	@PostMapping("/LinAlg/makeMatrix")
	@ResponseBody
	public void addMatrix(@RequestBody String s) { // Matrix syntax: 1,2,3;4,5,6;7,8,9
		s = s.replaceAll(" ", "");
		String[] rows = s.split(";");
		int numberOfColumns = StringUtils.countOccurrencesOf(rows[0], ",") + 1;
		int numberOfRows = rows.length;
		try {
			for (String row : rows) {
				if (StringUtils.countOccurrencesOf(row, ",") != numberOfColumns - 1) {
					throw new MatrixDimensionException("Number of columns not consistent for every row!");
				}
			}
		} catch (MatrixDimensionException mde) {
			this.errormessage = "ERROR: " + mde.getMessage();
		} catch (Exception e) {
			this.errormessage = "ERROR: " + e.getMessage();
			e.printStackTrace();
		}
		Matrix m = new Matrix(numberOfRows, numberOfColumns);
		m.setId(lastId);
		lastId++;
		m.stringToMatrix(s);
		if (m.getErrormessage().equals("None")) {
			this.matrices.add(m);
		} else {
			this.errormessage = m.getErrormessage();
		}
	}

	@GetMapping("/LinAlg/getErrorMessage")
	public String getErrorMessage() {
		if (this.errormessage.equals("None")) {
			return "";
		} else {
			String totalError = this.errormessage + "<br>";
			for (Matrix m : matrices) {
				totalError += m.getErrormessage() + "<br>";
			}
			return totalError;
		}
	}

	@GetMapping("/LinAlg/Operations/add/{id1}/{id2}")
	public String addMatrices(@PathVariable int id1, @PathVariable int id2) {
		if (id1 >= matrices.size() || id2 >= matrices.size()) {
			return "ID not found!";
		}
		Matrix m;
		try {
			m = matrices.get(id1).add(matrices.get(id2));
		} catch (MatrixDimensionException mde) {
			return mde.getMessage();
		}
		matrices.add(m);
		m.setId(this.lastId);
		this.lastId++;
		String ans = new String();
		ans += "$$" + matrices.get(id1).toTexString() + "+" + matrices.get(id2).toTexString() + " = " + m.toTexString()
				+ "$$";
		return ans;
	}

	@GetMapping("/LinAlg/Operations/multiply/{id1}/{id2}")
	public String multiplyMatrices(@PathVariable int id1, @PathVariable int id2) {
		if (id1 >= matrices.size() || id2 >= matrices.size()) {
			return "ID not found!";
		}
		Matrix m;
		try {
			m = matrices.get(id1).multiply(matrices.get(id2));
		} catch (MatrixDimensionException mde) {
			return mde.getMessage();
		}
		m.setId(this.lastId);
		this.lastId++;
		matrices.add(m);
		String ans = new String();
		ans += "$$";
		ans += matrices.get(id1).toTexString() + matrices.get(id2).toTexString() + " = " + m.toTexString() + "$$";
		return ans;
	}

	@GetMapping("/LinAlg/Operations/transpose/{id}")
	public String transposeMatrix(@PathVariable int id) {
		if (id >= matrices.size()) {
			return "ID not found!";
		}
		String ans = new String();
		Matrix m = matrices.get(id).transposeMatrix();
		m.setId(this.lastId);
		this.lastId++;
		matrices.add(m);
		ans += "$$";
		ans += matrices.get(id).toTexString() + "^T" + " = " + m.toTexString() + "$$";
		return ans;
	}

	@GetMapping("/LinAlg/Operations/subtract/{id1}/{id2}")
	public String subtractMatrices(@PathVariable int id1, @PathVariable int id2) {
		if (id1 >= matrices.size() || id2 >= matrices.size()) {
			return "ID not found!";
		}
		Matrix m;
		try {
			m = matrices.get(id1).subtract(matrices.get(id2));
		} catch (MatrixDimensionException mde) {
			return mde.getMessage();
		}
		matrices.add(m);
		m.setId(this.lastId);
		this.lastId++;
		String ans = new String();
		ans += "$$";
		ans += matrices.get(id1).toTexString() + " - " + matrices.get(id2).toTexString() + " = " + m.toTexString()
				+ "$$";
		return ans;
	}

	@GetMapping("/LinAlg/Operations/rref/{id}")
	public String getRref(@PathVariable int id) {
		if (id >= matrices.size()) {
			return "ID not found!";
		}
		String ans = new String();
		Matrix m = matrices.get(id).rref();
		matrices.add(m);
		m.setId(this.lastId);
		this.lastId++;
		ans += "$$";
		ans += "\\text{RREF}" + matrices.get(id).toTexString() + " = " + m.toTexString() + "$$";
		return ans;
	}

	@GetMapping("/LinAlg/Operations/scale/{id}")
	public String scaleMatrix(@PathVariable int id, @RequestParam BigDecimal scalar) {
		if (id >= matrices.size()) {
			return "ID not found!";
		}
		String ans = new String();
		Matrix m = matrices.get(id).scale(scalar);
		matrices.add(m);
		m.setId(this.lastId);
		this.lastId++;
		ans += "$$";
		ans += scalar + matrices.get(id).toTexString() + " = " + m.toTexString() + "$$";
		return ans;
	}

	@GetMapping("/LinAlg/Operations/determinant/{id}")
	public String determinant(@PathVariable int id) {
		if (id >= matrices.size()) {
			return "ID not found!";
		}
		String ans = new String();
		BigDecimal determinant;
		try {
			determinant = Matrix.determinant(matrices.get(id));
		} catch (MatrixDimensionException mde) {
			return mde.getMessage();
		}
		ans += "$$";
		ans += "\\text{det}" + matrices.get(id).toTexString() + " = " + determinant + "$$";
		return ans;
	}

	@GetMapping("/LinAlg/Operations/ref/{id}")
	public String getRef(@PathVariable int id) {
		if (id >= matrices.size()) {
			return "ID not found!";
		}
		String ans = new String();
		Matrix m = matrices.get(id).ref();
		matrices.add(m);
		m.setId(this.lastId);
		this.lastId++;
		ans += "$$";
		ans += "\\text{REF}" + matrices.get(id).toTexString() + " = " + m.toTexString() + "$$";
		return ans;
	}

	@GetMapping("/LinAlg/Operations/inverse/{id}")
	public String getInverse(@PathVariable int id) {
		if (id >= matrices.size()) {
			return "ID not found!";
		}
		String ans = new String();
		Matrix m;
		try {
			m = matrices.get(id).inverse();
		} catch (MatrixDimensionException e) {
			return e.getMessage();
		} catch (MatrixException e) {
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		matrices.add(m);
		m.setId(this.lastId);
		this.lastId++;
		ans += "$$";
		ans += matrices.get(id).toTexString() + "^{-1}" + " = " + m.toTexString() + "$$";
		return ans;
	}

	@GetMapping("/LinAlg/Operations/Trace/{id}")
	public String getTrace(@PathVariable int id) {
		if (id >= matrices.size()) {
			return "ID not found!";
		}
		Matrix m = matrices.get(id);
		BigDecimal ans;
		try {
			ans = Matrix.trace(m);
		} catch (MatrixDimensionException mde) {
			return mde.getMessage();
		}
		String ansstring = "$$\\text{tr}";
		ansstring += m.toTexString() + " = " + ans.toString() + "$$";
		return ansstring;
	}

	@GetMapping("/LinAlg/Operations/Power/{id}")
	public String getPower(@PathVariable int id, @RequestParam double power) {
		if (id >= matrices.size()) {
			return "ID not found!";
		}
		Matrix ans = matrices.get(id).power(power);
		this.matrices.add(ans);
		ans.setId(this.lastId);
		this.lastId++;
		String ansString = "$$";
		ansString += matrices.get(id).toTexString();
		ansString += "^{" + power + "}" + " = " + ans.toTexString() + "$$";
		return ansString;
	}

	@GetMapping("/LinAlg/Operations/Cross/")
	public String getCrossProduct(@RequestParam int idN, @RequestParam int idM) {
		if (idN >= matrices.size() || idM >= matrices.size()) {
			return "ID not found!";
		}
		Matrix a;
		try {
			a = Matrix.crossProduct(this.matrices.get(idN), this.matrices.get(idM));
		} catch (MatrixDimensionException mde) {
			return mde.getMessage();
		}
		String ans = new String("$$");
		ans += this.matrices.get(idN).toTexString() + " \\times " + this.matrices.get(idM).toTexString() + " = ";
		ans += a.transposeMatrix().toTexString();
		a.setId(lastId);
		this.matrices.add(a);
		lastId++;
		ans += "$$";
		return ans;
	}
}
