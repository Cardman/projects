package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecCloneOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaDirectCloneRefectMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaDirectCloneRefectMethodPageEl(Argument _instance, ArgumentListCall _array, Argument _right, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _right, _metaInfo);
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

    @Override
    Argument prepare(ContextEl _context, String _className, Argument _instance, Argument _right, ArgumentListCall _list) {
        return ExecCloneOperation.cloneArray(_instance,_context);
    }
}
