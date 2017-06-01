package com.havana.games.triovision.model;

import java.util.Arrays;

import com.havana.games.triovision.exceptions.InvalidCardException;

public class Card {
	
	private Pawn[][] cardInfo;
	
	private static final int TOP = 0;
	private static final int MIDDLE = 1;
	private static final int BOTTOM = 2;
	
	private static final int LEFT = 0;
	private static final int RIGHT = 1;

	private final static int CARD_NUM_OF_ROWS = 3;
	private final static int CARD_NUM_OF_COLS = 2;
	
	public Card(Pawn[][] cardInfo) {
		this.cardInfo = getCardInfoCopy(cardInfo);
		validate();
	}

	private void validate() {
		
		final int expectedNumberOfEmptyTiles = 3;
		boolean hasNulls = getNoOfNulls() != 0;
		if (hasNulls) {
			throw new InvalidCardException("There are null values on the Card. This should never happen!");					
		}
		
		int noOfEmptyTiles = getNoOfEmptyTiles(); 
		if (noOfEmptyTiles != expectedNumberOfEmptyTiles) {
			throw new InvalidCardException("Expected " + expectedNumberOfEmptyTiles + " empty tiles on the card, found " + noOfEmptyTiles);
		}
		
		if (!topMiddleBottomAreSetCorrectly()) {
			throw new InvalidCardException("Only one value can be set on the same horizontal line on a card.");
		}
	}
	
	private Pawn[][] getCardInfoCopy(Pawn[][] cardInfo) {
		Pawn cardInfoCopy[][] = new Pawn[CARD_NUM_OF_ROWS][CARD_NUM_OF_COLS];
		
		for (int i = 0; i < CARD_NUM_OF_ROWS; i++) {
			for (int j = 0; j < CARD_NUM_OF_COLS; j++) {
				cardInfoCopy[i][j] = cardInfo[i][j];
			}
		}
		
		return cardInfoCopy;
	}
	
	public Pawn[][] getCardInfo() {
		return getCardInfoCopy(cardInfo);
	}
	
	public static class CardBuilder {

		private Pawn[][] cardInfo = new Pawn[CARD_NUM_OF_ROWS][CARD_NUM_OF_COLS];		
		
		public CardBuilder() {
			for (int i = 0; i < CARD_NUM_OF_ROWS; i++) {
				for (int j = 0; j < CARD_NUM_OF_COLS; j++) {
					cardInfo[i][j] = Pawn.EMPTY;
				}
			}
		}
		
		public CardBuilder topLeft(Pawn pawn) {
			cardInfo[TOP][LEFT] = pawn;
			return this;
		}
		
		public CardBuilder topRight(Pawn pawn) {
			cardInfo[TOP][RIGHT] = pawn;
			return this;
		}
		
		public CardBuilder middleLeft(Pawn pawn) {
			cardInfo[MIDDLE][LEFT] = pawn;
			return this;
		}

		public CardBuilder middleRight(Pawn pawn) {
			cardInfo[MIDDLE][RIGHT] = pawn;
			return this;
		}
		
		public CardBuilder bottomLeft(Pawn pawn) {
			cardInfo[BOTTOM][LEFT] = pawn;
			return this;
		}
		
		public CardBuilder bottomRight(Pawn pawn) {
			cardInfo[BOTTOM][RIGHT] = pawn;
			return this;
		}
		
		public Card build() {		
			Card card = new Card(cardInfo);
			
			return card;
		}
	}

	public static CardBuilder builder() {
		return new CardBuilder();
	}	
	
	private boolean topMiddleBottomAreSetCorrectly() {
		boolean hasSingleTop = cardInfo[TOP][LEFT] == Pawn.EMPTY || cardInfo[TOP][RIGHT] == Pawn.EMPTY;
		boolean hasSingleMiddle = cardInfo[MIDDLE][LEFT] == Pawn.EMPTY || cardInfo[MIDDLE][RIGHT] == Pawn.EMPTY;
		boolean hasSingleBottom = cardInfo[BOTTOM][LEFT] == Pawn.EMPTY || cardInfo[BOTTOM][RIGHT] == Pawn.EMPTY;
		
		boolean topMiddleBottomValid = hasSingleTop && hasSingleMiddle && hasSingleBottom; 
		
		return topMiddleBottomValid;
	}
	
	private int getNoOfNulls() {
		return countInstancesOf(null);
	}
	
	private int getNoOfEmptyTiles() {
		return countInstancesOf(Pawn.EMPTY);
	}
	
	private int countInstancesOf(Pawn pawn) {
		int count = 0;
		
		for (int i = 0; i < CARD_NUM_OF_ROWS; i++) {
			for (int j = 0; j < CARD_NUM_OF_COLS; j++) {
				if (cardInfo[i][j] == pawn) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			Card that = (Card)obj;
		
			return Arrays.deepEquals(this.cardInfo, that.cardInfo);
		}
		
		return false;
	}
}
