package code.expressionlanguage.stacks;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.ProcessXmlMethod;

public abstract class LoopStack extends BlockStack implements BreakableStack {

    private static final String INDEX = "index";

    private static final String WHILE_LOOP = "while loop";

    private static final String HAS_NEXT = "has next";

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

    private boolean finished;

    private Object iterator;

    private long index;

    private long maxIteration;

    @Override
    public String toString() {
        String iteration_;
        try {
            iteration_ = HAS_NEXT+SEP_KEY_VAL+ProcessXmlMethod.hasNext(null, iterator);
        } catch (Throwable _0) {
            iteration_ = WHILE_LOOP;
        }
        return iteration_+SEP_INFO+INDEX+SEP_KEY_VAL+index+SEP_INFO;
    }

    public boolean hasNext(ContextEl _conf) {
        if (iterator != null) {
            return ProcessXmlMethod.hasNext(_conf, iterator);
        }
        return index + 1 < maxIteration;
    }

    public Object getIterator() {
        return iterator;
    }

    public void setIterator(Object _iterator, long _maxIteration) {
        iterator = _iterator;
        maxIteration = _maxIteration;
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
