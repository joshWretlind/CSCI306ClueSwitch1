package clueTests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class spreadsheetTest {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		board = new Board();
	}


	@Test
	public void adjacentWalkwayTest() {
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(15, 7));
		Assert.assertTrue(testList.contains(board.calcIndex(14, 7))); 
		Assert.assertTrue(testList.contains(board.calcIndex(15, 8))); 
		Assert.assertTrue(testList.contains(board.calcIndex(15, 6))); 
		Assert.assertTrue(testList.contains(board.calcIndex(16, 7))); 
		Assert.assertEquals(4, testList.size());
//		Assert.fail("Part 2 has to fail.  This forces it to fail.");
	}
	
	@Test
	public void nextToEdgeTest() {
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(21, 6));
		Assert.assertTrue(testList.contains(board.calcIndex(20, 6))); 
		Assert.assertTrue(testList.contains(board.calcIndex(21, 7))); 
		Assert.assertEquals(2, testList.size());
		
		testList = board.getAdjList(board.calcIndex(13, 22));
		Assert.assertTrue(testList.contains(board.calcIndex(13, 21))); 
		Assert.assertTrue(testList.contains(board.calcIndex(14, 22))); 
		Assert.assertEquals(2, testList.size());
//		Assert.fail("Part 2 has to fail.  This forces it to fail.");
	}
	
	@Test
	public void nextToRoomButNotADoorTest() {
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(6, 5));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 5))); 
		Assert.assertTrue(testList.contains(board.calcIndex(6, 4)));
		Assert.assertTrue(testList.contains(board.calcIndex(6, 6)));
		Assert.assertEquals(3, testList.size());
		
		testList = board.getAdjList(board.calcIndex(20, 17));
		Assert.assertTrue(testList.contains(board.calcIndex(21, 17))); 
		Assert.assertTrue(testList.contains(board.calcIndex(20, 16))); 
		Assert.assertTrue(testList.contains(board.calcIndex(19, 17))); 
		Assert.assertEquals(3, testList.size());
//		Assert.fail("Part 2 has to fail.  This forces it to fail.");
	}
	
	@Test
	public void adjacentToDoorTest() {
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(17, 15));
		Assert.assertTrue(testList.contains(board.calcIndex(18, 15))); 
		Assert.assertTrue(testList.contains(board.calcIndex(17, 16)));
		Assert.assertTrue(testList.contains(board.calcIndex(16, 15)));
		Assert.assertTrue(testList.contains(board.calcIndex(17, 14)));
		Assert.assertEquals(4, testList.size());
		
		testList = board.getAdjList(board.calcIndex(9, 16));
		Assert.assertTrue(testList.contains(board.calcIndex(10, 16))); 
		Assert.assertTrue(testList.contains(board.calcIndex(8, 16))); 
		Assert.assertTrue(testList.contains(board.calcIndex(9, 17))); 
		Assert.assertEquals(3, testList.size());
//		Assert.fail("Part 2 has to fail.  This forces it to fail.");
	}
	
	@Test
	public void testTargetsAlongWalkways() {
		board.calcTargets(board.calcIndex(13, 8), 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 9))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 7))));
		
		board.calcTargets(board.calcIndex(5, 10), 2);
		targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 8))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 9))));
		
		board.calcTargets(board.calcIndex(5, 17), 2);
		targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 18))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 19))));
		
		board.calcTargets(board.calcIndex(14, 17), 3);
		targets= board.getTargets();
		Assert.assertEquals(12, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 15))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 19))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 18))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 20))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 15))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 16))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 18))));
//		Assert.fail("Part 2 has to fail.  This forces it to fail.");
	}
	
	@Test
	public void goingInToARoomButStillNotRightNextToItTest() {
		board.calcTargets(board.calcIndex(3, 19), 6);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 20))));
		
		board.calcTargets(board.calcIndex(10, 8), 2);
		targets= board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 6))));
//		Assert.fail("Part 2 has to fail.  This forces it to fail.");

	}
	
	@Test
	public void targetFromInsideRoomTest() {
		board.calcTargets(board.calcIndex(4, 3), 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 4))));
		
		board.calcTargets(board.calcIndex(11, 6), 2);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 7))));
//		Assert.fail("Part 2 has to fail.  This forces it to fail.");

		
	}

}
