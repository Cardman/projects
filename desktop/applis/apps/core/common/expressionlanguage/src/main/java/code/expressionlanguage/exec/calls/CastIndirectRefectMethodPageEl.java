package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.CastParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class CastIndirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public CastIndirectRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo, boolean _refer) {
        super(_instance,_array, _metaInfo, new DefInitPreparerCast(_metaInfo), _refer);
    }

    @Override
    Argument prepare(ContextEl _context, ArrayStruct _args, Argument _right, StackCall _stack) {
        ArgumentListCall l_ = ArgumentListCall.wrapCall(_args.listArgs());
        return new CastParamChecker(getPair(), l_).checkParams(getClassName(), Argument.createVoid(), null, _context, _stack);
    }


}
