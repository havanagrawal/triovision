package com.havana.games.triovision.model;

import java.util.Arrays;

import com.havana.games.triovision.exceptions.InvalidMoveException;

public class Board {

	private static final int BOARD_SIZE = 4; //Based on the fact that the board is square!

	private Pawn[][] boardRepresentation;
	
	public Board() {
		boardRepresentation = new Pawn[BOARD_SIZE][BOARD_SIZE];
		initialize();
	}
	
	private Board(Board boardCopy, int x1, int y1, int x2, int y2) {
		this.boardRepresentation = boardCopy.getBoardCopy();
		
		Pawn temp = boardRepresentation[x1][y1];
		boardRepresentation[x1][y1] = boardRepresentation[x2][y2];
		boardRepresentation[x2][y2] = temp;
	}

	private Pawn[][] getBoardCopy() {
		Pawn[][] boardCopy = new Pawn[BOARD_SIZE][BOARD_SIZE];
		

		for (int i = 0; i < BOARD_SIZE; i++) {
			boardCopy[i] = Arrays.copyOf(boardRepresentation[i], BOARD_SIZE);
		}
		
		return boardCopy;
	}
	
	private void initialize() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
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
		
		return new Board(this, x1, y1, x2, y2);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		Board that = (Board)obj;
		
		return Arrays.deepEquals(this.boardRepresentation, that.boardRepresentation);
	}

	public boolean matches(Card card) {	

		Pawn[][] orientation = this.boardRepresentation;  
		
		for (int noOfClockwiseTurns = 0; noOfClockwiseTurns < 4; noOfClockwiseTurns++) {
			if (matches(orientation, card)) {
				return true;
			}
			orientation = rotateClockwise(orientation);
		}
		
		return false;
	}
	
	private boolean matches(Pawn[][] board, Card card) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				Pawn[][] cardSliceFromBoard = getCardSlice(board, i, j);
				
				boolean matches = cardMatchesCardSlice(card, cardSliceFromBoard);
				 
				if (matches) {
					return true;
				}
			}
		}
		return false;
	}

	private Pawn[][] rotateClockwise(Pawn[][] currentBoard) {
		Pawn[][] rotated = new Pawn[BOARD_SIZE][BOARD_SIZE];
		
		for (int r = 0; r < BOARD_SIZE; r++) {
			for (int c = 0; c < BOARD_SIZE; c++) {
				rotated[c][BOARD_SIZE - 1 - r] = currentBoard[r][c];
			}
		}
		
		return rotated;
	}
	
	private boolean cardMatchesCardSlice(Card card, Pawn[][] cardSliceFromBoard) {
		Pawn[][] cardInfo = card.getCardInfo();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				if (cardInfo[i][j] != cardSliceFromBoard[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}

	private Pawn[][] getCardSlice(Pawn[][] currentBoard, int rowOffset, int columnOffset) {
		Pawn[][] cardSlice = new Pawn[3][2];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				cardSlice[i][j] = currentBoard[i + rowOffset][j + columnOffset];
			}
		}
		
		return cardSlice;
	}
	
	@SuppressWarnings("unused")
	private void printSlice(Pawn[][] cardSlice) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(cardSlice[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	@SuppressWarnings("unused")
	private void printBoard() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(boardRepresentation[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
