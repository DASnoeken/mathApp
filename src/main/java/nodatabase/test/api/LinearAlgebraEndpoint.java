package nodatabase.test.api;

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

import nodatabase.test.domain.Matrix;
import nodatabase.test.domain.MatrixDimensionException;
import nodatabase.test.domain.MatrixException;

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
			return matrices.get(id).toHTMLString();
		} catch (IndexOutOfBoundsException ioobe) {
			return "Matrix not found!";
		}
	}

	@GetMapping("/LinAlg/getAllMatrixStrings")
	public String getAllMatrixStrings() {
		String ans = "<table class=\"matrixEquation\">\r\n";
		for (Matrix m : this.matrices) {
			ans += "<tr class=\"MatrixEquationRow\">\r\n" + "<td class=\"MatrixEquationColumn\">" + "Matrix(id = "
					+ m.getId()
					+ ")</td><td class=\"MatrixEquationColumn\"> = </td><td class=\"MatrixEquationColumn\">";
			ans += m.toHTMLString() + "</td></tr>";
		}
		ans += "</td>\r\n" + "</tr>\r\n" + "</table>";
		if (!ans.equals(""))
			return ans;
		else
			return "No matrices found!";
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
		ans += "<table class=\"matrixEquation\">\r\n" + "<tr class=\"MatrixEquationRow\">\r\n"
				+ "<td class=\"MatrixEquationColumn\">";
		ans += matrices.get(id1).toHTMLString() + "</td>\r\n" + "<td class=\"MatrixEquationColumn\">+</td>\r\n"
				+ "<td class=\"MatrixEquationColumn\">" + matrices.get(id2).toHTMLString()
				+ "</td class=\"MatrixEquationColumn\">\r\n" + "<td> = </td>\r\n"
				+ "<td class=\"MatrixEquationColumn\">" + m.toHTMLString() + "</td>\r\n" + "</tr>\r\n" + "</table>";
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
		ans += "<table class=\"matrixEquation\">\r\n" + "<tr class=\"MatrixEquationRow\">\r\n"
				+ "<td class=\"MatrixEquationColumn\">";
		ans += matrices.get(id1).toHTMLString() + "</td>\r\n" + "<td class=\"MatrixEquationColumn\">"
				+ matrices.get(id2).toHTMLString() + "</td class=\"MatrixEquationColumn\">\r\n" + "<td> = </td>\r\n"
				+ "<td class=\"MatrixEquationColumn\">" + m.toHTMLString() + "</td>\r\n" + "</tr>\r\n" + "</table>";
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
		ans += "<table class=\"matrixEquation\">\r\n" + "<tr class=\"MatrixEquationRow\">\r\n"
				+ "<td class=\"MatrixEquationColumn\">";
		ans += matrices.get(id).toHTMLString() + "</td><td>T</td>\r\n" + "<td class=\"MatrixEquationColumn\">"
				+ "</td class=\"MatrixEquationColumn\">\r\n" + "<td> = </td>\r\n"
				+ "<td class=\"MatrixEquationColumn\">" + m.toHTMLString() + "</td>\r\n" + "</tr>\r\n" + "</table>";
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
		ans += "<table class=\"matrixEquation\">\r\n" + "<tr class=\"MatrixEquationRow\">\r\n"
				+ "<td class=\"MatrixEquationColumn\">";
		ans += matrices.get(id1).toHTMLString() + "</td>\r\n" + "<td class=\"MatrixEquationColumn\">-</td>\r\n"
				+ "<td class=\"MatrixEquationColumn\">" + matrices.get(id2).toHTMLString() + "</td>\r\n"
				+ "<td class=\"MatrixEquationColumn\"> = </td>\r\n" + "<td class=\"MatrixEquationColumn\">"
				+ m.toHTMLString() + "</td>\r\n" + "</tr>\r\n" + "</table>";
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
		ans += "<table class=\"matrixEquation\">\r\n" + "<tr class=\"MatrixEquationRow\">\r\n"
				+ "<td class=\"MatrixEquationColumn\">";
		ans += "RREF:</td><td class=\"MatrixEquationColumn\">" + matrices.get(id).toHTMLString()
				+ "</td><td class=\"MatrixEquationColumn\"> = </td>\r\n" + "<td class=\"MatrixEquationColumn\">"
				+ m.toHTMLString() + "</td>\r\n" + "</tr>\r\n" + "</table>";
		return ans;
	}
	
	@GetMapping("/LinAlg/Operations/scale/{id}")
	public String scaleMatrix(@PathVariable int id, @RequestParam Double scalar) {
		if (id >= matrices.size()) {
			return "ID not found!";
		}
		String ans = new String();
		Matrix m = matrices.get(id).scale(scalar);
		matrices.add(m);
		m.setId(this.lastId);
		this.lastId++;
		ans += "<table class=\"matrixEquation\">\r\n" + "<tr class=\"MatrixEquationRow\">\r\n"
				+ "<td class=\"MatrixEquationColumn\">";
		ans += scalar+"</td><td class=\"MatrixEquationColumn\">" + matrices.get(id).toHTMLString()
				+ "</td><td class=\"MatrixEquationColumn\"> = </td>\r\n" + "<td class=\"MatrixEquationColumn\">"
				+ m.toHTMLString() + "</td>\r\n" + "</tr>\r\n" + "</table>";
		return ans;
	}
	
	@GetMapping("/LinAlg/Operations/determinant/{id}")
	public String determinant(@PathVariable int id) {
		if (id >= matrices.size()) {
			return "ID not found!";
		}
		String ans = new String();
		double determinant;
		try {
			determinant = Matrix.determinant(matrices.get(id));
		}catch(MatrixDimensionException mde) {
			return mde.getMessage();
		}
		ans += "<table class=\"matrixEquation\">\r\n" + "<tr class=\"MatrixEquationRow\">\r\n"
				+ "<td class=\"MatrixEquationColumn\">";
		ans += "det:</td><td class=\"MatrixEquationColumn\">" + matrices.get(id).toHTMLString()
				+ "</td><td class=\"MatrixEquationColumn\"> = </td>\r\n" + "<td class=\"MatrixEquationColumn\">"
				+ determinant + "</td>\r\n" + "</tr>\r\n" + "</table>";
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
		ans += "<table class=\"matrixEquation\">\r\n" + "<tr class=\"MatrixEquationRow\">\r\n"
				+ "<td class=\"MatrixEquationColumn\">";
		ans += "REF:</td><td class=\"MatrixEquationColumn\">" + matrices.get(id).toHTMLString()
				+ "</td><td class=\"MatrixEquationColumn\"> = </td>\r\n" + "<td class=\"MatrixEquationColumn\">"
				+ m.toHTMLString() + "</td>\r\n" + "</tr>\r\n" + "</table>";
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
		} catch(MatrixException e) {
			return e.getMessage();
		} catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		matrices.add(m);
		m.setId(this.lastId);
		this.lastId++;
		ans += "<table class=\"matrixEquation\">\r\n" + "<tr class=\"MatrixEquationRow\">\r\n"
				+ "<td class=\"MatrixEquationColumn\">";
		ans += "inverse:</td><td class=\"MatrixEquationColumn\">" + matrices.get(id).toHTMLString()
				+ "</td><td class=\"MatrixEquationColumn\"> = </td>\r\n" + "<td class=\"MatrixEquationColumn\">"
				+ m.toHTMLString() + "</td>\r\n" + "</tr>\r\n" + "</table>";
		return ans;
	}
}
