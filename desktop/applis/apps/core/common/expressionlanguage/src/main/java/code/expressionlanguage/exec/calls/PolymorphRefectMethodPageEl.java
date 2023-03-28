package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ReflectMethodParamChecker;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class PolymorphRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public PolymorphRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo, boolean _refer) {
        super(_instance,_array, _metaInfo, new PolyInitPreparer(_metaInfo), _refer);
    }

    Argument prepare(ContextEl _context, ArrayStruct _args, Argument _right, StackCall _stack) {
        return new ReflectMethodParamChecker(getPair(), _args, _right, getAccessKind(), isRef()).checkParams(getClassName(), getInstance(), getMetaInfo().getCache(), _context, _stack);
    }

}
