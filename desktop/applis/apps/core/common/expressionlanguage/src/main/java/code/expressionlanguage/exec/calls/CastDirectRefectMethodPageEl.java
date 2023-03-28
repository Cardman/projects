package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class CastDirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public CastDirectRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo, boolean _refer) {
        super(_instance,_array, _metaInfo, new DefPreparer(), _refer);
    }


    @Override
    Argument prepare(ContextEl _context, ArrayStruct _args, Argument _right, StackCall _stack) {
        ArgumentListCall l_ = ArgumentListCall.wrapCall(_args.listArgs());
        return direct(_context, _stack, l_);
    }


}
