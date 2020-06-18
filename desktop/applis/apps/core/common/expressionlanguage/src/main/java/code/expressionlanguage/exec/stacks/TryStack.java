package code.expressionlanguage.exec.stacks;

public abstract class TryStack {

    private boolean visitedFinally;
    private String label;

    public boolean isVisitedFinally() {
        return visitedFinally;
    }

    public void setVisitedFinally(boolean _visitedFinally) {
        visitedFinally = _visitedFinally;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String _label) {
        label = _label;
    }
}
