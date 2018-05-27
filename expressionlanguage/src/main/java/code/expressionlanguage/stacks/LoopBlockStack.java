package code.expressionlanguage.stacks;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.methods.BracedBlock;


public final class LoopBlockStack extends LoopStack implements BreakableBlockStack, RemovableVars {

    private boolean evaluatingKeepLoop;

    private BracedBlock block;

    @Override
    public BracedBlock getBlock() {
        return block;
    }

    @Override
    public void setBlock(BracedBlock _block) {
        block = _block;
    }
    @Override
    public void removeVarAndLoop(AbstractPageEl _ip) {
        BracedBlock forNode_ = getBlock();
        forNode_.removeLocalVars(_ip);
        forNode_.removeVarAndLoop(_ip);
    }

    public boolean isEvaluatingKeepLoop() {
        return evaluatingKeepLoop;
    }

    public void setEvaluatingKeepLoop(boolean _evaluatingKeepLoop) {
        evaluatingKeepLoop = _evaluatingKeepLoop;
    }

    @Override
    public BracedBlock getLastBlock() {
        return getBlock();
    }

    @Override
    public BracedBlock getCurrentVisitedBlock() {
        return getBlock();
    }

}
