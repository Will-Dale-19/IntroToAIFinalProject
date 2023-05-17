package agents;

import blackJack.Card;

import java.util.ArrayList;

public class Dealer extends Agent {

    private boolean takingTurn = false;

    public void flipCard() {
        System.out.println("The dealer flips over a " + this.cards.get(0) + "!");
        takingTurn = true;
    }

    @Override
    public char takeTurn() {
        System.out.println("It's the dealer's turn.");
        if (!takingTurn) {
            flipCard();
            printCards();
        }
        if (!isStanding()) {
            char move = (score() < 17) ? 'h' : 's';
            if (move == 'h') {
                System.out.println("The dealer has decided to hit.");
            } else {
                System.out.println("The dealer has decided to stand.");
            }
            return move;
        } else {
            System.out.println("The dealer is standing.");
            return ' ';
        }
    }

    @Override
    public void win() {
        System.out.println("The dealer has won!");
    }

    @Override
    public void lose() {
        System.out.println("The dealer has lost!");
    }

    @Override
    public void tie() {
        System.out.println();
    }

    @Override
    public void react() {
        if (!standing) {
            System.out.println("Dealer was dealt a " + this.cards.get(this.cards.size() - 1));
        } else {
            System.out.println("Dealer is standing.");
        }
    }

    @Override
    public void printCards() {
        System.out.print("The dealers cards: " + (takingTurn ? this.cards.get(0) + " " : "?? "));
        for (int i = 1; i < this.cards.size(); i++) {
            System.out.print(this.cards.get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public String getName() {
        return "Dealer";
    }

    @Override
    public void printCardDrawn() {
        System.out.println("The dealer drew a " + this.cards.get(this.cards.size() - 1));
    }
}