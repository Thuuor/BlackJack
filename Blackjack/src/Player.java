import java.util.Scanner;

public class Player {

	String name;
	private int money;
	private Card[] cards;
	private int bet;
	private boolean check;
	
	public Player(String name,int money) {
		this.name = name;
		this.money = money;
		cards = new Card[11];
		check = false;
		for (int i = 0; i < cards.length; i++) {
			cards[i] = null;
		}
		bet = 0;
	}
	
	public boolean isCheck() {
		return check;
	}
	
	public void resetPlayer() {
		for (int i = 0; i < cards.length;i++) {
			cards[i] = null;
		}
		check = false;
		bet = 0;
	}
	
	public boolean isBusted() {
		if(getCardValue() > 21) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasBlackJack() {
		if (getCardValue() == 21) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAlive() {
		if (!isBroke() && !isCheck() && 
				!isBusted() && !hasBlackJack()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Player readFromKeyboard(Scanner input) {
		
		System.out.println("Enter name: ");
		String name = input.next();
		System.out.println("Enter initial money: ");
		int money = input.nextInt();
		Player p = new Player(name, money);
		return p;
	}
	
	@Override
	public String toString() {
		String s = "Name: " + name + "\n" +
				"Money: " + money + "   Bet: " + bet + "\n";
		
		s += cardsToString();
		return s;
	}
	
	public String cardsToString() {
		String s = "";
		boolean first = true;
		for (int i = 0; i < getNumCards();i++) {
			if (first) {
				s += cards[i];
				first = false;
			} else {
				s += ", " + cards[i];
			}
		}
		return s;
		
	}

	public void makeBet(int bet) {
		if (bet > money) {
			bet = money;
		}
		if (bet < Table.MIN_BET) {
			bet = Table.MIN_BET;
		}
		this.bet = bet;
		money -= bet;
	}
	
	public boolean isBroke() {
		if (money == 0 && bet == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getBet() {
		return bet;
	}
	
	public int getMoney() {
		return money;
	}
	
	public String getName() {
		return name;
	}
	
	public void giveCard(Card card) {
		int pos = 0;
		while(cards[pos] != null) {
			pos ++;
		}
		cards[pos] = card;
	}
	
	public int getCardValue() {
		int acc = 0;
		int pos = 0;
		boolean ace = false;
		while(cards[pos] != null) {
			int cardValue = cards[pos].getValue();
			acc += cardValue;
			if (cardValue == 1) {
				ace = true;
			}
			pos ++;
		}
		if (ace && acc + 10 <= 21) {
			acc += 10;
		}
		return acc;
	}
	
	public int getNumCards() {
		int counter = 0;
		while(cards[counter] != null) {
			counter ++;
		}
		return counter;
	}
	
	public void payWinner() {
		money += getWinnings();
	}
	
	public int getWinnings() {
		if (getNumCards() == 2 && getCardValue() == 21) {
			// BLACK JACK !!!
			return (int)(bet * 2.5);
		} else {
			return bet * 2;
		}
	}
	
	public void payEven() {
		money += bet;
	}
	
	public void askCheck(Scanner input) {
		System.out.println("Do you check? (Y/N)");
		String answer = input.next();
		if (answer.equalsIgnoreCase("Y")) {
			check = true;
		}
	}
	
	
}

