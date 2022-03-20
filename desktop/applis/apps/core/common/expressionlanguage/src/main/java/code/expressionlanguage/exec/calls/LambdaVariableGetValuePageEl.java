package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;

public final class LambdaVariableGetValuePageEl extends AbstractLambdaVariable {

    public LambdaVariableGetValuePageEl(ArgumentListCall _array) {
        super(_array);
    }

    Argument prepare(ContextEl _context, ArgumentListCall _array, StackCall _stack) {
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(_array.getArgumentWrappers());
        return new Argument(ExecTemplates.getWrap(firstArgumentWrapper_.getWrapper()).getValue(_stack,_context));
    }

}
