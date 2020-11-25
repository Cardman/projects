package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectEnumMethods extends AbstractRefectMethodPageEl {
    public DirectEnumMethods(CustList<Argument> _arguments, MethodMetaInfo _metaInfo) {
        super(_arguments, _metaInfo);
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
    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        MethodMetaInfo method_ = getMetaInfo();
        ArgumentListCall l_ = new ArgumentListCall();
        l_.getArguments().addAllElts(_args);
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, _className,method_.getPair(), _instance,method_.getCache(), l_, _right, getAccessKind(),getMethodName());
    }
}
