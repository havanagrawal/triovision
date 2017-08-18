package com.havana.games.triovision.notifier;

import org.springframework.stereotype.Component;

import com.havana.games.triovision.model.Game;

@Component
public interface PlayerNotifier {

	void notifyPlayers(Game game);

}