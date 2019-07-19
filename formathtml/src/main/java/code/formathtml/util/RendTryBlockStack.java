package code.formathtml.util;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.methods.Eval;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryStack;
import code.formathtml.ImportingPage;
import code.formathtml.RendCallingFinally;
import code.formathtml.RendEval;
import code.formathtml.RendParentBlock;

public final class RendTryBlockStack extends TryStack implements RendRemovableVars {

    private RendCallingFinally calling;

    private RendParentBlock block;

    private RendParentBlock lastBlock;

    private RendEval currentBlock;

    @Override
    public RendParentBlock getBlock() {
        return block;
    }

    public void setBlock(RendParentBlock _block) {
        block = _block;
    }
    @Override
    public RendParentBlock getLastBlock() {
        return lastBlock;
    }

    public void setLastBlock(RendParentBlock _lastBlock) {
        lastBlock = _lastBlock;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return (RendParentBlock) currentBlock;
    }

    public void setCurrentBlock(RendEval _currentBlock) {
        currentBlock = _currentBlock;
    }

    public RendCallingFinally getCalling() {
        return calling;
    }

    public void setCalling(RendCallingFinally _calling) {
        calling = _calling;
    }

    @Override
    public void removeVarAndLoop(ImportingPage _ip) {
        currentBlock.processToFinally(_ip, this);
    }

}
