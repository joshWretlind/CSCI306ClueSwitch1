import java.awt.Color;
import java.util.List;

public abstract class Player {
	private String name;
	private List<Card> cards; 
	private List<Card> seenCards;
	private BoardCell location;
	private Color color;
	
	public Player(){
		
	}
	
	public Card disproveSuggestion(Solution solution){
		return null;
	}
	
	public Card disproveSuggestion(String person, String weapon, String room){
		return null;
	}


	public List<Card> getCards() {
		return cards;
	}


	public void setCards(List<Card> cards) {
		this.cards = cards;
	}


	public List<Card> getSeenCards() {
		return seenCards;
	}


	public void setSeenCards(List<Card> seenCards) {
		this.seenCards = seenCards;
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
}
