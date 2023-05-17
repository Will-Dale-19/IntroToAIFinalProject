package QLearning.action;

import agents.states.State;


import java.io.Serializable;
import java.util.HashMap;

import java.util.List;

public class QTable<S extends State> implements Serializable {
    private final HashMap<S, List<Action>> qTable = new HashMap<>();

    public QTable() {

    }

    public boolean contains(S object) {
        return this.qTable.containsKey(object);
    }

    public void addNewState(S toAdd) {
        if (!this.qTable.containsKey(toAdd)) {
            this.qTable.put(toAdd, List.of(new Hit(), new Stand()));
        }
    }

    public void updateReward(S toUpdate, double delta) {
        this.qTable.keySet().stream()
                .filter((k) -> k.equals(toUpdate))
                .forEach((s) -> s.updateReward(delta));
    }

    public List<Action> get(S key) {
        return this.qTable.get(key);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("States").append(":\t").append("Hit, Stand").append("\n");
        for (S state : this.qTable.keySet()) {
            List<Action> localActions = this.qTable.get(state);
            s.append(state).append(":\t").append(localActions.get(0).getActionValue())
                    .append(", ").append(localActions.get(1).getActionValue()).append("\n");
        }
        return s.toString();
    }
}
