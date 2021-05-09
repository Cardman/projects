package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.Struct;

public final class DefaultParamChecker extends AbstractParamChecker {
    private final ExecTypeFunction pair;
    private final ExecNamedFunctionBlock method;
    private final ArgumentListCall args;
    private final CallPrepareState state;
    private final InstancingStep kindCall;
    private final ExecRootBlock type;

    public DefaultParamChecker(ExecTypeFunction _pair, ArgumentListCall _args,
                               CallPrepareState _state,
                               InstancingStep _kindCall) {
        this.pair = _pair;
        this.method = _pair.getFct();
        this.type = _pair.getType();
        this.args = _args;
        this.state = _state;
        this.kindCall = _kindCall;
    }

    @Override
    public Argument redirect(ContextEl _conf, String _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        if (_conf.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        if (state == CallPrepareState.METHOD) {
            Struct prev_ = _previous.getStruct();
            if (prev_ instanceof AbstractFunctionalInstance && ((AbstractFunctionalInstance) prev_).getNamed() == method) {
                Argument fctInst_ = new Argument(((AbstractFunctionalInstance) prev_).getFunctional());
                return ExecInvokingOperation.prepareCallDyn(fctInst_, args, _conf, _stackCall);
            }
            _stackCall.setCallingState(new CustomFoundMethod(_previous, _classFormat.getFormattedClass(), pair, _classFormat.getParameters()));
            return Argument.createVoid();
        }
        Parameters parameters_ = _classFormat.getParameters();
        if (state == CallPrepareState.CTOR) {
            _stackCall.setCallingState(new CustomFoundConstructor(_classNameFound, pair, "", -1, _previous, parameters_, kindCall));
        } else {
            _stackCall.setCallingState(new CustomFoundMethod(_classNameFound, pair, parameters_));
        }
        return Argument.createVoid();
    }

    @Override
    public Parameters check(String _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSet(type, method, _classFormat, _cache, args, _conf, _stackCall);
    }
}
