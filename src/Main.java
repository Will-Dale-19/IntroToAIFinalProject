import agents.Player;
import agents.QAgent;
import blackJack.Blackjack;

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
                    QAgent ai = Test.loadQAgent();
                    ai.setPrint(true);
                    game = new Blackjack(true, new Player(true, "Player 1"), ai);

                    break;
                case 2:
                    game = new Blackjack(true, new Player(true, "Player 1"), new Player(true, "Player 2"));
                    break;
                case 3:
                    game = new Blackjack(true, new Player(true, "Player 1"), new Player(true, "Player 2"), new Player(true, "Player 3"));
                    break;
                default:
                    game = new Blackjack(true, new Player(true, "Player"));
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