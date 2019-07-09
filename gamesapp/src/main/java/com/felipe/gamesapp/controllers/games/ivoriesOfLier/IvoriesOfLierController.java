package com.felipe.gamesapp.controllers.games.ivoriesOfLier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGame;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierMove;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierMove.DecisionType;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierGameService;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierMoveService;

@Controller
@RequestMapping(path="/games/ivoriesoflier")
public class IvoriesOfLierController {
	
	@Autowired
	private IIvoriesOfLierGameService gameService;
	@Autowired
	private IIvoriesOfLierMoveService moveService;

	@PostMapping(path="/start")
	public @ResponseBody String startGame(@RequestParam int roomId) throws Exception {
		// utworzenie gry
		IvoriesOfLierGame game = gameService.startGame(roomId);
		
		return "Game started!";
	}

	@PostMapping(path="/move")
	public @ResponseBody String move(@RequestParam int playerId,
			@RequestParam int gameId,
			@RequestParam DecisionType decisionType,
			@RequestParam int numberOfCubes,
			@RequestParam int cubeValue) throws Exception {
		
		IvoriesOfLierMove move = new IvoriesOfLierMove();
		move.setDecisionType(decisionType);
		move.setDecisionCubeValue(cubeValue);
		move.setDecisionNumberOfCubes(numberOfCubes);
		
		String result = moveService.makeMove(playerId, gameId, move);
		
		return result;
	}
}
