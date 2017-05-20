package com.havana.games.triovision.model;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
public class GameTest {

	private Game game; 
	
	@Before
	public void setup() {
		game = new Game(2);
	}
	
	@Test
	public void testGameIsIntantiableWithTwoPlayers() {
		new Game(2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGameIsNotInstantiableWithOnePlayer() {
		new Game(1);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testGameIsNotInstantiableWithNegativePlayers() {
		new Game(-1);
	}
	
	@Test
	public void testGameReturnsPlayerByIndex() {
		Player p = game.getPlayer(1);
		
		assertThat(p, is(notNullValue()));
	}
	
	@Test
	public void testGameReturnsSamePlayerForSameIndex() {
		Player p1 = game.getPlayer(1);
		Player p2 = game.getPlayer(1);
		
		assertThat(p1, is(sameInstance(p2)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGameThrowsExceptionForInvalidIndexForPlayer() {
		game.getPlayer(3);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testGameThrowsExceptionForNegativeIndexForPlayer() {
		game.getPlayer(-1);
	}
	
	@Test
	public void testEveryGameHasANonNullBoard() {
		Board b = game.getBoard();
		
		assertThat(b, is(notNullValue()));
	}
	
	@Test
	public void testGameMaintainsASingleBoard() {		
		Board b1 = game.getBoard();
		Board b2 = game.getBoard();
		
		assertThat(b1, is(sameInstance(b2)));
	}

}
