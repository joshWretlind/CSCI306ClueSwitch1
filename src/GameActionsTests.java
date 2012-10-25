

import static org.junit.Assert.*;

import java.util.ArrayList;

import Board;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class GameActionsTests {

	static int NUM_OF_TIMES_TO_TEST;

	@Before
	public void before() {
		Board board = new Board();
		try{
			board.loadPlayers();
			board.loadCards();
			board.loadConfigFiles("");
			board.loadLegend("");
		}catch(Exception e){
			Assert.fail(e.toString());
		}
		board.calcAdjacencies();
	}
	
	@Test
	public void testAccusation() {
		
	}
	
	@Test
	public void testComputerMovement() {
		NUM_OF_TIMES_TO_TEST = 1000;
		ComputerPlayer cp = new ComputerPlayer();
		List<BoardCell> pickedList = new ArrayList<BoardCell>();

		board.calculateTargets(8,14);
		Set<BoardCell> targets = board.getTargets();
		
		for(int i = 0; i <NUM_OF_TIMES_TO_TEST; i++){
			BoardCell pickedCell = cp.pickLocation(targets);
			pickedList.append(pickedCell);
		}
		BoardCell compare = pickedList(0);
		
		for(int i = 1; i < NUM_OF_TIMES_TO_TEST; i++){
			if(!compare.equals(pickedList(i))){
				break;
			}
			else if(i == (NUM_OF_TIMES_TO_TEST -1)){
				fail("Picked the same location 1000 times. Should not happen");
			}
		}
			
	}

}
