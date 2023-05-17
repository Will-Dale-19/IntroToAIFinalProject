package blackJack;

import agents.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Blackjack {
    private Deck deck;
    private final ArrayList<Agent> agents = new ArrayList<>();
    private final Dealer dealer;

    public Blackjack(Agent... agents) {
        this.deck = new Deck();
        deck.shuffleDeck();
        this.agents.addAll(Arrays.asList(agents));
        this.dealer = new Dealer();
    }

    public void startGame() {
        resetGame();
        for (Agent agent : this.agents) {
            agent.addCard(this.deck.dealTopCard());
        }
        dealer.addCard(this.deck.dealTopCard());
        for (Agent agent : this.agents) {
            agent.addCard(this.deck.dealTopCard());
        }
        dealer.addCard(this.deck.dealTopCard());
    }

    private void resetGame() {
        this.deck = new Deck();
        this.deck.shuffleDeck();
        for (Agent agent : this.agents) {
            agent.reset();
        }
        this.dealer.reset();
    }

    public boolean round() {
        printAllCards();
        for (Agent agent : this.agents) {
            if (agent.score() == 21) {
                if (agent.canPlay()) {
                    agent.setCanPlay(false);
                }
            }
            if (agent.canPlay()) {
                char move = agent.takeTurn();
                switch (move) {
                    case 'h':
                        agent.addCard(this.deck.dealTopCard());
                        agent.printCardDrawn();
                        if (agent.score() > 21) {
                            System.out.println("\n" + agent.getName() + " went over 21!\n");
                            agent.setCanPlay(false);
                        }
                        if (agent.score() == 21) {
                            System.out.println("\n" + agent.getName() + " got to 21!\n");
                            agent.setCanPlay(false);
                        }
                        break;
                    case 's':
                        System.out.println("\n" + agent.getName() + " has chosen to stand.\n");
                        agent.setCanPlay(false);
                        agent.stand(true);
                        break;
                    default:
                        break;
                }
            }
            agent.react();
        }
        if (this.agents.stream().allMatch(x -> x.isStanding() || x.score() >= 21 || !x.canPlay())) {
            this.dealer.printCards();
            char move = this.dealer.takeTurn();
            switch (move) {
                case 'h':
                    dealer.addCard(this.deck.dealTopCard());
                    dealer.printCardDrawn();
                    break;
                case 's':
                    if (dealer.score() == 21) {
                        System.out.println("The Dealer got to 21!");
                    }
                    dealer.stand(true);
                    break;
                default:
                    break;
            }
            if (dealer.isStanding() || dealer.score() >= 21) {
                for (Agent agent : this.agents) {
                    evaluateGame(agent);
                }
                printScore();
                return false;
            }
        }
        return true;
    }

    private void printAllCards() {
        this.dealer.printCards();
        for (Agent agent : agents) {
            agent.printCards();
        }
    }

    private void printScore() {
        System.out.println("\nDealer's score: " + this.dealer.score());
        for (Agent agent : this.agents) {
            System.out.println(agent.getName() + "'s score: " + agent.score());
        }
    }

    private void evaluateGame(Agent agent) {
        System.out.println();
        if (agent.score() == 21) {
            if (dealer.score() == 21) {
                agent.tie();
            } else {
                agent.win();
            }
        } else if (agent.score() > 21) {
            System.out.println(agent.getName() + " busted!");
            agent.lose();
        } else if (dealer.score() == 21) {
            System.out.println("The Dealer has reached 21!");
            agent.lose();
        } else if (dealer.score() > 21) {
            System.out.println("The Dealer busted!");
            agent.win();
        } else {
            if (dealer.score() > agent.score()) {
                System.out.println("The dealer's score is greater than " + agent.getName() + "'s score!");
                agent.lose();
            } else if (dealer.score() == agent.score()) {
                System.out.println("The dealer's score is equal to " + agent.getName() + "'s score!");
                agent.tie();
            } else {
                System.out.println("The dealer's score is less than " + agent.getName() + "'s score!");
                agent.win();
            }
        }
    }

    /**
     * This method returns the dealer's visible card.
     * @return A Card
     */
    public Card getDealerCard() {
        return dealer.getVisibleCard();
    }

    /**
     * This method adds agents to the game.
     * @param agents Any number of agents
     */
    public void addAgents(Agent... agents) {
        this.agents.addAll(Arrays.asList(agents));
    }
}
