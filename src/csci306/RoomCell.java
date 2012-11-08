package csci306;


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
	//Override draw method later
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
}
