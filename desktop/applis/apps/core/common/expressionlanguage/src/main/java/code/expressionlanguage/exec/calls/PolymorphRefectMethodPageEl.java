package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.inherits.ReflectMethodParamChecker;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class PolymorphRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public PolymorphRefectMethodPageEl(Struct _instance, MethodMetaInfo _metaInfo, ArrayRefState _a) {
        super(_instance, _metaInfo, new PolyInitPreparer(_metaInfo), _a,new DefParamReflectCheckerStepping());
    }

    Struct prepare(ContextEl _context, ArrayRefState _args, Struct _right, StackCall _stack) {
        new ReflectMethodParamChecker(getPair(), _args, _right, getAccessKind()).checkParams(getClassName(), getInstance(), getMetaInfo().getCache(), _context, _stack);
        return NullStruct.NULL_VALUE;
    }
}
