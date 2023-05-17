package agents;

import QLearning.action.Action;
import QLearning.action.QTable;
import agents.states.State;

import java.io.Serializable;
import java.util.List;

public abstract class QAgent extends Agent implements Serializable {
    protected Action latestAction;
    protected State currentState;
    protected QTable<State> table;
    protected final double ALPHA, GAMMA, DELTA, EPSILON;
    protected int numWins;
    protected int totalGames;
    public QAgent(boolean print, double alpha, double gamma, double delta, double epsilon) {
        super(print);
        this.ALPHA = alpha;
        this.GAMMA = gamma;
        this.DELTA = delta;
        this.EPSILON = epsilon;
        this.table = new QTable<>();
        totalGames = 0;
        numWins = 0;
    }

    @Override
    public char takeTurn() {
        if (print) {
            System.out.println("It's " + this.getName() + "'s turn.");
        }
        Action action = getAction(this.table.get(currentState));
        latestAction = action;
        if (!isStanding()) {
            if (action.getAction() == 'h') {
                if (print) {
                    System.out.println(this.getName() + " has decided to hit.");
                }
            } else {
                if (print) {
                    System.out.println(this.getName() + " has decided to stand.");
                }
            }
        } else {
            if (print) {
                System.out.println(this.getName() + " is standing.");
            }
        }
        return action.getAction();
    }

    @Override
    public void win() {
        if (print) {
            System.out.println(this.getName() + " won!");
        }
        this.totalGames++;
        this.numWins++;
        this.table.updateReward(this.currentState, DELTA);
    }

    @Override
    public void printCardDrawn() {
        if (print) {
            System.out.println(this.getName() + " drew a " + this.cards.get(this.cards.size() - 1));
        }
    }

    @Override
    public void lose() {
        if (print) {
            System.out.println(this.getName() + " lost!");
        }
        this.totalGames++;
        this.table.updateReward(this.currentState, -2*DELTA);
    }

    @Override
    public void tie() {
        if (print) {
            System.out.println(this.getName() + " tied with the dealer!");
        }
        this.totalGames++;
    }

    @Override
    public void printCards() {
        if (print) {
            System.out.print(this.getName() + "'s cards: ");
            this.cards.forEach((c) -> System.out.print(c + " "));
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return this.table.toString();
    }

    protected Action getAction(List<Action> actions) {
        Action action;
        if (Math.random() > EPSILON) {
            action = actions
                        .stream()
                        .max((a1, a2) -> (int) (a1.getActionValue() - a2.getActionValue()))
                        .orElseThrow();
        } else {
            action = actions.get((int) (Math.random() * actions.size()));
        }
        return action;
    }

    public int getNumWins() {
        return numWins;
    }

    public int getTotalGames() {
        return totalGames;
    }
}
