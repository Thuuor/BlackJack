import java.util.*;

public class Table {

	public static final int NUMDECKS = 6;
	public static final int MIN_BET = 5;
	private Deck deck;
	private Player[] players;
	private Dealer dealer;
	private Scanner input;
	
	public Table(int numPlayers, Scanner input){
		deck = new Deck(NUMDECKS);
		players = new Player[numPlayers];
		dealer = new Dealer();
		this.input = input;
		for (int i = 0; i < players.length; i++) {
			System.out.println("Player number " + (i + 1));
			players[i] = Player.readFromKeyboard(input);
		}
	}
	
	@Override
	public String toString() {
		String s = dealer.toString() + "\n";
		for (Player p: players) {
			s += p.toString() + "\n";
		}
		return s;
	}
	
	public boolean play() {
		boolean gameOver = false;
		while (!gameOver) {
			firstDeal();
			boolean anyoneAlive = true;
			while (anyoneAlive) {
				restOfDeals();
				anyoneAlive = isAnyoneAlive();
			}
			playDealer();
			printWinners();
			resetPlayers();
			dealer.resetPlayer();
			gameOver = askExit();
		}
		return true;
	}
	
	private boolean askExit() {
		System.out.println("END GAME? (Y/N)");
		String answer = input.next();
		if (answer.equalsIgnoreCase("Y")) {
			return true;
		}
		return false;
	}
	
	private void resetPlayers() {
		for (Player p: players) {
			p.resetPlayer();
		}
		
	}

	private void printWinners() {
		System.out.println("RESULTS: -------------");
		int valueCardsDealer = dealer.getCardValue();
		if (dealer.isBusted()) {
			valueCardsDealer = -1;
		}
		System.out.println(dealer);
		if (dealer.isBusted()) {
			System.out.println("--BUSTED--");
		}
		for (Player player: players) {
			System.out.println(player);
			if (player.isBusted()) {
				System.out.println("--BUSTED--");
			}
			if (!player.isBroke() && !player.isBusted()) {
				if (player.getCardValue() > valueCardsDealer) {
					// Wins
					System.out.println("You WIN: " + player.getWinnings());
					player.payWinner();
				} else {
					if (player.getCardValue() == valueCardsDealer) {
						// Even
						System.out.println("You are EVEN");
						player.payEven();
					} else {
						System.out.println("--You lose--");
					}
				}
			}
		}
		
	}

	private void playDealer() {
		while (!dealer.isBusted() && dealer.getCardValue() <= 16 ) {
			deal(dealer);
		}
	}
	
	private boolean isAnyoneAlive() {
		for (Player p: players) {
			if (p.isAlive()) {
				return true;
			}
		}
		return false;
	}

	public void restOfDeals() {
		for (Player p: players) {
			if (p.isAlive()) {
				System.out.println(p);
				p.askCheck(input);
				if (!p.isCheck()) {
					deal(p);
				}
				System.out.println(p);
				if (p.isBusted()) {
					System.out.println("--BUSTED--");
				}
			}
		}
	}
	
	public void firstDeal() {
		for (Player p: players) {
			if (!p.isBroke()) {
				deal(p);
			}
		}		
		deal(dealer);
		System.out.println(this);
		for (Player p : players) {
			if (!p.isBroke()) {
				askForBet(p);
				deal(p);
				System.out.println(p);
			}
		}
		
	}
	
	private void askForBet(Player p) {
		
		System.out.println(p.getName() + ": Enter your bet (" + 
							p.getMoney() + " left): ");
		int bet = input.nextInt();
		
		p.makeBet(bet);
	}
	
	private void deal(Player p) {
		p.giveCard(deck.extracCard());
	}
	
	
}
