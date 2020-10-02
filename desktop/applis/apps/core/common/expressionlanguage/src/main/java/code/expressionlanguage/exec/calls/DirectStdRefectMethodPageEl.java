package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectStdRefectMethodPageEl extends AbstractRefectMethodPageEl {

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
    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        return ExecInvokingOperation.callStd(_context.getExiting(), _context, _className, _mid, _instance, _args, _right);
    }
}
