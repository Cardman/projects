package code.expressionlanguage.exec;

import code.expressionlanguage.AbstractFullStack;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultFullStack;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.montecarlo.CustomSeedGene;
import code.util.CustList;

public final class StackCall implements AbstractStackCall {

    private CallingState callingState;
    private ReadWrite readWrite = ReadWrite.ENTRY;

    private final CustList<AbstractPageEl> importing = new CustList<AbstractPageEl>();

    private final InitializingTypeInfos initializingTypeInfos;
    private AbstractFullStack fullStack;
    private Struct seedSpecGenerator;
    private Struct seedSpecDoubleGenerator;
    private Struct seed;
    private final CustomSeedGene seedCust;

    private final BreakPointInfo breakPointInfo = new BreakPointInfo();
    private final AbsStackStopper stopper;
    private AbstractWrapper wrapper;
    private Struct returnedArgument = NullStruct.NULL_VALUE;
    private ArrayStruct stackView = new ArrayStruct(0,"");
    private int callCondition;
    public StackCall(AbsStackStopper _s,InitPhase _readOnlyOthers, CustomSeedGene _seedCust) {
        stopper = _s;
        breakPointInfo.getBreakPointInputInfo().setStep(_s.firstStep());
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

    public boolean stopAt(ContextEl _context){
        return getStopper().stopAt(this) || _context.callsOrException(this);
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

    public boolean normalCallNoExit(ContextEl _context) {
        return getBreakPointInfo().getBreakPointMiddleInfo().getExiting() == null && normalCall(_context);
    }

    public boolean normalCall(ContextEl _context) {
        return trueException() == null && _context.callsOrException(this);
    }
    public void stackView(ContextEl _cont) {
        stackView = MetaInfoUtil.newStackTraceElementArray(_cont,this);
    }

    public ArrayStruct getStackView() {
        return stackView;
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

    public ArgumentWrapper aw() {
        return new ArgumentWrapper(ArgumentListCall.toStr(getReturnedArgument()),getWrapper());
    }
    public ReadWrite getReadWrite() {
        return readWrite;
    }
    public void setNullReadWrite() {
        setReadWrite(ReadWrite.EXIT);
    }

    public void setNullReadWriteFail() {
        setReadWrite(ReadWrite.EXIT_FAIL);
    }

    public void setReadWrite(ReadWrite _readWrite) {
        readWrite = _readWrite;
    }

    public void entryReadWrite() {
        setReadWrite(ReadWrite.ENTRY);
    }

    public void nullReadWrite() {
        setNullReadWrite();
    }

    public void nullReadWriteFail() {
        setNullReadWriteFail();
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

    public AbstractFullStack getFullStack() {
        return fullStack;
    }

    public void setFullStack(AbstractFullStack _fullStack) {
        this.fullStack = _fullStack;
    }

    public BreakPointInfo getBreakPointInfo() {
        return breakPointInfo;
    }

    public Struct getReturnedArgument() {
        return returnedArgument;
    }

    public void setReturnedArgument(Struct _r) {
        this.returnedArgument = _r;
    }

    public AbstractWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(AbstractWrapper _w) {
        this.wrapper = _w;
    }

    public int getCallCondition() {
        return callCondition;
    }

    public void setCallCondition(int _c) {
        this.callCondition = _c;
    }
}
