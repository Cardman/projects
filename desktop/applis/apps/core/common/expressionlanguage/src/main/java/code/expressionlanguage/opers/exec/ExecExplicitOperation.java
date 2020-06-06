package code.expressionlanguage.opers.exec;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.calls.util.CustomFoundCast;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ExplicitOperation;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
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

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
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
        checkCustomOper(_exit, _castOpId, _arguments, _classNameOwner, _page, _conf);
        return Argument.createVoid();
    }

    public static boolean checkCustomOper(AbstractExiting _exit, MethodId _castOpId, CustList<Argument> _arguments, String _classNameOwner, PageEl _page, ContextEl _conf) {
        String paramNameOwner_ = _page.formatVarType(_classNameOwner, _conf);
        if (_exit.hasToExit(paramNameOwner_)) {
            return true;
        }
        MethodId check_ = new MethodId(_castOpId.getKind(),_castOpId.getName(),_castOpId.shiftFirst(),_castOpId.isVararg());
        if (!Templates.okArgs(check_,true, paramNameOwner_,_arguments, _conf, null)) {
            return true;
        }
        _conf.setCallingState(new CustomFoundCast(paramNameOwner_,_castOpId,_arguments));
        return false;
    }

    static Argument getArgument(CustList<Argument> _arguments, String _className, PageEl _page, ContextEl _conf) {
        Argument objArg_ = _arguments.first();
        String paramName_ = _page.formatVarType(_className, _conf);
        Templates.checkObject(paramName_, objArg_, _conf);
        return objArg_;
    }
}
