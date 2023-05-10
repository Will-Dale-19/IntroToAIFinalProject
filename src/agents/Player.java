package agents;

import blackJack.Card;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Agent {

    public Player() {
        this.standing = false;
        this.cards = new ArrayList<>();
    }



    @Override
    public char takeTurn() {
        System.out.print("\nIt's your turn. ");
        if (!isStanding()) {
            System.out.println("What would you like to do?");
            Scanner in = new Scanner(System.in);
            boolean loop = true;
            char move = ' ';
            while (loop) {
                System.out.print("Enter h to hit, s to stand: ");
                String command = in.nextLine().toLowerCase();
                if (!command.equals("h") && !command.equals("s")) {
                    System.out.println("Invalid command.");
                } else {
                    loop = false;
                    move = command.charAt(0);
                }
            }
            return move;
        } else {
            System.out.println("You are standing.");
            return ' ';
        }
    }

    @Override
    public void printCards() {
        System.out.print("Your cards: ");
        for (Card card : cards) {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    @Override
    public void win() {
        System.out.println("You have won!");
    }

    @Override
    public void lose() {
        System.out.println("You have lost!");
    }

    @Override
    public void tie() {
        System.out.println("You tied with the dealer!");
    }

    @Override
    public void printCardDrawn() {
        System.out.println("You drew a " + this.cards.get(this.cards.size() - 1));
    }
}
