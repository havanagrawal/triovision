package com.havana.games.triovision.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.havana.games.triovision.model.Game;
import com.havana.games.triovision.model.Player;

@Service
public class LobbyListener {
	private static Logger log = LoggerFactory.getLogger(LobbyListener.class);
	
	private Lobby lobby;
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	public LobbyListener(Lobby lobby, SimpMessagingTemplate simpMessagingTemplate) {
		this.lobby = lobby;
		this.messagingTemplate = simpMessagingTemplate;
		log.info("Starting up the lobby listener");
	}
	
	@Scheduled(initialDelay = 30_000, fixedDelay = 120_000)
	public void tryToMakeGame() {
		try {
			Game newGame = lobby.tryNewGame();
			startUpGame(newGame);
		}
		catch (IllegalStateException ise) {
			log.info(ise.getMessage());
		}
	}
	
	private void startUpGame(Game game) {
		
		List<Player> players = game.getPlayers();
		
		for (Player p : players) {
			messagingTemplate.convertAndSend("topic/" + p.getPlayerId(), game);
		}
	}
}
