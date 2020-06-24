package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.files.CommentDelimiters;
import code.expressionlanguage.instr.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.types.*;
import code.util.*;

public abstract class ContextEl {

    private static final int DEFAULT_TAB_WIDTH = 4;

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private int stackOverFlow;

    private Options options;

    private LocalThrowing throwing;

    private CallingState callingState;

    private LgNames standards;

    private AnalyzedPageEl analyzing;

    private Classes classes;

    private CustList<AbstractPageEl> importing = new CustList<AbstractPageEl>();

    private AnalysisMessages analysisMessages;
    private KeyWords keyWords;
    private InitializingTypeInfos initializingTypeInfos = new InitializingTypeInfos();
    private boolean covering;
    private Coverage coverage;
    private AbstractFullStack fullStack;
    private CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
    private Struct seed = NullStruct.NULL_VALUE;

    public ContextEl(boolean _covering, int _stackOverFlow,
                     DefaultLockingClass _lock,Options _options,
                     AnalysisMessages _mess,
                     KeyWords _keyWords, LgNames _stds, int _tabWitdth) {
        this();
        setCovering(_covering);
        setOptions(_options);
        setStackOverFlow(_stackOverFlow);
        setStandards(_stds);
        setTabWidth(_tabWitdth);
        setAnalysisMessages(_mess);
        setKeyWords(_keyWords);
        setClasses(new Classes());
        setThrowing(new LocalThrowing());
        setCoverage(new Coverage());
        classes.setLocks(_lock);
        comments = _options.getComments();
    }
    protected ContextEl() {
        setFullStack(new DefaultFullStack(this));
    }

    public GeneType getClassBody(String _type) {
        ExecRootBlock c_ = classes.getClassBody(_type);
        if (c_ != null) {
            return c_;
        }
        return standards.getStandards().getVal(_type);
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options _options) {
        options = _options;
    }

    public int getStackOverFlow() {
        return stackOverFlow;
    }
    public void setStackOverFlow(int _stackOverFlow) {
        stackOverFlow = _stackOverFlow;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    private String getLocationFile(String _fileName, int _sum) {
        ExecFileBlock file_ = classes.getFileBody(_fileName);
        int r_ = file_.getRowFile(_sum);
        int c_ = file_.getColFile(_sum,r_);
        return StringList.concat( Integer.toString(r_),",",Integer.toString(c_),",",Integer.toString(_sum));
    }

    public void addWarning(FoundWarningInterpret _warning) {
        _warning.setLocationFile(getLocationFile(_warning.getFileName(),_warning.getIndexFile()));
        classes.addWarning(_warning);
    }

    public boolean isEmptyErrors() {
        return classes.isEmptyErrors() && classes.isEmptyStdError() && classes.isEmptyMessageError();
    }

    public void addError(FoundErrorInterpret _error) {
        _error.setLocationFile(getLocationFile(_error.getFileName(),_error.getIndexFile()));
        classes.addError(_error);
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes _classes) {
        classes = _classes;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public void setCoverage(Coverage _coverage) {
        coverage = _coverage;
    }

    public boolean isCovering() {
        return covering;
    }

    public void setCovering(boolean _covering) {
        covering = _covering;
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

    public void setNullAnalyzing() {
        analyzing = null;
    }

    public AbstractPageEl getLastPage() {
        return importing.last();
    }

    public LgNames getStandards() {
        return standards;
    }

    public void setStandards(LgNames _standards) {
        standards = _standards;
    }

    public void setGlobalClass(String _globalClass) {
        analyzing.setGlobalClass(_globalClass);
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

    public LocalThrowing getThrowing() {
        return throwing;
    }
    public void setThrowing(LocalThrowing _throwing) {
        throwing = _throwing;
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

    public boolean isFinalLocalVar(String _key, int _index) {
        return analyzing.isFinalLocalVar(_key, _index);
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

    public boolean isFinalMutableLoopVar(String _key, int _index) {
        return analyzing.isFinalMutableLoopVar(_key,_index);
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
        return analysisMessages;
    }

    public void setAnalysisMessages(AnalysisMessages _analysisMessages) {
        analysisMessages = _analysisMessages;
    }

    public KeyWords getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(KeyWords _keyWords) {
        keyWords = _keyWords;
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
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
