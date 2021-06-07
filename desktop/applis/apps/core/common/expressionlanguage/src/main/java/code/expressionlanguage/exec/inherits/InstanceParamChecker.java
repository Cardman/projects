package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class InstanceParamChecker extends AbstractInstanceParamChecker {


    public InstanceParamChecker(ExecTypeFunction _pair, ArgumentListCall _arguments, String _fieldName,
                                int _blockIndex) {
        super(_pair, _arguments, _fieldName, _blockIndex);
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSet(getMethod(),_classFormat,_cache,getArguments(),_conf,_stackCall);
    }
}
