package code.formathtml.util;
import code.expressionlanguage.stacks.LoopStack;
import code.formathtml.ImportingPage;
import code.formathtml.RendParentBlock;


public final class RendLoopBlockStack extends LoopStack implements RendRemovableVars {

    private boolean evaluatingKeepLoop;

    private RendParentBlock block;

    @Override
    public RendParentBlock getBlock() {
        return block;
    }

    public void setBlock(RendParentBlock _block) {
        block = _block;
    }
    @Override
    public void removeVarAndLoop(ImportingPage _ip) {
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
    public RendParentBlock getLastBlock() {
        return block;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return block;
    }

}
