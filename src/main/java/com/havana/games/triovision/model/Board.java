package com.havana.games.triovision.model;

import com.havana.games.triovision.exceptions.InvalidMoveException;

public class Board {

	private Pawn[][] boardRepresentation;
	
	public Board() {
		boardRepresentation = new Pawn[4][4];
		initialize();
	}
	
	private void initialize() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				boardRepresentation[i][j] = Pawn.EMPTY;
			}
		}
		
		// top row
		boardRepresentation[0][1] = boardRepresentation[0][2] = Pawn.BLUE;
		
		// middle rows, left most column
		boardRepresentation[1][0] = boardRepresentation[2][0] = Pawn.YELLOW;
		
		// middle rows, right most column
		boardRepresentation[1][3] = boardRepresentation[2][3] = Pawn.RED;
		
		// bottom row
		boardRepresentation[3][1] = boardRepresentation[3][2] = Pawn.GREEN;
	}
	
	public Pawn get(int i, int j) {
		return boardRepresentation[i][j];
	}

	public Board swap(int x1, int y1, int x2, int y2) {
		
		Pawn tile1 = boardRepresentation[x1][y1];
		Pawn tile2 = boardRepresentation[x2][y2];
		
		if (tile1 != Pawn.EMPTY && tile2 != Pawn.EMPTY) {
			throw new InvalidMoveException("You can only swap a pawn with an empty tile on the board.");
		}
		
		if (tile1 == Pawn.EMPTY && tile2 == Pawn.EMPTY) {
			throw new InvalidMoveException("You can not swap two empty tiles. You can, however, choose not to make a move.");
		}
		
		return new Board();
	}

}
