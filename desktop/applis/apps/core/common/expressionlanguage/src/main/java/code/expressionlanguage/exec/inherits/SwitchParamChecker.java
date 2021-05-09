package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundSwitch;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;

public final class SwitchParamChecker extends AbstractFormatParamChecker {
    private final ExecRootBlock type;
    private final ExecAbstractSwitchMethod method;
    private final CustList<Argument> args;

    public SwitchParamChecker(ExecRootBlock _type, ExecAbstractSwitchMethod _method, CustList<Argument> _args, MethodAccessKind _kind) {
        super(_kind);
        this.type = _type;
        this.method = _method;
        this.args = _args;
    }

    @Override
    public Argument redirect(ContextEl _context, String _classNameFound, Argument _instance, StackCall _stack, FormattedParameters _formatted) {
        Parameters parameters_ = _formatted.getParameters();
        _stack.setCallingState(new CustomFoundSwitch(_instance, _formatted.getFormattedClass(), type, method, parameters_.getCache(), new Argument(parameters_.getRefParameters().firstValue().getValue(_stack, _context))));
        return Argument.createVoid();
    }

    @Override
    public Parameters check(String _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSetSw(type, method, _classFormat, _cache, _conf, _stackCall, args);
    }
}
