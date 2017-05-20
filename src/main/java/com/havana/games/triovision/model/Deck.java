package com.havana.games.triovision.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

	private static final List<Pawn> pawnSampleSpace = Collections.unmodifiableList(Arrays.asList(Pawn.BLUE, Pawn.BLUE, Pawn.RED, Pawn.RED, Pawn.GREEN, Pawn.GREEN, Pawn.YELLOW, Pawn.YELLOW));

	private Deque<Card> cards;
	
	private int maxSize = 60;
	
	public Deck() {
		List<Card> cardList = Stream.generate(this::getRandomCard).limit(maxSize).collect(Collectors.toList());
		cards = new ArrayDeque<>(cardList);
	}
	
	public Card getNextCard() {
		return cards.pop();
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	private Card getRandomCard() {
		ArrayList<Pawn> shuffled = new ArrayList<>(pawnSampleSpace);
		Collections.shuffle(shuffled);
		
		Pawn p1 = shuffled.get(0);
		Pawn p2 = shuffled.get(1);
		Pawn p3 = shuffled.get(2);
		
		int templateIndex = new Random().nextInt(CardTemplate.values().length);
		CardTemplate template = CardTemplate.values()[templateIndex];
		
		return template.getCard(p1, p2, p3);
	}
	
	private static enum CardTemplate {
		LEFT_LEFT_RIGHT {
			@Override
			Card getCard(Pawn top, Pawn middle, Pawn bottom) {
				return Card.builder().topLeft(top).middleLeft(middle).bottomRight(bottom).build();
			}
		},
		
		LEFT_RIGHT_RIGHT {
			@Override
			Card getCard(Pawn top, Pawn middle, Pawn bottom) {
				return Card.builder().topLeft(top).middleRight(middle).bottomRight(bottom).build();
			}
		},
		
		RIGHT_LEFT_LEFT {
			@Override
			Card getCard(Pawn top, Pawn middle, Pawn bottom) {
				return Card.builder().topRight(top).middleLeft(middle).bottomLeft(bottom).build();
			}
		},
		
		RIGHT_RIGHT_LEFT {
			@Override
			Card getCard(Pawn top, Pawn middle, Pawn bottom) {
				return Card.builder().topRight(top).middleRight(middle).bottomLeft(bottom).build();
			}
		};
		
		abstract Card getCard(Pawn top, Pawn middle, Pawn bottom);
	}

	public int size() {
		return cards.size();
	}
}
