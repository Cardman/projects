package code.expressionlanguage.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundCast;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecExplicitOperation extends ExecAbstractUnaryOperation {
    private String className;
    private String classNameOwner;
    private int offset;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public ExecExplicitOperation(ExplicitOperation _a, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_a);
        className = _a.getClassName();
        classNameOwner = _a.getClassNameOwner();
        offset = _a.getOffset();
        named = _named;
        rootBlock = _rootBlock;
    }

    public ExecExplicitOperation(InvokingOperation _inv,AbstractCallFctOperation _a, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_inv);
        className = _a.getClassMethodId().getClassName();
        classNameOwner = _a.getClassMethodId().getClassName();
        offset = StringList.getFirstPrintableCharIndex(_a.getMethodName());
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecOperationNode o: getChildrenNodes()) {
            if (o instanceof ExecIdFctOperation) {
                continue;
            }
            arguments_.add(getArgument(_nodes, o));
        }
        Argument argres_ =  prepare(new DefaultExiting(_conf),rootBlock,false,named,arguments_,className,classNameOwner,_conf.getLastPage(),_conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }
    public static Argument prepare(AbstractExiting _exit, ExecRootBlock _rootBlock, boolean _direct, ExecNamedFunctionBlock _castOpId, CustList<Argument> _arguments, String _className,
                                   String _classNameOwner, PageEl _page, ContextEl _conf) {
        if (_direct) {
            return getArgument(_arguments, _className, _page, _conf);
        }
        if (!ExplicitOperation.customCast(_className)) {
            return getArgument(_arguments,_className, _page,_conf);
        }
        return checkCustomCast(_exit, _rootBlock,_castOpId, _arguments, _className,_classNameOwner, _page, _conf);
    }


    private static Argument checkCustomCast(AbstractExiting _exit, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _castOpId, CustList<Argument> _arguments,
                                            String _className, String _classNameOwner, PageEl _page, ContextEl _conf) {
        if (_castOpId == null) {
            return getArgument(_arguments,_className, _page,_conf);
        }
        checkCustomOper(_exit,_rootBlock, _castOpId, _arguments, _classNameOwner, _page, _conf,null);
        return Argument.createVoid();
    }

    public static boolean checkCustomOper(AbstractExiting _exit, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _castOpId, CustList<Argument> _arguments, String _classNameOwner, PageEl _page, ContextEl _conf, Argument _fwd) {
        String paramNameOwner_ = _page.formatVarType(_classNameOwner, _conf);
        if (_exit.hasToExit(paramNameOwner_,_fwd)) {
            return true;
        }
        Parameters parameters_ = ExecTemplates.okArgs(_rootBlock, _castOpId, true, paramNameOwner_, _arguments, _conf, null);
        if (parameters_.getError() != null) {
            return true;
        }
        _conf.setCallingState(new CustomFoundCast(paramNameOwner_,_rootBlock,_castOpId,parameters_));
        return false;
    }

    static Argument getArgument(CustList<Argument> _arguments, String _className, PageEl _page, ContextEl _conf) {
        Argument objArg_ = new Argument(_arguments.first().getStruct());
        String paramName_ = _page.formatVarType(_className, _conf);
        ExecTemplates.checkObject(paramName_, objArg_, _conf);
        return objArg_;
    }
}
