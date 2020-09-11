package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.instr.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.types.*;
import code.util.*;

public abstract class ContextEl {

    private CommonExecutionInfos executionInfos;

    private CallingState callingState;

    private AnalyzedPageEl analyzing;

    private CustList<AbstractPageEl> importing = new CustList<AbstractPageEl>();

    private InitializingTypeInfos initializingTypeInfos = new InitializingTypeInfos();
    private AbstractFullStack fullStack;
    private Struct seed = NullStruct.NULL_VALUE;

    public ContextEl(int _stackOverFlow,
                     DefaultLockingClass _lock, Options _options,
                     LgNames _stds, int _tabWitdth) {
        this();
        setExecutionInfos(new CommonExecutionInfos(_tabWitdth,_stackOverFlow,_stds,new Classes(),new Coverage(_options.isCovering())));
        getExecutionInfos().getClasses().setLocks(_lock);
    }
    protected ContextEl() {
        setFullStack(new DefaultFullStack(this));
    }

    public GeneType getClassBody(String _type) {
        ExecRootBlock c_ = executionInfos.getClasses().getClassBody(_type);
        if (c_ != null) {
            return c_;
        }
        return executionInfos.getStandards().getStandards().getVal(_type);
    }

    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
    }

    public void setExecutionInfos(CommonExecutionInfos executionInfos) {
        this.executionInfos = executionInfos;
    }

    public int getStackOverFlow() {
        return executionInfos.getStackOverFlow();
    }

    public int getTabWidth() {
        return executionInfos.getTabWidth();
    }

    private String getLocationFile(String _fileName, int _sum) {
        FileBlock file_ = analyzing.getFileBody(_fileName);
        FileMetrics metrics_ = file_.getMetrics(analyzing.getTabWidth());
        int r_ = metrics_.getRowFile(_sum);
        int c_ = metrics_.getColFile(_sum,r_);
        return StringList.concat( Integer.toString(r_),",",Integer.toString(c_),",",Integer.toString(_sum));
    }

    public void addWarning(FoundWarningInterpret _warning) {
        _warning.setLocationFile(getLocationFile(_warning.getFileName(),_warning.getIndexFile()));
        analyzing.addWarning(_warning);
    }

    public boolean isEmptyMessageError() {
        return analyzing.isEmptyMessageError();
    }
    public void addMessageError(String _std) {
        analyzing.addMessageError(_std);
    }

    public boolean isEmptyErrors() {
        return analyzing == null || (analyzing.isEmptyErrors() && analyzing.isEmptyStdError() && analyzing.isEmptyMessageError());
    }
    public boolean isEmptyStdError() {
        return analyzing.isEmptyStdError();
    }
    public void addStdError(StdWordError _std) {
        analyzing.addStdError(_std);
    }
    public void addError(FoundErrorInterpret _error) {
        _error.setLocationFile(getLocationFile(_error.getFileName(),_error.getIndexFile()));
        analyzing.addError(_error);
    }

    public Classes getClasses() {
        return executionInfos.getClasses();
    }

    public Coverage getCoverage() {
        return executionInfos.getCoverage();
    }

    public boolean isGettingParts() {
        return isCovering() || isGettingErrors();
    }

    public boolean isCovering() {
        return analyzing.getCoverage().isCovering();
    }

    public boolean isGettingErrors() {
        return analyzing.isGettingErrors();
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

    public String getCurrentFileName() {
        return analyzing.getCurrentBlock().getFile().getFileName();
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public void setAnalyzing() {
        analyzing = new AnalyzedPageEl();
        analyzing.setProcessKeyWord(new DefaultProcessKeyWord());
        analyzing.setHiddenTypes(new DefaultHiddenTypes(this));
        analyzing.setCurrentConstraints(new DefaultCurrentConstraints(this));
        analyzing.setAnnotationAnalysis(new DefaultAnnotationAnalysis(this));
        analyzing.setCurrentGlobalBlock(new DefaultCurrentGlobalBlock(this));
        analyzing.setLoopDeclaring(new DefaultLoopDeclaring(this));
        analyzing.setLocalDeclaring(new DefaultLocalDeclaring(this));
        analyzing.setBuildingConstraints(new DefaultBuildingConstraints(this));
        analyzing.setLocalizer(new DefaultLocalizer(this));
        analyzing.setTokenValidation(new DefaultTokenValidation(this));
    }
    public void forwardAndClear() {
        for (ClassMetaInfo c: getAnalyzing().getClassMetaInfos()) {
            getAnalyzing().getClasses().getClassMetaInfos().add(c);
        }
        getAnalyzing().getClassMetaInfos().clear();
        getAnalyzing().getClasses().setKeyWordValue(getKeyWords().getKeyWordValue());
        ValidatorStandard.buildIterable(this);
    }
    public void setNullAnalyzing() {
        analyzing = null;
    }

    public AbstractPageEl getLastPage() {
        return importing.last();
    }

    public LgNames getStandards() {
        return executionInfos.getStandards();
    }

    public void setGlobalClass(String _globalClass) {
        analyzing.setGlobalClass(_globalClass);
        analyzing.setGlobalType(analyzing.getAnaClassBody(StringExpUtil.getIdFromAllTypes(_globalClass)));
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

    public abstract Initializer getInit();

    public void setCurrentChildTypeIndex(int _index) {
        analyzing.setIndexChildType(_index);
    }

    public AbstractFullStack getFullStack() {
        return fullStack;
    }

    public final void setFullStack(AbstractFullStack fullStack) {
        this.fullStack = fullStack;
    }

    public StringList getNeedInterfaces() {
        return analyzing.getNeedInterfaces();
    }


    public boolean isAssignedStaticFields() {
        return analyzing.isAssignedStaticFields();
    }

    public void setAssignedStaticFields(boolean _assignedStaticFields) {
        analyzing.setAssignedStaticFields(_assignedStaticFields);
    }

    public boolean isAssignedFields() {
        return analyzing.isAssignedFields();
    }

    public void setAssignedFields(boolean _assignedFields) {
        analyzing.setAssignedFields(_assignedFields);
    }

    public String getIndexClassName() {
        return ((ForMutableIterativeLoop)analyzing.getCurrentBlock()).getImportedClassIndexName();
    }

    public boolean isAnnotAnalysis() {
        return analyzing.isAnnotAnalysis();
    }

    public void setAnnotAnalysis(boolean _ana) {
        analyzing.setAnnotAnalysis(_ana);
    }
    public boolean isAnnotAnalysisField() {
        return analyzing.isAnnotAnalysisField();
    }

    public void setAnnotAnalysisField(boolean _ana) {
        analyzing.setAnnotAnalysisField(_ana);
    }

    public int getCurrentLocationIndex() {
        return analyzing.getTraceIndex();
    }

    public AnalysisMessages getAnalysisMessages() {
        return analyzing.getAnalysisMessages();
    }

    public KeyWords getKeyWords() {
        return analyzing.getKeyWords();
    }


    public AccessedBlock getCurrentGlobalBlockImporting() {
        return getAnalyzing().getImportingTypes();
    }

    public ExecAccessingImportingBlock getImportingAcces() {
        return getAnalyzing().getImportingAcces();
    }

    public AccessedBlock getCurrentGlobalBlock() {
        return getAnalyzing().getImporting();
    }


    public AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl) {
        CustList<PartOffset> offs_ = getAnalyzing().getCurrentParts();
        offs_.clear();
        return _bl;
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
