package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public final class CustomFoundSwitch implements CallingState {

    private final Argument gl;

    private final ExecFormattedRootBlock className;

    private final ExecAbstractSwitchMethod switchMethod;

    private final Cache cache;
    private final Argument value;

    public CustomFoundSwitch(Argument _gl, ExecFormattedRootBlock _className, ExecAbstractSwitchMethod _switchMethod, Cache _cache, Argument _value) {
        gl = _gl;
        className = _className;
        switchMethod = _switchMethod;
        cache = _cache;
        value = _value;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createCallingSwitch(_context,this);
    }
    public Argument getGl() {
        return gl;
    }

    public ExecFormattedRootBlock getClassName() {
        return className;
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
