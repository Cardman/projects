package code.expressionlanguage.stacks;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;

public abstract class TryStack extends BlockStack {

    private int visitedCatch = CustList.INDEX_NOT_FOUND_ELT;

    private boolean visitedFinally;
    private Struct exception;

    public int getVisitedCatch() {
        return visitedCatch;
    }

    public void setVisitedCatch(int _visitedCatch) {
        visitedCatch = _visitedCatch;
    }

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
