package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundCast;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
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

    public StaticCallMethodPageEl(CustList<Argument> _arguments, MethodMetaInfo _metaInfo) {
        super(_arguments, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _cont) {
        return initDefault(_cont);
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
            _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, _className, null_)));
            return Argument.createVoid();
        }
        MethodMetaInfo method_ = getMetaInfo();
        return prepareStaticCall(getPair(),method_.getCache(),_args,res_, _context);
    }
    private static Argument prepareStaticCall(ExecTypeFunction _pair, Cache _cache, CustList<Argument> _arguments, String _className,
                                              ContextEl _conf) {
        if (!StringExpUtil.customCast(_className)) {
            LgNames stds_ = _conf.getStandards();
            String null_;
            null_ = stds_.getContent().getCoreNames().getAliasIllegalType();
            _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, _className, null_)));
            return Argument.createVoid();
        }
        return checkStaticCall(_pair,_cache, _arguments, _className, _conf);
    }

    private static Argument checkStaticCall(ExecTypeFunction _pair, Cache _cache, CustList<Argument> _arguments,
                                            String _className, ContextEl _conf) {
        String paramName_ = _conf.formatVarType(_className);
        ExecRootBlock type_ = _pair.getType();
        ExecNamedFunctionBlock fct_ = _pair.getFct();
        ArgumentListCall l_ = ExecTemplates.wrapAndCallDirect(_pair,paramName_,Argument.createVoid(),_arguments,_conf, MethodAccessKind.STATIC_CALL);
        Parameters parameters_ = ExecTemplates.okArgsSet(type_, fct_, paramName_, _cache, l_, _conf, null, true);
        if (parameters_.getError() != null) {
            return Argument.createVoid();
        }
        _conf.setCallingState(new CustomFoundCast(paramName_, _pair, parameters_));
        return Argument.createVoid();

    }
    @Override
    public String formatVarType(String _varType) {
        return _varType;
    }
}
