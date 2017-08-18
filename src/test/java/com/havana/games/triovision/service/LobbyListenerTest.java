package com.havana.games.triovision.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import org.junit.Before;
import org.junit.Test;

import com.havana.games.triovision.model.Game;
import com.havana.games.triovision.notifier.PlayerNotifier;

public class LobbyListenerTest {

	private LobbyListener lobbyListener;
	private Lobby lobby;
	private PlayerNotifier mockPlayerNotifier;
	
	@Before
	public void setUp() throws Exception {
		lobby = new Lobby();
		mockPlayerNotifier = mock(PlayerNotifier.class);
		lobbyListener = new LobbyListener(lobby, mockPlayerNotifier);
	}

	@Test
	public void testNewGameFailsWithNoPlayersInLobby() {
		assertThat(lobby.players().size(), is(0));
		lobbyListener.tryToMakeGame();
		assertThat(lobby.players().size(), is(0));
		verify(mockPlayerNotifier, times(0)).notifyPlayers(any(Game.class));
	}
	
	@Test
	public void testNewGameFailsWithLessThanMinAllowedPlayersInLobby() {
		
		int noOfPlayers = Game.MIN_PLAYERS_IN_SINGLE_GAME - 1;
		
		for (int i = 1; i <= noOfPlayers; i++) {
			lobby.makePlayerAndAddToLobby();			
		}

		assertThat(lobby.players().size(), is(noOfPlayers));
		lobbyListener.tryToMakeGame();
		assertThat(lobby.players().size(), is(noOfPlayers));
		
		verify(mockPlayerNotifier, times(0)).notifyPlayers(any(Game.class));
	}
	
	@Test
	public void testNewGameSucceedsWithMinAllowedPlayersInLobby() {
		
		int noOfPlayers = Game.MIN_PLAYERS_IN_SINGLE_GAME;
		
		for (int i = 1; i <= noOfPlayers; i++) {
			lobby.makePlayerAndAddToLobby();			
		}
		
		assertThat(lobby.players().size(), is(noOfPlayers));
		lobbyListener.tryToMakeGame();
		assertThat(lobby.players().size(), is(0));
		
		verify(mockPlayerNotifier, times(1)).notifyPlayers(any(Game.class));
	}
	
	@Test
	public void testNewGameSucceedsWithMaxAllowedPlayersInLobby() {
		
		int noOfPlayers = Game.MAX_PLAYERS_IN_SINGLE_GAME;
		
		for (int i = 1; i <= noOfPlayers; i++) {
			lobby.makePlayerAndAddToLobby();			
		}
		
		assertThat(lobby.players().size(), is(noOfPlayers));
		lobbyListener.tryToMakeGame();
		assertThat(lobby.players().size(), is(0));
		
		verify(mockPlayerNotifier, times(1)).notifyPlayers(any(Game.class));
	}
	
	@Test
	public void testNewGameSucceedsWithMoreThanMaxAllowedPlayersInLobby() {
		
		int extra = 1;
		int noOfPlayers = Game.MAX_PLAYERS_IN_SINGLE_GAME + extra;
		
		for (int i = 1; i <= noOfPlayers; i++) {
			lobby.makePlayerAndAddToLobby();			
		}
		
		assertThat(lobby.players().size(), is(noOfPlayers));
		lobbyListener.tryToMakeGame();
		assertThat(lobby.players().size(), is(extra));
		
		verify(mockPlayerNotifier, times(1)).notifyPlayers(any(Game.class));
	}
}
