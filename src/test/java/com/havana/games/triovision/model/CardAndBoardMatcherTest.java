package com.havana.games.triovision.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CardAndBoardMatcherTest {

	Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@Test
	public void testCardMatchesInitialLeftSideConfiguration() {
		
		Card card = Card.builder().topRight(Pawn.BLUE)
				.middleLeft(Pawn.YELLOW)
				.bottomLeft(Pawn.YELLOW)
				.build();
		
		assertTrue(board.matches(card));
	}
	
	@Test
	public void testCardMatchesInitialRightSideConfiguration() {
		
		Card card = Card.builder()
				.topLeft(Pawn.BLUE)
				.middleRight(Pawn.RED)
				.bottomRight(Pawn.RED)
				.build();
		
		assertTrue(board.matches(card));
	}
	
	@Test
	public void testCardDoesNotMatchInitalConfiguration() {
		Card card = Card.builder()
				.topLeft(Pawn.BLUE)
				.middleRight(Pawn.RED)
				.bottomRight(Pawn.YELLOW)
				.build();
		
		assertFalse(board.matches(card));
	}

}