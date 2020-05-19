package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.exec.ExecExplicitOperation;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;

public final class CastRefectMethodPageEl extends AbstractRefectMethodPageEl {

    private boolean direct;
    public CastRefectMethodPageEl(boolean _direct) {
        direct = _direct;
    }

    @Override
    boolean initType(ContextEl _cont) {
        return false;
    }

    @Override
    boolean isAbstract(ContextEl _cont) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont) {
        return false;
    }

    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        return ExecExplicitOperation.prepare(direct,_mid,_args,_className,_context.getLastPage(),_context, _mid.getKind() == MethodAccessKind.STATIC_CALL);
    }

    @Override
    public String formatVarType(String _varType, ContextEl _cont) {
        return _varType;
    }
}
