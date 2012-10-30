import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	
	private char lastRoomVisited;
	private Solution lastGuessedSolution;
	
	public BoardCell pickLocation(Set targets){
		List l = new ArrayList(targets);
		Collections.shuffle(l);
		return (BoardCell) l.get(0);
	}
	
	public void createSuggestion() {
		List<Card> availableGuesses = new ArrayList<Card>(super.getCards());
		lastGuessedSolution = new Solution();
		availableGuesses.removeAll(this.getSeenCards());
		boolean hasPerson = false;
		boolean hasRoom = false;
		boolean hasWeapon = false;
				
		for (Card c: availableGuesses) {
			System.out.println(c.getName());
			if (!hasPerson && c.getType().equals("PERSON")) {
				lastGuessedSolution.setPerson(c.getName());
				hasPerson = true;
			}
			else if (!hasWeapon && c.getType().equals("WEAPON")) {
				lastGuessedSolution.setWeapon(c.getName());
				hasWeapon = true;
			}
		}
		lastGuessedSolution.setRoom(((RoomCell)super.getLocation()).name);
	}
	
	public void updateSeen(Card seen){
	}

	public char getLastRoomVisited() {
		return lastRoomVisited;
	}

	public void setLastRoomVisited(char lastRoomVisited) {
		this.lastRoomVisited = lastRoomVisited;
	}

	public Solution getLastGuessedSolution() {
		return lastGuessedSolution;
	}

	public void setLastGuessedSolution(Solution lastGuessedSolution) {
		this.lastGuessedSolution = lastGuessedSolution;
	}
	
	
}