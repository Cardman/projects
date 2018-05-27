package code.expressionlanguage.stacks;
import code.util.CustList;

public abstract class IfStack {

    private boolean entered;

    private int visitedBlock = CustList.INDEX_NOT_FOUND_ELT;

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean _entered) {
        entered = _entered;
    }

    public int getVisitedBlock() {
        return visitedBlock;
    }

    public void setVisitedBlock(int _visitedBlock) {
        visitedBlock = _visitedBlock;
    }
}
