package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.Struct;

public final class DefaultParamChecker extends AbstractFormatParamChecker {
    private final ExecTypeFunction pair;
    private final ExecNamedFunctionBlock method;
    private final ArgumentListCall args;
    private final CallPrepareState state;

    public DefaultParamChecker(ExecTypeFunction _pair, ArgumentListCall _args,
                               MethodAccessKind _kind,
                               CallPrepareState _state) {
        super(_kind);
        this.pair = _pair;
        this.method = _pair.getFct();
        this.args = _args;
        this.state = _state;
    }

    @Override
    public void redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Struct _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        Parameters parameters_ = _classFormat.getParameters();
        if (state == CallPrepareState.CTOR) {
            _stackCall.setCallingState(new CustomFoundConstructor(_classNameFound, pair, _previous, parameters_));
        } else {
            _stackCall.setCallingState(new CustomFoundMethod(_classNameFound, pair, parameters_));
        }
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSet(method, _classFormat, _cache, args, _conf, _stackCall);
    }
}
