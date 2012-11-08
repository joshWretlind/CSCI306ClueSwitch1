package Tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import csci306.Board;
import csci306.Card;
import csci306.Player;

public class GameSetupTests {
	
	public static Board board;
	
	@Before
	public void before() {
		board = new Board();
		try{
			
		}catch(Exception e){
			Assert.fail(e.toString());
		}
	}
	
	@Test
	public void testLoadPeople() {
		board.loadPlayers();
		
		//We don't necessarily know which players these first 3 are 
		Player player1 = board.players.get(0);
		Player player2 = board.players.get(1);
		Player player3 = board.players.get(2);
		
		//Assert each player has a name
		Assert.assertNotNull(player1.getName());
		Assert.assertNotNull(player2.getName());
		Assert.assertNotNull(player3.getName());
		
		//Assert each player has a color
		Assert.assertNotNull(player1.getColor());
		Assert.assertNotNull(player2.getColor());
		Assert.assertNotNull(player3.getColor());
		
		//Assert each player has a location(Starting location in this case)
		Assert.assertNotNull(player1.getLocation());
		Assert.assertNotNull(player2.getLocation());
		Assert.assertNotNull(player3.getLocation());
	}
	
	@Test
	public void testLoadCards() {
		board.loadCards();
		int playerCardCount = 0;
		int weaponCardCount = 0;
		int roomCardCount = 0;
		
		//The number of cards should be a multiple of 3
		Assert.assertEquals(0, board.allCards.size()%3);
		
		//Check if there is the same number of cards of each type
		for(Card card: board.allCards) {
			switch (card.getType()) {
				case "PERSON": playerCardCount++;
				case "ROOM": roomCardCount++;
				case "WEAPON": weaponCardCount++;
			}
		}
		
		Assert.assertEquals(playerCardCount, roomCardCount);
		Assert.assertEquals(roomCardCount, weaponCardCount);
		Assert.assertEquals(playerCardCount, weaponCardCount);
	}
	
	@Test
	public void dealCards() {
		board.deal();
		
		List<Card> cards = new ArrayList<Card>();
		
		for(Player p: board.players){
			cards.addAll(p.getCards());
		}
		
		Assert.assertEquals(cards.size(), board.allCards.size());
		
		//The reason for this map's existence is so that we can check if all the cards in the cards list are unique, and do so in linear time
		Map<Integer, Card> uniqueMap = new HashMap<Integer,Card>();
		boolean unique = true;
		int i = 0;
		
		for(Card card: cards){
			if(uniqueMap.containsValue(card)){
				unique = false;
				break;
			}
			uniqueMap.put(i, card);
			i++;
		}
		
		Assert.assertTrue(unique);
		
		//Check to make sure each player gets roughly the same number of cards.
		float averageNumberOfCards = 0;
		for(Player p: board.players){
			averageNumberOfCards += p.getCards().size();
		}
		averageNumberOfCards = averageNumberOfCards/board.players.size();
		for(Player p: board.players){
			if(!((Math.floor(averageNumberOfCards) <= p.getCards().size()) && (p.getCards().size() <= Math.ceil(averageNumberOfCards)))){
				fail("Incorrect number of Cards dealt");
			}
		}		
	}

}
