package agents.states;

import blackJack.Card;

import java.util.List;

public class BlindState extends State {

    public BlindState(List<Card> hand) {
        super(hand);
    }

    @Override
    public int hashCode() {
        return ("" + (int) this.value + this.size).hashCode();
    }



    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("(").append(this.size).append(", ").append(this.value).append("):")
                .append(this.reward);
        return s.toString();
    }
}
