package agents;

import QLearning.action.Action;
import QLearning.action.QTable;
import agents.states.CompetitiveState;
import blackJack.Card;

public class CompetitiveQAgent extends QAgent {
    private final Card dealerCard;
    public CompetitiveQAgent(double alpha, double gamma, double delta, double epsilon, Card dealerCard) {
        super(alpha, gamma, delta, epsilon);
        this.dealerCard = dealerCard;
        this.currentState = new CompetitiveState(this.cards, dealerCard);
        this.table.addNewState(this.currentState);
    }

    @Override
    public void react() {
        currentState = new CompetitiveState(this.cards, this.dealerCard);
        double maxQ = table.get(currentState).getActionValue();
        double q = (1 - ALPHA) * latestAction.getActionValue() +
                ALPHA * (currentState.getReward() + GAMMA * maxQ);
        latestAction.setActionValue(q);
    }

    @Override
    public String getName() {
        return "Competitive Agent";
    }
}
