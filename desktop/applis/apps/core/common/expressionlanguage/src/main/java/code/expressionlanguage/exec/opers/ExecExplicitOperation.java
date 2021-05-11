package code.expressionlanguage.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.StaticCallParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
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
        ArgumentListCall list_ = new ArgumentListCall();
        for (ExecOperationNode o: getChildrenNodes()) {
            if (ExecConstLeafOperation.isFilter(o)) {
                continue;
            }
            list_.addArg(getArgument(_nodes, o));
        }
        Argument argres_ =  prepare(_conf.getExiting(),pair, explicitContent.getClassName(), explicitContent.getClassNameOwner(),_conf,_stack, list_);
        setSimpleArgument(argres_, _conf, _nodes, _stack);
    }
    public static Argument prepare(AbstractExiting _exit, ExecTypeFunction _rootBlock, String _className,
                                   String _classNameOwner, ContextEl _conf, StackCall _stackCall, ArgumentListCall _list) {
        if (direct(_rootBlock, _className)) {
            String paramName_ = _stackCall.formatVarType(_className);
            return getArgument(paramName_, _conf, _stackCall, _list);
        }
        checkFormattedCustomOper(_exit, _rootBlock, _classNameOwner, _conf,null, _stackCall, _list);
        return Argument.createVoid();
    }

    public static boolean direct(boolean _direct, ExecTypeFunction _castOpId, String _className) {
        return _direct || direct(_castOpId, _className);
    }

    public static boolean direct(ExecTypeFunction _castOpId, String _className) {
        return !StringExpUtil.customCast(_className) || _castOpId == null;
    }


    public static boolean checkFormattedCustomOper(AbstractExiting _exit, ExecTypeFunction _rootBlock, String _classNameOwner, ContextEl _conf, Argument _fwd, StackCall _stackCall, ArgumentListCall _list) {
        String paramNameOwner_ = _stackCall.formatVarType(_classNameOwner);
        return checkCustomOper(_exit, _rootBlock, paramNameOwner_, _conf, _fwd, _stackCall, _list);
    }

    public static boolean checkCustomOper(AbstractExiting _exit, ExecTypeFunction _rootBlock, String _paramNameOwner, ContextEl _conf, Argument _fwd, StackCall _stackCall, ArgumentListCall _list) {
        if (_exit.hasToExit(_stackCall, _paramNameOwner,_fwd)) {
            return true;
        }
        new StaticCallParamChecker(_rootBlock,_list).checkParams(_paramNameOwner,Argument.createVoid(),null,_conf,_stackCall);
        return false;
    }

    public static Argument getArgument(String _paramName, ContextEl _conf, StackCall _stackCall, ArgumentListCall _list) {
        Argument objArg_ = new Argument(ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(_list.getArgumentWrappers())).getStruct());
        ExecTemplates.checkObject(_paramName, objArg_, _conf, _stackCall);
        return objArg_;
    }
}
