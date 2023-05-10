package blackJack;

import agents.*;

public class Blackjack {
    private final Deck deck;
    private final Agent agent;
    private final Dealer dealer;

    public Blackjack(Agent agent) {
        this.deck = new Deck();
        deck.shuffleDeck();
        this.agent = agent;
        this.dealer = new Dealer();
    }

    public boolean startGame() {
        Card[] dealerCards = this.deck.dealTopCards(2);
        for (Card card : dealerCards) {
            this.dealer.addCard(card);
        }
        Card[] agentCards = this.deck.dealTopCards(2);
        for (Card card : agentCards) {
            this.agent.addCard(card);
        }
        return evaluateGame();
    }

    public boolean round() {
        boolean evaluation;
        this.agent.printCards();
        this.dealer.printCards();
        switch (this.agent.takeTurn()) {
            case 'h' -> {
                this.agent.addCard(this.deck.dealTopCard());
                this.agent.printCardDrawn();
            }
            case 's' -> this.agent.stand(true);
            default -> {
            }
        }
        evaluation = evaluateGame();
        if (!evaluation) {
            return false;
        }
        switch (this.dealer.takeTurn()) {
            case 'h' -> {
                this.dealer.addCard(this.deck.dealTopCard());
                this.dealer.printCardDrawn();
            }
            case 's' -> this.dealer.stand(true);
            default -> {
            }
        }
        evaluation = evaluateGame();
        return evaluation;
    }

    private void printScore() {
        System.out.println("Dealer's score: " + this.dealer.score());
        System.out.println("Agent's score: " + this.agent.score());
    }

    private boolean evaluateGame() {
        if (this.agent.score() == 21) {
            System.out.println("The game is over. The agent got to 21!");
            this.agent.win();
            this.dealer.lose();
            printScore();
            return false;
        } else if (this.dealer.score() == 21) {
            System.out.println("The game is over. The dealer got to 21!");
            this.dealer.win();
            this.agent.lose();
            printScore();
            return false;
        } else if (this.agent.score() > 21) {
            System.out.println("The game is over. The agent has gone over 21!");
            this.agent.lose();
            this.dealer.win();
            printScore();
            return false;
        } else if (this.dealer.score() > 21) {
            System.out.println("The game is over. The dealer has gone over 21!");
            this.dealer.lose();
            this.agent.win();
            printScore();
            return false;
        } else if (this.dealer.isStanding() && this.agent.isStanding()) {
            System.out.println("The game is over. Both players are standing!");
            if (this.dealer.score() > this.agent.score()) {
                this.dealer.win();
                this.agent.lose();
                printScore();
                return false;
            } else if (this.dealer.score() < this.agent.score()) {
                this.agent.win();
                this.dealer.lose();
                printScore();
                return false;
            } else {
                this.agent.tie();
                printScore();
                return false;
            }
        }
        return true;
    }
}
