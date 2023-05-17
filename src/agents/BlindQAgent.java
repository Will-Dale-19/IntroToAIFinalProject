package agents;

import agents.states.BlindState;

public class BlindQAgent extends QAgent {

    public BlindQAgent(double alpha, double gamma, double delta, double epsilon) {
        super(alpha, gamma, delta, epsilon);
        this.currentState = new BlindState(this.cards);
        this.table.addNewState(this.currentState);
    }

    @Override
    public void react() {
        currentState = new BlindState(this.cards);
        this.table.addNewState(currentState);
        if (latestAction == null && this.score() == 21) {
            this.table.updateReward(currentState, 100);
        } else {
            double maxQ = table.get(currentState).getActionValue();

            double q = (1 - ALPHA) * latestAction.getActionValue() +
                    ALPHA * (currentState.getReward() + GAMMA * maxQ);
            latestAction.setActionValue(q);
        }
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return "Blind Agent";
    }


}
