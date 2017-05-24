package com.havana.games.triovision.cardloader;

import com.havana.games.triovision.exceptions.MalformedCardException;
import com.havana.games.triovision.model.Card;
import com.havana.games.triovision.model.Pawn;

public class CardLoader {

	public static Card fromString(String serializedCard) {
		
		String[] rows = serializedCard.split(",");
		
		if (rows.length != 3) {
			throw new MalformedCardException("The serialized card [" + serializedCard + "] is malformed.");
		}
		
		Pawn[][] cardInfo = new Pawn[3][2];
		
		for (int i = 0; i < 3; i++) {
			String row = rows[i].trim();
			String[] cols = row.split(" ");
			
			if (cols.length != 2) {
				throw new MalformedCardException("The serialized card [" + serializedCard + "] is malformed.");
			}
			
			cardInfo[i][0] = Pawn.valueOfByAlias(cols[0].charAt(0));
			cardInfo[i][1] = Pawn.valueOfByAlias(cols[1].charAt(0));
		}
		
		return new Card(cardInfo);
	}

}
