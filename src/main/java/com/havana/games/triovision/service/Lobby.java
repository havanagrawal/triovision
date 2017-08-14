package com.havana.games.triovision.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.havana.games.triovision.model.Game;
import com.havana.games.triovision.model.Player;

@Component
public class Lobby {
	
	Map<UUID, Player> players = new HashMap<>();
	
	public Player makePlayerAndAddToLobby() {
		Player p = new Player();
		players.put(p.getPlayerId(), p);
		
		return p;
	}
	
	public Game tryNewGame() {
		List<Player> playersForNewGame = players.values()
												.stream()
												.limit(Game.MAX_PLAYERS_IN_SINGLE_GAME)
												.collect(Collectors.toList());
		
		if (playersForNewGame.size() >= Game.MIN_PLAYERS_IN_SINGLE_GAME) {
			removePlayersFromLobby(playersForNewGame);
			return GameFactory.newGame(playersForNewGame);
		}
		
		throw new IllegalStateException("There aren't enough players to start a new game!");
	}
	
	private void removePlayersFromLobby(List<Player> playersToRemove) {
		playersToRemove.forEach(player -> players.remove(player.getPlayerId()));
	}
	
	public List<Player> players() {
		return new ArrayList<>(players.values());
	}
	
}
