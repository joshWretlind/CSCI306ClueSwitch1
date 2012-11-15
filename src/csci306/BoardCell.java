package csci306;

import java.awt.Graphics;

public abstract class BoardCell {
	
	protected int row;
	protected int col;
	
	protected boolean isTarget;
	
	public BoardCell(int row,int col){
		this.row = row;
		this.col = col;
		
	}

	public boolean isWalkway(){
		return false;
	}
	public boolean isRoom(){
		return false;
	}
	public boolean isDoorway(){
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public void isATarget(boolean val) {
		isTarget = val;
	}
	public void resetTargetFlag() {
		isTarget = false;
	}
	
	public String toString() {
		return "<" + row + ", " + col + ">";
	}
	
	abstract void draw(Graphics g);
	
}