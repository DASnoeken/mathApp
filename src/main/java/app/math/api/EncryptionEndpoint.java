package app.math.api;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.math.domain.Encryption;
import app.math.domain.Matrix;

@RestController
public class EncryptionEndpoint {
	@GetMapping("/Encrypt/{s}/{order}")
	public String getEncryptedString(@PathVariable String s, @PathVariable String order) {
		int orderI = Integer.parseInt(order);
		if(orderI > 7) {
			String returnString = new String();
			int j = orderI/7;
			if(orderI%7!=0) {
				j++;
			}
			returnString+="Sorry, but something goes wrong for order > 7.<br>"
					+ "Try to cut your input into " + j + " pieces and encrypt these separately.";
			String newInput = new String();
			int lastIndex = s.length()/j;
			int intervalSize = s.length()/j;
			newInput += s.substring(0, lastIndex);
			for(int i=0;i<j-1;i++) {
				newInput+="||"+s.substring(lastIndex, lastIndex+intervalSize);
				lastIndex=lastIndex+intervalSize;
			}
			if(lastIndex!=s.length()) {
				newInput+="||"+s.substring(lastIndex, s.length());
			}
			returnString += "<br>Use: "+newInput;
			String orderString = new String();
			orderString += "With example orders: || ";
			for(int i=0;i<orderI/7;i++) {
				orderString += 7+" || ";
			}
			if(orderI%7!=0) {
				orderString += orderI%7;
			}
			returnString +="<br>"+orderString;
			return returnString;
		}
		Encryption e = new Encryption(s,orderI);
		return e.getOutput();
	}
	
	@GetMapping("/Decrypt/{s}")
	public String getDecryptedNumber(@PathVariable String s) {
		Encryption d = new Encryption(s);
		return d.getOutput();
	}
	
	@GetMapping("/Decrypt/points/{s}")
	public String decryptPoints(@PathVariable String s) {
		String matrixInput;
		try {
			matrixInput = Encryption.decryptPoints(s);
		}catch(Exception e) {
			return e.getMessage();
		}
		Matrix m = new Matrix(matrixInput);
		Matrix rrefM = m.rref();
		BigDecimal ans = rrefM.getElement(1, rrefM.getColumnsCount());
		return "Your number is: "+ans.toString();
	}
}
