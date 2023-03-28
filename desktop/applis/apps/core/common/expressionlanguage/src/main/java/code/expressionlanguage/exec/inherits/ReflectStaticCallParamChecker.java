package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.ArrayStruct;

public final class ReflectStaticCallParamChecker extends AbstractStaticCallParamChecker {
    private final ExecNamedFunctionBlock method;

    private final ArrayStruct argsList;
    private final boolean re;
    public ReflectStaticCallParamChecker(ExecTypeFunction _pair, ArrayStruct _args, boolean _r) {
        super(_pair);
        this.method = _pair.getFct();
        this.argsList = _args;
        re = _r;
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        ArgumentListCall call_ = new ArgumentListCall();
        ExecTemplates.wrapAndCallDirect(call_,getPair(),argsList,null,_classFormat,re,0);
        return ExecTemplates.okArgsSet(method, _classFormat, _cache, call_, _conf, _stackCall);
    }
}
