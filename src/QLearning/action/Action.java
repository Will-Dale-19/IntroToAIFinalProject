package QLearning.action;

public interface Action {

    public char getAction();
    public double getActionValue();
    public void setActionValue(double actionValue);
}
