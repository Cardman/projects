package code.expressionlanguage.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.analyze.opers.ChoiceFctOperation;
import code.expressionlanguage.analyze.opers.FctOperation;
import code.expressionlanguage.analyze.opers.SuperFctOperation;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundCast;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ExplicitOperation;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecExplicitOperation extends ExecAbstractUnaryOperation {
    private String className;
    private String classNameOwner;
    private int offset;
    private MethodId castOpId;
    public ExecExplicitOperation(ExplicitOperation _a) {
        super(_a);
        className = _a.getClassName();
        classNameOwner = _a.getClassNameOwner();
        offset = _a.getOffset();
        castOpId = _a.getCastOpId();
    }
    public ExecExplicitOperation(FctOperation _a) {
        super(_a);
        className = _a.getClassMethodId().getClassName();
        classNameOwner = _a.getClassMethodId().getClassName();
        offset = StringList.getFirstPrintableCharIndex(_a.getMethodName());
        castOpId = _a.getClassMethodId().getConstraints();
    }
    public ExecExplicitOperation(SuperFctOperation _a) {
        super(_a);
        className = _a.getClassMethodId().getClassName();
        classNameOwner = _a.getClassMethodId().getClassName();
        offset = StringList.getFirstPrintableCharIndex(_a.getMethodName());
        castOpId = _a.getClassMethodId().getConstraints();
    }
    public ExecExplicitOperation(ChoiceFctOperation _a) {
        super(_a);
        className = _a.getClassMethodId().getClassName();
        classNameOwner = _a.getClassMethodId().getClassName();
        offset = StringList.getFirstPrintableCharIndex(_a.getMethodName());
        castOpId = _a.getClassMethodId().getConstraints();
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
        Argument argres_ =  prepare(new DefaultExiting(_conf),false,castOpId,arguments_,className,classNameOwner,_conf.getLastPage(),_conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }
    public static Argument prepare(AbstractExiting _exit, boolean _direct, MethodId _castOpId, CustList<Argument> _arguments, String _className,
                                   String _classNameOwner, PageEl _page, ContextEl _conf) {
        if (_direct) {
            return getArgument(_arguments, _className, _page, _conf);
        }
        if (!ExplicitOperation.customCast(_className)) {
            return getArgument(_arguments,_className, _page,_conf);
        }
        return checkCustomCast(_exit, _castOpId, _arguments, _className,_classNameOwner, _page, _conf);
    }


    private static Argument checkCustomCast(AbstractExiting _exit, MethodId _castOpId, CustList<Argument> _arguments,
                                            String _className, String _classNameOwner, PageEl _page, ContextEl _conf) {
        if (_castOpId == null) {
            return getArgument(_arguments,_className, _page,_conf);
        }
        checkCustomOper(_exit, _castOpId, _arguments, _classNameOwner, _page, _conf,null);
        return Argument.createVoid();
    }

    public static boolean checkCustomOper(AbstractExiting _exit, MethodId _castOpId, CustList<Argument> _arguments, String _classNameOwner, PageEl _page, ContextEl _conf, Argument _fwd) {
        String paramNameOwner_ = _page.formatVarType(_classNameOwner, _conf);
        if (_exit.hasToExit(paramNameOwner_,_fwd)) {
            return true;
        }
        MethodId check_ = new MethodId(_castOpId.getKind(),_castOpId.getName(),_castOpId.shiftFirst(),_castOpId.isVararg());
        if (!ExecTemplates.okArgs(check_,true, paramNameOwner_,_arguments, _conf, null)) {
            return true;
        }
        _conf.setCallingState(new CustomFoundCast(paramNameOwner_,_castOpId,_arguments));
        return false;
    }

    static Argument getArgument(CustList<Argument> _arguments, String _className, PageEl _page, ContextEl _conf) {
        Argument objArg_ = new Argument(_arguments.first().getStruct());
        String paramName_ = _page.formatVarType(_className, _conf);
        ExecTemplates.checkObject(paramName_, objArg_, _conf);
        return objArg_;
    }
}
