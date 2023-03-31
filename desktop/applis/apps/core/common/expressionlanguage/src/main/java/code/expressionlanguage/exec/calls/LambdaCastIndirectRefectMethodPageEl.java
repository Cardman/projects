package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.CastParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaCastIndirectRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaCastIndirectRefectMethodPageEl(Argument _instance, ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r) {
        super(_instance,_array, _metaInfo, new DefInitPreparerCast(_metaInfo), _r);
    }

    @Override
    Argument prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        return new CastParamChecker(getPair(), _list).checkParams(getClassName(), Argument.createVoid(), null, _context, _stack);
    }


}
