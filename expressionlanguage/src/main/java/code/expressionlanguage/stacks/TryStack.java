package code.expressionlanguage.stacks;
import code.util.CustList;

public abstract class TryStack extends BlockStack {

    private int visitedCatch = CustList.INDEX_NOT_FOUND_ELT;

    private boolean visitedFinally;

    private Throwable thrownException;

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

    public Throwable getThrownException() {
        return thrownException;
    }

    public void setThrownException(Throwable _thrownException) {
        thrownException = _thrownException;
    }
}
