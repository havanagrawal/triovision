package com.havana.games.triovision.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.havana.games.triovision.model.Game;

@Component
public class GameRepository {
	private Map<UUID, Game> gameMap = new HashMap<>();
	
	public void add(Game game) {
		gameMap.put(game.getGameId(), game);
	}
	
	public Game get(UUID gameId) {
		return gameMap.get(gameId);
	}
}
