package com.havana.games.triovision.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.havana.games.triovision.exceptions.InvalidPlayerException;

public class Game {

	private UUID gameId;
	
	private final int noOfPlayers;
	private Board board;

	List<Player> players;
	private Deck deck;

	List<Card> openCards;
	private boolean started = false;
	private boolean ended = false;
	
	public static final int MIN_PLAYERS_IN_SINGLE_GAME = 2;
	public static final int MAX_PLAYERS_IN_SINGLE_GAME = 4;
	
	public Game(int noOfPlayers) {	
		this(new Board(), new Deck(), new ArrayList<>(), getNPlayers(noOfPlayers));
	}
	
	public Game(Board board, Deck deck, List<Card> openCards, List<Player> players) {
		this.board = board;
		this.deck = deck;
		this.openCards = new ArrayList<>(openCards);
		this.players = new ArrayList<>(players);
		this.noOfPlayers = players.size();
		gameId = UUID.randomUUID();
		
		if (noOfPlayers < MIN_PLAYERS_IN_SINGLE_GAME) {
			throw new IllegalArgumentException("There must be at least 2 players for a TrioVision Game");
		}
		
		if (noOfPlayers > MAX_PLAYERS_IN_SINGLE_GAME) {
			throw new IllegalArgumentException("There may be at most 4 players for a TrioVision Game");
		}
	}

	private static List<Player> getNPlayers(int noOfPlayers) {
		List<Player> players = new ArrayList<>();

		for (int i = 0; i < noOfPlayers; i++) {
			players.add(new Player());
		}
		
		return players;
	}
	
	public List<Player> getPlayers() {
		return new ArrayList<>(players);
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

	public UUID getGameId() {
		return gameId;
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
	
	public boolean makeMoveForPlayer(Move move) {	
		
		if (ended) {
			return false;
		}
		
		Board newBoard = board.swap(move.fromX(), move.fromY(), move.toX(), move.toY());
		Card card = move.getCard();
		Player player = move.getPlayer();
		
		if (!isPlayerPartOfThisGame(player)) {
			throw new InvalidPlayerException("Player " + player + " does not seem to be a valid player in this game!");
		}
		
		if (!newBoard.matches(card)) {
			return false;
		}
		
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
	
	private boolean isPlayerPartOfThisGame(Player player) {
		return players.contains(player);
	}

	@Override
	public String toString() {
		return "Game [board=" + board + ", players=" + players + ", openCards=" + openCards + "]";
	}
	
}
