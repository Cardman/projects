package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.opers.ExplicitOperation;
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
        MethodMetaInfo method_ = NumParsers.getMethod(getGlobalArgument().getStruct());
        if (direct) {
            return false;
        }
        String res_ = ExecTemplates.correctClassPartsDynamic(method_.getClassName(), _cont, false);
        if (res_.isEmpty()) {
            String null_;
            null_ = _cont.getStandards().getAliasIllegalArg();
            _cont.setException(new ErrorStruct(_cont,null_));
            return true;
        }
        if (!ExplicitOperation.customCast(res_)) {
            return false;
        }
        String paramNameOwner_ = _cont.getLastPage().formatVarType(res_, _cont);
        return ExecutingUtil.hasToExit(_cont, paramNameOwner_);
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
        String res_ = ExecTemplates.correctClassPartsDynamic(_className, _context, false);
        if (res_.isEmpty()) {
            String null_;
            null_ = _context.getStandards().getAliasIllegalArg();
            _context.setException(new ErrorStruct(_context,null_));
            return Argument.createVoid();
        }
        return ExecExplicitOperation.prepare(new DefaultExiting(_context),direct,_mid,_args,res_,res_,_context.getLastPage(),_context);
    }

    @Override
    public String formatVarType(String _varType, ContextEl _cont) {
        return _varType;
    }
}
