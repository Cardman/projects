package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectEnumMethods extends AbstractRefectMethodPageEl {
    public DirectEnumMethods(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo, new DefInitPreparerDir(_metaInfo));
    }

    @Override
    Argument prepare(ContextEl _context, CustList<Argument> _args, Argument _right, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        ArgumentListCall l_ = new ArgumentListCall(_args);
        return ParamCheckerUtil.processEnums(_context.getExiting(), _context, l_, _stack, method_.getPairType());
    }
}
