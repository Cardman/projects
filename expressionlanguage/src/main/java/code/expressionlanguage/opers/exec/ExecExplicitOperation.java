package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
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

public final class ExecExplicitOperation extends ExecAbstractUnaryOperation {
    private String className;
    private int offset;
    private MethodId castOpId;
    public ExecExplicitOperation(ExplicitOperation _a) {
        super(_a);
        className = _a.getClassName();
        offset = _a.getOffset();
        castOpId = _a.getCastOpId();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ =  prepare(false,castOpId,arguments_,className,_conf.getLastPage(),_conf,false);
        setSimpleArgument(argres_, _conf, _nodes);
    }
    public static Argument prepare(boolean _direct, MethodId _castOpId, CustList<Argument> _arguments, String _className, PageEl _page, ContextEl _conf, boolean _simpleCall) {
        if (!_simpleCall) {
            if (_direct) {
                return getArgument(_arguments,_className, _page,_conf);
            }
            if (!ExplicitOperation.customCast(_className)) {
                return getArgument(_arguments,_className, _page,_conf);
            }
            if (_castOpId == null) {
                return getArgument(_arguments,_className, _page,_conf);
            }
        } else if (!ExplicitOperation.customCast(_className)) {
            LgNames stds_ = _conf.getStandards();
            String null_;
            null_ = stds_.getAliasIllegalArg();
            _conf.setException(new ErrorStruct(_conf,null_));
            return Argument.createVoid();
        }
        String paramName_ = _page.formatVarType(_className, _conf);
        if (!Templates.okArgs(_castOpId,true, paramName_,_arguments, _conf, null)) {
            return Argument.createVoid();
        }
        _conf.setCallingState(new CustomFoundCast(paramName_,_castOpId,_arguments));
        return Argument.createVoid();
    }
    static Argument getArgument(CustList<Argument> _arguments, String _className, PageEl _page, ContextEl _conf) {
        Argument objArg_ = _arguments.first();
        String paramName_ = _page.formatVarType(_className, _conf);
        Templates.checkObject(paramName_, objArg_, _conf);
        return objArg_;
    }
}
