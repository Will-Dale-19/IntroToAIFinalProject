package agents;

import QLearning.action.Action;
import QLearning.action.QTable;
import agents.states.BlindState;
import agents.states.CompetitiveState;
import blackJack.Card;

import java.util.Comparator;

public class CompetitiveQAgent extends QAgent {
    private Card dealerCard;
    public CompetitiveQAgent(double alpha, double gamma, double delta, double epsilon) {
        super(alpha, gamma, delta, epsilon);
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
            System.out.println(maxQ);
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
