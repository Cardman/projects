package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class LambdaPolymorphRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaPolymorphRefectMethodPageEl(ArgumentListCall _array, MethodMetaInfo _metaInfo, int _r, LambdaMethodStruct _lms) {
        super(_array, _metaInfo, new PolyInitPreparer(_metaInfo), _r, _lms,new DefParamReflectCheckerStepping());
    }

    Struct prepare(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        new MethodParamChecker(getPair(), _list, getAccessKind()).checkParams(getClassName(), getParent(), getMetaInfo().getCache(), _context, _stack);
        return NullStruct.NULL_VALUE;
    }
}
