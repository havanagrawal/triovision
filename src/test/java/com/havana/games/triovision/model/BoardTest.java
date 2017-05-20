package com.havana.games.triovision.model;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.havana.games.triovision.exceptions.InvalidMoveException;

public class BoardTest {

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@Test
	public void testBoardHas8EmptyTiles() {
		int emptyTiles = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board.get(i, j) == Pawn.EMPTY) {
					emptyTiles++;
				}
			}
		}

		assertThat(emptyTiles, is(8));
	}

	@Test
	public void testBoardHasTwoTilesOfEachColor() {
		int noOfYellowTiles = 0;
		int noOfBlueTiles = 0;
		int noOfRedTiles = 0;
		int noOfGreenTiles = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				switch (board.get(i, j)) {
					case BLUE:
						noOfBlueTiles++;
						break;
					case GREEN:
						noOfGreenTiles++;
						break;
					case RED:
						noOfRedTiles++;
						break;
					case YELLOW:
						noOfYellowTiles++;
						break;
					default:
						break;
				}
			}
		}
		
		assertThat(noOfBlueTiles, is(2));
		assertThat(noOfYellowTiles, is(2));
		assertThat(noOfRedTiles, is(2));
		assertThat(noOfGreenTiles, is(2));
	}

	@Test
	public void testSwapReturnsNewBoard() {
		Board newBoard = board.swap(1, 0, 1, 1);
		
		assertThat(newBoard, is(not(sameInstance(board))));
	}
	
	@Test(expected = InvalidMoveException.class)
	public void testSwappingTwoNonEmptyTilesThrowsException() {
		board.swap(1, 0, 2, 0);
	}
	
	@Test(expected = InvalidMoveException.class)
	public void testSwappingTwoEmptyTilesThrowsException() {
		board.swap(0, 0, 3, 3);
	}
}
