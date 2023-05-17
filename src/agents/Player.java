package agents;

import blackJack.Card;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Agent {

    private final String name;

    public Player(boolean print, String name) {
        super(print);
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public char takeTurn() {
        System.out.print("\nIt's " + this.name + "'s turn. ");
        if (!isStanding()) {
            System.out.println("What would they like to do?");
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
            System.out.println("They are standing.");
            return ' ';
        }
    }

    @Override
    public void react() {
    }

    @Override
    public void printCards() {
        System.out.print(this.name + "'s cards: ");
        for (Card card : cards) {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    @Override
    public void win() {
        System.out.println(this.name + " has won!");
    }

    @Override
    public void lose() {
        System.out.println(this.name + " has lost!");
    }

    @Override
    public void tie() {
        System.out.println(this.name + " tied with the dealer!");
    }

    @Override
    public void printCardDrawn() {
        System.out.println("\n" + this.name + " drew a " + this.cards.get(this.cards.size() - 1));
        System.out.println();
    }
}
