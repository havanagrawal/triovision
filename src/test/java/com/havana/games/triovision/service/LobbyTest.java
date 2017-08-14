package com.havana.games.triovision.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.havana.games.triovision.model.Game;
import com.havana.games.triovision.model.Player;

public class LobbyTest {

	private Lobby lobby;

	@Before
	public void setUp() throws Exception {
		lobby = new Lobby();
	}

	@Test
	public void testMakePlayerReturnsNewPlayerEveryTime() {
		
		HashSet<Player> players = new HashSet<>();
		
		int noOfPlayers = 100_000;
		
		for (int i = 0; i < noOfPlayers; i++) {
			players.add(lobby.makePlayerAndAddToLobby());
		}
		
		assertThat(players.size(), is(noOfPlayers));
	}
	
	@Test
	public void testMakePlayerAddsPlayerToLobby() {
		int noOfPlayers = 100_000;
		
		for (int i = 0; i < noOfPlayers; i++) {
			lobby.makePlayerAndAddToLobby();
		}
		
		assertThat(lobby.players().size(), is(noOfPlayers));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testMakingGameFromLobbyWithNoPlayersFails() {
		lobby.tryNewGame();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testMakingGameFromLobbyWithSinglePlayerFails() {
		lobby.makePlayerAndAddToLobby();
		lobby.tryNewGame();
	}
	
	@Test
	public void testMakingGameFromLobbyRemovesPlayersFromLobby() {
		lobby.makePlayerAndAddToLobby();
		lobby.makePlayerAndAddToLobby();
		lobby.makePlayerAndAddToLobby();
		lobby.makePlayerAndAddToLobby();
		
		assertThat(lobby.players().size(), is(4));
		
		lobby.tryNewGame();
		
		assertThat(lobby.players().size(), is(0));
	}

	@Test
	public void testMakingGameFromLobbyDoesNotRemoveUnnecessaryPlayers() {
		
		for (int i = 0; i < 100; i++) {
			lobby.makePlayerAndAddToLobby();
		}
		
		assertThat(lobby.players().size(), is(100));
		
		lobby.tryNewGame();
		
		assertThat(lobby.players().size(), is(100 - Game.MAX_PLAYERS_IN_SINGLE_GAME));
	}
	
}
