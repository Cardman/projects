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

public abstract class AbstractStaticCallParamChecker extends AbstractFormatParamChecker {
    private final ExecTypeFunction pair;

    public AbstractStaticCallParamChecker(ExecTypeFunction _pair) {
        super(MethodAccessKind.STATIC_CALL);
        this.pair = _pair;
    }

    @Override
    public Argument redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        _stackCall.setCallingState(new CustomFoundMethod(_classNameFound, pair, _classFormat.getParameters()));
        return Argument.createVoid();
    }

    protected ExecTypeFunction getPair() {
        return pair;
    }
}
