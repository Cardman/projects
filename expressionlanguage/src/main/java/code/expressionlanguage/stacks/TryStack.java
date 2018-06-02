package code.expressionlanguage.stacks;
import code.expressionlanguage.opers.util.Struct;

public abstract class TryStack {

    private boolean visitedFinally;
    private Struct exception;

    public boolean isVisitedFinally() {
        return visitedFinally;
    }

    public void setVisitedFinally(boolean _visitedFinally) {
        visitedFinally = _visitedFinally;
    }

    public Struct getException() {
        return exception;
    }

    public void setException(Struct _exception) {
        exception = _exception;
    }

}
