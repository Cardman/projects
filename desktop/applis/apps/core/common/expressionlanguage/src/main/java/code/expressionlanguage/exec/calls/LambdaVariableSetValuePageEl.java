package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class LambdaVariableSetValuePageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaVariableSetValuePageEl(Argument _instance, ArgumentListCall _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo, new DefPreparer());
    }

    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        CustList<ArgumentWrapper> argumentWrappers_ = _list.getArgumentWrappers();
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
        Argument right_ = ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_));
        ExecTemplates.getWrap(firstArgumentWrapper_.getWrapper()).setValue(_stack,_context,right_);
        return new Argument();
    }

}
