
public class Dealer extends Player {

	public Dealer() {
		super("DEALER", Integer.MAX_VALUE);
	}
	
	@Override
	public String toString() {
		return name + ": " + cardsToString();
	}
}
