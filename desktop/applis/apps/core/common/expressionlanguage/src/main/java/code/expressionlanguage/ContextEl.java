package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.instr.DefaultProcessKeyWord;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.types.DefaultAnnotationAnalysis;
import code.expressionlanguage.types.DefaultBuildingConstraints;
import code.expressionlanguage.types.DefaultCurrentConstraints;
import code.expressionlanguage.types.DefaultCurrentGlobalBlock;
import code.expressionlanguage.types.DefaultHiddenTypes;
import code.expressionlanguage.types.DefaultLocalDeclaring;
import code.expressionlanguage.types.DefaultLocalizer;
import code.expressionlanguage.types.DefaultLoopDeclaring;
import code.expressionlanguage.types.DefaultTokenValidation;
import code.util.*;

public abstract class ContextEl {

    private final CommonExecutionInfos executionInfos;

    private CallingState callingState;

    private AnalyzedPageEl analyzing;

    private final CustList<AbstractPageEl> importing = new CustList<AbstractPageEl>();

    private InitializingTypeInfos initializingTypeInfos;
    private AbstractFullStack fullStack;
    private Struct seed;

    protected ContextEl(CommonExecutionInfos _executionInfos) {
        executionInfos = _executionInfos;
        initializingTypeInfos = new InitializingTypeInfos();
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
        importing.removeLast();
    }

    public void addInternPage(AbstractPageEl _page) {
        importing.add(_page);
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public AnalyzedPageEl setAnalyzing() {
        analyzing = setInnerAnalyzing();
        return analyzing;
    }
    public static AnalyzedPageEl setInnerAnalyzing() {
        AnalyzedPageEl page_ = new AnalyzedPageEl();
        page_.setProcessKeyWord(new DefaultProcessKeyWord());
        page_.setHiddenTypes(new DefaultHiddenTypes(page_));
        page_.setCurrentConstraints(new DefaultCurrentConstraints(page_));
        page_.setAnnotationAnalysis(new DefaultAnnotationAnalysis(page_));
        page_.setCurrentGlobalBlock(new DefaultCurrentGlobalBlock(page_));
        page_.setLoopDeclaring(new DefaultLoopDeclaring(page_));
        page_.setLocalDeclaring(new DefaultLocalDeclaring(page_));
        page_.setBuildingConstraints(new DefaultBuildingConstraints(page_));
        page_.setLocalizer(new DefaultLocalizer(page_));
        page_.setTokenValidation(new DefaultTokenValidation(page_));
        return page_;
    }
    public void forwardAndClear(AnalyzedPageEl _ana) {
        for (ClassMetaInfo c: _ana.getClassMetaInfos()) {
            _ana.getClasses().getClassMetaInfos().add(c);
        }
        _ana.getClassMetaInfos().clear();
        _ana.getClasses().setKeyWordValue(_ana.getKeyWords().getKeyWordValue());
        ValidatorStandard.buildIterable(_ana);
    }
    public void setNullAnalyzing() {
        analyzing = null;
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
        return !hasException();
    }

    public boolean hasException() {
        return callingState instanceof Struct;
    }
    public boolean isFailInit() {
        return initializingTypeInfos.isFailInit();
    }

    public void setException(Struct _exception) {
        callingState = _exception;
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

    public final void setFullStack(AbstractFullStack fullStack) {
        this.fullStack = fullStack;
    }


    public InitializingTypeInfos getInitializingTypeInfos() {
        return initializingTypeInfos;
    }

    public Struct getSeed() {
        return seed;
    }

    public void setSeed(Struct seed) {
        this.seed = seed;
    }
}
