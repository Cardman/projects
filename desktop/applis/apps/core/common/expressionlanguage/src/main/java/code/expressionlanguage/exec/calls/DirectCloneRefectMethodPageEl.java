package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecCloneOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectCloneRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectCloneRefectMethodPageEl(CustList<Argument> _arguments, MethodMetaInfo _metaInfo) {
        super(_arguments, _metaInfo);
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
    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        if (ExecTemplates.checkParams(_context,_className,_mid,_instance,_args).isEmpty()) {
            return Argument.createVoid();
        }
        return ExecCloneOperation.cloneArray(_instance,_context);
    }
}
