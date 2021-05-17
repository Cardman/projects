package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;

public final class ReflectInstanceParamChecker extends AbstractInstanceParamChecker {


    private final CustList<Argument> argsList;
    public ReflectInstanceParamChecker(ExecTypeFunction _pair, CustList<Argument> _args, String _fieldName,
                                       int _blockIndex) {
        super(_pair, new ArgumentListCall(), _fieldName, _blockIndex);
        argsList = _args;
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        ExecTemplates.wrapAndCallDirect(getArguments(),getPair(),argsList,null,_classFormat);
        return ExecTemplates.okArgsSet(getMethod(),_classFormat,_cache,getArguments(),_conf,_stackCall);
    }
}
