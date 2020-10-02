package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectEnumMethods extends AbstractRefectMethodPageEl {
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
        MethodMetaInfo method_ = NumParsers.getMethod(getGlobalStruct());
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, _className,method_.getDeclaring(), _instance,method_.getCache(), _args, _right,null, getAccessKind(),getMethodName());
    }
}
