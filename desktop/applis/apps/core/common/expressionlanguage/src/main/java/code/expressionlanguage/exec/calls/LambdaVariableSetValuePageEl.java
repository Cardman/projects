package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class LambdaVariableSetValuePageEl extends AbstractLambdaVariable {

    private final ArgumentListCall arr;
    public LambdaVariableSetValuePageEl(ArgumentListCall _array) {
        super(true, new NoInitClass());
        arr = _array;
    }

    Struct calculate(ContextEl _context, StackCall _stack) {
        CustList<ArgumentWrapper> argumentWrappers_ = arr.getArgumentWrappers();
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
        Struct right_ = ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_));
        ExecTemplates.getWrap(firstArgumentWrapper_.getWrapper()).setValue(_stack,_context,right_);
        return right_;
    }

    public ArgumentListCall getArr() {
        return arr;
    }
}
