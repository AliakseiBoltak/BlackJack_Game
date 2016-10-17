package by.minsk.blackjack.model;

import java.util.LinkedList;
import java.util.List;

public abstract class Player {
	private int sum;
	private int numCard;
	private int bet;
	private String name;
	private int bank;

	public abstract int makeBet();

	public List step(List<Card> st) {
		Card card = st.get(0);
		this.setSum(sum += card.getValue());
		System.out.println(this.getName() + " new card  :");
		System.out.println(card);
		return st;
	}

	public int incnumCard() {
		numCard++;
		return numCard;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getNumCard() {
		return numCard;
	}

	public void setNumCard(int numCard) {
		this.numCard = numCard;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}
}
