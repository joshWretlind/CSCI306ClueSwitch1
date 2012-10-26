package board;

import static org.junit.Assert.*;
import Board;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class GameActionsTests {

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
		
	}
	
	@Test
	public void testComputerGuesses() {
		
	}
	
	@Test
	public void testHuamnGuesses() {
		
	}
	
	@Test
	public void testMakingComputerGusses() {
		
	}
}
