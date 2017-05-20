package com.havana.games.triovision.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private int noOfPlayers;
	private Board board;

	List<Player> players;
	
	public Game(int noOfPlayers) {
		if (noOfPlayers < 2) {
			throw new IllegalArgumentException("There must be at least 2 players for a TrioVision Game");
		}
		
		this.noOfPlayers = noOfPlayers;
		
		players = new ArrayList<>();
		
		for (int i = 0; i < noOfPlayers; i++) {
			players.add(new Player());
		}
		
		board = new Board();
	}

	public Player getPlayer(int i) {
		
		if (i > noOfPlayers || i <= 0) {
			throw new IllegalArgumentException("Valid indices for players for this game are 1 to " + noOfPlayers);
		}
		
		return players.get(i - 1);
	}

	public Board getBoard() {
		return board;
	}

}
