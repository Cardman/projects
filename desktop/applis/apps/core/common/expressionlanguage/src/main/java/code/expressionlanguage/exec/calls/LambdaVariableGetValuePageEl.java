package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;

public final class LambdaVariableGetValuePageEl extends AbstractLambdaVariable {

    private final ArgumentListCall arr;
    public LambdaVariableGetValuePageEl(ArgumentListCall _array) {
        super(true);
        arr = _array;
    }

    Argument prepare(ContextEl _context, StackCall _stack) {
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(arr.getArgumentWrappers());
        return new Argument(ExecTemplates.getWrap(firstArgumentWrapper_.getWrapper()).getValue(_stack,_context));
    }

}
