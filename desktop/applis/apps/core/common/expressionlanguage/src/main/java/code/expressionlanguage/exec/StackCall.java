package code.expressionlanguage.exec;

import code.expressionlanguage.AbstractFullStack;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultFullStack;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.dbg.StackState;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.montecarlo.CustomSeedGene;
import code.util.CustList;

public final class StackCall implements AbstractStackCall {

    private CallingState callingState;
    private CallingState callingStateSub;

    private final CustList<AbstractPageEl> importing = new CustList<AbstractPageEl>();

    private final InitializingTypeInfos initializingTypeInfos;
    private AbstractFullStack fullStack;
    private Struct seedSpecGenerator;
    private Struct seedSpecDoubleGenerator;
    private Struct seed;
    private final CustomSeedGene seedCust;
    private boolean stoppedBreakPoint;

    private StepDbgActionEnum step;
    private boolean mute;
    private int previousNbPages;
    private final StackState stackState = new StackState();
    private final AbsStackStopper stopper;
    private CoreCheckedExecOperationNodeInfos operElt;
    public StackCall(AbsStackStopper _s,InitPhase _readOnlyOthers, CustomSeedGene _seedCust) {
        stopper = _s;
        step = _s.firstStep();
        initializingTypeInfos = new InitializingTypeInfos();
        initializingTypeInfos.setInitEnums(_readOnlyOthers);
        seedSpecGenerator = NullStruct.NULL_VALUE;
        seedSpecDoubleGenerator = NullStruct.NULL_VALUE;
        seed = NullStruct.NULL_VALUE;
        this.seedCust = _seedCust;
    }

    public static StackCall newInstance(InitPhase _readOnlyOthers, ContextEl _ctx) {
        return newInstance(_ctx.getClasses().getDebugMapping().getStopper(), _readOnlyOthers, _ctx, _ctx.getExecutionInfos().getSeed());
    }

    public static StackCall newInstance(AbsStackStopper _s,InitPhase _readOnlyOthers, ContextEl _ctx, CustomSeedGene _seed) {
        StackCall st_ = new StackCall(_s,_readOnlyOthers,CustomSeedGene.copy(_seed));
        st_.setFullStack(new DefaultFullStack(_ctx));
        return st_;
    }

    public AbsStackStopper getStopper() {
        return stopper;
    }

    public void clearPages() {
        importing.clear();
    }
    public boolean hasPages() {
        return !importing.isEmpty();
    }

    public AbstractPageEl getCall(int _index) {
        return importing.get(_index);
    }
    public int nbPages() {
        return importing.size();
    }

    public void removeLastPage() {
        importing.removeQuicklyLast();
    }

    public void addInternPage(AbstractPageEl _page) {
        importing.add(_page);
        setNullCallingState();
    }
    public void failInitEnums() {
        getInitializingTypeInfos().failInitEnums(this);
    }
    public CustomFoundExc trueException() {
        CallingState c_ = getCallingState();
        if (c_ instanceof CustomFoundExc && !((CustomFoundExc)c_).isFailInit()) {
            return (CustomFoundExc) c_;
        }
        return null;
    }
    public CustomFoundExc falseException() {
        CallingState c_ = getCallingState();
        if (c_ instanceof CustomFoundExc && ((CustomFoundExc)c_).isFailInit()) {
            return (CustomFoundExc) c_;
        }
        return null;
    }
    public static ExecFormattedRootBlock formatVarType(AbstractStackCall _stack,ExecFormattedRootBlock _varType) {
        return new ExecFormattedRootBlock(_varType, _stack.formatVarType(_varType.getFormatted()));
    }
    public String formatVarType(String _varType) {
        return getLastPage().formatVarType(_varType);
    }

    @Override
    public StackCall stack() {
        return this;
    }

    @Override
    public void putVar(String _key, LocalVariable _wrapper) {
        getLastPage().putValueVar(_key, _wrapper);
    }

    @Override
    public int sizeElLast() {
        return getLastPage().sizeEl();
    }

    public void nullReadWrite() {
        AbstractPageEl l_ = getLastPage();
        l_.setNullReadWrite();
        getStackState().resetVisit(false);
    }
    public AbstractPageEl getLastPage() {
        return importing.last();
    }

    public void setOffset(int _offset) {
        getLastPage().setOffset(_offset);
    }

    public boolean callsOrException() {
        return callsOrExceptionBase(this);
    }

    private static boolean callsOrExceptionBase(StackCall _context) {
        return _context.callingState != null;
    }

    public boolean calls() {
        return trueException() == null;
    }

    public InitializingTypeInfos getInitializingTypeInfos() {
        return initializingTypeInfos;
    }

    public boolean isFailInit() {
        return falseException() != null;
    }

    public Struct getSeedSpecDoubleGenerator() {
        return seedSpecDoubleGenerator;
    }

    public void setSeedSpecDoubleGenerator(Struct _seedSpecDoubleGenerator) {
        this.seedSpecDoubleGenerator = _seedSpecDoubleGenerator;
    }

    public Struct getSeedSpecGenerator() {
        return seedSpecGenerator;
    }

    public void setSeedSpecGenerator(Struct _seedSpecGenerator) {
        this.seedSpecGenerator = _seedSpecGenerator;
    }

    public Struct getSeed() {
        return seed;
    }

    public void setSeed(Struct _seed) {
        this.seed = _seed;
    }

    public CustomSeedGene getSeedCust() {
        return seedCust;
    }

    public CallingState getCallingState() {
        return callingState;
    }

    public void setNullCallingState() {
        this.callingState = null;
    }
    public void setCallingState(CallingState _callingState) {
        this.callingState = _callingState;
    }

    public CallingState getCallingStateSub() {
        return callingStateSub;
    }

    public void setCallingStateSub(CallingState _c) {
        this.callingStateSub = _c;
    }

    public AbstractFullStack getFullStack() {
        return fullStack;
    }

    public void setFullStack(AbstractFullStack _fullStack) {
        this.fullStack = _fullStack;
    }

    public boolean isStoppedBreakPoint() {
        return stoppedBreakPoint;
    }

    public void setStoppedBreakPoint(boolean _s) {
        this.stoppedBreakPoint = _s;
    }

    public StepDbgActionEnum getStep() {
        return step;
    }

    public void setStep(StepDbgActionEnum _s) {
        this.step = _s;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean _m) {
        this.mute = _m;
    }

    public int getPreviousNbPages() {
        return previousNbPages;
    }

    public void setPreviousNbPages(int _p) {
        this.previousNbPages = _p;
    }

    public StackState getStackState() {
        return stackState;
    }

    public CoreCheckedExecOperationNodeInfos getOperElt() {
        return operElt;
    }

    public void setOperElt(CoreCheckedExecOperationNodeInfos _o) {
        this.operElt = _o;
    }
}
