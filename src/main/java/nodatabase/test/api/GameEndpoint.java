package nodatabase.test.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Game;

@RestController
public class GameEndpoint {
	@GetMapping("/game/getpolynomial/{degree}")
	public ArrayList<String> getPolynomial(@PathVariable int degree) {
		Game g = new Game(degree);
		g.generateCoefficients();
		g.setPoints();
		return g.getPoints();
	}
}
