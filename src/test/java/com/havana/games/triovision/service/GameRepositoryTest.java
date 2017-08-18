package com.havana.games.triovision.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.havana.games.triovision.model.Game;

public class GameRepositoryTest {

	private GameRepository gameRepo;
	
	@Before
	public void setUp() throws Exception {
		gameRepo = new GameRepository();
	}

	@Test
	public void testGameRepositoryBehavesLikeAMap() {
		
		Game game = new Game(2);
		
		gameRepo.add(game);
		
		Game retrievedGame = gameRepo.get(game.getGameId());
		
		assertTrue(game == retrievedGame);
	}

}
