package clueTests;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.RoomCell;


public class BoardTest {
	public Board board;
	public BoardCell boardCell;
	 
	@Before 
	public void setup(){
		board = new Board();
	}
	
	@Test 
	public void numOfRooms(){
		Assert.assertEquals(11, board.rooms.size());
	}
	
	
	
	@Test
	public void correctRoomName(){
		Assert.assertEquals("Conservatory",board.getRoomName('C'));
	}
	
	
	
	@Test
	public void correctNumOfRowsAndCol(){
		Assert.assertEquals(22, board.numRows);
		Assert.assertEquals(23,board.numColumns);
	}

	@Test
	public void isDoorStuff(){
		RoomCell bc = board.getRoomCellAt(4, 3);
		
		System.out.println(bc.getInitial());
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT,board.doorDirectionAtIndex(4, 3));
		Assert.assertEquals('D',board.roomInitialAtIndex(19, 10));
		Assert.assertEquals(27,board.calcIndex(1, 4));
	}
	
	@Test
	public void calcInitTest(){
		Assert.assertEquals(24, board.calcIndex(1, 1));
	}
	@Test
	public void test() {
		Assert.assertEquals(true, true);
	}

}
