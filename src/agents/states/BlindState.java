package agents.states;

import blackJack.Card;

import java.util.List;
import java.util.Optional;

public class BlindState extends State {
    private final List<Integer> hand;

    public BlindState(List<Integer> hand) {
        this.hand = hand;
        this.calculateValue();
    }

    @Override
    protected void calculateValue() {
        for(Integer i : hand){
            this.value += i;
        }
    }

    @Override
    public boolean equals(Object o){

        if (this == o){
            return true;
        }
        if (o == null){
            return false;
        }
        if (this.getClass() != o.getClass()){
            return false;
        }

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (Integer i : hand){
            s1.append(i);
        }
        for (Integer i : ((BlindState) o).hand){
            s2.append(i);
        }
        return s1.toString().equals(s2.toString());

    }

    @Override
    public int hashCode() {
        return Double.valueOf(this.value).hashCode();
    }
}
