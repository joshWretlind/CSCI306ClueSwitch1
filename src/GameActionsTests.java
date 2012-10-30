import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class GameActionsTests {

	static int NUM_OF_TIMES_TO_TEST;

	public Board board;
	
	@Before
	public void before() {
		board = new Board();
		board.loadPlayers();
		board.loadCards();
		board.deal();
	}
	
	@Test
	public void testAccusation() {
		board.selectAnswer();
		Solution solution = board.getAnswer();
		
		Assert.assertFalse(board.checkAccusation("", "", ""));
		Assert.assertTrue(board.checkAccusation(solution.person, solution.weapon, solution.room));
	}
	
	@Test
	public void testComputerMovement() {
		NUM_OF_TIMES_TO_TEST = 1000;
		ComputerPlayer cp = new ComputerPlayer();
		List<BoardCell> pickedList = new ArrayList<BoardCell>();

		board.calcTargets(6,14);
		Set<BoardCell> targets = board.getTargets();
		
		for(int i = 0; i <NUM_OF_TIMES_TO_TEST; i++){
			BoardCell pickedCell = cp.pickLocation(targets);
			pickedList.add(pickedCell);
		}
		
		BoardCell compare = pickedList.get(0);
		
		for(int i = 1; i < NUM_OF_TIMES_TO_TEST; i++){
			if(!compare.equals(pickedList.get(i))){
				break;
			}
			else if(i == (NUM_OF_TIMES_TO_TEST -1)){
				fail("Picked the same location 1000 times. Should not happen");
			}
		}
			
	}
	
	@Test
	public void testDisprovingSuggestion() {
		ComputerPlayer compPlayer = (ComputerPlayer) board.players.get(2);
		compPlayer.setLocation(board.getCellAt(8));

		List<Card> seen = compPlayer.getSeenCards();
		compPlayer.createSuggestion();
		Solution solution = compPlayer.getLastGuessedSolution();
		board.handleSuggestion(solution.person, solution.weapon, solution.room);
		List<Card> seenAfterSuggestion = compPlayer.getSeenCards();
		
		Assert.assertTrue(!seen.equals(seenAfterSuggestion));
	}
	
	@Test
	public void makingSuggestion(){
		
		ComputerPlayer compPlayer = (ComputerPlayer) board.players.get(2);
		compPlayer.setLocation(board.getCellAt(8));
		Set<Card> cards = new HashSet<Card>(compPlayer.getCards());
		compPlayer.createSuggestion();
		Solution solution = compPlayer.getLastGuessedSolution();
		Card person = new Card(solution.person,"PERSON");
		Card weapon = new Card(solution.weapon,"WEAPON");
		Card room = new Card(solution.room,"ROOM");
		
		int equl = 0;

		for (Card c: cards) {
			if (c.getName().equals(person.getName())) {
				equl++;
			}
			else if (c.getName().equals(weapon.getName())) {
				equl++;
			}
		}
		Assert.assertEquals(equl,2);

		Assert.assertEquals(((RoomCell)compPlayer.getLocation()).name,room.getName());
	}
}
