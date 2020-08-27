package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecInitBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class CustomFoundBlock implements CallingState {

    private final String className;

    private final Argument currentObject;

    private final ExecRootBlock rootBlock;
    private final ExecInitBlock block;

    public CustomFoundBlock(String _className,
                            Argument _currentObject,ExecRootBlock _rootBlock, ExecInitBlock _block) {
        className = _className;
        currentObject = _currentObject;
        rootBlock = _rootBlock;
        block = _block;
    }

    public String getClassName() {
        return className;
    }

    public Argument getCurrentObject() {
        return currentObject;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public ExecInitBlock getBlock() {
        return block;
    }
}
