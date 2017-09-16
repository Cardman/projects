package code.expressionlanguage.stacks;
import code.expressionlanguage.exceptions.WrapperException;
import code.util.CustList;

public abstract class TryStack extends BlockStack {

    private int visitedCatch = CustList.INDEX_NOT_FOUND_ELT;

    private boolean visitedFinally;

    private WrapperException thrownException;

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

    public WrapperException getThrownException() {
        return thrownException;
    }

    public void setThrownException(WrapperException _thrownException) {
        thrownException = _thrownException;
    }
}
