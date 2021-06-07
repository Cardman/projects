package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class MethodParamChecker extends AbstractMethodParamChecker {

    public MethodParamChecker(ExecTypeFunction _pair, ArgumentListCall _args,
                              MethodAccessKind _kind) {
        super(_pair, _args, _kind);
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSet(getMethod(), _classFormat, _cache, getArgs(), _conf, _stackCall);
    }
}
