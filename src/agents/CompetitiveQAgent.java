package agents;

import QLearning.action.Action;
import QLearning.action.QTable;
import agents.states.BlindState;
import agents.states.CompetitiveState;
import blackJack.Card;

import java.io.Serializable;
import java.util.Comparator;

public class CompetitiveQAgent extends QAgent implements Serializable {
    private Card dealerCard;
    public CompetitiveQAgent(boolean print, double alpha, double gamma, double delta, double epsilon) {
        super(print, alpha, gamma, delta, epsilon);
    }

    @Override
    public void react() {
        currentState = new CompetitiveState(this.cards, this.dealerCard);
        this.table.addNewState(currentState);
        if (latestAction == null && this.score() == 21) {
            this.table.updateReward(currentState, 100);
        } else {
            double maxQ = table.get(currentState)
                    .stream()
                    .map(Action::getActionValue)
                    .max(Double::compareTo).orElseThrow();
            double q = (1 - ALPHA) * latestAction.getActionValue() +
                    ALPHA * (currentState.getReward() + GAMMA * maxQ);
            latestAction.setActionValue(q);
        }
    }

    @Override
    public String getName() {
        return "Competitive Agent";
    }

    public void setDealerCard(Card card) {
        this.dealerCard = card;
        this.currentState = new CompetitiveState(this.cards, dealerCard);
        this.table.addNewState(this.currentState);
    }
}
