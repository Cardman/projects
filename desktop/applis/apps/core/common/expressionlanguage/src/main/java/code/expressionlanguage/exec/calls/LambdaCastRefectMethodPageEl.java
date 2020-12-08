package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaCastRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    private boolean direct;
    public LambdaCastRefectMethodPageEl(boolean _direct, Argument _instance, ArgumentListCall _array, Argument _right, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _right, _metaInfo);
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
    Argument prepare(ContextEl _context, String _className, Argument _instance, Argument _right, ArgumentListCall _list) {
        return prepareCast(_context, _className, _list.getArguments(), direct);
    }

    @Override
    public String formatVarType(String _varType) {
        return _varType;
    }
}
