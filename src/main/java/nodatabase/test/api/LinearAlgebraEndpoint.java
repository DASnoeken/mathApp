package nodatabase.test.api;

import java.util.ArrayList;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Matrix;
import nodatabase.test.domain.MatrixDimensionException;

@RestController
public class LinearAlgebraEndpoint {
	private ArrayList<Matrix> matrices;
	private String errormessage;

	public LinearAlgebraEndpoint(){
		this.matrices = new ArrayList<Matrix>();
	}
	
	@GetMapping("/LinAlg/getMatrix/{id}")
	public Matrix getMatrix(@PathVariable int id) {
		try {
			return matrices.get(id);
		}catch(IndexOutOfBoundsException ioobe) {
			return null;
		}
	}
	
	@PostMapping("/LinAlg/makeMatrix")
	public void addMatrix(@RequestBody String s) {	//Matrix syntax: 1,2,3;4,5,6;7,8,9
		s=s.replaceAll(" ", "");
		String[] rows = s.split(";");
		int numberOfColumns = StringUtils.countOccurrencesOf(rows[0], ",") + 1;
		int numberOfRows = rows.length;
		try {	
			for(String row:rows) {
				if(StringUtils.countOccurrencesOf(row, ",")!=numberOfColumns) {
					throw new MatrixDimensionException("Number of columns not consistent for every row!");
				}
			}
		}catch(MatrixDimensionException mde) {
			this.errormessage = "ERROR: " + mde.getMessage();
		}catch(Exception e) {
			this.errormessage = "ERROR: " + e.getMessage();
			e.printStackTrace();
		}
		Matrix m = new Matrix(numberOfRows,numberOfColumns);
		m.stringToMatrix(s);
		if(m.getErrormessage().equals("None")) {
			matrices.add(m);
		}else {
			this.errormessage = m.getErrormessage();
		}
	}
	
	@GetMapping("/LinAlg/getErrorMessage")
	public String getErrorMessage() {
		return this.errormessage;
	}
}
