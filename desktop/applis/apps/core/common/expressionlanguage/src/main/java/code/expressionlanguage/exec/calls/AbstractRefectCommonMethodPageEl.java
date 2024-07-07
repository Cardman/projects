package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecInheritsAdv;
import code.expressionlanguage.exec.opers.ExecImplicitOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;

public abstract class AbstractRefectCommonMethodPageEl extends AbstractReflectPageEl {

    private boolean initClass;
    private ExecOverrideInfo overrideInfo;

    private final Argument instance;

    private MethodAccessKind accessKind;
    private final MethodMetaInfo metaInfo;
    private final AbstractPreparer preparer;
    private boolean calledAf;
    private boolean checkingEntryExit;
    private int checkedParams;
    private final AbstractParamReflectCheckerStepping paramCheck;

    protected AbstractRefectCommonMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo, AbstractPreparer _preparer, boolean _lda) {
        this(_instance,_metaInfo,_preparer,_lda,new AllParamReflectCheckerStepping());
    }

    protected AbstractRefectCommonMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo, AbstractPreparer _preparer, boolean _lda, AbstractParamReflectCheckerStepping _abParam) {
        super(_lda);
        instance = _instance;
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
        preparer = _preparer;
        paramCheck = _abParam;
    }
    @Override
    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        setWrapper(_wrap);
        setReturnedArgument(_argument);
    }

    boolean keep(ContextEl _context, StackCall _stackCall, Argument _instance) {
        LgNames stds_ = _context.getStandards();
        if (!initClass) {
            initClass = true;
            if (preparer.initType(_context, _stackCall)) {
                setWrapException(true);
                return false;
            }
        }
        setWrapException(false);
        if (overrideInfo == null) {
            if (metaInfo.isInstanceMethod() && _instance.isNull()) {
                String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_, _stackCall)));
                return false;
            }
            if (preparer.isAbstract(_context, _stackCall)) {
                String null_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, metaInfo.getDisplayedString(_context).getInstance(), null_, _stackCall)));
                return false;
            }
            ExecFormattedRootBlock className_ = metaInfo.getFormatted();
            if (preparer.isPolymorph(_context, _stackCall)) {
                Struct instance_ = _instance.getStruct();
                overrideInfo = ExecInvokingOperation.polymorph(_context, instance_, metaInfo.getPair());
            } else {
                overrideInfo = new ExecOverrideInfo(className_, metaInfo.getPair());
            }
            accessKind = metaInfo.getKind();
        }
        return true;
    }

    public ExecFormattedRootBlock getClassName() {
        return getOverrideInfo().getClassName();
    }

    public MethodMetaInfo getMetaInfo() {
        return metaInfo;
    }

    public StandardMethod getStdCallee() {
        return metaInfo.getStdCallee();
    }

    MethodAccessKind getAccessKind() {
        return accessKind;
    }

    public ExecTypeFunction getPair() {
        return getOverrideInfo().getPair();
    }

    public ExecOverrideInfo getOverrideInfo() {
        return overrideInfo;
    }

    public Argument getInstance() {
        return instance;
    }

    Argument direct(ContextEl _context, StackCall _stack, ArgumentListCall _l) {
        MethodMetaInfo method_ = getMetaInfo();
        String className_ = method_.getFormatted().getFormatted();
        String res_ = ExecInheritsAdv.correctClassPartsDynamicNotWildCard(className_, _context);
        if (res_.isEmpty()) {
            String null_;
            null_ = _context.getStandards().getContent().getCoreNames().getAliasIllegalType();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_, null_, _stack)));
            return Argument.createVoid();
        }
        return ExecImplicitOperation.getArgument(getClassName().getFormatted(), _context, _stack, _l);
    }

    boolean callPhase(ContextEl _context, StackCall _stack) {
        if (!calledAf) {
            setWrapException(false);
            checkingEntryExit = true;
            if (paramCheck.checkParams(this,_context, _stack)) {
                return false;
            }
            checkingEntryExit = false;
            Argument arg_ = prepareCall(_context, _stack);
            if (_stack.getCallingState() instanceof NotInitializedClass) {
                setWrapException(true);
                return false;
            }
            calledAf = true;
            if (_context.callsOrException(_stack)) {
                setWrapException(_stack.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return paramCheck.postArg(this,_stack);
    }
    protected abstract boolean checkParams(ContextEl _context, StackCall _stack);

    protected boolean checkParamsAnnot(StackCall _stack) {
        if (getCheckedParams() == 0) {
            setCheckedParams(1);
            return _stack.getStopper().isStopAtExcMethod();
        }
        return false;
    }

    protected abstract boolean postArg(StackCall _stack);
    protected boolean postArgBase(StackCall _stack) {
        if (getCheckedParams() == 1) {
            checkingEntryExit = true;
            setCheckedParams(2);
            return !_stack.getStopper().isStopAtExcMethod();
        }
        checkingEntryExit = false;
        return true;
    }
    abstract Argument prepareCall(ContextEl _context, StackCall _stack);
    public abstract int getRef();

    public boolean isCheckingEntryExit() {
        return checkingEntryExit;
    }

    public int getCheckedParams() {
        return checkedParams;
    }

    public void setCheckedParams(int _c) {
        this.checkedParams = _c;
    }
}
