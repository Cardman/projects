package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;


public final class LoopBlockStack extends AbstractStask {

    private boolean evaluatingKeepLoop;

    private ExecBracedBlock execBlock;

    private boolean finished;

    private Struct structIterator = NullStruct.NULL_VALUE;

    private long index;

    private long maxIteration;

    private long step;

    private Struct container = NullStruct.NULL_VALUE;

    private long currentValue;

    private long achieveValue;

    private boolean eq;

    public boolean hasNextEx() {
        return index + 1 < maxIteration;
    }

    public boolean hasNextIterEx() {
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

    public void incrEx() {
        currentValue+=step;
    }

    public void setAchieveValueEx(long _achieveValue) {
        this.achieveValue = _achieveValue;
    }

    public void setCurrentValueEx(long _currentValue) {
        this.currentValue = _currentValue;
    }

    public void setEqEx(boolean _eq) {
        this.eq = _eq;
    }

    public void setMaxIterationEx(long _maxIteration) {
        maxIteration = _maxIteration;
    }

    public Struct getStructIteratorEx() {
        return structIterator;
    }

    public void setStructIteratorEx(Struct _structIterator) {
        structIterator = _structIterator;
    }

    public boolean isFinishedEx() {
        return finished;
    }

    public void setFinishedEx(boolean _finished) {
        finished = _finished;
    }

    public long getIndexEx() {
        return index;
    }

    public void setIndexEx(long _index) {
        index = _index;
    }

    public boolean isEvaluatingKeepLoopEx() {
        return evaluatingKeepLoop;
    }

    public void setEvaluatingKeepLoopEx(boolean _evaluatingKeepLoop) {
        evaluatingKeepLoop = _evaluatingKeepLoop;
    }

    @Override
    public ExecBracedBlock getCurrentVisitedBlock() {
        return execBlock;
    }

    @Override
    public void setCurrentVisitedBlock(ExecBracedBlock _bl) {
        execBlock = _bl;
    }

    public long getStepEx() {
        return step;
    }

    public void setStepEx(long _step) {
        this.step = _step;
    }

    public Struct getContainerEx() {
        return container;
    }

    public void setContainerEx(Struct _container) {
        this.container = Argument.getNull(_container);
    }
}
