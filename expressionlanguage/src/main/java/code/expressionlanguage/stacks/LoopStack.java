package code.expressionlanguage.stacks;
import java.util.Iterator;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.opers.util.Struct;

public abstract class LoopStack extends BlockStack implements BreakableStack {

    private static final String INDEX = "index";

    private static final String WHILE_LOOP = "while loop";

    private static final String HAS_NEXT = "has next";

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

    private boolean finished;

    private Iterator<?> iterator;

    private boolean keyValue;

    private long index;

    private long maxIteration;

    @Override
    public String toString() {
        String iteration_;
        if (iterator != null) {
            try {
                iteration_ = HAS_NEXT+SEP_KEY_VAL+iterator.hasNext();
            } catch (Error _0) {
                iteration_ = HAS_NEXT;
            } catch (RuntimeException _0) {
                iteration_ = HAS_NEXT;
            }
        } else {
            iteration_ = WHILE_LOOP;
        }
        return iteration_+SEP_INFO+INDEX+SEP_KEY_VAL+index+SEP_INFO;
    }

    public boolean hasNext(ContextEl _conf) {
        if (iterator != null) {
            try {
                return iterator.hasNext();
            } catch (Throwable _0) {
                throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
            }
        }
        return index + 1 < maxIteration;
    }

    public Iterator<?> getIterator() {
        return iterator;
    }

    public void setIterator(Iterator<?> _iterator, long _maxIteration) {
        iterator = _iterator;
        maxIteration = _maxIteration;
    }

    public boolean isKeyValue() {
        return keyValue;
    }

    public void setKeyValue(boolean _keyValue) {
        keyValue = _keyValue;
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
