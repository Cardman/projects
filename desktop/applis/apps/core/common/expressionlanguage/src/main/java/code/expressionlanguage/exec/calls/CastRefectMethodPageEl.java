package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class CastRefectMethodPageEl extends AbstractRefectMethodPageEl {

    private final boolean direct;
    public CastRefectMethodPageEl(boolean _direct, Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo);
        direct = _direct;
    }

    @Override
    boolean initType(ContextEl _cont, StackCall _stack) {
        return initType(_cont,direct, _stack);
    }

    @Override
    boolean isAbstract(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right, StackCall _stack) {
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(_args);
        return prepareCast(_context, _className, direct, _stack, l_);
    }

    @Override
    public String formatVarType(String _varType) {
        return _varType;
    }
}
