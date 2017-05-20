package com.havana.games.triovision.model;

public enum Pawn {
	EMPTY, RED, BLUE, GREEN, YELLOW;
	
	public static Pawn valueOfByAlias(char c) {
		switch(c) {
			case 'R':
			case 'r': return RED;
			case 'B':
			case 'b': return BLUE;
			case 'G':
			case 'g': return GREEN;
			case 'Y':
			case 'y': return YELLOW;
			default: return EMPTY; 
		}
	}
}
