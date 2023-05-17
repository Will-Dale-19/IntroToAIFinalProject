package agents;

import QLearning.action.Action;
import QLearning.action.QTable;
import agents.states.State;

public abstract class QAgent extends Agent {
    protected Action latestAction;
    protected State currentState;
    protected QTable<State> table;
    protected final double ALPHA, GAMMA, DELTA;
    public QAgent(double alpha, double gamma, double delta, double epsilon) {
        this.ALPHA = alpha;
        this.GAMMA = gamma;
        this.DELTA = delta;
        this.table = new QTable<>(epsilon);
    }

    @Override
    public char takeTurn() {
        Action action = this.table.get(currentState);
        latestAction = action;
        return action.getAction();
    }

    @Override
    public void win() {
        System.out.println(this.getName() + " Won!");
        this.table.updateReward(this.currentState, DELTA);
    }

    @Override
    public void printCardDrawn() {
        System.out.println(this.getName() + " drew a " + this.cards.get(this.cards.size() - 1));
    }

    @Override
    public void lose() {
        System.out.println(this.getName() + " Lost!");
        this.table.updateReward(this.currentState, -DELTA);
    }

    @Override
    public void tie() {
        System.out.println(this.getName() + " Tied!");
    }

    @Override
    public void printCards() {
        System.out.print(this.getName() + "'s cards: ");
        this.cards.forEach((c) -> System.out.print(c + " "));
        System.out.println();
    }
}
