package agents.states;

public abstract class State {
    protected double value;
    public double getValue() {
        return value;
    }

    protected abstract void calculateValue();

    @Override
    public abstract int hashCode();
}
