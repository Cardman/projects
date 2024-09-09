package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class LambdaCastDirectRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaCastDirectRefectMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r, LambdaMethodStruct _lms) {
        super(_array, _metaInfo, new DefPreparer(), _r, _lms);
    }

    @Override
    Struct prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        return direct(_context, _stack, _list);
    }


}
