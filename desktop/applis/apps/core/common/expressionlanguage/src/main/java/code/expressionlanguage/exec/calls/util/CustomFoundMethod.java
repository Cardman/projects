package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class CustomFoundMethod implements CallingState {

    private final Argument gl;

    private final ExecFormattedRootBlock className;

    private final ExecTypeFunction pair;

    private final Parameters arguments;

    public CustomFoundMethod(ExecFormattedRootBlock _className, ExecTypeFunction _pair, Parameters _arguments) {
        this(Argument.createVoid(),_className,_pair,_arguments);
    }

    public CustomFoundMethod(Argument _gl, ExecFormattedRootBlock _className, ExecTypeFunction _pair, Parameters _arguments) {
        gl = _gl;
        className = _className;
        pair = _pair;
        arguments = _arguments;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createCallingMethod(_context,this);
    }
    public Argument getGl() {
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
