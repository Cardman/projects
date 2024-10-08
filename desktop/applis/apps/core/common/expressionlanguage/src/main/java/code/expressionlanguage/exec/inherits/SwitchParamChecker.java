package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.calls.util.CustomFoundSwitch;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class SwitchParamChecker extends AbstractFormatParamChecker {
    private final ExecAbstractSwitchMethod method;
    private final CustList<Struct> args;

    public SwitchParamChecker(ExecAbstractSwitchMethod _method, CustList<Struct> _args, MethodAccessKind _kind) {
        super(_kind);
        this.method = _method;
        this.args = _args;
    }

    @Override
    public void redirect(ContextEl _context, ExecFormattedRootBlock _classNameFound, Struct _instance, StackCall _stack, FormattedParameters _formatted) {
        Parameters parameters_ = _formatted.getParameters();
        _stack.setCallingState(new CustomFoundSwitch(_instance, _formatted.getFormattedClass(), method, parameters_.getCache(), args.first()));
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSetSw(method, _classFormat, _cache, _conf, _stackCall, args);
    }
}
