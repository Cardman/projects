package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.Struct;

public abstract class AbstractMethodParamChecker extends AbstractFormatParamChecker {
    private final ExecTypeFunction pair;
    private final ExecNamedFunctionBlock method;
    private final ArgumentListCall args;

    protected AbstractMethodParamChecker(ExecTypeFunction _pair, ArgumentListCall _args,
                                      MethodAccessKind _kind) {
        super(_kind);
        this.pair = _pair;
        this.method = _pair.getFct();
        this.args = _args;
    }

    protected static Argument redir(ContextEl _conf, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat, ExecNamedFunctionBlock _method, ArgumentListCall _args, ExecTypeFunction _pair) {
        Struct prev_ = _previous.getStruct();
        if (prev_ instanceof AbstractFunctionalInstance && ((AbstractFunctionalInstance) prev_).getNamed() == _method) {
            Argument fctInst_ = new Argument(((AbstractFunctionalInstance) prev_).getFunctional());
            return ExecInvokingOperation.prepareCallDyn(fctInst_, _args, _conf, _stackCall);
        }
        return simpleRedir(_previous, _stackCall, _classFormat, _pair);
    }

    protected static Argument simpleRedir(Argument _previous, StackCall _stackCall, FormattedParameters _classFormat, ExecTypeFunction _pair) {
        _stackCall.setCallingState(new CustomFoundMethod(_previous, _classFormat.getFormattedClass(), _pair, _classFormat.getParameters()));
        return Argument.createVoid();
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return getParameters(_classFormat, _cache, _conf, _stackCall, method, args);
    }

    protected static Parameters getParameters(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall, ExecNamedFunctionBlock _method, ArgumentListCall _args) {
        return ExecTemplates.okArgsSet(_method, _classFormat, _cache, _args, _conf, _stackCall);
    }

    protected ExecTypeFunction getPair() {
        return pair;
    }

    protected ExecNamedFunctionBlock getMethod() {
        return method;
    }

    protected ArgumentListCall getArgs() {
        return args;
    }
}
