package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.expressionlanguage.structs.Struct;

public final class TryBlockStack extends AbstractStask implements EnteredStack {

    private AbruptCallingFinally calling;
    private Struct exception;

    private ExecBracedBlock execLastBlock;

    private ExecBracedBlock execCurrentBlock;

    private boolean entered;

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean _entered) {
        entered = _entered;
    }

    public ExecBracedBlock getLastBlock() {
        return execLastBlock;
    }


    public void setExecLastBlock(ExecBracedBlock _execLastBlock) {
        execLastBlock = _execLastBlock;
    }

    @Override
    public ExecBracedBlock getCurrentVisitedBlock() {
        return execCurrentBlock;
    }

    @Override
    public void setCurrentVisitedBlock(ExecBracedBlock _bl) {
        execCurrentBlock = _bl;
    }

    public AbruptCallingFinally getCalling() {
        return calling;
    }

    public void setCalling(AbruptCallingFinally _calling) {
        calling = _calling;
    }

    public Struct getException() {
        return exception;
    }

    public void setException(Struct _exception) {
        this.exception = _exception;
    }
}
