package com.havana.games.triovision.model;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.havana.games.triovision.exceptions.InvalidCardException;

public class CardTest {
	
	@Test
	public void testTwoSameCardsAreEqualInValue() {
		Card card1 = Card.builder().topLeft(Pawn.RED).middleLeft(Pawn.RED).bottomLeft(Pawn.BLUE).build();
		Card card2 = Card.builder().topLeft(Pawn.RED).middleLeft(Pawn.RED).bottomLeft(Pawn.BLUE).build();
		
		assertThat(card1, is(equalTo(card2)));
	}
	
	@Test
	public void testTwoDifferentCardsAreNotEqualInValue() {
		Card card1 = Card.builder().topLeft(Pawn.RED).middleLeft(Pawn.RED).bottomLeft(Pawn.BLUE).build();
		Card card2 = Card.builder().topLeft(Pawn.RED).middleLeft(Pawn.RED).bottomLeft(Pawn.GREEN).build();
		
		assertThat(card1, is(not(equalTo(card2))));
	}
	
	@Test(expected = InvalidCardException.class)
	public void testInvalidCardWithTooManyPawnsThrowsException() {
		Card.builder().topLeft(Pawn.RED).topRight(Pawn.GREEN).middleLeft(Pawn.RED).bottomLeft(Pawn.BLUE).build();
	}
	
	@Test(expected = InvalidCardException.class)
	public void testInvalidCardWithTooLessPawnsThrowsException() {
		Card.builder().topLeft(Pawn.RED).bottomRight(Pawn.GREEN).build();
	}

}
