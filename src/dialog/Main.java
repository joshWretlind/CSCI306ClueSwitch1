/*
 * This class is the entry point for the program. 
 */

package dialog;

import javax.swing.JOptionPane;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClueGame cb = new ClueGame();
		cb.setVisible(true);
		JOptionPane.showMessageDialog(null, "You are " + cb.getBoard().hPlayer.getName() + ", press Next Player to play");
		cb.startGame();
	}

}
