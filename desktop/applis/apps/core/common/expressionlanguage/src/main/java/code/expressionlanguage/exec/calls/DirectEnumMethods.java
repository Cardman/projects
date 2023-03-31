package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DirectEnumMethods extends AbstractRefectMethodPageEl {
    public DirectEnumMethods(Argument _instance, MethodMetaInfo _metaInfo, ArrayRefState _a) {
        super(_instance, _metaInfo, new DefInitPreparerDir(_metaInfo), _a);
    }

    @Override
    Argument prepare(ContextEl _context, ArrayRefState _args, Argument _right, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        ArgumentListCall l_ = ArgumentListCall.wrapCall(_args.getArray().listArgs());
        return ParamCheckerUtil.processEnums(_context.getExiting(), _context, l_, _stack, method_.getPairType());
    }
}
