package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class CustomFoundMethod implements CallingState,GlobalClassCallingState {

    private final Struct gl;

    private final ExecFormattedRootBlock className;

    private final ExecTypeFunction pair;

    private final Parameters arguments;

    public CustomFoundMethod(ExecFormattedRootBlock _className, ExecTypeFunction _pair, Parameters _arguments) {
        this(NullStruct.NULL_VALUE,_className,_pair,_arguments);
    }

    public CustomFoundMethod(Struct _gl, ExecFormattedRootBlock _className, ExecTypeFunction _pair, Parameters _arguments) {
        gl = _gl;
        className = _className;
        pair = _pair;
        arguments = _arguments;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createCallingMethod(_context,this);
    }
    public Struct getGl() {
        return gl;
    }

    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public Parameters getArguments() {
        return arguments;
    }

}
