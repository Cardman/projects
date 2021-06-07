package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
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

    @Override
    public Argument redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        Struct prev_ = _previous.getStruct();
        if (prev_ instanceof AbstractFunctionalInstance && ((AbstractFunctionalInstance) prev_).getNamed() == method) {
            Argument fctInst_ = new Argument(((AbstractFunctionalInstance) prev_).getFunctional());
            return ExecInvokingOperation.prepareCallDyn(fctInst_, args, _conf, _stackCall);
        }
        _stackCall.setCallingState(new CustomFoundMethod(_previous, _classFormat.getFormattedClass(), pair, _classFormat.getParameters()));
        return Argument.createVoid();
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
