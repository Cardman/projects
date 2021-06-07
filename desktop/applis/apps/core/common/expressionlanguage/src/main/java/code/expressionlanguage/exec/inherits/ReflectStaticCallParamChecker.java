package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;

public final class ReflectStaticCallParamChecker extends AbstractStaticCallParamChecker {
    private final ExecNamedFunctionBlock method;

    private final CustList<Argument> argsList;
    public ReflectStaticCallParamChecker(ExecTypeFunction _pair, CustList<Argument> _args) {
        super(_pair);
        this.method = _pair.getFct();
        this.argsList = _args;
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        ArgumentListCall call_ = new ArgumentListCall();
        ExecTemplates.wrapAndCallDirect(call_,getPair(),argsList,null,_classFormat);
        return ExecTemplates.okArgsSet(method, _classFormat, _cache, call_, _conf, _stackCall);
    }
}
