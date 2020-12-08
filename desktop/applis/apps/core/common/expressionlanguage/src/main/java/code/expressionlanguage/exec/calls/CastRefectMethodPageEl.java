package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class CastRefectMethodPageEl extends AbstractRefectMethodPageEl {

    private boolean direct;
    public CastRefectMethodPageEl(boolean _direct, Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo);
        direct = _direct;
    }

    @Override
    boolean initType(ContextEl _cont) {
        return initType(_cont,direct);
    }

    @Override
    boolean isAbstract(ContextEl _cont) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        return prepareCast(_context, _className, _args, direct);
    }

    @Override
    public String formatVarType(String _varType) {
        return _varType;
    }
}
