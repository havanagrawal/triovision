package com.havana.games.triovision.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import com.havana.games.triovision.exceptions.InvalidPlayerException;

public class GameMoveTest {
	
	private Game game;

	private Board board;
	private Deck deck;
	private List<Card> openCards;
	private List<Player> players;
	
	@Before
	public void setup() {
		board = new Board();
		deck = new Deck();
		
		openCards = new ArrayList<>();
		
		Player p1 = new Player();
		Player p2 = new Player();
		
		players = Collections.unmodifiableList(Lists.newArrayList(p1, p2));
		
		game = new Game(board, deck, openCards, players);
	}
	
	@Test(expected = InvalidPlayerException.class)
	public void testOutsidePlayerCannotMakeMove() {
		
		Card card = Card.builder()
				.topRight(Pawn.BLUE)
				.middleRight(Pawn.YELLOW)
				.bottomLeft(Pawn.YELLOW)
				.build();
		
		Player player = new Player();
		
		// Moving the top left yellow pawn one square to the right makes the card match
		Move move = new Move(1, 0, 1, 1, card, player);
		
		game.makeMoveForPlayer(move);
	}
	
	@Test
	public void testMakeCorrectMoveOnBehalfOfPlayer() {
		
		Card card = Card.builder()
				.topRight(Pawn.BLUE)
				.middleRight(Pawn.YELLOW)
				.bottomLeft(Pawn.YELLOW)
				.build();
		
		Player player = players.get(1);
		
		// Moving the top left yellow pawn one square to the right makes the card match
		Move move = new Move(1, 0, 1, 1, card, player);
		
		boolean moveMade = game.makeMoveForPlayer(move);
		
		assertThat(moveMade, is(true));
	}
	
	@Test
	public void testMakeInvalidMoveOnBehalfOfPlayer() {

		Card card = Card.builder()
				.topRight(Pawn.BLUE)
				.middleRight(Pawn.YELLOW)
				.bottomLeft(Pawn.YELLOW)
				.build();
		
		Player player = players.get(1);
		
		// Moving the top left blue pawn one square below does not make the card match
		Move move = new Move(0, 1, 1, 1, card, player);
		
		boolean moveMade = game.makeMoveForPlayer(move);
		
		assertThat(moveMade, is(false));
	}
	
	@Test
	public void testCorrectMoveOnBehalfOfPlayerAddsCardToPlayerPile() {

		Card card = Card.builder()
				.topRight(Pawn.BLUE)
				.middleRight(Pawn.YELLOW)
				.bottomLeft(Pawn.YELLOW)
				.build();
		
		Player player = players.get(1);
		
		// Moving the top left yellow pawn one square to the right makes the card match
		Move move = new Move(1, 0, 1, 1, card, player);
		
		assertThat(player.getWonCards().size(), is(0));
		
		boolean moveMade = game.makeMoveForPlayer(move);
		
		assertThat(moveMade, is(true));
		assertThat(player.getWonCards().size(), is(1));
	}

}
