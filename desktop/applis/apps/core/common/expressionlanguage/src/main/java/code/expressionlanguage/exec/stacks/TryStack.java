package code.expressionlanguage.exec.stacks;

public abstract class TryStack {

    private boolean visitedFinally;

    public boolean isVisitedFinally() {
        return visitedFinally;
    }

    public void setVisitedFinally(boolean _visitedFinally) {
        visitedFinally = _visitedFinally;
    }

}
