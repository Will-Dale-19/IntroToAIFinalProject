package agents;

import blackJack.Card;

import java.util.ArrayList;

public abstract class Agent {

    protected boolean standing;
    protected ArrayList<Card> cards;

    public int score() {
        int score = 0;
        for (Card card : cards) {
            score += card.getValue();
        }
        if (score > 21) {
            for (int i = 0; i < cards.size() && score > 21; i++) {
                if (cards.get(i).getValue() == 11) {
                    score -= 10;
                }
            }
        }
        return score;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void stand(boolean standing) {
        this.standing = standing;
    }

    public boolean isStanding() {
        return this.standing;
    }

    public abstract char takeTurn();

    public abstract void win();

    public abstract void lose();

    public abstract void printCardDrawn();

    public abstract void tie();

    public abstract void printCards();
}
