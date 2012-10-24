import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class GameSetupTests {
	
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
	public void testLoadPeople() {
		
	}
	
	@Test
	public void testLoadCards() {
		
	}
	
	@Test
	public void DealCards() {
		
	}

}
