package com.havana.games.triovision.notifier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.havana.games.triovision.model.Game;
import com.havana.games.triovision.model.Player;

@Component
public class WebSocketPlayerNotifier implements PlayerNotifier {
	
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	public WebSocketPlayerNotifier(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}
	
	@Override
	public void notifyPlayers(Game game) {
		List<Player> players = game.getPlayers();
		
		for (Player p : players) {
			messagingTemplate.convertAndSend("/topic/player-" + p.getPlayerId(), game);
		}
	}
	
}
