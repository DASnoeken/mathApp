package nodatabase.test.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Game;

@RestController
public class GameEndpoint {
	Game theGame;
	boolean answer;
	@GetMapping("/game/getpolynomial/{degree}")
	public ArrayList<String> getPolynomial(@PathVariable int degree) {
		Game g = new Game(degree);
		g.generateCoefficients();
		g.setPoints();
		this.theGame = g;
		return g.getPoints();
	}
	
	@PostMapping("/game/submitAnswer")
	public void submitAnswer(@RequestBody String[] as) {
		this.answer = true;
		for(int i =0;i<as.length;i++) {
			if(Integer.parseInt(as[i]) != theGame.getCoefficients().get(i)) {
				this.answer = false;
			}
		}
	}
	
	@GetMapping("/game/checkAnswer")
	public String checkAnswer() {
		if(answer) {
			return "CORRECT!";
		}else {
			return "YOU MADE A MISTAKE!";
		}
	}
}

