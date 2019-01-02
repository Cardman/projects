package code.expressionlanguage.stacks;
import code.expressionlanguage.structs.Struct;

public abstract class LoopStack implements BreakableStack {

    private boolean finished;
    
    private Struct structIterator;

    private long index;

    private long maxIteration;

    public boolean hasNext() {
        return index + 1 < maxIteration;
    }

    public void setMaxIteration(long _maxIteration) {
        maxIteration = _maxIteration;
    }

    public Struct getStructIterator() {
        return structIterator;
    }

    public void setStructIterator(Struct _structIterator) {
        structIterator = _structIterator;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public void setFinished(boolean _finished) {
        finished = _finished;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long _index) {
        index = _index;
    }
}
