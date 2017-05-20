package com.havana.games.triovision.model;

public class Card {
	
	private Pawn[][] cardInfo;
	
	private Card() {
		
	}
	
	private Card(Pawn[][] cardInfo) {
		this.cardInfo = getCardInfoCopy(cardInfo);
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
	
	public static class CardBuilder {

		private Pawn[][] cardInfo = new Pawn[3][2];
		
		private static final int TOP = 0;
		private static final int MIDDLE = 1;
		private static final int BOTTOM = 2;
		
		private static final int LEFT = 0;
		private static final int RIGHT = 1;
		
		
		public CardBuilder() {
			
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
			return new Card(cardInfo);
		}
	}

	public static CardBuilder builder() {
		return new CardBuilder();
	}	
}
