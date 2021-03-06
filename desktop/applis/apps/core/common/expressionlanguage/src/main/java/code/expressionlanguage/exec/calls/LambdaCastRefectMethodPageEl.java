package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaCastRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    private final boolean direct;
    public LambdaCastRefectMethodPageEl(boolean _direct, Argument _instance, ArgumentListCall _array, Argument _right, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _right, _metaInfo);
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
    Argument prepare(ContextEl _context, String _className, Argument _instance, Argument _right, ArgumentListCall _list, StackCall _stack) {
        return prepareCast(_context, _className, _list.getArguments(), direct, _stack);
    }

    @Override
    public String formatVarType(String _varType) {
        return _varType;
    }
}
