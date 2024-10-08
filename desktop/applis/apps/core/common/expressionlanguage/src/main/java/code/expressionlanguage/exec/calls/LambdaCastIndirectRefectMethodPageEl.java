package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.CastParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class LambdaCastIndirectRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaCastIndirectRefectMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r, LambdaMethodStruct _lms) {
        super(_array, _metaInfo, new DefInitPreparerCast(_metaInfo), _r, _lms);
    }

    @Override
    Struct prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        new CastParamChecker(getPair(), _list).checkParams(getClassName(), NullStruct.NULL_VALUE, null, _context, _stack);
        return NullStruct.NULL_VALUE;
    }


}
