package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.StaticCallParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;

public final class LambdaStaticCallMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaStaticCallMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r, LambdaMethodStruct _lms) {
        super(_array, _metaInfo, new DefInitPreparerDir(_metaInfo), _r, _lms);
    }

    @Override
    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        ExecFormattedRootBlock paramName_ = getClassName();
        new StaticCallParamChecker(getPair(), _list).checkParams(paramName_,Argument.createVoid(), method_.getCache(), _context, _stack);
        return ArgumentListCall.toStr(NullStruct.NULL_VALUE);
    }

}
