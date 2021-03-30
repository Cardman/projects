package code.expressionlanguage.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundCast;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecExplicitOperation extends ExecAbstractUnaryOperation {
    private final ExecTypeFunction pair;
    private final ExecExplicitContent explicitContent;
    public ExecExplicitOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, ExecExplicitContent _explicitContent) {
        super(_opCont);
        pair = _pair;
        explicitContent = _explicitContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(explicitContent.getOffset(), _stack);
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecOperationNode o: getChildrenNodes()) {
            if (ExecConstLeafOperation.isFilter(o)) {
                continue;
            }
            arguments_.add(getArgument(_nodes, o));
        }
        Argument argres_ =  prepare(_conf.getExiting(),pair,false,arguments_, explicitContent.getClassName(), explicitContent.getClassNameOwner(),_conf,_stack);
        setSimpleArgument(argres_, _conf, _nodes, _stack);
    }
    public static Argument prepare(AbstractExiting _exit, ExecTypeFunction _rootBlock, boolean _direct, CustList<Argument> _arguments, String _className,
                                   String _classNameOwner, ContextEl _conf, StackCall _stackCall) {
        if (direct(_direct, _rootBlock, _className)) {
            String paramName_ = _stackCall.formatVarType(_className);
            return getArgument(_arguments, paramName_, _conf, _stackCall);
        }
        checkFormattedCustomOper(_exit, _rootBlock, _arguments, _classNameOwner, _conf,null, _stackCall);
        return Argument.createVoid();
    }

    public static boolean direct(boolean _direct, ExecTypeFunction _castOpId, String _className) {
        return _direct || !StringExpUtil.customCast(_className) || _castOpId == null;
    }


    public static boolean checkFormattedCustomOper(AbstractExiting _exit, ExecTypeFunction _rootBlock, CustList<Argument> _arguments, String _classNameOwner, ContextEl _conf, Argument _fwd, StackCall _stackCall) {
        String paramNameOwner_ = _stackCall.formatVarType(_classNameOwner);
        return checkCustomOper(_exit, _rootBlock, _arguments, paramNameOwner_, _conf, _fwd, _stackCall);
    }

    public static boolean checkCustomOper(AbstractExiting _exit, ExecTypeFunction _rootBlock, CustList<Argument> _arguments, String _paramNameOwner, ContextEl _conf, Argument _fwd, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _paramNameOwner,_fwd)) {
            return true;
        }
        ExecNamedFunctionBlock fct_ = _rootBlock.getFct();
        ExecRootBlock type_ = _rootBlock.getType();
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(_arguments);
        Parameters parameters_ = ExecTemplates.okArgsSet(type_, fct_, _paramNameOwner, null, l_, _conf, null, true, _stackCall);
        if (parameters_.getError() != null) {
            return true;
        }
        _stackCall.setCallingState(new CustomFoundCast(_paramNameOwner, _rootBlock, parameters_));
        return false;
    }

    public static Argument getArgument(CustList<Argument> _arguments, String _paramName, ContextEl _conf, StackCall _stackCall) {
        Argument objArg_ = new Argument(ExecHelper.getFirstArgument(_arguments).getStruct());
        ExecTemplates.checkObject(_paramName, objArg_, _conf, _stackCall);
        return objArg_;
    }
}
