package code.formathtml.stacks;
import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.RendLoop;
import code.formathtml.exec.blocks.RendParentBlock;


public final class RendLoopBlockStack extends RendAbstractStask {

    private RendParentBlock block;
    private RendLoop loop;

    private boolean finished;

    private Struct structIterator = NullStruct.NULL_VALUE;

    private long index;

    private long maxIteration;

    private long step;

    private Struct container = NullStruct.NULL_VALUE;

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

    @Override
    public RendParentBlock getBlock() {
        return block;
    }

    public void setBlock(RendParentBlock _block) {
        block = _block;
    }

    @Override
    public RendParentBlock getLastBlock() {
        return block;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return block;
    }

    public RendLoop getLoop() {
        return loop;
    }

    public void setLoop(RendLoop _loop) {
        this.loop = _loop;
    }

    @Override
    public void setCurrentVisitedBlock(RendParentBlock _bl) {
        block = _bl;
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
    }
}
