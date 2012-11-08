package csci306;

import java.awt.Graphics;

public abstract class BoardCell {
	
	protected int row;
	protected int col;
	
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

	//Create draw method later
	abstract void draw(Graphics g);
	
}