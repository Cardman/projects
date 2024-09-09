package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class CastDirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public CastDirectRefectMethodPageEl(Struct _instance, MethodMetaInfo _metaInfo, ArrayRefState _a) {
        super(_instance, _metaInfo, new DefPreparer(), _a);
    }


    @Override
    Struct prepare(ContextEl _context, ArrayRefState _args, Struct _right, StackCall _stack) {
        ArgumentListCall l_ = ArgumentListCall.wrapCall(_args.getArray().listArgs());
        return direct(_context, _stack, l_);
    }


}
