package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;

public final class ReflectMethodParamChecker extends AbstractMethodParamChecker {

    private final CustList<Argument> argsList;
    private final Argument right;
    public ReflectMethodParamChecker(ExecTypeFunction _pair,
                                     CustList<Argument> _args, Argument _right,
                                     MethodAccessKind _kind) {
        super(_pair, new ArgumentListCall(), _kind);
        argsList = _args;
        right = _right;
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        ExecTemplates.wrapAndCallDirect(getArgs(),getPair(),argsList,right,_classFormat);
        return ExecTemplates.okArgsSet(getMethod(), _classFormat, _cache, getArgs(), _conf, _stackCall);
    }
}
