package agents.states;

import blackJack.Card;

import java.util.List;
import java.util.Optional;

public class CompetitiveState extends State {
    private final Card dealerCard;
    public CompetitiveState(List<Card> hand, Card dealerCard) {
        super(hand);
        this.dealerCard = dealerCard;
    }

    @Override
    public int hashCode() {
        return ("" + (int)this.value + this.hand.size() + dealerCard.getValue()).hashCode();
    }



    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("(").append(this.size).append(", ").append(this.value).append(", ")
                .append(this.dealerCard.getValue()).append("):").append(this.reward);
        return s.toString();
    }
}
