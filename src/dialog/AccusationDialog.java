package dialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import csci306.Board;
import csci306.Card;
import csci306.Player;
import csci306.Solution;
import csci306.Card.CardType;

public class AccusationDialog extends JDialog {

	private final JComboBox<String> personBox = new JComboBox();;
	private final JComboBox<String> roomBox = new JComboBox();;
	private final JComboBox<String> weaponBox = new JComboBox();;
	
	private static final long serialVersionUID = -483259800725920714L;
	private final Board board_copy; // this is an extra copy 
	public AccusationDialog(Board board) {
		setTitle("Detective Notes");
		setSize(300,300);
		setLayout(new GridLayout(0,1));
		this.board_copy = board;
		add(createPersonPanel());
		add(createWeaponPanel());
		add(createRoomPanel());
		JButton accuse = new JButton("Accuse!");
		add(accuse);
		accuse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Solution guess = new Solution();
				guess.person = (String) personBox.getSelectedItem();
				guess.room = (String) roomBox.getSelectedItem();
				guess.weapon = (String) weaponBox.getSelectedItem();
				boolean check = board_copy.checkAccusation(guess);
				if(!check){
					String notRight = "Sorry, but your accusation was not correct";
					JOptionPane.showMessageDialog(null, notRight);
				} else {
					String right = "Congradulations, your accusation was correct";
					JOptionPane.showMessageDialog(null, right);
				}
			}
		});	
	}
	public JPanel createPersonPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Person:"));
		for(Card c: board_copy.allCards){
			if(c.getType() == CardType.PERSON){
				personBox.addItem(c.getName());
			}
		}
		personBox.addItem(board_copy.solution.person);
		panel.add(personBox);
		return panel;
	}
	
	public JPanel createWeaponPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Weapon:"));
		for(Card c: board_copy.allCards){
			if(c.getType() == CardType.WEAPON){
				weaponBox.addItem(c.getName());
			}
		}
		weaponBox.addItem(board_copy.solution.weapon);
		panel.add(weaponBox);
		return panel;
	}

	public JPanel createRoomPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Room:"));
		for(Card c: board_copy.allCards){
			if(c.getType() == CardType.ROOM){
				roomBox.addItem(c.getName());
			}
		}
		roomBox.addItem(board_copy.solution.room);
		panel.add(roomBox);
		return panel;
	}
}
