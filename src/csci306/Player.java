package csci306;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
	private String name;
	private List<Card> cards; 
	private List<Card> seenCards;
	private List<Card> possibleCards;
	private BoardCell location;
	private Color color;
	
	public Player(){
		cards = new ArrayList<Card>();
		seenCards = new ArrayList<Card>();
	}
	
	public Card disproveSuggestion(String person, String weapon, String room){
		List<Card> potentialMatches = new ArrayList<Card>();
		for(Card c: cards){
			if(c.getName().equals(person)){
				potentialMatches.add(c);
			}
			if(c.getName().equals(weapon)){
				potentialMatches.add(c);
			}
			if(c.getName().equals(room)){
				potentialMatches.add(c);
			}
		}
		
		Collections.shuffle(potentialMatches);
		if(potentialMatches.size() == 0){
			return null;
		}
		return potentialMatches.get(0);
	}


	public List<Card> getCards() {
		return cards;
	}


	public void setCards(List<Card> cards) {
		this.cards = cards;
	}


	public void setPossibleCards(List<Card> allCards) {
		this.possibleCards = new ArrayList<Card>(allCards);
	}
	
	public List<Card> getPossibleCards() {
		return this.possibleCards;
	}
	
	public List<Card> getSeenCards() {
		return seenCards;
	}
	
	public void setSeenCards(List<Card> seenCards) {
		this.seenCards = seenCards;
	}
	
	public void addSeenCard(Card c) {
		this.seenCards.add(c);
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public BoardCell getLocation() {
		return location;
	}

	public void setLocation(BoardCell location) {
		this.location = location;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	abstract void draw(Graphics g);
}
