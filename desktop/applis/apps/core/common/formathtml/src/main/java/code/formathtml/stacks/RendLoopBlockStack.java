package code.formathtml.stacks;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.RendParentBlock;


public final class RendLoopBlockStack implements RendRemovableVars {

    private RendParentBlock block;

    private boolean finished;

    private Struct structIterator;

    private long index;

    private long maxIteration;

    private long step;

    private Struct container;

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

    @Override
    public void setCurrentVisitedBlock(RendParentBlock _bl) {
        block = _bl;
    }

    public long getStep() {
        return step;
    }

    public void setStep(long step) {
        this.step = step;
    }

    public Struct getContainer() {
        return container;
    }

    public void setContainer(Struct container) {
        this.container = container;
    }
}
