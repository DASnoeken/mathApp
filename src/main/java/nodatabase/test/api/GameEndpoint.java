package nodatabase.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Game;

@RestController
public class GameEndpoint {
	@GetMapping("/game/getpolynomial/{degree}")
	public String[] getPolynomial(@PathVariable int degree) {
		Game g = new Game(degree);
		g.generateCoefficients();
		g.setPoints();
		return g.getPoints();
	}
}
