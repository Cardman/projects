package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class MethodParamChecker extends AbstractFormatParamChecker {
    private final ExecTypeFunction pair;
    private final ExecNamedFunctionBlock method;
    private final ArgumentListCall args;

    public MethodParamChecker(ExecTypeFunction _pair, ArgumentListCall _args,
                                         MethodAccessKind _kind) {
        super(_kind);
        this.pair = _pair;
        this.method = _pair.getFct();
        this.args = _args;
    }

    @Override
    public Argument redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        return redir(_conf, _previous, _stackCall, _classFormat, method, args, pair);
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return getParameters(_classFormat, _cache, _conf, _stackCall, method, args);
    }

}
