package com.havana.games.triovision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.havana.games.triovision.model.Player;
import com.havana.games.triovision.service.Lobby;

@RestController
public class PlayerController {

	private Lobby lobby;
	
	@Autowired
	public PlayerController(Lobby lobby) {
		this.lobby = lobby;
	}
	
	
	@RequestMapping(value = "/player", method = RequestMethod.POST)
	public Player enterLobby() {
		return lobby.makePlayerAndAddToLobby();
	}
	
	/*
	// the hello is used as a suffix for sending messages to this method
	// Effectively a listener on "/app/hello"
	@MessageMapping("/player")
	// This will send/broadcast a message
    @SendToUser("/topic/player")
    public Player greeting(String message) throws Exception {
		System.out.println("Got message!" + message);
        Thread.sleep(1000); // simulated delay
        return new Player();
    }*/
}
