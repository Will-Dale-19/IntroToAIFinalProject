package agents.states;

import blackJack.Card;

import java.util.List;

public class BlindState extends State {

    public BlindState(List<Card> hand) {
        super(hand);
        this.calculateValue();
    }

    @Override
    public int hashCode() {
        return ("" + (int) this.value + this.size).hashCode();
    }
}
