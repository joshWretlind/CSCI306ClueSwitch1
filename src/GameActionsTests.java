import clueGame.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
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

		board.calcTargets(8,14);
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
		ComputerPlayer compPlayer = new ComputerPlayer();
		List<Card> seen = compPlayer.getSeenCards();
		compPlayer.createSuggestion();
		compPlayer.getSeenCards().add(compPlayer.disproveSuggestion(compPlayer.getLastGuessedSolution()));
		List<Card> seenAfterSuggestion = compPlayer.getSeenCards();
		Assert.assertTrue(!seen.equals(seenAfterSuggestion));
	}
	
	@Test
	public void makingSuggestion(){
		ComputerPlayer compPlayer = new ComputerPlayer();
		List<Card> cards = compPlayer.getCards();
		compPlayer.createSuggestion();
		Solution solution = compPlayer.getLastGuessedSolution();
		Card person = new Card(solution.person);
		Card weapon = new Card(solution.weapon);
		Card room = new Card(solution.room);
		
		Assert.assertTrue(cards.contains(person));
		Assert.assertTrue(cards.contains(weapon));
		Assert.assertTrue(cards.contains(room));
		
		char roomLetter = ' ';
		
		for(Entry<Character, String> e: board.rooms.entrySet()){
			if(e.getValue() == room.getName()){
				roomLetter = e.getKey();
				break;
			}
		}
		
		Assert.assertEquals(((RoomCell)compPlayer.getLocation()).getInitial(),roomLetter);
	}
}
