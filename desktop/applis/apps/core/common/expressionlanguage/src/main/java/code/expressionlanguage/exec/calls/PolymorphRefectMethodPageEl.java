package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class PolymorphRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public PolymorphRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo, new PolyInitPreparer(_metaInfo));
    }

    Argument prepare(ContextEl _context, CustList<Argument> _args, Argument _right, StackCall _stack) {
        return initRefl(_args,_right).checkParams(getClassName(), getInstance(), getMetaInfo().getCache(), _context, _stack);
    }

}
