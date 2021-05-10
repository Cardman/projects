package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.StaticCallParamChecker;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;

public abstract class AbstractRefectCommonMethodPageEl extends AbstractReflectPageEl {

    private boolean initClass;
    private String className = "";
    private ExecTypeFunction pair;

    private final Argument instance;

    private MethodAccessKind accessKind;
    private final MethodMetaInfo metaInfo;

    public AbstractRefectCommonMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo) {
        instance = _instance;
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }
    @Override
    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        setWrapper(_wrap);
        setReturnedArgument(_argument);
    }

    protected boolean initDefault(ContextEl _cont, StackCall _stackCall) {
        return metaInfo.isWideStatic()&&_cont.getExiting().hasToExit(_stackCall, metaInfo.getClassName());
//        return method_.isWideStatic()&&ExecutingUtil.hasToExit(_cont,method_.getClassName());
    }

    boolean keep(ContextEl _context, StackCall _stackCall) {
        LgNames stds_ = _context.getStandards();
        if (!initClass) {
            initClass = true;
            if (initType(_context, _stackCall)) {
                setWrapException(true);
                return false;
            }
        }
        setWrapException(false);
        if (pair == null) {
            if (metaInfo.isInstanceMethod()) {
                if (instance.isNull()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_, _stackCall)));
                    return false;
                }
            }
            if (isAbstract(_context, _stackCall)) {
                String null_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, metaInfo.getDisplayedString(_context).getInstance(), null_, _stackCall)));
                return false;
            }
            String className_ = metaInfo.getClassName();
            if (isPolymorph(_context, _stackCall)) {
                Struct instance_ = instance.getStruct();
                ExecOverrideInfo polymorph_ = ExecInvokingOperation.polymorph(_context, instance_, metaInfo.getPair());
                className = polymorph_.getClassName();
                pair = polymorph_.getPair();
            } else {
                className = className_;
                pair = metaInfo.getPair();
            }
            accessKind = metaInfo.getRealId().getKind();
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

    ExecTypeFunction getPair() {
        return pair;
    }

    Argument getInstance() {
        return instance;
    }

    Argument direct(ContextEl _context, StackCall _stack, ArgumentListCall _l) {
        MethodMetaInfo method_ = getMetaInfo();
        String className_ = method_.getClassName();
        String res_ = ExecTemplates.correctClassPartsDynamicNotWildCard(className_, _context);
        if (res_.isEmpty()) {
            String null_;
            null_ = _context.getStandards().getContent().getCoreNames().getAliasIllegalType();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_, null_, _stack)));
            return Argument.createVoid();
        }
        return ExecExplicitOperation.getArgument(getClassName(), _context, _stack, _l);
    }

    Argument indirect(ContextEl _context, ArgumentListCall _list, StackCall _stack) {
        String className_ = getClassName();
        String res_ = ExecTemplates.correctClassPartsDynamicNotWildCard(className_, _context);
        if (res_.isEmpty()) {
            String null_;
            null_ = _context.getStandards().getContent().getCoreNames().getAliasIllegalType();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_, null_, _stack)));
            return Argument.createVoid();
        }
        new StaticCallParamChecker(getPair(), _list).checkParams(getClassName(), Argument.createVoid(), null, _context, _stack);
        return Argument.createVoid();
    }

    abstract boolean initType(ContextEl _context, StackCall _stack);
    abstract boolean isAbstract(ContextEl _context, StackCall _stack);
    abstract boolean isPolymorph(ContextEl _context, StackCall _stack);
}
