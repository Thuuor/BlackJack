import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter number of players");
		int numPlayers = input.nextInt();
		
		Table table = new Table(numPlayers, input);
		
		table.play();
		
		System.out.println("END OF GAME");
	}

}
