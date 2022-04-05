package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaDirectEnumMethods extends AbstractRefectLambdaMethodPageEl {
    public LambdaDirectEnumMethods(Argument _instance, ArgumentListCall _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo, new DefInitPreparerDir(_metaInfo));
    }

    @Override
    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        return ParamCheckerUtil.processEnums(_context.getExiting(), _context, _list, _stack, method_.getPairType());
    }
}
