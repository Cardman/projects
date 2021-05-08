package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class StaticCallMethodPageEl extends AbstractRefectMethodPageEl {

    public StaticCallMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _cont, StackCall _stack) {
        return initDefault(_cont, _stack);
    }

    @Override
    boolean isAbstract(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, MethodId _mid, Argument _instance, CustList<Argument> _args, Argument _right, StackCall _stack) {
        String res_ = ExecTemplates.correctClassPartsDynamicNotWildCard(_className, _context);
        if (res_.isEmpty()) {
            String null_;
            null_ = _context.getStandards().getContent().getCoreNames().getAliasIllegalType();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, _className, null_, _stack)));
            return Argument.createVoid();
        }
        MethodMetaInfo method_ = getMetaInfo();
        return prepareStaticCall(getCallee(),getPair(),method_.getCache(),_args,_className, _context, _stack);
    }
    private static Argument prepareStaticCall(ExecMemberCallingsBlock _callee,ExecTypeFunction _pair, Cache _cache, CustList<Argument> _arguments, String _className,
                                              ContextEl _conf, StackCall _stackCall) {
        if (!StringExpUtil.customCast(_className)) {
            LgNames stds_ = _conf.getStandards();
            String null_;
            null_ = stds_.getContent().getCoreNames().getAliasIllegalType();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, _className, null_, _stackCall)));
            return Argument.createVoid();
        }
        return checkStaticCall(_callee,_pair,_cache, _arguments, _className, _conf, _stackCall);
    }

    private static Argument checkStaticCall(ExecMemberCallingsBlock _callee,ExecTypeFunction _pair, Cache _cache, CustList<Argument> _arguments,
                                            String _className, ContextEl _conf, StackCall _stackCall) {
        String paramName_ = _stackCall.formatVarType(_className);
        if (_callee instanceof ExecAbstractSwitchMethod) {
            return checkStaticCallSw(_pair.getType(), (ExecAbstractSwitchMethod)_callee,_cache, _conf, paramName_, _stackCall, _arguments);
        }
        ArgumentListCall l_ = ExecTemplates.wrapAndCallDirect(_pair,paramName_,Argument.createVoid(),_arguments,_conf, MethodAccessKind.STATIC_CALL);
        return checkStaticCall(_pair, _cache, _conf, paramName_, l_, _stackCall);
    }

    @Override
    public String formatVarType(String _varType) {
        return _varType;
    }
}
