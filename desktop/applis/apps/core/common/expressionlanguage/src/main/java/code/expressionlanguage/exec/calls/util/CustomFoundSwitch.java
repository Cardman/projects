package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.Cache;

public final class CustomFoundSwitch implements CallingState {

    private final Argument gl;

    private final String className;

    private final ExecRootBlock type;
    private final ExecAbstractSwitchMethod switchMethod;

    private final Cache cache;
    private final Argument value;

    public CustomFoundSwitch(Argument _gl, String _className, ExecRootBlock _type, ExecAbstractSwitchMethod _switchMethod, Cache _cache, Argument _value) {
        gl = _gl;
        className = _className;
        type = _type;
        switchMethod = _switchMethod;
        cache = _cache;
        value = _value;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createCallingSwitch(_context,this, _stack);
    }
    public Argument getGl() {
        return gl;
    }

    public String getClassName() {
        return className;
    }

    public ExecRootBlock getType() {
        return type;
    }

    public ExecAbstractSwitchMethod getSwitchMethod() {
        return switchMethod;
    }

    public Argument getValue() {
        return value;
    }

    public Cache getCache() {
        return cache;
    }
}
