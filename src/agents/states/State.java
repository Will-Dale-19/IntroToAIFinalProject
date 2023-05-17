package agents.states;

import blackJack.Card;

import java.util.*;

public abstract class State {
    protected double value;
    protected final List<Card> hand;
    protected int size;
    protected double reward;

    public State(List<Card> hand) {
        this.hand = new LinkedList<>();
        Collections.copy(this.hand, hand); // make a deep copy to keep states atomic
        this.reward = 0;
    }

    public double getValue() {
        return value;
    }

    public double getReward() {
        return this.reward;
    }

    public void updateReward(double delta) {
        this.reward += delta;
    }

    protected void calculateValue() {
        int score = this.hand.stream()
                .map(Card::getValue)
                .reduce(Integer::sum)
                .orElse(0);
        if (score > 21) {
            for (int i = 0; i < this.hand.size() && score > 21; i++) {
                if (this.hand.get(i).getValue() == 11) {
                    score -= 10;
                }
            }
        }
        this.value = score <= 21 ? score : 22;
        this.size = this.hand.size();
    }

    /**
     * States must override the hashCode function, and states that are meant to
     * be equivalent are
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null){
            return false;
        }
        if (this.getClass() != o.getClass()){
            return false;
        }
        return this.hashCode() == o.hashCode();
    };

    @Override
    public abstract int hashCode();
}
