package by.minsk.blackjack.logger;

import by.minsk.blackjack.model.Player;

public class InformationLogger {

	public static void printSum(Player...players) {
		System.out.println();
		for(Player player: players ){
			System.out.println("Total sum of " + player.getName() + " :" + player.getSum());
		}
	}

	public static void printSum(Player player) {
		System.out.println(player.getName() + " sum is: " + player.getSum());
	}

	public static void printBank(Player...players) {
		for(Player player: players ){
			System.out.println(player.getName() + " bank is: " + player.getBank());
		}
	}

	public static void printVictoryMessage(Player winner) {
		System.out.println(winner.getName() + " won, casino pays double bet to " + winner.getName());
	}

	public static void printLooserMessage(Player looser) {
		System.out.println(looser.getName() + " loose, casino takes his bet");
	}

	public static void printDrawMessage(Player player) {
		System.out.println(player.getName() + " has an equal number of points  with casino, casino returns "
				+ player.getName() + " his bet");
	}
}
