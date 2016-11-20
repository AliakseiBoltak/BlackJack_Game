package by.minsk.blackjack.model;

import java.util.LinkedList;
import java.util.Collections;
import java.util.List;


//bububub

public class Diler extends Player {

	public Diler() {
		setName(Constants.DILER_NAME);
		setBank(Constants.DILER_BANK);
	}

	public List createDeck(List<Card> coloda) {
		coloda.add(new Card("Valet bubnovyi", 2));
		coloda.add(new Card("Valet chervovyi", 2));
		coloda.add(new Card("Valet pikovyi", 2));
		coloda.add(new Card("Valet krestovyi", 2));
		coloda.add(new Card("Dama bubnovaya", 3));
		coloda.add(new Card("Dama chervovaya", 3));
		coloda.add(new Card("Dama pikovaya", 3));
		coloda.add(new Card("Dama krestovaya", 3));
		coloda.add(new Card("Korol chervovyi", 4));
		coloda.add(new Card("Korol krestovyi", 4));
		coloda.add(new Card("Korol bybnovyi", 4));
		coloda.add(new Card("Korol pikovyi", 4));
		coloda.add(new Card("Tuz pikovyi", 11));
		coloda.add(new Card("Tuz bybnovyi", 11));
		coloda.add(new Card("Tuz krestovyi", 11));
		coloda.add(new Card("Tuz chervovyi", 11));
		coloda.add(new Card("Shesterka pikovaya", 6));
		coloda.add(new Card("Shesterka bybnovaya", 6));
		coloda.add(new Card("Shesterka krestovaya", 6));
		coloda.add(new Card("Shesterka chervovaya", 6));
		coloda.add(new Card("Semerka pikovaya", 7));
		coloda.add(new Card("Semerka bybnovaya", 7));
		coloda.add(new Card("Semerka krestovaya", 7));
		coloda.add(new Card("Semerka chervovaya", 7));
		coloda.add(new Card("Vosmerka pikovaya", 8));
		coloda.add(new Card("Vosmerka bybnovaya", 8));
		coloda.add(new Card("Vosmerka krestovaya", 8));
		coloda.add(new Card("Vosmerka chervovaya", 8));
		coloda.add(new Card("Devyatka pikovaya", 9));
		coloda.add(new Card("Devyatka bybnovaya", 9));
		coloda.add(new Card("Devyatka krestovaya", 9));
		coloda.add(new Card("Devyatka chervovaya", 9));
		coloda.add(new Card("Desyatka pikovaya", 10));
		coloda.add(new Card("Desyatka bybnovaya", 10));
		coloda.add(new Card("Desyatka krestovaya", 10));
		coloda.add(new Card("Desyatka chervovaya", 10));
		return coloda;
	}

	public void shuffleDeck(List<Card> coloda) {
		Collections.shuffle(coloda);

	}

	public void showDeck(List<Card> coloda) {
		for (Card c : coloda) {
			System.out.println(c);
		}
	}

	public void checkColoda(List<Card> st) {
		if (st.isEmpty()) {
			System.out.println();
			System.out.println("Deck is empty, diler create new deck !");
			System.out.println();
			List<Card> coloda = new LinkedList<Card>();
			this.createDeck(coloda);
			this.shuffleDeck(coloda);
			st.addAll(coloda);
		}
	}

	public void del(List<Card> coloda) {
		checkColoda(coloda);
		coloda.remove(0);
		checkColoda(coloda);
	}

	public int makeBet() {
		System.out.println();
		return 0;
	}
}
