import QLearning.action.QTable;
import agents.*;
import agents.states.BlindState;
import blackJack.Blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        boolean continueTesting = true;
        while (continueTesting) {
            Blackjack game = new Blackjack();
            int numEpochs = 0;
            Scanner in = new Scanner(System.in);
            boolean invalid = true;
            while (invalid) {
                System.out.print("Please enter the number of epochs: ");
                String nl = in.nextLine();
                try {
                    numEpochs = Integer.parseInt(nl);
                    invalid = false;
                } catch (NumberFormatException e) {
                    System.out.println("Value entered not a number. Try again.");
                    invalid = true;
                }
            }
            Agent ai;
            invalid = true;
            String currAgentString = "";
            while (invalid) {
                System.out.print("Please select the QAgent to be tested ((B)lind/(C)ompetitive): ");
                String nl = in.nextLine().toLowerCase();
                if (!(nl.equals("blind") || nl.equals("b") || nl.equals("competitive") || nl.equals("c"))) {
                    invalid = true;
                    System.out.println("Invalid selection. Try again.");
                } else {
                    currAgentString = nl;
                    invalid = false;
                }
            }
            invalid = true;
            double[] parameters = {0.0, 0.0, 0.0, 0.0};
            System.out.println("Please enter your hyperparameters for your Q-Learning Algorithm:");
            // alpha
            while (invalid) {
                System.out.print("\tALPHA: ");
                String param = in.nextLine();
                try {
                    parameters[0] = Double.parseDouble(param);
                    invalid = false;
                } catch (NumberFormatException e) {
                    System.out.println("Value entered not a number. Try again.");
                    invalid = true;
                }
            }
            invalid = true;
            // gamma
            while (invalid) {
                System.out.print("\tGAMMA: ");
                String param = in.nextLine();
                try {
                    parameters[1] = Double.parseDouble(param);
                    invalid = false;
                } catch (NumberFormatException e) {
                    System.out.println("Value entered not a number. Try again.");
                    invalid = true;
                }
            }
            invalid = true;
            // delta
            while (invalid) {
                System.out.print("\tDELTA: ");
                String param = in.nextLine();
                try {
                    parameters[2] = Double.parseDouble(param);
                    invalid = false;
                } catch (NumberFormatException e) {
                    System.out.println("Value entered not a number. Try again.");
                    invalid = true;
                }
            }
            invalid = true;
            // epsilon
            while (invalid) {
                System.out.print("\tEPSILON: ");
                String param = in.nextLine();
                try {
                    parameters[3] = Double.parseDouble(param);
                    invalid = false;
                } catch (NumberFormatException e) {
                    System.out.println("Value entered not a number. Try again.");
                    invalid = true;
                }
            }
            switch (currAgentString) {
                case "b", "blind":
                    ai = new BlindQAgent(parameters[0], parameters[1], parameters[2], parameters[3]);
                    game.addAgents(ai);
                    break;
                case "c", "competitive":
                    ai = new CompetitiveQAgent(parameters[0], parameters[1], parameters[2], parameters[3]);
                    game.addAgents(ai);
                    break;
                default:
                    ai = new Dealer();
            }
            for (int i = 0; i < numEpochs; i++) {
                game.startGame();
                if (ai instanceof CompetitiveQAgent) {
                    ((CompetitiveQAgent) ai).setDealerCard(game.getDealerCard());
                }
                boolean gamePlaying = true;
                while (gamePlaying) {
                    gamePlaying = game.round();
                }
            }
            boolean loop = true;
            while (loop) {
                System.out.println("\nWould you like to test again?");
                System.out.print("\nY/N: ");
                String command = in.nextLine().toLowerCase();
                if (!command.equals("y") && !command.equals("n")) {
                    System.out.println("Invalid command.");
                } else {
                    if (command.equals("n")) {
                        continueTesting = false;
                    }
                    loop = false;
                }
            }
            System.out.println(ai);
        }
    }
}
