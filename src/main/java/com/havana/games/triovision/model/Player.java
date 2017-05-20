package com.havana.games.triovision.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private List<Card> wonCards;
	
	public Player() {
		wonCards = new ArrayList<>();
	}
	
	public List<Card> getWonCards() {
		// TODO Auto-generated method stub
		return wonCards;
	}

	public void addWonCard(Card card) {
		wonCards.add(card);
	}

}
