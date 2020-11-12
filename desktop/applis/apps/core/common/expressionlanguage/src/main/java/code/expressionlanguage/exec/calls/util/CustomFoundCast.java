package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class CustomFoundCast implements CallingState {

    private final String className;

    private final ExecTypeFunction pair;

    private final Parameters arguments;

    public CustomFoundCast(String _className, ExecTypeFunction _pair, Parameters _arguments) {
        className = _className;
        pair = _pair;
        arguments = _arguments;
    }
    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context) {
        return ExecutingUtil.createCallingCast(_context,this);
    }
    public String getClassName() {
        return className;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public Parameters getArguments() {
        return arguments;
    }
}
