package com.havana.games.triovision.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.havana.games.triovision.model.Game;
import com.havana.games.triovision.notifier.PlayerNotifier;

@Service
public class LobbyListener {
	private static Logger log = LoggerFactory.getLogger(LobbyListener.class);
	
	private Lobby lobby;
	private PlayerNotifier playerNotifier;
	
	@Autowired
	public LobbyListener(Lobby lobby, PlayerNotifier playerNotifier) {
		this.lobby = lobby;
		this.playerNotifier = playerNotifier;
		log.info("Starting up the lobby listener");
	}
	
	@Scheduled(initialDelay = 30_000, fixedDelay = 30_000)
	public void tryToMakeGame() {
		log.info("Current lobby: " + lobby);

		try {
			Game newGame = lobby.tryNewGame();
			newGame.start();
			log.info("Created new game: " + newGame);
			playerNotifier.notifyPlayers(newGame);
		}
		catch (IllegalStateException ise) {
			log.info(ise.getMessage());
		}
	}

}
