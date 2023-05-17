package QLearning.action;

import agents.states.State;
import blackJack.Deck;


import java.util.HashMap;

import java.util.List;

public class QTable<S extends State> {
    private final HashMap<S, List<Action>> qTable = new HashMap<>();
    private final int numUniqueCards = 13;

    public QTable(){

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

    public Action get(S key) {
        return this.qTable.get(key)
                .stream()
                .max((a1, a2) -> (int) (a1.getActionValue() - a2.getActionValue()))
                .orElseThrow();
    }
}
