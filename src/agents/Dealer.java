package agents;

import blackJack.Card;

import java.util.ArrayList;

public class Dealer extends Agent {

    public Dealer() {
        this.standing = false;
        this.cards = new ArrayList<>();
    }

    @Override
    public char takeTurn() {
        System.out.print("It's the dealer's turn. ");
        if (!isStanding()) {
            char move = (score() < 17) ? 'h' : 's';
            if (move == 'h') {
                System.out.println("\nThe dealer has decided to hit.");
            } else {
                System.out.println("\nThe dealer has decided to stand.");
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
    public void printCards() {
        System.out.print("The dealers cards: ?? ");
        for (int i = 1; i < this.cards.size(); i++) {
            System.out.print(this.cards.get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public void printCardDrawn() {
        System.out.println("The dealer drew a " + this.cards.get(this.cards.size() - 1));
    }
}