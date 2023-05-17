package agents;

import QLearning.action.Action;
import QLearning.action.QTable;
import agents.states.BlindState;

public class BlindQAgent extends Agent {

    private Action latestAction;
    private BlindState currentState;
    private QTable<BlindState> table;
    private final double ALPHA, GAMMA, DELTA, EPSILON;
    public BlindQAgent(double alpha, double gamma, double delta, double epsilon) {
        this.ALPHA = alpha;
        this.GAMMA = gamma;
        this.DELTA = delta;
        this.EPSILON = epsilon;
        this.currentState = new BlindState(this.cards);
        this.table = new QTable<>();
        this.table.addNewState(this.currentState);
    }

    /**
     * @return
     */
    @Override
    public char takeTurn() {
        Action action = this.table.get(currentState);
        latestAction = action;
        return action.getAction();
    }

    @Override
    public void react() {
        currentState = new BlindState(this.cards);
        double maxQ = table.get(currentState).getActionValue();
        double q = (1 - ALPHA) * latestAction.getActionValue() +
                ALPHA * (currentState.getReward() + GAMMA * maxQ);
        latestAction.setActionValue(q);
    }

    /**
     *
     */
    @Override
    public void win() {
        System.out.println("Blind Agent Won!");
        this.table.updateReward(this.currentState, DELTA);
    }

    /**
     *
     */
    @Override
    public void lose() {
        System.out.println("Blind Agent Lost!");
        this.table.updateReward(this.currentState, -DELTA);
    }

    /**
     *
     */
    @Override
    public void printCardDrawn() {
        System.out.println("Blind Agent drew a " + this.cards.get(this.cards.size() - 1));
    }

    /**
     *
     */
    @Override
    public void tie() {
        System.out.println("Blind Agent Tied!");
    }

    /**
     *
     */
    @Override
    public void printCards() {
        System.out.print("Blind Agent's cards: ");
        this.cards.forEach((c) -> System.out.print(c + " "));
        System.out.println();
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return "Blind Agent";
    }
}
