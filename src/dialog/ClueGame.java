package dialog;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import csci306.Board;

public class ClueGame extends JFrame {
	Board board;
	
	public ClueGame() {
		setTitle("Clue Game");
		setSize(800, 600);
		
		createMenuBar();
		createBoard();
		createControls();
		
		
		add(board, BorderLayout.CENTER);
	}
	
	public void createMenuBar() {
		
	}
	
	public void createBoard() {
		board = new Board();
		board.loadPlayers();
		board.loadCards();
	}
	
	public void createControls() {
		
	}
	
	public static void main(String[] args) {
		ClueGame cb = new ClueGame();
		cb.setVisible(true);
	}

}
