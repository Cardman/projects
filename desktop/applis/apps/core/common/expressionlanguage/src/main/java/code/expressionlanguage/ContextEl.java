package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.*;

public abstract class ContextEl {

    private final CommonExecutionInfos executionInfos;

    private CallingState callingState;

    private final CustList<AbstractPageEl> importing = new CustList<AbstractPageEl>();

    private InitializingTypeInfos initializingTypeInfos;
    private AbstractFullStack fullStack;
    private Struct seed;
    private AbstractExiting exiting = new NoExiting();

    protected ContextEl(CommonExecutionInfos _executionInfos, InitPhase _readOnlyOthers) {
        executionInfos = _executionInfos;
        initializingTypeInfos = new InitializingTypeInfos();
        initializingTypeInfos.setInitEnums(_readOnlyOthers);
        seed = NullStruct.NULL_VALUE;
    }

    public GeneType getClassBody(String _type) {
        ExecRootBlock c_ = getExecutionInfos().getClasses().getClassBody(_type);
        if (c_ != null) {
            return c_;
        }
        return getExecutionInfos().getStandards().getStandards().getVal(_type);
    }
    public Initializer getInit() {
        return getExecutionInfos().getInitializer();
    }
    public int getStackOverFlow() {
        return getExecutionInfos().getStackOverFlow();
    }

    public int getTabWidth() {
        return getExecutionInfos().getTabWidth();
    }


    public AbstractMethodCriteria getDefCriteria() {
        return getExecutionInfos().getDefCriteria();
    }

    public AbstractMethodCriteria getStaticCriteria() {
        return getExecutionInfos().getStaticCriteria();
    }
    public Classes getClasses() {
        return getExecutionInfos().getClasses();
    }

    public Coverage getCoverage() {
        return getExecutionInfos().getCoverage();
    }

    public LgNames getStandards() {
        return getExecutionInfos().getStandards();
    }

    public DefaultLockingClass getLocks() {
        return getExecutionInfos().getLocks();
    }
    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
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
    }

    public void forwardAndClear(AnalyzedPageEl _ana, Forwards _forwards) {
        ForwardInfos.generalForward(_ana,_forwards, this);
        for (ClassMetaInfo c: _ana.getClassMetaInfos()) {
            getClasses().getClassMetaInfos().add(c);
        }
        _ana.getClassMetaInfos().clear();
        getClasses().setKeyWordValue(_ana.getKeyWords().getKeyWordValue());
        ExecClassesUtil.buildIterable(getClasses(), this);
    }

    public String formatVarType(String _varType) {
        return getLastPage().formatVarType(_varType);
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

    private static boolean callsOrExceptionBase(ContextEl _context) {
        if (_context.callingState != null) {
            return true;
        }
        return _context.isFailInit();
    }

    public boolean calls() {
        return !(getCallingState() instanceof CustomFoundExc);
    }

    public boolean isFailInit() {
        return initializingTypeInfos.isFailInit();
    }

    public CallingState getCallingState() {
        return callingState;
    }

    public void setCallingState(CallingState _callingState) {
        callingState = _callingState;
    }

    public AbstractFullStack getFullStack() {
        return fullStack;
    }

    public final void setFullStack(AbstractFullStack _fullStack) {
        this.fullStack = _fullStack;
    }


    public InitializingTypeInfos getInitializingTypeInfos() {
        return initializingTypeInfos;
    }

    public Struct getSeed() {
        return seed;
    }

    public void setSeed(Struct _seed) {
        this.seed = _seed;
    }

    public AbstractExiting getExiting() {
        return exiting;
    }

    public void setExiting(AbstractExiting _exiting) {
        exiting = _exiting;
    }
}
