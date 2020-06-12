package code.expressionlanguage;

import code.expressionlanguage.calls.*;
import code.expressionlanguage.calls.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.files.CommentDelimiters;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.instr.*;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.Coverage;
import code.expressionlanguage.methods.util.LocalThrowing;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.AnnotationInstanceOperation;
import code.expressionlanguage.opers.AssocationOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.*;
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

    public static CustList<AnnotationMethodBlock> getAnnotationMethods(GeneType _element) {
        CustList<AnnotationMethodBlock> methods_ = new CustList<AnnotationMethodBlock>();
        for (Block b: Classes.getDirectChildren((RootBlock)_element)) {
            if (b instanceof AnnotationMethodBlock) {
                methods_.add((AnnotationMethodBlock) b);
            }
        }
        return methods_;
    }
    public static CustList<GeneMethod> getMethodBlocks(GeneType _element) {
        CustList<GeneMethod> methods_ = new CustList<GeneMethod>();
        if (_element instanceof RootBlock) {
            for (GeneCustMethod m:Classes.getMethodBlocks((RootBlock) _element)) {
                methods_.add(m);
            }
        }
        if (_element instanceof StandardType) {
            for (StandardMethod m : ((StandardType) _element).getMethods().values()) {
                methods_.add(m);
            }
        }
        return methods_;
    }

    public static CustList<InfoBlock> getFieldBlocks(RootBlock _element){
        CustList<InfoBlock> methods_ = new CustList<InfoBlock>();
        for (Block b: Classes.getDirectChildren(_element)) {
            if (b instanceof InfoBlock) {
                methods_.add((InfoBlock) b);
            }
        }
        return methods_;
    }

    public GeneType getClassBody(String _type) {
        if (classes.isCustomType(_type)) {
            return classes.getClassBody(_type);
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

    public void addErrorIfNoMatch(String _generic, String _base, Block _currentBlock, int _location) {
        String id_ = Templates.getIdFromAllTypes(_generic);
        if (!StringList.quickEq(id_,_base)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //one full direct super type
            un_.buildError(getAnalysisMessages().getUnknownType(),
                    _generic);
            addError(un_);
        }
    }

    private String getLocationFile(String _fileName, int _sum) {
        FileBlock file_ = classes.getFileBody(_fileName);
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

    void clearPages() {
        importing.clear();
    }
    public boolean hasPages() {
        return !importing.isEmpty();
    }

    AbstractPageEl getCall(int _index) {
        return importing.get(_index);
    }
    public int nbPages() {
        return importing.size();
    }

    public void removeLastPage() {
        importing.removeLast();
    }

    public void addPage(AbstractPageEl _page) {
        LgNames stds_ = getStandards();
        String sof_ = stds_.getAliasSof();
        if (getStackOverFlow() >= CustList.FIRST_INDEX && getStackOverFlow() <= importing.size()) {
            callingState = new ErrorStruct(this,sof_);
        } else {
            importing.add(_page);
        }
    }

    public String getCurrentFileName() {
        return analyzing.getCurrentBlock().getFile().getFileName();
    }

    public boolean hasDeclarator() {
        Block bl_ = analyzing.getCurrentBlock();
        return bl_.getPreviousSibling() instanceof DeclareVariable;
    }

    public void setupDeclaratorClass(String _className) {
        Block bl_ = analyzing.getCurrentBlock();
        Block previousSibling_ = bl_.getPreviousSibling();
        ((DeclareVariable)previousSibling_).setImportedClassName(_className);
    }

    public boolean hasLoopDeclarator() {
        Block bl_ = analyzing.getCurrentBlock();
        return bl_ instanceof ForMutableIterativeLoop;
    }

    public void setupLoopDeclaratorClass(String _className) {
        Block bl_ = analyzing.getCurrentBlock();
        ((ForMutableIterativeLoop)bl_).setImportedClassName(_className);
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public boolean isStaticAccess() {
        if (isAnnotAnalysis()) {
            return true;
        }
        Block bl_ = analyzing.getCurrentBlock();
        if (bl_ instanceof InfoBlock) {
            return ((InfoBlock)bl_).isStaticField();
        }
        if (bl_ instanceof RootBlock) {
            return ((RootBlock)bl_).isStaticType();
        }
        FunctionBlock fct_ = analyzing.getCurrentFct();
        return fct_.getStaticContext() == MethodAccessKind.STATIC;
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

    public static int getCurrentChildTypeIndex(ContextEl _an, OperationNode _op, GeneType _type, String _fieldName, String _realClassName) {
        if (isEnumType(_type)) {
            if (_fieldName.isEmpty()) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                String file_ = _an.getAnalyzing().getLocalizer().getCurrentFileName();
                int fileIndex_ = _an.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                call_.setFileName(file_);
                call_.setIndexFile(fileIndex_);
                //type len
                call_.buildError(_an.getAnalysisMessages().getIllegalCtorEnum());
                _an.getAnalyzing().getLocalizer().addError(call_);
                _op.setResultClass(new ClassArgumentMatching(_realClassName));
                return -2;
            }
            return _an.getAnalyzing().getIndexChildType();
        }
        return -1;
    }
    public static boolean isAbstractType(GeneType _type) {
        if (_type instanceof StandardInterface) {
            return true;
        }
        if (_type instanceof RootBlock) {
            return ((RootBlock)_type).isAbstractType();
        }
        return ((StandardClass)_type).isAbstractStdType();
    }
    public static boolean isEnumType(GeneType _type) {
        return _type instanceof EnumBlock || _type instanceof InnerElementBlock;
    }

    public void setCurrentChildTypeIndex(int _index) {
        analyzing.setIndexChildType(_index);
    }

    public boolean isFinalLocalVar(String _key, int _index) {
        return analyzing.isFinalLocalVar(_key, _index);
    }

    public final void setFullStack(AbstractFullStack fullStack) {
        this.fullStack = fullStack;
    }

    public ArrayStruct newStackTraceElementArrayFull() {
        return fullStack.newStackTraceElementArray();
    }

    public ArrayStruct newStackTraceElementArray() {
        int count_ = nbPages();
        Struct[] arr_ = new Struct[count_];
        for (int i = 0; i < count_; i++) {
            arr_[i] = newStackTraceElement(i);
        }
        String cl_ = getStandards().getAliasStackTraceElement();
        cl_ = PrimitiveTypeUtil.getPrettyArrayType(cl_);
        return new ArrayStruct(arr_, cl_);
    }

    public StackTraceElementStruct newStackTraceElement(int _index) {
        AbstractPageEl call_ = getCall(_index);
        int indexFileType = call_.getTraceIndex();
        FileBlock f_ = call_.getFile();
        String fileName;
        int row;
        int col;
        if (f_ != null) {
            fileName = f_.getFileName();
            row = f_.getRowFile(indexFileType);
            col = f_.getColFile(indexFileType,row);
        } else {
            fileName = "";
            row = 0;
            col = 0;
        }
        String currentClassName = call_.getGlobalClass();
        Block bl_ = call_.getBlockRoot();
        if (bl_ != null) {
            FunctionBlock fct_ = bl_.getFunction();
            if (fct_ instanceof ReturnableWithSignature) {
                String signature =((ReturnableWithSignature)fct_).getSignature(this);
                return new StackTraceElementStruct(fileName,row,col,indexFileType,currentClassName,signature);
            }
        }
        String signature = "";
        return new StackTraceElementStruct(fileName,row,col,indexFileType,currentClassName,signature);
    }

    public StringList getNeedInterfaces() {
        return analyzing.getNeedInterfaces();
    }

    public StringMap<StringList> getCurrentConstraints() {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (EntryCust<String,TypeVar> e: getCurrentConstraintsFull().entryList()) {
            vars_.addEntry(e.getKey(), e.getValue().getConstraints());
        }
        return vars_;
    }

    public void buildCurrentConstraintsFull() {
        StringMap<TypeVar> vars_ = getCurrentConstraintsFull();
        analyzing.getAvailableVariables().clear();
        for (EntryCust<String,TypeVar> e: vars_.entryList()) {
            analyzing.getAvailableVariables().addEntry(e.getKey(),e.getValue().getOffset());
        }
    }
    private StringMap<TypeVar> getCurrentConstraintsFull() {
        if (isAnnotAnalysis()) {
            return new StringMap<TypeVar>();
        }
        Block bl_ = analyzing.getCurrentBlock();
        AccessingImportingBlock r_ = getCurrentGlobalBlock();
        StringMap<TypeVar> vars_ = new StringMap<TypeVar>();

        boolean static_;
        if (bl_ instanceof InfoBlock) {
            static_ = ((InfoBlock)bl_).isStaticField();
        } else {
            FunctionBlock fct_ = analyzing.getCurrentFct();
            if (fct_ == null) {
                static_ = true;
            } else if (isExplicitFct(fct_)){
                static_ = false;
            } else {
                static_ = fct_.getStaticContext() == MethodAccessKind.STATIC;
            }
        }
        if (r_ instanceof RootBlock && !static_) {
            for (TypeVar t: ((RootBlock)r_).getParamTypesMapValues()) {
                vars_.put(t.getName(), t);
            }
        }
        return vars_;
    }

    private boolean isExplicitFct(FunctionBlock _fct) {
        return _fct instanceof OverridableBlock
                && (((OverridableBlock) _fct).getKind() == MethodKind.EXPLICIT_CAST
        ||((OverridableBlock) _fct).getKind() == MethodKind.IMPLICIT_CAST);
    }


    public void appendParts(int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        if (!isCovering()) {
            return;
        }
        GeneType g_ = getClassBody(_in);
        if (!ElUtil.isFromCustFile(g_)) {
            return;
        }
        AccessingImportingBlock r_ = getCurrentGlobalBlock();
        int rc_ = getCurrentLocationIndex();
        String curr_ = ((Block)r_).getFile().getRenderFileName();
        String ref_ = ((RootBlock) g_).getFile().getRenderFileName();
        String rel_ = ElUtil.relativize(curr_,ref_);
        int id_ = ((RootBlock) g_).getIdRowCol();
        _parts.add(new PartOffset("<a title=\""+g_.getFullName()+"\" href=\""+rel_+"#m"+id_+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    public void appendTitleParts(int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        if (!isCovering()) {
            return;
        }
        int rc_ = getCurrentLocationIndex();
        _parts.add(new PartOffset("<a title=\""+ElUtil.transform(_in)+"\">",rc_+_begin));
        _parts.add(new PartOffset("</a>",rc_+_end));
    }

    public FieldInfo getFieldInfo(ClassField _classField) {
        GeneType g_ = getClassBody(_classField.getClassName());
        String search_ = _classField.getFieldName();
        if (g_ instanceof RootBlock) {
            for (Block b: Classes.getDirectChildren((Block) g_)) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock) b;
                if (!StringList.contains(i_.getFieldName(), search_)) {
                    continue;
                }
                String type_ = i_.getImportedClassName();
                boolean final_ = i_.isFinalField();
                boolean static_ = i_.isStaticField();
                return FieldInfo.newFieldMetaInfo(search_, g_.getFullName(), type_, static_, final_,i_);
            }
            return null;
        }
        if (g_ instanceof StandardType) {
            for (EntryCust<String, StandardField> f: ((StandardType)g_).getFields().entryList()) {
                StandardField f_ = f.getValue();
                if (!StringList.contains(f_.getFieldName(), search_)) {
                    continue;
                }
                String type_ = f_.getImportedClassName();
                boolean final_ = f_.isFinalField();
                boolean static_ = f_.isStaticField();
                return FieldInfo.newFieldMetaInfo(search_, g_.getFullName(), type_, static_, final_, f_);
            }
        }
        return null;
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

    public boolean isAnnotAnalysis(OperationNode _op, OperationsSequence _seq) {
        boolean ok_ = false;
        if ((analyzing.getCurrentBlock() instanceof AnnotationMethodBlock && _op == null)
                || _op instanceof AssocationOperation
                || _op instanceof AnnotationInstanceOperation) {
            ok_ = true;
        }
        if (!ok_) {
            return false;
        }
        String op_ = _seq.getOperators().firstValue();
        return StringList.quickEq(op_, String.valueOf('{'));
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

    public boolean isValidSingleToken(String _id) {
        if (!isValidToken(_id)) {
            return false;
        }
        return idDisjointToken(_id);
    }

    public boolean idDisjointToken(String _id) {
        return isNotVar(_id);
    }

    public boolean isNotVar(String _id) {
        if (analyzing.containsLocalVar(_id)) {
            return false;
        }
        if (analyzing.containsCatchVar(_id)) {
            return false;
        }
        if (analyzing.containsMutableLoopVar(_id)) {
            return false;
        }
        if (analyzing.containsVar(_id)) {
            return false;
        }
        return !analyzing.getParameters().contains(_id);
    }

    public boolean isValidToken(String _id) {
        Block b_ = analyzing.getCurrentBlock();
        boolean pred_ = b_.getFile().isPredefined();
        return isValidToken(_id, pred_);
    }

    public boolean isValidToken(String _id, boolean _pred) {
        if (_pred) {
            if (!StringList.isDollarWord(_id)) {
                return false;
            }
        } else {
            if (!StringList.isWord(_id)) {
                return false;
            }
        }
        if (PrimitiveTypeUtil.isPrimitive(_id, this)) {
            return false;
        }
        if (keyWords.isKeyWordNotVar(_id)) {
            return false;
        }
        if (StringList.quickEq(_id, standards.getAliasVoid())) {
            return false;
        }
        return !StringExpUtil.isDigit(_id.charAt(0));
    }

    public boolean hasToExit(String _className) {
        Classes classes_ = getClasses();
        String idClass_ = Templates.getIdFromAllTypes(_className);
        String curClass_ = getLastPage().getGlobalClass();
        curClass_ = Templates.getIdFromAllTypes(curClass_);
        if (StringList.quickEq(curClass_, idClass_)) {
            return false;
        }
        if (classes_.isCustomType(idClass_)) {
            DefaultLockingClass locks_ = classes_.getLocks();
            if (getInitializingTypeInfos().isInitEnums()) {
                InitClassState res_ = locks_.getState(idClass_);
                if (res_ != InitClassState.SUCCESS) {
                    getInitializingTypeInfos().failInitEnums();
                    return true;
                }
                return false;
            }
            InitClassState res_ = locks_.getState(this, idClass_);
            if (res_ == InitClassState.NOT_YET) {
                setCallingState(new NotInitializedClass(idClass_));
                return true;
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(idClass_, this);
                setException(causing_);
                return true;
            }
        }
        return false;
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }


    public AccessingImportingBlock getCurrentGlobalBlock() {
        return getAnalyzing().getImporting();
    }


    public AccessingImportingBlock getCurrentGlobalBlock(AccessingImportingBlock _bl) {
        CustList<PartOffset> offs_ = getCoverage().getCurrentParts();
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
