package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.ErrorStruct;

public final class CastParamChecker extends AbstractParamChecker {
    private final ExecTypeFunction pair;
    private final ExecNamedFunctionBlock method;
    private final ExecRootBlock type;
    private final ArgumentListCall args;
    public CastParamChecker(ExecTypeFunction _pair, ArgumentListCall _args) {
        this.pair = _pair;
        this.method = _pair.getFct();
        this.type = _pair.getType();
        this.args = _args;
    }

    @Override
    public String checkFormmattedParams(String _classNameFound, Argument _previous, ContextEl _conf, StackCall _stackCall) {
        String res_ = ExecTemplates.correctClassPartsDynamicNotWildCard(_classNameFound, _conf);
        if (res_.isEmpty()) {
            String null_;
            null_ = _conf.getStandards().getContent().getCoreNames().getAliasIllegalType();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, _classNameFound, null_, _stackCall)));
        }
        return _classNameFound;
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
