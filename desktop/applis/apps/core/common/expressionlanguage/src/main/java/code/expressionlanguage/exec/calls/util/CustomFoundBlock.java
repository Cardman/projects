package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecInitBlock;

public final class CustomFoundBlock implements CallingState {

    private final String className;

    private final Argument currentObject;

    private final ExecInitBlock block;

    public CustomFoundBlock(String _className,
                            Argument _currentObject, ExecInitBlock _block) {
        className = _className;
        currentObject = _currentObject;
        block = _block;
    }

    public String getClassName() {
        return className;
    }

    public Argument getCurrentObject() {
        return currentObject;
    }

    public ExecInitBlock getBlock() {
        return block;
    }
}
