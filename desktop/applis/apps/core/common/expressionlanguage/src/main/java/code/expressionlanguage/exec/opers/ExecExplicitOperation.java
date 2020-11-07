package code.expressionlanguage.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundCast;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecExplicitOperation extends ExecAbstractUnaryOperation {
    private ExecExplicitContent explicitContent;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public ExecExplicitOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _opCont, ExecExplicitContent _explicitContent) {
        super(_opCont);
        explicitContent = _explicitContent;
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelOffsetPossibleLastPage(explicitContent.getOffset(), _conf);
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecOperationNode o: getChildrenNodes()) {
            if (ExecConstLeafOperation.isFilter(o)) {
                continue;
            }
            arguments_.add(getArgument(_nodes, o));
        }
        Argument argres_ =  prepare(_conf.getExiting(),rootBlock,false,named,arguments_, explicitContent.getClassName(), explicitContent.getClassNameOwner(),_conf.getLastPage(),_conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }
    public static Argument prepare(AbstractExiting _exit, ExecRootBlock _rootBlock, boolean _direct, ExecNamedFunctionBlock _castOpId, CustList<Argument> _arguments, String _className,
                                   String _classNameOwner, AbstractPageEl _page, ContextEl _conf) {
        if (direct(_direct, _castOpId, _className)) {
            return getFormattedArgument(_arguments, _className, _conf);
        }
        checkFormattedCustomOper(_exit, _rootBlock, _castOpId, _arguments, _classNameOwner, _conf,null);
        return Argument.createVoid();
    }

    public static boolean direct(boolean _direct, ExecNamedFunctionBlock _castOpId, String _className) {
        return _direct || !StringExpUtil.customCast(_className) || _castOpId == null;
    }


    public static boolean checkFormattedCustomOper(AbstractExiting _exit, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _castOpId, CustList<Argument> _arguments, String _classNameOwner, ContextEl _conf, Argument _fwd) {
        String paramNameOwner_ = _conf.formatVarType(_classNameOwner);
        return checkCustomOper(_exit, _rootBlock, _castOpId, _arguments, paramNameOwner_, _conf, _fwd);
    }

    public static boolean checkCustomOper(AbstractExiting _exit, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _castOpId, CustList<Argument> _arguments, String _paramNameOwner, ContextEl _conf, Argument _fwd) {
        if (_exit.hasToExit(_paramNameOwner,_fwd)) {
            return true;
        }
        Parameters parameters_ = ExecTemplates.okArgsSet(_rootBlock, _castOpId, _paramNameOwner, null, _arguments, _conf, null, true);
        if (parameters_.getError() != null) {
            return true;
        }
        _conf.setCallingState(new CustomFoundCast(_paramNameOwner,_rootBlock,_castOpId,parameters_));
        return false;
    }

    public static Argument getFormattedArgument(CustList<Argument> _arguments, String _className, ContextEl _conf) {
        String paramName_ = _conf.formatVarType(_className);
        return getArgument(_arguments, paramName_, _conf);
    }

    public static Argument getArgument(CustList<Argument> _arguments, String _paramName, ContextEl _conf) {
        Argument objArg_ = new Argument(ExecTemplates.getFirstArgument(_arguments).getStruct());
        ExecTemplates.checkObject(_paramName, objArg_, _conf);
        return objArg_;
    }
}
