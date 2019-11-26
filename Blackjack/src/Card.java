
public class Card {

	private int number;
	private Suit suit;
	
	public Card(int number,Suit suit) {
		this.number = number;
		this.suit = suit;
	}
	
	@Override
	public String toString() {
		String s = "";
		switch (number) {
		case 1: s = "A";			
			break;
		case 11: s = "J";
			break;
		case 12: s = "Q";
			break;
		case 13: s = "K";
			break;
		default:
			s += number;
			break;
		}
		switch (suit) {
		case CLOVERS:
			s += "\u2663";
			break;
		case SPADES:
			s += "\u2660";
			break;
		case DIAMONDS:
			s += "\u2666";
			break;
		case HEARTS:
			s += "\u2665";
			break;
		default:
			break;
		}
		return s;
	}
	
	public int getValue() {
		if (number <= 10) {
			return number;
		} else {
			return 10;
		}
	}
	
	
}
