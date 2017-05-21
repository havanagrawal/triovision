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
	
	public Card(Pawn[][] cardInfo) {
		this.cardInfo = getCardInfoCopy(cardInfo);
		validate();
	}

	private void validate() {
		
		boolean hasNulls = getNoOfNulls() != 0;
		if (hasNulls) {
			throw new InvalidCardException("There are null values on the Card. This should never happen!");					
		}
		
		int noOfEmptyTiles = getNoOfEmptyTiles(); 
		if (noOfEmptyTiles != 3) {
			throw new InvalidCardException("Expected 3 empty tiles on the card, found " + noOfEmptyTiles);
		}
		
		if (!topMiddleBottomAreSetCorrectly()) {
			throw new InvalidCardException("Only one value can be set on the same horizontal line on a card.");
		}
	}
	
	private Pawn[][] getCardInfoCopy(Pawn[][] cardInfo) {
		Pawn cardInfoCopy[][] = new Pawn[3][2];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				cardInfoCopy[i][j] = cardInfo[i][j];
			}
		}
		
		return cardInfoCopy;
	}
	
	public Pawn[][] getCardInfo() {
		return getCardInfoCopy(cardInfo);
	}
	
	public static class CardBuilder {

		private Pawn[][] cardInfo = new Pawn[3][2];		
		
		public CardBuilder() {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 2; j++) {
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
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				if (cardInfo[i][j] == pawn) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		Card that = (Card)obj;
		
		return Arrays.deepEquals(this.cardInfo, that.cardInfo);
	}
}
