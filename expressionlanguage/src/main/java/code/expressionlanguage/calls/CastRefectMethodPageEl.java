package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.calls.util.CustomFoundCast;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.ExplicitOperation;
import code.expressionlanguage.opers.exec.ExecExplicitOperation;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.stds.LgNames;
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
        MethodMetaInfo method_ = ApplyCoreMethodUtil.getMethod(getGlobalArgument().getStruct());
        if (direct) {
            if (method_.getKind() != MethodAccessKind.STATIC_CALL) {
                return false;
            }
        }
        String res_ = Templates.correctClassPartsDynamic(method_.getClassName(), _cont, false);
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
        if (_cont.hasToExit(paramNameOwner_)) {
            return true;
        }
        return false;
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
        String res_ = Templates.correctClassPartsDynamic(_className, _context, false);
        if (res_.isEmpty()) {
            String null_;
            null_ = _context.getStandards().getAliasIllegalArg();
            _context.setException(new ErrorStruct(_context,null_));
            return Argument.createVoid();
        }
        if (_mid.getKind() == MethodAccessKind.STATIC_CALL) {
            return prepareStaticCall(_mid,_args,res_,_context.getLastPage(),_context);
        }
        return ExecExplicitOperation.prepare(new DefaultExiting(_context),direct,_mid,_args,res_,res_,_context.getLastPage(),_context);
    }
    private static Argument prepareStaticCall(MethodId _castOpId, CustList<Argument> _arguments, String _className,
                                              PageEl _page, ContextEl _conf) {
        if (!ExplicitOperation.customCast(_className)) {
            LgNames stds_ = _conf.getStandards();
            String null_;
            null_ = stds_.getAliasIllegalArg();
            _conf.setException(new ErrorStruct(_conf,null_));
            return Argument.createVoid();
        }
        return checkStaticCall(_castOpId, _arguments, _className, _page, _conf);
    }

    private static Argument checkStaticCall(MethodId _castOpId, CustList<Argument> _arguments,
                                            String _className, PageEl _page, ContextEl _conf) {
        String paramName_ = _page.formatVarType(_className, _conf);
        if (!Templates.okArgs(_castOpId,true, paramName_,_arguments, _conf, null)) {
            return Argument.createVoid();
        }
        _conf.setCallingState(new CustomFoundCast(paramName_,_castOpId,_arguments));
        return Argument.createVoid();

    }
    @Override
    public String formatVarType(String _varType, ContextEl _cont) {
        return _varType;
    }
}
