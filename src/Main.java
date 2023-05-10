import agents.Player;
import blackJack.Blackjack;
import blackJack.Deck;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean continuePlaying = true;
        while (continuePlaying) {
            System.out.println("How many players would you like? (1-3)");
            Scanner in = new Scanner(System.in);
            int numPlayers = Integer.parseInt(in.nextLine());
            Blackjack game;
            switch (numPlayers) {
                case 1:
                    game = new Blackjack(new Player("Player 1"));
                    break;
                case 2:
                    game = new Blackjack(new Player("Player 1"), new Player("Player 2"));
                    break;
                case 3:
                    game = new Blackjack(new Player("Player 1"), new Player("Player 2"), new Player("Player 3"));
                    break;
                default:
                    game = new Blackjack(new Player("Player"));
            }
            game.startGame();
            boolean gamePlaying = true;
            while (gamePlaying) {
                gamePlaying = game.round();
            }
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