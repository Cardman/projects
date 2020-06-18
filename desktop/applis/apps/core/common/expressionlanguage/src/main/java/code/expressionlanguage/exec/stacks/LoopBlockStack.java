package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.expressionlanguage.methods.BracedBlock;


public final class LoopBlockStack extends LoopStack implements RemovableVars {

    private boolean evaluatingKeepLoop;

    private ExecBracedBlock execBlock;

    @Override
    public ExecBracedBlock getBlock() {
        return execBlock;
    }

    public void setExecBlock(ExecBracedBlock _execBlock) {
        execBlock = _execBlock;
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
