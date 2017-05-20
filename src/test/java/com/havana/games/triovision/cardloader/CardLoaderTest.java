package com.havana.games.triovision.cardloader;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.havana.games.triovision.model.Card;
import com.havana.games.triovision.model.Pawn; 


public class CardLoaderTest {
	
	@Test(expected = MalformedCardException.class)
	public void testCardLoadedFromEmptyStringThrowsException() {
		CardLoader.fromString("");
	}
	
	@Test(expected = MalformedCardException.class)
	public void testCardLoadedFromAlmostCorrectStringThrowsException() {
		CardLoader.fromString("B, R -, - B");
	}
	
	@Test
	public void testCardLoadedfromValidStringIsNotNull() {
		Card card = CardLoader.fromString("- B, Y -, Y -");
		
		assertThat(card, is(notNullValue()));
	}
	
	@Test
	public void testCardLoadedfromValidStringIsCorrect() {
		Card card = CardLoader.fromString("- B, Y -, Y -");
		
		assertThat(card, is(notNullValue()));
		
		Pawn[][] cardInfo = card.getCardInfo();
		Pawn[][] expected = {
					{Pawn.EMPTY, Pawn.BLUE}, 
					{Pawn.YELLOW, Pawn.EMPTY},
					{Pawn.YELLOW, Pawn.EMPTY}};
		
		assertTrue(Arrays.deepEquals(cardInfo, expected));
	}

}
