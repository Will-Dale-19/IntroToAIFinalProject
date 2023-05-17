package agents;

import QLearning.action.Action;
import QLearning.action.QTable;
import agents.states.BlindState;

public class BlindQAgent extends Agent {

    private BlindState currentState;
    private QTable<BlindState> table;
    private final double ALPHA, GAMMA, DELTA, EPSILON;
    public BlindQAgent(double alpha, double gamma, double delta, double epsilon) {
        this.ALPHA = alpha;
        this.GAMMA = gamma;
        this.DELTA = delta;
        this.EPSILON = epsilon;
    }

    /**
     * @return
     */
    @Override
    public char takeTurn() {
        Action action = this.table.get(currentState);
        double q = (1 - ALPHA) * action.getActionValue() + ALPHA * (1); // TODO: acutally implement
        return action.getAction();
    }

    /**
     *
     */
    @Override
    public void win() {
        this.table.updateReward(this.currentState, DELTA);
    }

    /**
     *
     */
    @Override
    public void lose() {
        this.table.updateReward(this.currentState, -DELTA);
    }

    /**
     *
     */
    @Override
    public void printCardDrawn() {

    }

    /**
     *
     */
    @Override
    public void tie() {

    }

    /**
     *
     */
    @Override
    public void printCards() {

    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return "Blind Agent";
    }
}
