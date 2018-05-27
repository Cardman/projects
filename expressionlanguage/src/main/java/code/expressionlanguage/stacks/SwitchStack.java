package code.expressionlanguage.stacks;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;

public abstract class SwitchStack implements BreakableStack {

    private boolean finished;

    private boolean entered;

    private Struct value = NullStruct.NULL_VALUE;

    private int visitedBlock = CustList.INDEX_NOT_FOUND_ELT;

    public boolean isFinished() {
        return finished;
    }

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
    public Struct getStruct() {
        return value;
    }
    public void setStruct(Struct _value) {
        value = _value;
    }

    public int getVisitedBlock() {
        return visitedBlock;
    }

    public void increment() {
        visitedBlock++;
    }

    public void setVisitedBlock(int _visitedBlock) {
        visitedBlock = _visitedBlock;
    }
}
