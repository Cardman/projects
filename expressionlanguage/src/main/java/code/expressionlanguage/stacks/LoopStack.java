package code.expressionlanguage.stacks;
import code.expressionlanguage.opers.util.Struct;

public abstract class LoopStack extends BlockStack implements BreakableStack {

    private static final String INDEX = "index";

    private static final String WHILE_LOOP = "while loop";

    private static final String HAS_NEXT = "has next";

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

    private boolean finished;
    
    private Struct structIterator;

    private Object iterator;

    private long index;

    private long maxIteration;

    public String getInfos() {
        String iteration_;
        if (iterator != null || index != -1) {
            iteration_ = HAS_NEXT;
        } else {
            iteration_ = WHILE_LOOP;
        }
        return iteration_+SEP_INFO+INDEX+SEP_KEY_VAL+index+SEP_INFO;
    }

    public boolean hasNext() {
        return index + 1 < maxIteration;
    }

    public void setMaxIteration(long _maxIteration) {
        maxIteration = _maxIteration;
    }

    public void setIterator(Object _iterator, long _maxIteration) {
        iterator = _iterator;
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
