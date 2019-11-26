
public class Deck {

	public static final int NUM_CARDS_IN_DECK = 52;
	private Card[] cards;
	private int numCards;
	
	public Deck(int numDecks) {
		numCards = NUM_CARDS_IN_DECK * numDecks;
		cards = new Card[numCards];
		int counter = 0;
		Card c;
		for (int i = 0; i < numDecks; i++) {
			for (int j = 1; j <= 13; j++) {
				c = new Card(j, Suit.CLOVERS);
				cards[counter] = c;
				counter++;
				c = new Card(j, Suit.DIAMONDS);
				cards[counter] = c;
				counter++;
				c = new Card(j, Suit.HEARTS);
				cards[counter] = c;
				counter++;
				c = new Card(j, Suit.SPADES);
				cards[counter] = c;
				counter++;
			}
		}
		shuffle();
	}
	
	public boolean isEmpty() {
		if (numCards == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void shuffle() {
		for (int i = 0; i < cards.length; i++) {
			int randPos = (int)(Math.random() * cards.length);
			Card temp;
			temp = cards[i];
			cards[i] = cards[randPos];
			cards[randPos] = temp;
		}
	}
	
	@Override
	public String toString() {
		String s = cards[0].toString();
		for (int i = 1; i < cards.length; i++) {
			s += ", " + cards[i];
		}
		return s;
	}
	
	public Card extracCard() {
		numCards --;
		return cards[numCards];
	}
}
