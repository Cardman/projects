package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.expressionlanguage.structs.Struct;

public final class TryBlockStack extends AbstractStask implements EnteredStack {

    private AbruptCallingFinally calling;
    private Struct exception;

    private final ExecBracedBlock execLastBlock;

    private ExecBracedBlock execCurrentBlock;

    private boolean entered;
    private boolean enteredCatch;
    private final ExecBracedBlock block;

    public TryBlockStack(ExecBracedBlock _execLastBlock, ExecBracedBlock _first) {
        execLastBlock = _execLastBlock;
        block = _first;
    }

    public ExecBracedBlock getBlock() {
        return block;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean _entered) {
        entered = _entered;
    }

    public boolean isEnteredCatch() {
        return enteredCatch;
    }

    public void setEnteredCatch(boolean _e) {
        this.enteredCatch = _e;
    }

    public ExecBracedBlock getLastBlock() {
        return execLastBlock;
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

    public void exception(Struct _exception) {
        setException(choice(getException(),_exception));
    }
    public Struct getException() {
        return exception;
    }

    public void setException(Struct _exception) {
        this.exception = _exception;
    }
    public static Struct choice(Struct _f,Struct _s) {
        if (_f == null) {
            return Argument.getNull(_s);
        }
        return _f;
    }
}
