package agents;

import QLearning.action.Action;
import agents.states.BlindState;

import java.io.Serializable;

public class BlindQAgent extends QAgent implements Serializable {

    public BlindQAgent(boolean print, double alpha, double gamma, double delta, double epsilon) {
        super(print, alpha, gamma, delta, epsilon);
    }

    @Override
    public void react() {
        currentState = new BlindState(this.cards);
        this.table.addNewState(currentState);
        if (latestAction == null && this.score() == 21) {
            this.table.updateReward(currentState, 100);
        } else {
            double maxQ = table.get(currentState)
                    .stream()
                    .map(Action::getActionValue)
                    .max(Double::compareTo).orElseThrow();
            double q = ((1 - ALPHA) * latestAction.getActionValue()) +
                    (ALPHA * (currentState.getReward() + (GAMMA * maxQ)));
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

    public void initializeState() {
        this.currentState = new BlindState(this.cards);
        this.table.addNewState(this.currentState);
    }
}
