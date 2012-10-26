
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class Board {
	private List<BoardCell> cells = new ArrayList<BoardCell>();
	public Map<Character,String> rooms = new HashMap<Character,String>();
	public int numRows;
	public int numColumns;
	public List<Player> players;
	public List<Card> allCards;
	
	public Board(){
		loadConfigFiles();
		
		
//		printBoard();
	}
	private void loadConfigFiles(){
			//insert try cat
		FileReader readBoard = null;
		FileReader readRooms = null;


		try {
			readBoard = new FileReader("CR-ClueLayout.csv");
			readRooms = new FileReader("ClueRms");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//read each room into rooms map
		Scanner in = new Scanner(readRooms);
		while(in.hasNextLine()){
			String[] room = in.nextLine().split(", ");

//			if(!room[0].equals("X") && !room[0].equals("W")){
			 rooms.put( room[0].charAt(0), room[1]);
//			}
		}
		
		
		
		//read each cell and input			System.out.println("ppppppppppppppppop");

		in = new Scanner(readBoard);
		int rowIndex = 0;
		int colIndex = 0;
		for(;in.hasNextLine();rowIndex++){
			colIndex = 0;
			String[] row = in.nextLine().split(",");
			for(;colIndex<row.length;colIndex++){
				if(!row[colIndex].equals("W")){
					cells.add(new RoomCell(rowIndex,colIndex,row[colIndex]));	
				}else{
					cells.add(new WalkwayCell(rowIndex,colIndex));
				}
				
			}
		}
		numRows = rowIndex;
		numColumns = colIndex;
	}
	
//	private String loadFile(){
//		
//		return "poop";
//	}
//	private String parseBoard(){
//		return "poop";
//	}
	public int calcIndex(int row, int col){
		return row*numColumns+col;
	}
	//checks if it is a room
	public RoomCell getRoomCellAt(int row, int col){
		
		BoardCell c =  cells.get(calcIndex(row,col));
		if(c.isRoom()){
			return (RoomCell) c;	
		}else{
			return null;
		}
		
	}
	public String getRoomName(char c){
		return rooms.get(c);
	}
	
	public RoomCell.DoorDirection doorDirectionAtIndex(int row, int col){
		RoomCell rc = getRoomCellAt(row, col);
		if(rc != null){
			return rc.getDoorDirection();
		}else{
			return RoomCell.DoorDirection.NONE;
		}
		
	}

	public char roomInitialAtIndex(int row, int col){
		RoomCell rc = getRoomCellAt(row, col);
		if(rc != null){
			return rc.getInitial();
		}else{
			return 'W';
		}
	}

	public Map<Character, String> getRooms() {

		return rooms;
	}

	public int getNumRows() {
		return numRows;
		
	}

	public int getNumColumns() {
		// TODO Auto-generated method stub
		return numColumns;
	}

	public BoardCell getCellAt(int calcIndex) {
		return(cells.get(calcIndex));
	}

	public LinkedList<Integer> getAdjList(int calcIndex) {

		LinkedList<Integer> newAdjMtx = new LinkedList<Integer>();
		//check if in nondoorway room cell
		if(cells.get(calcIndex).isRoom()   &&  !cells.get(calcIndex).isDoorway()){
			return newAdjMtx;
		}
		boolean sourceIsRoom = getCellAt(calcIndex).isRoom();
		//if not at top add one above
		if(calcIndex >= numColumns){
			int i = calcIndex-numColumns;
			//check if attempting to move into nondoorway room cell
			if((cells.get(i).isWalkway()   ||  cells.get(i).isDoorway())){
				//if in a room cant move to room
				if(!(getCellAt(i).isRoom() && sourceIsRoom)){
					//if not in doorway and moving in the same direction as the door
					if(!(cells.get(i).isDoorway() &&   ((RoomCell)(cells.get(i))).getDoorDirection() != RoomCell.DoorDirection.DOWN ))	
						if(!(cells.get(calcIndex).isDoorway() &&   ((RoomCell)(cells.get(calcIndex))).getDoorDirection() != RoomCell.DoorDirection.UP ))
							newAdjMtx.push(i);

				}
			}
		}
	
		//if not at left side add one left
		if(calcIndex%numColumns != 0){
			int i = calcIndex-1;
			//check if attempting to move into nondoorway room cell
			if((cells.get(i).isWalkway()   ||  cells.get(i).isDoorway())){
				if(!(getCellAt(i).isRoom() && sourceIsRoom)){
					if(!(cells.get(i).isDoorway() &&   ((RoomCell)(cells.get(i))).getDoorDirection() != RoomCell.DoorDirection.RIGHT ))	
						if(!(cells.get(calcIndex).isDoorway() &&   ((RoomCell)(cells.get(calcIndex))).getDoorDirection() != RoomCell.DoorDirection.LEFT ))	
							newAdjMtx.push(i);
				}
			}
		}
			

		//if not at right side add one to right
		if(calcIndex%numColumns != numColumns-1){
			int i = calcIndex+1;			
			//check if attempting to move into nondoorway room cell
			if((cells.get(i).isWalkway()   ||  cells.get(i).isDoorway())){
				if(!(getCellAt(i).isRoom() && sourceIsRoom)){
					if(!(cells.get(i).isDoorway() &&   ((RoomCell)(cells.get(i))).getDoorDirection() != RoomCell.DoorDirection.LEFT ))
						if(!(cells.get(calcIndex).isDoorway() &&   ((RoomCell)(cells.get(calcIndex))).getDoorDirection() != RoomCell.DoorDirection.RIGHT ))
							newAdjMtx.push(i);
				}
			}
		}

		//if not at bottom add one to bottom
		if(calcIndex<numRows*numColumns-numColumns){
			int i = calcIndex+numColumns;
			//check if attempting to move into nondoorway room cell
			if((cells.get(i).isWalkway()   ||  cells.get(i).isDoorway())){
				if(!(getCellAt(i).isRoom() && sourceIsRoom)){
					if(!(cells.get(i).isDoorway() &&   ((RoomCell)(cells.get(i))).getDoorDirection() != RoomCell.DoorDirection.UP))
						if(!(cells.get(calcIndex).isDoorway() &&   ((RoomCell)(cells.get(calcIndex))).getDoorDirection() != RoomCell.DoorDirection.DOWN ))
							newAdjMtx.push(i);
				}
			}
		}
		return newAdjMtx;

	}

	public void printBoard(){
		for(int row=0;row<numRows;row++){
			for(int cols=0;cols<numColumns;cols++){
				System.out.print( cells.get(calcIndex(row, cols)).toString());
				System.out.print(" ");
			}
			System.out.print("\n");
		}
		
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("\n");
	}
	
	

	Set<Integer> targets;
	
	
	public void calcTargets(int startLoc, int numSteps) {
		targets = new HashSet<Integer>();
		calcTargets(startLoc,numSteps,new HashSet<Integer>());
	}
	
	
	public void calcTargets(int startLoc, int numSteps, Set<Integer> seen) {
		//if bad spot return
		if(cells.get(startLoc).isRoom()   &&  !cells.get(startLoc).isDoorway()){
			return;
		}
		///1 0
		//if original place then return and don't add target
		if(seen.contains(startLoc))
			return;
		seen.add(startLoc);
		if(numSteps == 0 || (cells.get(startLoc).isDoorway()    && seen.size()>1)){
			targets.add(startLoc);
			return;
		}

		//if no more steps add target

		//call calcTargets for every adjacent pair
		LinkedList<Integer> raa = getAdjList(startLoc);
		for (Integer adj : raa){
			calcTargets(adj,numSteps-1,new HashSet<Integer>(seen));
		}

	}
	
	
	
	
	public Set<BoardCell> getTargets() {
		Set<BoardCell> tempTargets = new HashSet<BoardCell>();
		
		for(Integer z : targets){
			tempTargets.add(getCellAt(z));
		}
		
		//set everything to not visited
		
		//create a new set of targets
		//update that there is no starting location for a new dice roll
		return tempTargets;
	}
	public void loadPlayers() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean checkAccusation(String person, String weapon, String room){
		return false;
	}

	public void handleSuggestion(String person, String weapon, String room){
		
	}
	
	public void deal(){
		
	}
	
	public void deal(ArrayList<Card> cards){
		
	}
	
	public void selectAnswer(){
		
	}
	
	public Solution getAnswer(){
		return null;
	}
	
	public void loadCards(){
		
	}

}
