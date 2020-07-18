package nodatabase.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Encryption;

@RestController
public class EncryptionEndpoint {
	@GetMapping("/Encrypt/{s}")
	public String getEncryptedString(@PathVariable String s) {
		Encryption e = new Encryption(s);
		return e.getOutput();
	}
}
