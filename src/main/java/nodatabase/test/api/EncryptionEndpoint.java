package nodatabase.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Encryption;

@RestController
public class EncryptionEndpoint {
	@GetMapping("/Encrypt/{s}/{order}")
	public String getEncryptedString(@PathVariable String s, @PathVariable String order) {
		int orderI = Integer.parseInt(order);
		Encryption e = new Encryption(s,orderI);
		return e.getOutput();
	}
}
