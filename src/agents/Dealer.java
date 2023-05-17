package agents;

import blackJack.Card;

import java.util.ArrayList;

public class Dealer extends Agent {

    private boolean takingTurn = false;

    public Dealer(boolean print) {
        super(print);
    }

    public void flipCard() {
        if (print) {
            System.out.println("The dealer flips over a " + this.cards.get(0) + "!");
        }
        takingTurn = true;
    }

    @Override
    public char takeTurn() {
        if (print) {
            System.out.println("It's the dealer's turn.");
        }
        if (!takingTurn) {
            flipCard();
            printCards();
        }
        if (!isStanding()) {
            char move = (score() < 17) ? 'h' : 's';
            if (move == 'h') {
                if (print) {
                    System.out.println("The dealer has decided to hit.");
                }
            } else {
                if (print) {
                    System.out.println("The dealer has decided to stand.");
                }
            }
            return move;
        } else {
            if (print) {
                System.out.println("The dealer is standing.");
            }
            return ' ';
        }
    }

    @Override
    public void win() {
        if (print) {
            System.out.println("The dealer has won!");
        }
    }

    @Override
    public void lose() {
        if (print) {
            System.out.println("The dealer has lost!");
        }
    }

    @Override
    public void tie() {
        if (print) {
            System.out.println();
        }
    }

    @Override
    public void react() {
    }

    @Override
    public void printCards() {
        if (print) {
            System.out.print("The dealers cards: " + (takingTurn ? this.cards.get(0) + " " : "?? "));
            for (int i = 1; i < this.cards.size(); i++) {
                System.out.print(this.cards.get(i) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String getName() {
        return "Dealer";
    }

    @Override
    public void printCardDrawn() {
        if (print) {
            System.out.println("The dealer drew a " + this.cards.get(this.cards.size() - 1));
        }
    }

    /**
     * This method gets the card that would be visible to the other players while they take their turn.
     * @return A card
     */
    public Card getVisibleCard() {
        return this.cards.get(1);
    }

    @Override
    public void reset() {
        super.reset();
        this.takingTurn = false;
    }
}