package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;


public final class LoopBlockStackContent {

    private boolean evaluatingKeepLoop;

    private boolean finished;

    private Struct structIterator = NullStruct.NULL_VALUE;

    private long index;

    private long maxIteration;

    private long step;

    private Struct container = NullStruct.NULL_VALUE;
    private ArrayStruct array = new ArrayStruct(0,"");

    private long currentValue;

    private long achieveValue;

    private boolean eq;

    public boolean hasNext() {
        return index + 1 < maxIteration;
    }

    public boolean hasNextIter() {
        if (eq) {
            if (step > 0) {
                return currentValue + step <= achieveValue;
            }
            return currentValue + step >= achieveValue;
        }
        if (step > 0) {
            return currentValue + step < achieveValue;
        }
        return currentValue + step > achieveValue;
    }

    public void incr() {
        currentValue+=step;
    }

    public ArrayStruct getArray() {
        return array;
    }

    public void setAchieveValue(long _achieveValue) {
        this.achieveValue = _achieveValue;
    }

    public void setCurrentValue(long _currentValue) {
        this.currentValue = _currentValue;
    }

    public void setEq(boolean _eq) {
        this.eq = _eq;
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

    public void setFinished(boolean _finished) {
        finished = _finished;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long _index) {
        index = _index;
    }

    public boolean isEvaluatingKeepLoop() {
        return evaluatingKeepLoop;
    }

    public void setEvaluatingKeepLoop(boolean _evaluatingKeepLoop) {
        evaluatingKeepLoop = _evaluatingKeepLoop;
    }

    public long getStep() {
        return step;
    }

    public void setStep(long _step) {
        this.step = _step;
    }

    public Struct getContainer() {
        return container;
    }

    public void setContainer(Struct _container) {
        this.container = Argument.getNull(_container);
        if (_container instanceof ArrayStruct) {
            array = (ArrayStruct) _container;
        }
    }
}
