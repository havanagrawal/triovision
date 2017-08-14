package com.havana.games.triovision.service;

import java.util.Collections;
import java.util.List;

import com.havana.games.triovision.model.Board;
import com.havana.games.triovision.model.Card;
import com.havana.games.triovision.model.Deck;
import com.havana.games.triovision.model.Game;
import com.havana.games.triovision.model.Player;

public class GameFactory {
	
	private GameFactory() {
		
	}
	
	public static Game newGame(List<Player> players) {
		return new Game(new Board(), new Deck(), Collections.<Card>emptyList(), players);
	}
}
