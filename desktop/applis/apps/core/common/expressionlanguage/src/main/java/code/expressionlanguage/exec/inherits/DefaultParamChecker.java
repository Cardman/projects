package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;

public final class DefaultParamChecker extends AbstractParamChecker {
    private final ExecNamedFunctionBlock method;
    private final ArgumentListCall args;

    public DefaultParamChecker(ExecNamedFunctionBlock _method, ArgumentListCall _args) {
        this.method = _method;
        this.args = _args;
    }

    @Override
    public Parameters check(ExecRootBlock _rootBlock, String _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSet(_rootBlock, method, _classFormat, _cache, args, _conf, _stackCall);
    }
}
