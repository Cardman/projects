package code.expressionlanguage.stacks;

public abstract class SwitchStack implements BreakableStack {

    private boolean finished;

    private boolean entered;

    @Override
    public void setFinished(boolean _finished) {
        finished = _finished;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean _entered) {
        entered = _entered;
    }
}
