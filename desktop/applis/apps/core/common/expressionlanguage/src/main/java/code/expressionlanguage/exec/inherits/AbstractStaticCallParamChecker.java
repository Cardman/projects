package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.Struct;

public abstract class AbstractStaticCallParamChecker extends AbstractFormatParamChecker {
    private final ExecTypeFunction pair;

    protected AbstractStaticCallParamChecker(ExecTypeFunction _pair) {
        super(MethodAccessKind.STATIC_CALL);
        this.pair = _pair;
    }

    @Override
    public void redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Struct _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        _stackCall.setCallingState(new CustomFoundMethod(_classNameFound, pair, _classFormat.getParameters()));
    }

    protected ExecTypeFunction getPair() {
        return pair;
    }
}
