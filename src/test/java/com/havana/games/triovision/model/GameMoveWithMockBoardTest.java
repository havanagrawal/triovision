package com.havana.games.triovision.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

public class GameMoveWithMockBoardTest {
	
	private Game game;

	private Board board;
	private Deck deck;
	private List<Card> openCards;
	private List<Player> players;
	
	@Before
	public void setup() {
		board = mock(Board.class);
		deck = new Deck();
		
		openCards = new ArrayList<>();
		
		Player p1 = new Player();
		Player p2 = new Player();
		
		players = Collections.unmodifiableList(Lists.newArrayList(p1, p2));
		
		game = new Game(board, deck, openCards, players);
	}
	
	@Test
	public void testCorrectMoveOnBehalfOfPlayerRemovesCardFromOpenCards() {
		game.start();
		Card card = game.getOpenCards().get(0);
		
		Board moveMadeBoard = mock(Board.class);
		
		when(board.swap(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(moveMadeBoard);
		
		// Ensure move is successful
		when(moveMadeBoard.matches(card)).thenReturn(true);
		
		Player p1 = players.get(0);
		
		Move move = new Move(0, 0, 1, 1, card, p1);
		
		boolean moveMade = game.makeMoveForPlayer(move);
		
		assertThat(moveMade, is(true));
		assertFalse(game.getOpenCards().contains(card));
	}
	
	
	@Test
	public void testInvalidMoveOnBehalfOfPlayerRetainsCardInOpenCards() {
		game.start();
		Card card = game.getOpenCards().get(0);
		
		Board moveMadeBoard = mock(Board.class);
		
		when(board.swap(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(moveMadeBoard);
		
		// Ensure move is unsuccessful
		when(moveMadeBoard.matches(card)).thenReturn(false);
		
		Player p1 = players.get(0);
		
		Move move = new Move(0, 0, 1, 1, card, p1);
		
		boolean moveMade = game.makeMoveForPlayer(move);
		
		assertThat(moveMade, is(false));
		assertTrue(game.getOpenCards().contains(card));
	}
	
	
	@Test
	public void testOpenCardIsReplacedFromDeckAfterOneCorrectMove() {
		game.start();
		Card card = game.getOpenCards().get(0);
		
		Board moveMadeBoard = mock(Board.class);
		
		when(board.swap(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(moveMadeBoard);
		
		// Ensure move is successful
		when(moveMadeBoard.matches(card)).thenReturn(true);
		
		Player p1 = players.get(0);
		Move move = new Move(0, 0, 1, 1, card, p1);
		
		int initialDeckSize = deck.size();
		boolean moveMade = game.makeMoveForPlayer(move);
		int finalDeckSize = deck.size();
		
		assertThat(moveMade, is(true));
		assertThat(game.getOpenCards().size(), is(12));
		assertThat(finalDeckSize, is(initialDeckSize - 1));
	}
	
	
	@Test
	public void testGameIsOverAfterLastCardIsTakenFromDeck() {
		game.start();
		
		Board moveMadeBoard = mock(Board.class);
		
		when(board.swap(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(moveMadeBoard);
		
		// Ensure all moves are successful
		when(moveMadeBoard.matches(any())).thenReturn(true);
		
		Player p1 = players.get(0); 
		
		while (!game.getOpenCards().isEmpty()) {
			Card card = game.getOpenCards().get(0);
			
			Move move = new Move(0, 1, 1, 1, card, p1);
			
			boolean moveMade = game.makeMoveForPlayer(move);
			assertThat(moveMade, is(true));
		}
		
		Deck deck = game.getDeck();
		int finalDeckSize = deck.size();
		
		assertThat(finalDeckSize, is(0));
		assertTrue(game.hasEnded());
	}
	
	
	@Test
	public void testMovesCannotBeMadeAfterGameHasEnded() {
		game.start();
		
		Board moveMadeBoard = mock(Board.class);
		
		when(board.swap(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(moveMadeBoard);
		
		// Ensure all moves are successful
		when(moveMadeBoard.matches(any())).thenReturn(true);
		
		Player p1 = players.get(0); 
		
		while (!game.getOpenCards().isEmpty()) {
			Card card = game.getOpenCards().get(0);
			
			Move move = new Move(0, 1, 1, 1, card, p1);
			
			boolean moveMade = game.makeMoveForPlayer(move);
			assertThat(moveMade, is(true));
		}
		
		Deck deck = game.getDeck();
		int finalDeckSize = deck.size();
		
		assertThat(finalDeckSize, is(0));
		assertTrue(game.hasEnded());
		
		Move move = new Move(0, 1, 1, 1, null, p1);
		
		boolean moveMadeAfterGameOver = game.makeMoveForPlayer(move);
		assertThat(moveMadeAfterGameOver, is(false));
	}
	
}
