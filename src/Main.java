import agents.Player;
import blackJack.Blackjack;
import blackJack.Deck;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean continuePlaying = true;
        while (continuePlaying) {
            Blackjack game = new Blackjack(new Player());
            boolean gamePlaying = game.startGame();
            while (gamePlaying) {
                gamePlaying = game.round();
            }
            Scanner in = new Scanner(System.in);
            boolean loop = true;
            while (loop) {
                System.out.println("\nWould you like to play again?");
                System.out.print("\nY/N: ");
                String command = in.nextLine().toLowerCase();
                if (!command.equals("y") && !command.equals("n")) {
                    System.out.println("Invalid command.");
                } else {
                    if (command.equals("n")) {
                        continuePlaying = false;
                    }
                    loop = false;
                }
            }
        }
    }
}