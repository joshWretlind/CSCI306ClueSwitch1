package csci306;

import java.awt.Color;
import java.awt.Graphics;


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

	@Override
	void draw(Graphics g) {
		int x = col * Board.CELL_SIZE;
		int y = row * Board.CELL_SIZE;
		int width = Board.CELL_SIZE;
		int height = Board.CELL_SIZE;
		
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);		
		
		if (isTarget) {
			g.setColor(new Color(51, 204, 255));
			g.fillRect(x, y, Board.CELL_SIZE, Board.CELL_SIZE);
		}
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		
	}
}
