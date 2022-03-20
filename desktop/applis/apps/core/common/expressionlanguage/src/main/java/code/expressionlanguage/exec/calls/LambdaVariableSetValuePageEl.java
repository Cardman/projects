package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.util.CustList;

public final class LambdaVariableSetValuePageEl extends AbstractLambdaVariable {

    public LambdaVariableSetValuePageEl(ArgumentListCall _array) {
        super(_array);
    }

    Argument prepare(ContextEl _context, ArgumentListCall _array, StackCall _stack) {
        CustList<ArgumentWrapper> argumentWrappers_ = _array.getArgumentWrappers();
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
        Argument right_ = ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_));
        ExecTemplates.getWrap(firstArgumentWrapper_.getWrapper()).setValue(_stack,_context,right_);
        return right_;
    }

}
