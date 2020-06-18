package code.expressionlanguage.exec.stacks;

public abstract class IfStack {

    private boolean entered;
    private String label;

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean _entered) {
        entered = _entered;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String _label) {
        label = _label;
    }
}
