package QLearning.action;


public class Stand implements Action {
    char action;

    double actionValue;

    public Stand(){
        this.action = 's';
        this.actionValue = 1.0;
    }


    @Override
    public char getAction() {
        return this.action;
    }

    @Override
    public double getActionValue() {
        return this.actionValue;
    }

    @Override
    public void setActionValue(double actionValue) {
        this.actionValue = actionValue;
    }

    @Override
    public String toString() {
        return this.action + ":" + this.actionValue;
    }
}
