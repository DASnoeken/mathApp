package app.math.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.math.domain.Complex;

@RestController
public class ComplexEndpoint {
	private ArrayList<Complex> numbers;

	public ComplexEndpoint() {
		numbers = new ArrayList<Complex>();
	}

	@GetMapping("/Complex/createComplex/")
	public String createComplex(@RequestParam double real, @RequestParam double imag) {
		Complex com = new Complex(real, imag);
		numbers.add(com);
		return "$$" + com.toString() + "$$";
	}

	@GetMapping("/Complex/showAllNumbers")
	public String showAllNumbers() {
		if (this.numbers.size() == 0) {
			return "None found!";
		}
		String ans = new String();
		int id = 0;
		for (Complex c : numbers) {
			ans += "ID: " + id + " $$" + c.toString() + "$$<br>";
			id++;
		}
		return ans;
	}

	@GetMapping("/Complex/deleteById/{id}")
	public String deleteById(@PathVariable int id) {
		try {
			this.numbers.remove(id);
		} catch (IndexOutOfBoundsException e) {
			return "Could not find ID!";
		}
		return "Deleted number with id: " + id;
	}

	@DeleteMapping("/Complex/clearAll")
	public void clearAll() {
		this.numbers.clear();
	}

	@GetMapping("/Complex/Op/Add/{id1}/{id2}")
	public String add(@PathVariable int id1, @PathVariable int id2) {
		if (id1 < 0 || id2 < 0) {
			return "ID's cannot be negative!";
		} else if (id1 > this.numbers.size() || id2 > this.numbers.size()) {
			return "ID not found!";
		}
		Complex c = numbers.get(id1).add(numbers.get(id2));
		numbers.add(c);
		return "$$" + c.toString() + "$$";
	}

	@GetMapping("/Complex/toPolar/{id}")
	public String toPolar(@PathVariable int id) {
		if (id < 0) {
			return "ID's cannot be negative!";
		} else if (id > this.numbers.size()) {
			return "ID not found!";
		}
		return "$$" + numbers.get(id) + "=" + numbers.get(id).toPolarString() + "$$";
	}
	
	@GetMapping("/Complex/Op/Conj/{id}")
	public String getConj(@PathVariable int id) {
		Complex c = numbers.get(id).conj();
		numbers.add(c);
		return c.toString();
	}
	
	@GetMapping("/Complex/Op/Multiply/")
	public String multiply(@RequestParam int id1, @RequestParam String other) {
		if(other.contains("i")) {
			Complex c;
			try {
				c = new Complex(other);
			}catch(Exception e) {
				return e.getMessage();
			}
			Complex ans=numbers.get(id1).multiply(c);
			numbers.add(ans);
			return "$$("+numbers.get(id1).toString()+")("+c.toString()+")="+ans.toString()+"$$";
		}else {
			if(other.contains(".")) {
				Double scalar = Double.parseDouble(other);
				Complex c = this.numbers.get(id1).multiply(scalar);
				this.numbers.add(c);
				return "$$"+scalar+"("+numbers.get(id1).toString()+")="+c.toString()+"$$";
			}else {
				Long scalar = Long.parseLong(other);
				Complex c = this.numbers.get(id1).multiply(scalar);
				this.numbers.add(c);
				return "$$"+scalar+"("+numbers.get(id1).toString()+")="+c.toString()+"$$";
			}
		}
	}
	
	//@GetMapping("/Complex/")
}
