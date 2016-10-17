package by.minsk.blackjack.model;

import java.util.LinkedList;
import java.util.Scanner;

public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		setName(name);
		setBank(Constants.PLAYER_BANK);
	}

	public int makeBet() { // делаем ставку
		boolean betRequired = true;
		while (betRequired) {
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out.println("Enter your bet:");
			if (sc.hasNextInt()) {
				setBet(sc.nextInt());
			} else {
				System.out.println("Enter only integer variables!");
				continue;
			}
			if (getBank() - getBet() < 0) {
				System.out.println("Not enough money to make this bet!");
				continue;
			}
			if (getBet() <= 0) {
				System.out.println("Bet should be more than 0 !");
			} else {
				betRequired = false;
			}
		}
		return getBet();
	}
}
