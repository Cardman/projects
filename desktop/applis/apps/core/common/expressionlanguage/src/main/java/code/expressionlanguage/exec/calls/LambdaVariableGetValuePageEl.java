package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;

public final class LambdaVariableGetValuePageEl extends AbstractLambdaVariable {

    private final ArgumentListCall arr;
    public LambdaVariableGetValuePageEl(ArgumentListCall _array) {
        super(true, new NoInitClass());
        arr = _array;
    }

    Struct calculate(ContextEl _context, StackCall _stack) {
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(arr.getArgumentWrappers());
        return ExecTemplates.getWrap(firstArgumentWrapper_.getWrapper()).getValue(_stack,_context);
    }

    public ArgumentListCall getArr() {
        return arr;
    }
}
