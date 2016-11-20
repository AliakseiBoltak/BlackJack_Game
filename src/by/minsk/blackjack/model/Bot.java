package by.minsk.blackjack.model;
//this is clever bot
public class Bot extends Player {

	public Bot() {
		setName(Constants.BOT_NAME);
		setBank(Constants.BOT_BANK);
	}

	public int makeBet() { 
		int s = this.getBank();
		setBet((int) Math.ceil(Math.random() * s));
		System.out.println("Bot bet is: " + getBet());
		return getBet();
	}
}
