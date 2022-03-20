package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaVariableGetValuePageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaVariableGetValuePageEl(Argument _instance, ArgumentListCall _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo, new DefPreparer());
    }

    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(_list.getArgumentWrappers());
        return new Argument(ExecTemplates.getWrap(firstArgumentWrapper_.getWrapper()).getValue(_stack,_context));
    }

}
