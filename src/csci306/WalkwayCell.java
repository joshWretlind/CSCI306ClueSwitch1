package csci306;


public class WalkwayCell extends BoardCell {
	public WalkwayCell(int row, int col) {
		super(row, col);
		
	}

	@Override
	public boolean isWalkway(){
		return true;
	}
	@Override
	public String toString() {
		return "W ";
	}

	//Override draw method later
}
