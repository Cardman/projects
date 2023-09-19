package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.inherits.CastParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;

public final class CastIndirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public CastIndirectRefectMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo, ArrayRefState _a) {
        super(_instance, _metaInfo, new DefInitPreparerCast(_metaInfo), _a);
    }

    @Override
    Argument prepare(ContextEl _context, ArrayRefState _args, Argument _right, StackCall _stack) {
        ArgumentListCall l_ = ArgumentListCall.wrapCall(_args.getArray().listArgs());
        new CastParamChecker(getPair(), l_).checkParams(getClassName(), Argument.createVoid(), null, _context, _stack);
        return ArgumentListCall.toStr(NullStruct.NULL_VALUE);
    }


}
