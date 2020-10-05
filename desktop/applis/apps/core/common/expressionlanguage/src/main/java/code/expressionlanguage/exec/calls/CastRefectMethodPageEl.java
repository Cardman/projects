package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class CastRefectMethodPageEl extends AbstractRefectMethodPageEl {

    private boolean direct;
    public CastRefectMethodPageEl(boolean _direct) {
        direct = _direct;
    }

    @Override
    boolean initType(ContextEl _cont) {
        MethodMetaInfo method_ = NumParsers.getMethod(getGlobalStruct());
        if (direct) {
            return false;
        }
        String res_ = ExecTemplates.correctClassPartsDynamicNotWildCard(method_.getClassName(), _cont);
        if (res_.isEmpty()) {
            String null_;
            null_ = _cont.getStandards().getContent().getCoreNames().getAliasIllegalType();
            _cont.setCallingState(new ErrorStruct(_cont,method_.getClassName(),null_));
            return true;
        }
        if (!StringExpUtil.customCast(res_)) {
            return false;
        }
        String paramNameOwner_ = _cont.getLastPage().formatVarType(res_, _cont);
        return _cont.getExiting().hasToExit(paramNameOwner_);
    }

    @Override
    boolean isAbstract(ContextEl _cont) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right) {
        String res_ = ExecTemplates.correctClassPartsDynamicNotWildCard(_className, _context);
        if (res_.isEmpty()) {
            String null_;
            null_ = _context.getStandards().getContent().getCoreNames().getAliasIllegalType();
            _context.setCallingState(new ErrorStruct(_context,_className,null_));
            return Argument.createVoid();
        }
        return ExecExplicitOperation.prepare(_context.getExiting(),getMethodToCallType(),direct,getMethodToCallBody(),_args,res_,res_,_context.getLastPage(),_context);
    }

    @Override
    public String formatVarType(String _varType, ContextEl _cont) {
        return _varType;
    }
}
