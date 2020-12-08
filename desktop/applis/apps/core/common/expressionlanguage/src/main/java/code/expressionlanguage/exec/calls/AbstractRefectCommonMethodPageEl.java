package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundCast;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbstractRefectCommonMethodPageEl extends AbstractReflectPageEl {

    private boolean initClass;
    private String className = "";
    private ExecTypeFunction pair;

    private final Argument instance;

    private MethodAccessKind accessKind;
    private String methodName = "";
    private MethodMetaInfo metaInfo;

    public AbstractRefectCommonMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo) {
        instance = _instance;
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }

    protected boolean initDefault(ContextEl _cont) {
        return metaInfo.isWideStatic()&&_cont.getExiting().hasToExit(metaInfo.getClassName());
//        return method_.isWideStatic()&&ExecutingUtil.hasToExit(_cont,method_.getClassName());
    }

    boolean keep(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        if (!initClass) {
            initClass = true;
            if (initType(_context)) {
                setWrapException(true);
                return false;
            }
        }
        setWrapException(false);
        if (pair == null) {
            if (metaInfo.isInstanceMethod()) {
                if (instance.isNull()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                    _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_)));
                    return false;
                }
            }
            if (isAbstract(_context)) {
                String null_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
                _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, metaInfo.getDisplayedString(_context).getInstance(), null_)));
                return false;
            }
            String className_ = metaInfo.getClassName();
            if (isPolymorph(_context)) {
                Struct instance_ = instance.getStruct();
                ExecOverrideInfo polymorph_ = ExecInvokingOperation.polymorph(_context, instance_, metaInfo.getPair());
                className = polymorph_.getClassName();
                pair = polymorph_.getPair();
            } else {
                className = className_;
                pair = metaInfo.getPair();
            }
            accessKind = metaInfo.getRealId().getKind();
            methodName = metaInfo.getRealId().getName();
        }
        return true;
    }

    String getClassName() {
        return className;
    }

    MethodMetaInfo getMetaInfo() {
        return metaInfo;
    }

    MethodAccessKind getAccessKind() {
        return accessKind;
    }

    String getMethodName() {
        return methodName;
    }

    ExecTypeFunction getPair() {
        return pair;
    }

    Argument getInstance() {
        return instance;
    }
    Argument prepareCast(ContextEl _context, String _className, CustList<Argument> _args, boolean _direct) {
        String res_ = ExecTemplates.correctClassPartsDynamicNotWildCard(_className, _context);
        if (res_.isEmpty()) {
            String null_;
            null_ = _context.getStandards().getContent().getCoreNames().getAliasIllegalType();
            _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, _className, null_)));
            return Argument.createVoid();
        }
        return ExecExplicitOperation.prepare(_context.getExiting(),getPair(), _direct,_args,res_,res_,_context);
    }

    static Argument checkStaticCall(ExecTypeFunction _pair, Cache _cache, ContextEl _conf, String _paramName, ArgumentListCall _list) {
        ExecRootBlock type_ = _pair.getType();
        ExecNamedFunctionBlock fct_ = _pair.getFct();
        Parameters parameters_ = ExecTemplates.okArgsSet(type_, fct_, _paramName, _cache, _list, _conf, null, true);
        if (parameters_.getError() != null) {
            return Argument.createVoid();
        }
        _conf.setCallingState(new CustomFoundCast(_paramName, _pair, parameters_));
        return Argument.createVoid();
    }
    boolean initType(ContextEl _cont, boolean _direct){
        MethodMetaInfo method_ = getMetaInfo();
        if (_direct) {
            return false;
        }
        String res_ = ExecTemplates.correctClassPartsDynamicNotWildCard(method_.getClassName(), _cont);
        if (res_.isEmpty()) {
            String null_;
            null_ = _cont.getStandards().getContent().getCoreNames().getAliasIllegalType();
            _cont.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, method_.getClassName(), null_)));
            return true;
        }
        if (!StringExpUtil.customCast(res_)) {
            return false;
        }
        String paramNameOwner_ = _cont.formatVarType(res_);
        return _cont.getExiting().hasToExit(paramNameOwner_);
    }
    abstract boolean initType(ContextEl _context);
    abstract boolean isAbstract(ContextEl _context);
    abstract boolean isPolymorph(ContextEl _context);
}
