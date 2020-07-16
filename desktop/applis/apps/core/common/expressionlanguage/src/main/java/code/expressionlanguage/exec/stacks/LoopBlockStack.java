package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.expressionlanguage.exec.blocks.ExecLoop;
import code.expressionlanguage.structs.Struct;


public final class LoopBlockStack extends AbstractStask {

    private boolean evaluatingKeepLoop;

    private ExecBracedBlock execBlock;
    private ExecLoop execLoop;

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
    public ExecBracedBlock getBlock() {
        return execBlock;
    }

    public void setExecBlock(ExecBracedBlock _execBlock) {
        execBlock = _execBlock;
    }

    public ExecLoop getExecLoop() {
        return execLoop;
    }

    public void setExecLoop(ExecLoop execLoop) {
        this.execLoop = execLoop;
    }

    public boolean isEvaluatingKeepLoop() {
        return evaluatingKeepLoop;
    }

    public void setEvaluatingKeepLoop(boolean _evaluatingKeepLoop) {
        evaluatingKeepLoop = _evaluatingKeepLoop;
    }

    @Override
    public ExecBracedBlock getLastBlock() {
        return execBlock;
    }

    @Override
    public ExecBracedBlock getCurrentVisitedBlock() {
        return execBlock;
    }

    @Override
    public void setCurrentVisitedBlock(ExecBracedBlock _bl) {
        execBlock = _bl;
    }
}
