package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class StaticCallParamChecker extends AbstractFormatParamChecker {
    private final ExecTypeFunction pair;
    private final ExecNamedFunctionBlock method;
    private final ExecRootBlock type;
    private final ArgumentListCall args;
    public StaticCallParamChecker(ExecTypeFunction _pair, ArgumentListCall _args) {
        super(MethodAccessKind.STATIC_CALL);
        this.pair = _pair;
        this.method = _pair.getFct();
        this.type = _pair.getType();
        this.args = _args;
    }

    @Override
    public Argument redirect(ContextEl _conf, String _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        _stackCall.setCallingState(new CustomFoundMethod(_classNameFound, pair, _classFormat.getParameters()));
        return Argument.createVoid();
    }

    @Override
    public Parameters check(String _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSet(type, method, _classFormat, _cache, args, _conf, _stackCall);
    }
}
