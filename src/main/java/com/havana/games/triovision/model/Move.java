package com.havana.games.triovision.model;

public class Move {
	private final int fromX;
	private final int fromY;
	private final int toX;
	private final int toY;
	
	private final Card card;
	private final Player player;
	
	public Move(int fromX, int fromY, int toX, int toY, Card card, Player player) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.card = card;
		this.player = player;
	}

	public int fromX() {
		return fromX;
	}

	public int fromY() {
		return fromY;
	}

	public int toX() {
		return toX;
	}

	public int toY() {
		return toY;
	}

	public Card getCard() {
		return card;
	}

	public Player getPlayer() {
		return player;
	}
	
}
