package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class LambdaDirectEnumMethods extends AbstractRefectLambdaMethodPageEl {
    public LambdaDirectEnumMethods(Argument _instance, ArgumentListCall _array, Argument _right, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _right, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _context) {
        return initDefault(_context);
    }

    @Override
    boolean isAbstract(ContextEl _context) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _context) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, Argument _instance, Argument _right, ArgumentListCall _list) {
        MethodMetaInfo method_ = getMetaInfo();
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, _className,method_.getPair(), _instance,method_.getCache(), _list, _right, getAccessKind(),getMethodName());
    }
}
