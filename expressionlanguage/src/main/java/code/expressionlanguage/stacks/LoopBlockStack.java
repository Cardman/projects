package code.expressionlanguage.stacks;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.BracedBlock;


public final class LoopBlockStack extends LoopStack implements RemovableVars {

    private boolean evaluatingKeepLoop;

    private BracedBlock block;

    @Override
    public BracedBlock getBlock() {
        return block;
    }

    public void setBlock(BracedBlock _block) {
        block = _block;
    }
    @Override
    public void removeVarAndLoop(AbstractPageEl _ip) {
        block.removeLocalVars(_ip);
        block.removeVarAndLoop(_ip);
    }

    public boolean isEvaluatingKeepLoop() {
        return evaluatingKeepLoop;
    }

    public void setEvaluatingKeepLoop(boolean _evaluatingKeepLoop) {
        evaluatingKeepLoop = _evaluatingKeepLoop;
    }

    @Override
    public BracedBlock getLastBlock() {
        return block;
    }

    @Override
    public BracedBlock getCurrentVisitedBlock() {
        return block;
    }

}
