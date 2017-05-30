package com.havana.games.triovision.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {

	private List<Card> wonCards;
	private UUID playerId;
	
	public Player() {
		wonCards = new ArrayList<>();
		playerId = UUID.randomUUID();
	}
	
	public List<Card> getWonCards() {
		return wonCards;
	}

	public void addWonCard(Card card) {
		wonCards.add(card);
	}

	public UUID getPlayerId() {
		return playerId;
	}

}
