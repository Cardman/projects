package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class StaticCallParamChecker extends AbstractStaticCallParamChecker {
    private final ExecNamedFunctionBlock method;
    private final ExecRootBlock type;
    private final ArgumentListCall args;
    public StaticCallParamChecker(ExecTypeFunction _pair, ArgumentListCall _args) {
        super(_pair);
        this.method = _pair.getFct();
        this.type = _pair.getType();
        this.args = _args;
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSet(method, _classFormat, _cache, args, _conf, _stackCall);
    }
}
