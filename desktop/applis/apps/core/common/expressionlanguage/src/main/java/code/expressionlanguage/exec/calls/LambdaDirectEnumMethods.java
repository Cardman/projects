package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaDirectEnumMethods extends AbstractRefectLambdaMethodPageEl {
    public LambdaDirectEnumMethods(ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r, LambdaMethodStruct _lms) {
        super(_array, _metaInfo, new DefInitPreparerDir(_metaInfo), _r, _lms);
    }

    @Override
    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        return ParamCheckerUtil.processEnums(_context.getExiting(), _context, _list, _stack, method_.getPairType());
    }
}
