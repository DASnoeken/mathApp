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
		Complex com = new Complex(real,imag);
		numbers.add(com);
		return "$$"+com.toString()+"$$";
	}
	
	@GetMapping("/Complex/showAllNumbers")
	public String showAllNumbers() {
		if(this.numbers.size()==0) {
			return "None found!";
		}
		String ans = new String();int id=0;
		for(Complex c:numbers) {
			ans+="ID: "+id+" $$"+c.toString()+"$$<br>";
			id++;
		}
		return ans;
	}
	
	@GetMapping("/Complex/deleteById/{id}")
	public String deleteById(@PathVariable int id) {
		try {
			this.numbers.remove(id);
		}catch(IndexOutOfBoundsException e) {
			return "Could not find ID!";
		}
		return "Deleted number with id: "+id;
	}
	
	@DeleteMapping("/Complex/clearAll")
	public void clearAll() {
		this.numbers.clear();
	}
}
