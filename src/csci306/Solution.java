package csci306;

public class Solution {

	public String person;
	public String weapon;
	public String room;
	
	public String getPerson() {
		return person;
	}
	
	public void setPerson(String person) {
		this.person = person;
	}
	
	public String getWeapon() {
		return weapon;
	}
	
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	
	public String getRoom() {
		return room;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
	
	public String toString() {
		return "<" + person + " with " + weapon + " in " + room + ">"; 
	}
}
