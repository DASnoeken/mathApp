package app.math.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.math.domain.Help;

@RestController
public class HelpEndpoint {
	@GetMapping("/help/Encryption")
	public String getEncryptionHelp() {
		Help h = new Help();
		return h.getEncryptionHelp();
	}
}
