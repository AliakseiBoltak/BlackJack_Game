package by.minsk.blackjack.model;

public class Bot extends Player {

	public Bot() {
		setName(Constants.BOT_NAME);
		setBank(Constants.BOT_BANK);
	}

	public int makeBet() { // бот делает ставку рандомно
		int s = this.getBank();
		setBet((int) Math.ceil(Math.random() * s));
		System.out.println("Bot bet is: " + getBet());
		return getBet();
	}
}