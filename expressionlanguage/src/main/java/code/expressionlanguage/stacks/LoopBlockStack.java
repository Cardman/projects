package code.expressionlanguage.stacks;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.BracedBlock;


public final class LoopBlockStack extends LoopStack implements BreakableBlockStack, RemovableVars {

    private boolean evaluatingKeepLoop;

    @Override
    public void removeVarAndLoop(PageEl _ip) {
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

}
