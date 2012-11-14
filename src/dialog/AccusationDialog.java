package dialog;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import csci306.Board;
import csci306.Card;
import csci306.Player;
import csci306.Card.CardType;

public class AccusationDialog extends JDialog {

	private Board board;
	public AccusationDialog(Board board) {
		setTitle("Detective Notes");
		setSize(300,300);
		setLayout(new GridLayout(0,1));
		this.board = board;
		add(createPersonPanel());
		add(createWeaponPanel());
		add(createRoomPanel());
	}
	
	public JPanel createPersonPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Person:"));
		JComboBox box = new JComboBox();
		for(Player p: board.players){
			box.addItem(p.getName());
		}
		panel.add(box);
		return panel;
	}
	
	public JPanel createWeaponPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Weapon:"));
		JComboBox box = new JComboBox();
		for(Card c: board.allCards){
			if(c.getType() == CardType.WEAPON){
				box.addItem(c.getName());
			}
		}
		panel.add(box);
		return panel;
	}

	public JPanel createRoomPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Room:"));
		JComboBox box = new JComboBox();
		for(String room: board.rooms.values()){
			box.addItem(room);
		}
		panel.add(box);
		return panel;
	}
}
