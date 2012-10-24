
public class Card {
	private String name;
	private String type;
	
	enum CardType {
		PERSON, WEAPON, ROOM;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
