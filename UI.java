package com.zamani.javatrainings.game2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author azamani
 *
 */
public class UI {

	class Card {

		private String type;
		private String value;
		private boolean shown;

		public Card(String type, String value, boolean shown) {
			this.type = type;
			this.value = value;
			this.shown = shown;
		}

		public int getIntValue() {
			return Integer.parseInt(value);
		}

		public boolean isShown() {
			return shown;
		}

		public void setShown(boolean shown) {
			this.shown = shown;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	private static final String SIOUFA = "s";
	private static final String ZRAWTE = "z";
	private static final String TBO9A = "t";
	private static final String DHAB = "d";

	private static final String RIGHT_TABULATION = "\t\t\t\t\t\t\t\t\t\t";

	private static Scanner scanner = new Scanner(System.in);

	private static List<Card> cpuCards = new ArrayList<>();
	private static List<Card> boardCards = new ArrayList<>();
	private static List<Card> distributedCards = new ArrayList<>();
	private static List<Card> playerCards = new ArrayList<>();

	private static boolean end;
	private static int playerScore;
	private static int cpuScore;
	private static UI ui;

	static {
		ui = new UI();
		distributedCards.add(ui.new Card(DHAB, "1", false));
		distributedCards.add(ui.new Card(DHAB, "2", false));
		distributedCards.add(ui.new Card(DHAB, "3", false));
		distributedCards.add(ui.new Card(DHAB, "4", false));
		distributedCards.add(ui.new Card(DHAB, "5", false));
		distributedCards.add(ui.new Card(DHAB, "6", false));
		distributedCards.add(ui.new Card(DHAB, "7", false));
		distributedCards.add(ui.new Card(DHAB, "10", false));
		distributedCards.add(ui.new Card(DHAB, "11", false));
		distributedCards.add(ui.new Card(DHAB, "12", false));

		distributedCards.add(ui.new Card(TBO9A, "1", false));
		distributedCards.add(ui.new Card(TBO9A, "2", false));
		distributedCards.add(ui.new Card(TBO9A, "3", false));
		distributedCards.add(ui.new Card(TBO9A, "4", false));
		distributedCards.add(ui.new Card(TBO9A, "5", false));
		distributedCards.add(ui.new Card(TBO9A, "6", false));
		distributedCards.add(ui.new Card(TBO9A, "7", false));
		distributedCards.add(ui.new Card(TBO9A, "10", false));
		distributedCards.add(ui.new Card(TBO9A, "11", false));
		distributedCards.add(ui.new Card(TBO9A, "12", false));

		distributedCards.add(ui.new Card(ZRAWTE, "1", false));
		distributedCards.add(ui.new Card(ZRAWTE, "2", false));
		distributedCards.add(ui.new Card(ZRAWTE, "3", false));
		distributedCards.add(ui.new Card(ZRAWTE, "4", false));
		distributedCards.add(ui.new Card(ZRAWTE, "5", false));
		distributedCards.add(ui.new Card(ZRAWTE, "6", false));
		distributedCards.add(ui.new Card(ZRAWTE, "7", false));
		distributedCards.add(ui.new Card(ZRAWTE, "10", false));
		distributedCards.add(ui.new Card(ZRAWTE, "11", false));
		distributedCards.add(ui.new Card(ZRAWTE, "12", false));

		distributedCards.add(ui.new Card(SIOUFA, "1", false));
		distributedCards.add(ui.new Card(SIOUFA, "2", false));
		distributedCards.add(ui.new Card(SIOUFA, "3", false));
		distributedCards.add(ui.new Card(SIOUFA, "4", false));
		distributedCards.add(ui.new Card(SIOUFA, "5", false));
		distributedCards.add(ui.new Card(SIOUFA, "6", false));
		distributedCards.add(ui.new Card(SIOUFA, "7", false));
		distributedCards.add(ui.new Card(SIOUFA, "10", false));
		distributedCards.add(ui.new Card(SIOUFA, "11", false));
		distributedCards.add(ui.new Card(SIOUFA, "12", false));
	}

	private static void distribute() {
		if (!distributedCards.isEmpty()) {
			Collections.shuffle(distributedCards);
			if (distributedCards.size() <= 4) {
				System.out.println(RIGHT_TABULATION + "Distributing 2 cards...");
				distributeMe(2);
				distributeCPU(2);
			} else {
				System.out.println(RIGHT_TABULATION + "Distributing 4 cards...");
				distributeMe(4);
				distributeCPU(4);
			}
		} else {
			System.out.println("No more cards !");
		}
	}

	private static void distributeCPU(int count) {
		int i = 0;
		for (Card card : distributedCards) {
			if (i == count) {
				break;
			}
			cpuCards.add(card);
			i++;
		}
		for (Card card : cpuCards) {
			distributedCards.remove(card);
		}
	}

	private static void distributeMe(int count) {
		int i = 0;
		for (Card card : distributedCards) {
			if (i == count) {
				break;
			}
			card.setShown(true);
			playerCards.add(card);
			i++;
		}
		for (Card card : playerCards) {
			distributedCards.remove(card);
		}

	}

	private static void displayTapis() {
		display(boardCards, "Board");
	}

	private static void displayCPUHand() {
		display(cpuCards, "CPU Hand");

	}

	private static void displayPlayerHand() {
		display(playerCards, "Player Hand");
	}

	private static void display(List<Card> cards, String message) {
		System.out.println("----------" + message + "---------");
		if (cards.isEmpty()) {
			System.out.println(RIGHT_TABULATION + "No more cards for " + message);
		} else {
			int i = 0;
			for (Card card : cards) {
				if (card != null) {
					if (card.isShown()) {
						System.out.print("[" + card.getValue() + " " + card.getType() + "]\t \t");
					} else {
						System.out.print("[**]\t \t");
					}
					if (i == cards.size() - 1) {
						System.out.println("\n");
					}
				}
				i++;
			}
		}
	}

	private static void cpuPlay() {
		System.out.println(RIGHT_TABULATION + "CPU is playing ...");
		Collections.shuffle(cpuCards);
		if (!cpuCards.isEmpty()) {
			Card selectedCard = cpuCards.get(0);
			Card boardCard = existsInBoard(selectedCard);
			selectedCard.setShown(true);
			System.out.println(RIGHT_TABULATION + "CPU serve " + selectedCard.getValue() + " " + selectedCard.getType());
			if (existsInBoard(selectedCard) != null) {
				boardCards.remove(boardCard);
				if (boardCards.size() == 0) {
					System.out.println(RIGHT_TABULATION + "Missa for CPU +2");
					cpuScore += 2;
				} else {
					cpuScore++;
					System.out.println(RIGHT_TABULATION + " +1 [" + selectedCard.getIntValue() + "] for cpu");
				}
				checkSequencedCards(selectedCard);
			} else {
				boardCards.add(selectedCard);
			}
			cpuCards.remove(0);
		}
	}

	private static void playerPlay() {
		displayPlayerHand();
		String playerChoice = scanner.nextLine();
		Card selectedCard = getPlayedCard(playerChoice);
		if (selectedCard != null) {
			Card boardCard = existsInBoard(selectedCard);
			if (existsInBoard(selectedCard) != null) {
				boardCards.remove(boardCard);
				if (boardCards.isEmpty()) {
					System.out.println(RIGHT_TABULATION + "Missa for player");
					playerScore += 2;
				} else {
					System.out.println(RIGHT_TABULATION + " +1 [" + selectedCard.getIntValue() + "]");
					playerScore++;
				}
				checkSequencedCards(selectedCard);
			} else {
				boardCards.add(selectedCard);
			}
			playerCards.remove(selectedCard);
			displayPlayerHand();
		} else {
			playerPlay();
		}
	}

	private static void checkSequencedCards(Card selectedCard) {
		List<Card> seqCards = new ArrayList<>();
		List<Integer> seqInt = new ArrayList<>();
		List<Card> removableSeq = new ArrayList<>();
		for (Card card : boardCards) {
			seqInt.add(card.getIntValue());
		}
		playSeqNumbers(seqInt, selectedCard.getIntValue(), seqCards);
		for (Card card : seqCards) {
			for (Card current : boardCards) {
				if (current.getValue().equals(card.getValue()) && !checkDuplicated(card.getIntValue(),removableSeq)) {
					System.out.println(RIGHT_TABULATION + " +1 [" + card.getValue() + "]");
					removableSeq.add(current);
					playerScore++;
				}
			}
		}
		pick(removableSeq);
	}

	private static boolean checkDuplicated(int value, List<Card> removableSeq) {
		boolean exists  = false;
		for (Card card : removableSeq) {
			if( card.getIntValue() == value) {
				 exists = true;
			}
		}
		return exists;
	}

	private static void pick(List<Card> values) {
		for (Card card : values) {
			boardCards.remove(card);
		}
	}

	private static Card existsInBoard(Card card) {
		Card existingCard = null;
		for (Card boardCard : boardCards) {
			if (card.getValue().equals(boardCard.getValue())) {
				existingCard = boardCard;
			}
		}
		return existingCard;
	}

	private static Card getPlayedCard(String playerChoice) {
		Card selectedCard = null;
		for (Card card : playerCards) {
			String[] playStrArray = playerChoice.split(" ");
			if (playStrArray.length == 2) {
				if (card.getValue().equals(playStrArray[0]) && card.getType().equals(playStrArray[1])) {
					selectedCard = card;
					System.out.println(RIGHT_TABULATION + "Player 1 serve " + selectedCard.getValue() + " " + selectedCard.getType());
				}
			} else {
				System.out.println("Wrong value");
			}
		}
		return selectedCard;
	}

	public static void main(String[] args) {
		startGame();
	}

	public static int playSeqNumbers(List<Integer> list, int number, List<Card> seqCards) {
		if (list.contains(number + 1) || (number == 7 && list.contains(number + 3))) {
			if (number == 7) {
				seqCards.add(ui.new Card("?", String.valueOf((number + 3)), false));
				return playSeqNumbers(list, number + 3, seqCards);
			} else {
				seqCards.add(ui.new Card("?", String.valueOf((number + 1)), false));
				return playSeqNumbers(list, number + 1, seqCards);
			}
		} else {
			return number;
		}
	}

	private static void startGame() {
		System.out.println(RIGHT_TABULATION + "###################### RONDA #########################");
		System.out.println(RIGHT_TABULATION + "s : Sioufa | z : Zrawte | t : Tba9 | d : Dhab");
		System.out.println(RIGHT_TABULATION + "######################################################");
		distribute();
		distributeBoard();
		displayCPUHand();
		displayTapis();
		doGameLoop();
		System.out.println(RIGHT_TABULATION + "Score : You " + playerScore + " - CPU : " + cpuScore);
		if (playerScore > cpuScore) {
			System.out.println("########################################### You win ###########################################");
		} else if (playerScore < cpuScore) {
			System.out.println("CPU win");
		} else {
			System.out.println("Deuce");
		}
	}

	private static void doGameLoop() {
		int i = 0;
		while (!end) {
			if (boardCards.isEmpty()) {
				distributeBoard();
			}
			if (i % 2 == 0) {
				playerPlay();
			} else {
				cpuPlay();
				displayCPUHand();
				displayTapis();
			}
			if (playerCards.isEmpty() && cpuCards.isEmpty() && !distributedCards.isEmpty()) {
				distribute();
			}
			if (cpuCards.isEmpty() && playerCards.isEmpty() && distributedCards.isEmpty()) {
				end = true;
			}
			i++;
		}
	}

	private static void distributeBoard() {
		if (!distributedCards.isEmpty()) {
			int i = 0;
			for (Card card : distributedCards) {
				if (i == 4) {
					break;
				}
				card.setShown(true);
				boardCards.add(card);
				i++;
			}
			for (Card card : boardCards) {
				distributedCards.remove(card);
			}
		} else {
			System.out.println("No more cards !");
		}
	}

}
