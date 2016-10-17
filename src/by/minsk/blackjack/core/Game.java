package by.minsk.blackjack.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import by.minsk.blackjack.logger.InformationLogger;
import by.minsk.blackjack.model.*;

public class Game {

	public static void startGame() {
		List<Card> coloda = new LinkedList<Card>();
		Player diler = new Diler();
		Player bot = new Bot();
		Scanner sd = new Scanner(System.in);
		String name = "";
		System.out.println("Enter your name: ");
		if (sd.hasNext()) {
			name = sd.nextLine();
		}
		Player humanPlayer = new HumanPlayer(name);
		 ((Diler) diler).createDeck(coloda);
		((Diler) diler).shuffleDeck(coloda);
		boolean prepare = true;
		while (prepare) {
			System.out.println("Start game? y/n   ");
			String start = "";
			if (sd.hasNext()) {
				start = sd.next();
			}
			if (start.equals("y")) {
				prepareForGame(humanPlayer, bot, diler);
				if (!checkBank(humanPlayer, bot, diler)) {
					prepare = false;
					break;
				}
				InformationLogger.printBank(humanPlayer, bot, diler);
				setPlayerBet(humanPlayer); // ????? ?????? ??????
				takeCards(humanPlayer, coloda, 2, diler);// ????? ????? ?????
				setPlayerBet(bot); // ??? ?????? ??????
				takeCards(bot, coloda, 2, diler); // ??? ????? ?????
				takeCards(diler, coloda, 2, diler);// ????? ????? ?????
			}
			if (start.equals("n")) {
				System.out.println("Goodbye!");
				prepare = false;
				break;
			}
			if (!start.equals("n") && !start.equals("y")) {
				System.out.println("Enter y-yes, or n - no !");
			} else {
				Game.playGame(coloda, bot, humanPlayer, diler);
				if (diler.getNumCard() > 0)
					Game.endGame(bot, humanPlayer, diler);
			}
		}
	}

	public static boolean checkBank(Player humanPlayer, Player bot, Player diler) {
		boolean check = true;
		if (diler.getBank() <= 0) {
			System.out.println("Casino have no money!  You win!");
			check = false;
		}
		if (bot.getBank() <= 0) {
			System.out.println("Bot have no money! You win! ");
			check = false;
		}
		if (humanPlayer.getBank() == 0) {
			System.out.println(humanPlayer.getName() + " Have no voney! You loose!");
			check = false;
		}
		return check;
	}

	public static void takeCard(Player player, List<Card> coloda, Player diler) {
		((Diler) diler).checkColoda(coloda);
		player.step(coloda);
		((Diler) diler).del(coloda);
		player.incnumCard();
	}

	public static void takeCards(Player player, List<Card> coloda, int count, Player diler) {
		for (int i = 0; i < count; i++) {
			takeCard(player, coloda, diler);
		}
		player.setNumCard(count);
		InformationLogger.printSum(player);
		System.out.println();
	}

	public static void setPlayerBet(Player player) {
		player.makeBet();
		player.setBank(player.getBank() - player.getBet());
		System.out.println(player.getName() + " money left :" + player.getBank());
	}

	public static void prepareForGame(Player humanPlayer, Player bot, Player diler) {
		humanPlayer.setSum(0); // ???????? ????? ????? ??? ?????? ????? ????
		diler.setSum(0);
		bot.setSum(0);
	}
	
	public static void takeCardBot(Player bot, List<Card> coloda, Player diler ){
		takeCard(bot, coloda, diler);
		InformationLogger.printSum(bot);
	}

	public static void playGame(List<Card> L2, Player bot, Player humanPlayer, Player diler) {
		boolean play = true;
		while (play) {
			System.out.println();
			System.out.println("Take one more card? y/n");
			Scanner sc = new Scanner(System.in);
			String c = "";
			if (sc.hasNext()) {
				c = sc.next();
			}
			if (c.equals("y") && humanPlayer.getNumCard() < 5) {
				takeCard(humanPlayer, L2, diler);
				InformationLogger.printSum(humanPlayer);
			}
			if (c.equals("n")) { // ?????? ???????? ???
				if (diler.getSum() > 21) {
					play = false;
					break;
				}
				boolean botPlay = true;
				if (diler.getSum() <= 21 && diler.getSum() >= 19) {
					while (bot.getSum() < 19 && bot.getNumCard() <= 5) {
						takeCardBot( bot, L2,  diler );
						botPlay = false;
					}
				}

				if (diler.getSum() == 18) {
					while (bot.getSum() < 18 && bot.getNumCard() <= 5) {
						takeCardBot( bot, L2,  diler );
						botPlay = false;
					}
				}
				if (botPlay) {
					while (bot.getSum() < 17 && bot.getNumCard() <= 5) {
						takeCardBot( bot, L2,  diler );
					}
				}
				while (diler.getSum() < 17 && diler.getNumCard() <= 5) {
					takeCard(diler, L2, diler);
					InformationLogger.printSum(diler);
				}
				play = false;
				break;
			}

			if (humanPlayer.getNumCard() >= 5) {
				System.out.println();
				System.out.println(humanPlayer.getName() + " you cannot take more than 5 cards ! ");
			}
			if (!c.equals("n") && !c.equals("y")) {
				System.out.println("Enter y-yes, or n - no !");
			}
		}
	}

	public static void endGame(Player bot, Player humanPlayer, Player diler) {
		int p = humanPlayer.getSum(); // c???? ??????
		int d = diler.getSum(); // ????? ??????
		int b = bot.getSum(); // ????? ????
		InformationLogger.printSum(bot, humanPlayer, diler);
		if (p <= 21 && d <= 21) {
			if (p > d) {
				InformationLogger.printVictoryMessage(humanPlayer);
				humanPlayer.setBank(humanPlayer.getBank() + 2 * humanPlayer.getBet());
				diler.setBank(diler.getBank() - humanPlayer.getBet());
			}
			if (p < d) {
				InformationLogger.printLooserMessage(humanPlayer);
				diler.setBank(diler.getBank() + humanPlayer.getBet());
			}
			if (p == d) {
				InformationLogger.printDrawMessage(humanPlayer);
				humanPlayer.setBank(humanPlayer.getBank() + humanPlayer.getBet());
			}
		}
		if (b <= 21 && d <= 21) {
			if (b > d) {
				InformationLogger.printVictoryMessage(bot);
				bot.setBank(bot.getBank() + 2 * bot.getBet());
				diler.setBank(diler.getBank() - bot.getBet());
			}
			if (b < d) {
				InformationLogger.printLooserMessage(bot);
				diler.setBank(diler.getBank() + bot.getBet());
			}
			if (b == d) {
				InformationLogger.printDrawMessage(bot);
				bot.setBank(bot.getBank() + bot.getBet());
			}
		}
		if (p > 21) {
			InformationLogger.printLooserMessage(humanPlayer);
			diler.setBank(diler.getBank() + humanPlayer.getBet());
		}
		if (b > 21) {
			InformationLogger.printLooserMessage(bot);
			diler.setBank(diler.getBank() + bot.getBet());
		}
		if (d > 21 && p <= 21) {
			InformationLogger.printVictoryMessage(humanPlayer);
			humanPlayer.setBank(humanPlayer.getBank() + 2 * humanPlayer.getBet());
			diler.setBank(diler.getBank() - humanPlayer.getBet());
		}
		if (d > 21 && b <= 21) {
			InformationLogger.printVictoryMessage(bot);
			bot.setBank(bot.getBank() + 2 * bot.getBet());
			diler.setBank(diler.getBank() - bot.getBet());
		}
		System.out.println();
	}
}