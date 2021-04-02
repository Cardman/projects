package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectEnumMethods extends AbstractRefectMethodPageEl {
    public DirectEnumMethods(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _context, StackCall _stack) {
        return initDefault(_context, _stack);
    }

    @Override
    boolean isAbstract(ContextEl _context, StackCall _stack) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _context, StackCall _stack) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(_args);
        return ExecInvokingOperation.processEnums(_context.getExiting(), _context, l_, _stack, method_.getPairType());
    }
}
