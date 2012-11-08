package csci306;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPlayer extends Player{

	@Override
	void draw(Graphics g) {
		BoardCell location = getLocation();
		int x = location.col * Board.CELL_SIZE;
		int y = location.row * Board.CELL_SIZE;
		g.setColor(getColor());
		g.fillOval(x, y, Board.CELL_SIZE, Board.CELL_SIZE);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, Board.CELL_SIZE, Board.CELL_SIZE);		
	}

}
