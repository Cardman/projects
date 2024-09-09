package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class CustomFoundSwitch implements CallingState,GlobalClassCallingState {

    private final Struct gl;

    private final ExecFormattedRootBlock className;

    private final ExecAbstractSwitchMethod switchMethod;

    private final Cache cache;
    private final Struct value;

    public CustomFoundSwitch(Struct _gl, ExecFormattedRootBlock _className, ExecAbstractSwitchMethod _switchMethod, Cache _cache, Struct _value) {
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
    public Struct getGl() {
        return gl;
    }

    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public ExecAbstractSwitchMethod getSwitchMethod() {
        return switchMethod;
    }

    public Struct getValue() {
        return value;
    }

    public Cache getCache() {
        return cache;
    }
}
