package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class ReflectInstanceParamChecker extends AbstractInstanceParamChecker {


    private final ArrayRefState argsList;
    private final int de;
    public ReflectInstanceParamChecker(ExecTypeFunction _pair, ArrayRefState _args, String _fieldName,
                                       int _blockIndex, int _delta) {
        super(_pair, new ArgumentListCall(), _fieldName, _blockIndex);
        argsList = _args;
        de = _delta;
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        ExecTemplates.wrapAndCallDirect(getArguments(),getPair(),argsList,null,_classFormat, de);
        return ExecTemplates.okArgsSet(getMethod(),_classFormat,_cache,getArguments(),_conf,_stackCall);
    }
}
