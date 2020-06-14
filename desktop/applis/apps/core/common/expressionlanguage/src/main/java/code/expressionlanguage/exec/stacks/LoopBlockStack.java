package code.expressionlanguage.exec.stacks;
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

    @Override
    public void setCurrentVisitedBlock(BracedBlock _bl) {
        block = _bl;
    }
}
