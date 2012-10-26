import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	
	private char lastRoomVisited;
	private Solution lastGuessedSolution;
	
	public BoardCell pickLocation(Set targets){
		return null;
	}
	
	public void createSuggestion(){
		
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
