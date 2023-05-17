package QLearning.action;

public class Hit implements Action {
    char action;

    double actionValue;
    public Hit(){
        this.action = 'h';
        this.actionValue = 1.0;
    }

    public char getAction(){
        return this.action;
    }
    public double getActionValue(){
        return this.actionValue;
    }

    public void setActionValue(double actionValue){
        this.actionValue = actionValue;
    }
}
