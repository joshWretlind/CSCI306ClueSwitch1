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
import csci306.RoomCell;
import csci306.Solution;
import csci306.Card.CardType;

public class SuggestionDialog extends JDialog {

	private final JComboBox<String> personBox = new JComboBox<String>();
	private final JComboBox<String> roomBox = new JComboBox<String>();
	private final JComboBox<String> weaponBox = new JComboBox<String>();
	
	private static final long serialVersionUID = -483259800725920714L;
	private final Board board_copy; // this is an extra copy 
	
	public SuggestionDialog(Board board) {
		setTitle("Make Suggestion");
		setSize(300,300);
		setLayout(new GridLayout(0,1));
		this.board_copy = board;
		add(createPersonPanel());
		add(createWeaponPanel());
		JButton suggest = new JButton("Suggest!");
		add(suggest);
		suggest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Solution guess = new Solution();
				guess.person = (String) personBox.getSelectedItem();
				guess.room = ((RoomCell) board_copy.hPlayer.getLocation()).getName();
				System.out.println("room: " + guess.room);
				guess.weapon = (String) weaponBox.getSelectedItem();
				boolean check = board_copy.handleSuggestion(guess, board_copy.hPlayer.getLocation());
				if(!check){
					String notRight = "Sorry, but your suggestion was not correct";
					JOptionPane.showMessageDialog(null, notRight);
				} else {
					String right = "Congratulations, your suggestion was correct";
					JOptionPane.showMessageDialog(null, right);
				}
			}
		});
		
	}
	public JPanel createPersonPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Person:"));
		for(Player p: board_copy.players){
			personBox.addItem(p.getName());
		}
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
		panel.add(weaponBox);
		return panel;
	}

	public JPanel createRoomPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Room:"));
		for(String room: board_copy.rooms.values()){
			roomBox.addItem(room);
		}
		panel.add(roomBox);
		return panel;
	}
}
