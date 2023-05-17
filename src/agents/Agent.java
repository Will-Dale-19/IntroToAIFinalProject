package agents;

import blackJack.Card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Agent {

    protected boolean standing;
    protected List<Card> cards;
    protected boolean canPlay;

    public Agent() {
        reset();
    }

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

    public boolean canPlay() {
        return this.canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    public boolean isStanding() {
        return this.standing;
    }

    public abstract void react();

    public abstract char takeTurn();

    public abstract void win();

    public abstract void lose();

    public abstract void printCardDrawn();

    public abstract void tie();

    public abstract void printCards();

    public abstract String getName();

    public void reset() {
        this.cards = new LinkedList<>();
        this.canPlay = true;
        this.standing = false;
    }
}
