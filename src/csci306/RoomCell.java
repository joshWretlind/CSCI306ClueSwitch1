package csci306;

import java.awt.Color;
import java.awt.Graphics;


public class RoomCell extends BoardCell {
	public enum DoorDirection {
		UP, DOWN, LEFT, RIGHT,NONE;
	}
	
	
	private String name;
	private DoorDirection doorDirection;
	private char room;
	private boolean displayRoomName = false;
	
	RoomCell(int row,int col,String room){

		super(row,col);

		this.room = room.charAt(0);
		doorDirection = DoorDirection.NONE;
		if(room.length()>1){
			switch(room.charAt(1)){
			case 'R':
				doorDirection = DoorDirection.RIGHT;
				break;
			case 'L':
				doorDirection = DoorDirection.LEFT;
				break;
			case 'U':
				doorDirection = DoorDirection.UP;
				break;
			case 'D':
				doorDirection = DoorDirection.DOWN;
				break;
			default :
				doorDirection = DoorDirection.NONE;
			}
			if (room.charAt(1) == 'N') {
				displayRoomName = true;
			}
		}

		
	}


	@Override
	public boolean isRoom(){
		return true;
	}
	
	@Override
	public boolean isDoorway(){
		return DoorDirection.NONE != doorDirection;
	}
	public DoorDirection getDoorDirection() {
		return doorDirection;
		
	}

	public char getInitial() {
		return room;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean displayTheName() {
		return displayRoomName;
	}
	
	@Override
	public String toString() {
		switch(doorDirection){
		case DOWN:
			return room + "D";
		case LEFT:
			return room + "L";
		case NONE:
			return room + " ";
		case RIGHT:
			return room + "R";
		case UP:
			return room + "U";
		default:
			return "error";
		}
		
	}


	@Override
	void draw(Graphics g) {
		int x = col * Board.CELL_SIZE;
		int y = row * Board.CELL_SIZE;
		
		if (isTarget) {
			g.setColor(new Color(51, 204, 255));
			g.fillRect(x, y, Board.CELL_SIZE, Board.CELL_SIZE);
		}
		
		if (doorDirection != DoorDirection.NONE) {
			int height = 5;
			int width = Board.CELL_SIZE;
			switch (doorDirection) {
			case UP:
				//x and y remain the same, as does the height and width
				break;
			case DOWN:
				y = (y + Board.CELL_SIZE - 5);
				height = 5;
				width = Board.CELL_SIZE;
				break;
			case RIGHT:
				x = (x + Board.CELL_SIZE - 5);
				height = Board.CELL_SIZE;
				width = 5;
				break;
			case LEFT:
				height = Board.CELL_SIZE;
				width = 5;
				break;
			}
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
			
		}
		if (displayRoomName) {
			g.setColor(Color.BLUE);
			//prepare string
			String roomName = name;
			
			g.drawString(name, x, y);
		}
	}
	
	
}
