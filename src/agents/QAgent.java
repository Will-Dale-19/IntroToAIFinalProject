package agents;

import QLearning.action.Action;
import QLearning.action.QTable;
import agents.states.State;

import java.util.List;

public abstract class QAgent extends Agent {
    protected Action latestAction;
    protected State currentState;
    protected QTable<State> table;
    protected final double ALPHA, GAMMA, DELTA, EPSILON;
    public QAgent(double alpha, double gamma, double delta, double epsilon) {
        this.ALPHA = alpha;
        this.GAMMA = gamma;
        this.DELTA = delta;
        this.EPSILON = epsilon;
        this.table = new QTable<>();
    }

    @Override
    public char takeTurn() {
        System.out.println("It's " + this.getName() + "'s turn.");
        latestAction = action;
        if (!isStanding()) {
            if (action.getAction() == 'h') {
                System.out.println(this.getName() + " has decided to hit.");
            } else {
                System.out.println(this.getName() + " has decided to stand.");
            }
        } else {
            System.out.println(this.getName() + " is standing.");
        }
        return action.getAction();
    }

    @Override
    public void win() {
        System.out.println(this.getName() + " won!");
        this.table.updateReward(this.currentState, DELTA);
    }

    @Override
    public void printCardDrawn() {
        System.out.println(this.getName() + " drew a " + this.cards.get(this.cards.size() - 1));
    }

    @Override
    public void lose() {
        System.out.println(this.getName() + " lost!");
        this.table.updateReward(this.currentState, -DELTA);
    }

    @Override
    public void tie() {
        System.out.println(this.getName() + " tied with the dealer!");
    }

    @Override
    public void printCards() {
        System.out.print(this.getName() + "'s cards: ");
        this.cards.forEach((c) -> System.out.print(c + " "));
        System.out.println();
    }

    @Override
    public String toString() {
        return this.table.toString();
    }

    protected double getActionValue(List<Action> actions) {
        Action action;
        if (Math.random() > EPSILON) {
            action = this.table.get(currentState)
                    .stream()
                    .max((a1, a2) -> (int) (a1.getActionValue() - a2.getActionValue()))
                    .orElseThrow();
        } else {
            List<Action> tempActions = this.table.get(currentState);
            action = tempActions.get((int) (Math.random() * actions.size()));
        }
        return action.getActionValue();
    }
}
