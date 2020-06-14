package code.expressionlanguage.exec.stacks;

public abstract class IfStack {

    private boolean entered;

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean _entered) {
        entered = _entered;
    }

}
