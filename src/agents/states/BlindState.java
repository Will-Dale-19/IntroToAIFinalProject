package agents.states;

import blackJack.Card;

import java.util.List;
import java.util.Optional;

public class BlindState extends State {
    private final List<Card> hand;

    public BlindState(List<Card> hand) {
        this.hand = hand;
        this.calculateValue();
    }

    @Override
    protected void calculateValue() {
        Optional<Integer> val = this.hand.stream().map(Card::getValue).reduce(Integer::sum);
        if (val.isPresent()) {
            this.value = val.get();
        } else {
            throw new RuntimeException("Unknown error occurred when calculating state's value.");
        }
    }

    @Override
    public int hashCode() {
        return Double.valueOf(this.value).hashCode();
    }
}
