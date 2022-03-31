package code.formathtml.exec.stacks;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.RendParentBlock;

public final class RendTryBlockStack extends RendAbstractStask implements RendEnteredStack {

    private RendAbruptCallingFinally calling;
    private Struct exception;

    private RendParentBlock lastBlock;

    private RendParentBlock currentBlock;

    private boolean entered;

    @Override
    public boolean isEntered() {
        return entered;
    }

    @Override
    public void setEntered(boolean _entered) {
        this.entered = _entered;
    }

    public RendParentBlock getLastBlock() {
        return lastBlock;
    }

    public void setLastBlock(RendParentBlock _lastBlock) {
        lastBlock = _lastBlock;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return currentBlock;
    }

    @Override
    public void setCurrentVisitedBlock(RendParentBlock _bl) {
        currentBlock = _bl;
    }

    public RendAbruptCallingFinally getCalling() {
        return calling;
    }

    public void setCalling(RendAbruptCallingFinally _calling) {
        calling = _calling;
    }

    public Struct getException() {
        return exception;
    }

    public void setException(Struct _exception) {
        this.exception = _exception;
    }
}
