package csci306;

public class Card {
	private String name;
	private CardType type;
	

	
	public Card(String name, String type){
		this.name = name;
		setType(type);
	}
	
	public enum CardType {
		PERSON, WEAPON, ROOM;
	}
	
	
	public CardType getType() {
		return type;
	}

	public void setType(String type) {
		switch(type.toUpperCase()) {
		case "PERSON":
			this.type = CardType.PERSON;
			break;
		case "WEAPON":
			this.type = CardType.WEAPON;
			break;
		case "ROOM":
			this.type = CardType.ROOM;
			break;
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equal(Card c) {
		if(this.name.equals(c.name) && this.type.equals(c.type)){
			return true;
		}
		
		return false;
		
	}
}
