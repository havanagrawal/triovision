package com.havana.games.triovision.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private int noOfPlayers;
	private Board board;

	List<Player> players;
	private Deck deck;

	List<Card> openCards;
	private boolean started = false;
	private boolean ended = false;
	
	public Game(int noOfPlayers) {
		if (noOfPlayers < 2) {
			throw new IllegalArgumentException("There must be at least 2 players for a TrioVision Game");
		}

		this.noOfPlayers = noOfPlayers;
		this.initializePlayers();
		
		board = new Board();
		deck = new Deck();
		openCards = new ArrayList<>();
	}

	private void initializePlayers() {
		players = new ArrayList<>();

		for (int i = 0; i < noOfPlayers; i++) {
			players.add(new Player());
		}
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

	public Deck getDeck() {
		return deck;
	}

	public List<Card> getOpenCards() {
		return new ArrayList<>(openCards);
	}

	public void start() {
		if (!started) {
			for (int i = 0; i < 12; i++) {
				openCards.add(deck.getNextCard());
			}
			started = true;
		}
	}

	public boolean hasEnded() {
		return ended;
	}
	
	public boolean makeMoveForPlayer(int i, Card card, Board newBoard) {	
		
		if (ended) {
			return false;
		}
		
		if (!newBoard.matches(card)) {
			return false;
		}
		
		Player player = getPlayer(i);
		player.addWonCard(card);
		openCards.remove(card);
		
		if (!deck.isEmpty()) {
			openCards.add(deck.getNextCard());
		}
		else if (openCards.isEmpty()) {
			ended = true;
		}
		
		return true;
	}
	
}
