package QLearning.action;

import java.io.Serializable;

public interface Action extends Serializable {

    public char getAction();
    public double getActionValue();
    public void setActionValue(double actionValue);
}
