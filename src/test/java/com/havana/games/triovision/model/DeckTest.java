package com.havana.games.triovision.model;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class DeckTest {

	private Deck deck;

	@Before
	public void setUp() throws Exception {
		deck = new Deck();
	}
	
	@Test
	public void testGetNextCardReturnsNotNullCard() {
		int size = deck.size();
		
		for (int i = 0; i < size; i++) {
			Card c = deck.getNextCard();
			assertThat(c, is(notNullValue()));			
		}
	}
	
	@Test
	public void testNewDeckHasSixtyCards() {
		assertThat(deck.size(), is(60));
	}
	
	@Test
	public void testGettingACardFromDeckReducesSize() {
		int initialSize = deck.size();
		
		deck.getNextCard();
		assertThat(deck.size(), is(initialSize - 1));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGettingCardFromEmptyDeckThrowsException() {
		int size = deck.size();
		
		for (int i = 0; i < size; i++) {
			deck.getNextCard();
		}
		
		assertTrue(deck.isEmpty());
		deck.getNextCard();
	}
	
	@Test
	public void testDeckHasUniqueCards() {
		int size = deck.size();
		
		Set<Card> cards = Stream.generate(deck::getNextCard)
								.limit(size)
								.collect(Collectors.toSet());
		
		assertTrue(deck.isEmpty());
		assertThat(cards.size(), is(size));
	}

}
