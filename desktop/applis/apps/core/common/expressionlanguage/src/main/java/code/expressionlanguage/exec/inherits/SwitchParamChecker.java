package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.Cache;
import code.util.CustList;

public final class SwitchParamChecker extends AbstractParamChecker {
    private final ExecAbstractSwitchMethod method;
    private final CustList<Argument> args;

    public SwitchParamChecker(ExecAbstractSwitchMethod _method, CustList<Argument> _args) {
        this.method = _method;
        this.args = _args;
    }

    @Override
    public Parameters check(ExecRootBlock _rootBlock, String _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSetSw(_rootBlock, method, _classFormat, _cache, _conf, _stackCall, args);
    }
}
