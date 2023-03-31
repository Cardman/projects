package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.inherits.ReflectMethodParamChecker;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class PolymorphRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public PolymorphRefectMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo, ArrayRefState _a) {
        super(_instance, _metaInfo, new PolyInitPreparer(_metaInfo), _a);
    }

    Argument prepare(ContextEl _context, ArrayRefState _args, Argument _right, StackCall _stack) {
        return new ReflectMethodParamChecker(getPair(), _args, _right, getAccessKind()).checkParams(getClassName(), getInstance(), getMetaInfo().getCache(), _context, _stack);
    }

}
