package code.expressionlanguage.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.InitBlock;

public final class CustomFoundBlock implements CallingState {

    private final String className;

    private final Argument currentObject;

    private final InitBlock block;

    public CustomFoundBlock(String _className,
            Argument _currentObject, InitBlock _block) {
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
    public InitBlock getBlock() {
        return block;
    }
}
