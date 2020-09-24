package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.AnonymousInstancingOperation;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.types.InaccessibleType;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.analyze.errors.stds.StdWordError;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ClassesCommon;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.exec.opers.ExecAnonymousInstancingOperation;
import code.expressionlanguage.exec.opers.ExecAnonymousLambdaOperation;
import code.expressionlanguage.exec.util.PolymorphMethod;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.instr.AbstractProcessKeyWord;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.DefaultProcessKeyWord;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.types.*;
import code.util.*;

public final class AnalyzedPageEl {

    /**Only used while throwing exception*/

    private static final int DEFAULT_TAB_WIDTH = 4;

    private int tabWidth = DEFAULT_TAB_WIDTH;
    private LgNames standards;
    private Classes classes;
    private ClassesCommon classesCommon;
    private Coverage coverage;

    private Block currentBlock;
    private AnalyzedBlock currentAnaBlock;

    private String globalClass = "";
    private RootBlock globalType;
    private RootBlock globalDirType;

    private final StringMap<AnaLocalVariable> infosVars = new StringMap<AnaLocalVariable>();
    private final StringMap<AnaLoopVariable> loopsVars = new StringMap<AnaLoopVariable>();
    private final AnaCache cache = new AnaCache();

    private MemberCallingsBlock currentFct;
    private AccessedBlock importing;
    private ExecAccessingImportingBlock importingAcces;
    private AccessedBlock importingTypes;
    private final CustList<RootBlock> listTypesNames = new CustList<RootBlock>();
    private int countTypes;
    private final IdMap<InnerElementBlock,ExecInnerElementBlock> mapInnerEltTypes = new IdMap<InnerElementBlock,ExecInnerElementBlock>();
    private int countInnerEltTypes;
    private final IdMap<RootBlock,Members> mapMembers = new IdMap<RootBlock,Members>();
    private final IdMap<OperatorBlock,ExecOperatorBlock> mapOperators = new IdMap<OperatorBlock,ExecOperatorBlock>();
    private int countOperators;
    private final IdMap<AnonymousFunctionBlock,ExecAnonymousFunctionBlock> mapAnonLambda = new IdMap<AnonymousFunctionBlock,ExecAnonymousFunctionBlock>();
    private final IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock> mapAnonTypes = new IdMap<AnonymousTypeBlock,ExecAnonymousTypeBlock>();
    private int countAnonTypes;
    private final IdMap<Block,AssBlock> fieldsAssSt = new IdMap<Block,AssBlock>();
    private final IdMap<Block,AssBlock> fieldsAss = new IdMap<Block,AssBlock>();
    private final IdMap<MemberCallingsBlock,AnalyzingEl> resultsAna = new IdMap<MemberCallingsBlock,AnalyzingEl>();
    private final IdMap<MemberCallingsBlock,AnalyzingEl> resultsAnaInst = new IdMap<MemberCallingsBlock,AnalyzingEl>();
    private final IdMap<MemberCallingsBlock,AnalyzingEl> resultsAnaNamed = new IdMap<MemberCallingsBlock,AnalyzingEl>();
    private final IdMap<MemberCallingsBlock,AnalyzingEl> resultsAnaMethod = new IdMap<MemberCallingsBlock,AnalyzingEl>();
    private final IdMap<AnonymousFunctionBlock,AnalyzingEl> resultsMethod = new IdMap<AnonymousFunctionBlock,AnalyzingEl>();
    private final IdMap<OperatorBlock,AnalyzingEl> resultsAnaOperator = new IdMap<OperatorBlock,AnalyzingEl>();
    private final CustList<RootBlock> foundTypes = new CustList<RootBlock>();
    private final NatStringTreeMap<RootBlock> sorted = new NatStringTreeMap<RootBlock>();
    private final CustList<RootBlock> allFoundTypes = new CustList<RootBlock>();
    private final CustList<OperatorBlock> foundOperators = new CustList<OperatorBlock>();
    private final CustList<OperatorBlock> allOperators = new CustList<OperatorBlock>();
    private final CustList<RootBlock> prevFoundTypes = new CustList<RootBlock>();
    private final CustList<RootBlock> refFoundTypes = new CustList<RootBlock>();
    private IdMap<MemberCallingsBlock,ExecMemberCallingsBlock> allFct = new IdMap<MemberCallingsBlock,ExecMemberCallingsBlock>();
    private final IdMap<RootBlock,ClassMethodIdReturn> toStr = new IdMap<RootBlock, ClassMethodIdReturn>();

    private int offset;

    private MethodAccessKind staticContext;

    private int globalOffset;

    private int translatedOffset;
    private int indexChildType;

    private boolean merged;
    private boolean acceptCommaInstr;
    private boolean finalVariable;
    private String currentVarSetting = "";
    private final StringList needInterfaces = new StringList();
    private final StringMap<Integer> availableVariables = new StringMap<Integer>();
    private final StringList variablesNames = new StringList();
    private final StringList variablesNamesToInfer = new StringList();
    private boolean assignedStaticFields;
    private boolean assignedFields;
    private ForLoopPart forLoopPart;
    private AnalyzingEl analysisAss;
    private boolean annotAnalysisField;
    private boolean annotAnalysis;
    private String lookLocalClass = "";
    private boolean okNumOp;
    private final CustList<InaccessibleType> currentBadIndexes = new CustList<InaccessibleType>();
    private final StringList initFields = new StringList();
    private final StringList initFieldsCtors = new StringList();
    private final StringList assignedDeclaredFields = new StringList();
    private final StringList allDeclaredFields = new StringList();

    private final CustList<PartOffset> currentParts = new CustList<PartOffset>();
    private String currentEmptyPartErr = "";
    private final Errors errors = new Errors();
    private MethodHeaders headers = new MethodHeaders();
    private final ReportedMessages messages = new ReportedMessages();

    private StringMap<MappingLocalType> mappingLocal = new StringMap<MappingLocalType>();
    private AbstractProcessKeyWord processKeyWord;
    private AbstractHiddenTypes hiddenTypes;
    private AbstractCurrentConstraints currentConstraints;
    private AbstractAnnotationAnalysis annotationAnalysis;
    private AbstractCurrentGlobalBlock currentGlobalBlock;
    private AbstractLoopDeclaring loopDeclaring;
    private AbstractLocalDeclaring localDeclaring;
    private AbstractBuildingConstraints buildingConstraints;
    private AbstractLocalizer localizer;
    private AbstractTokenValidation tokenValidation;
    private CustList<AnonymousResult> anonymousResults = new CustList<AnonymousResult>();
    private CustList<CustList<AnonymousInstancingOperation>> anonymous = new CustList<CustList<AnonymousInstancingOperation>>();
    private CustList<AnonymousInstancingOperation> anonymousList = new CustList<AnonymousInstancingOperation>();
    private CustList<CustList<AnonymousLambdaOperation>> anonymousLambda = new CustList<CustList<AnonymousLambdaOperation>>();
    private CustList<AnonymousLambdaOperation> allAnonymousLambda = new CustList<AnonymousLambdaOperation>();
    private IdMap<AnonymousInstancingOperation,ExecAnonymousInstancingOperation> mapAnonymous = new IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation>();
    private IdMap<AnonymousLambdaOperation,ExecAnonymousLambdaOperation> mapAnonymousLambda = new IdMap<AnonymousLambdaOperation,ExecAnonymousLambdaOperation>();
    private final StringMap<FileBlock> filesBodies = new StringMap<FileBlock>();
    private int localInType = -1;
    private String refFileName = "";
    private int indexBlock;
    private final StringMap<ToStringMethodHeader> toStringMethods = new StringMap<ToStringMethodHeader>();
    private final CustList<ClassMetaInfo> classMetaInfos = new CustList<ClassMetaInfo>();
    private boolean variableIssue;
    private final StringList toStringOwners = new StringList();
    private CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
    private AnalysisMessages analysisMessages;
    private KeyWords keyWords;
    private boolean gettingErrors;
    private Options options;

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

    public StringMap<StringMap<Struct>> getStaticFields() {
        return getClassesCommon().getStaticFields();
    }

    public StringMap<String> getResources() {
        return getClassesCommon().getResources();
    }

    public StringMap<PolymorphMethod> getToStringMethodsToCallBodies() {
        return getClassesCommon().getToStringMethodsToCallBodies();
    }

    public void addResources(StringMap<String> _resources) {
        getClassesCommon().addResources(_resources);
    }
    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public ClassesCommon getClassesCommon() {
        return classesCommon;
    }

    public void setClassesCommon(ClassesCommon classesCommon) {
        this.classesCommon = classesCommon;
    }

    public LgNames getStandards() {
        return standards;
    }

    public void setStandards(LgNames standards) {
        this.standards = standards;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int tabWidth) {
        this.tabWidth = tabWidth;
    }

    public void setTranslatedOffset(int _translatedOffset) {
        translatedOffset = _translatedOffset;
    }

    public void setGlobalOffset(int _globalOffset) {
        globalOffset = _globalOffset;
    }

    public int getTraceIndex() {
        return globalOffset + getOffset() + translatedOffset;
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Block _currentBlock) {
        currentBlock = _currentBlock;
    }

    public String getGlobalClass() {
        return globalClass;
    }

    public void setGlobalType(String _globalClass) {
        setGlobalClass(_globalClass);
        setGlobalType(getAnaClassBody(StringExpUtil.getIdFromAllTypes(_globalClass)));
    }
    public void setGlobalClass(String _globalClass) {
        globalClass = _globalClass;
    }

    public RootBlock getGlobalType() {
        return globalType;
    }

    public void setGlobalType(RootBlock _globalType) {
        globalType = _globalType;
    }

    public RootBlock getGlobalDirType() {
        return globalDirType;
    }

    public void setGlobalDirType(RootBlock _globalDirType) {
        globalDirType = _globalDirType;
    }

    public void clearAllLocalVars(AssignedVariablesBlock _a) {
        _a.getLocalVars().clear();
        _a.getVariables().clear();
        _a.getFinalVariablesGlobal().getVariables().clear();
        _a.getFinalVariablesGlobal().getVariablesRoot().clear();
        _a.getFinalVariablesGlobal().getVariablesRootBefore().clear();
        _a.getFinalVariablesGlobal().getVariablesBefore().clear();
    }

    public StringMap<Boolean> getDeclaredAssignments() {
        StringMap<Boolean> o_ = new StringMap<Boolean>();
        for (String f: allDeclaredFields) {
            o_.addEntry(f, !StringList.contains(assignedDeclaredFields,f));
        }
        return o_;
    }

    public StringMap<AnaLocalVariable> getInfosVars() {
        return infosVars;
    }

    public StringMap<AnaLoopVariable> getLoopsVars() {
        return loopsVars;
    }

    public AnaCache getCache() {
        return cache;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int _offset) {
        offset = _offset;
    }

    public MethodAccessKind getStaticContext() {
        return staticContext;
    }

    public boolean isStaticContext() {
        return staticContext == MethodAccessKind.STATIC;
    }

    public void setAccessStaticContext(MethodAccessKind _staticContext) {
        staticContext = _staticContext;
    }

    public MemberCallingsBlock getCurrentFct() {
        return currentFct;
    }

    public void setCurrentFct(MemberCallingsBlock _currentFct) {
        currentFct = _currentFct;
    }

    public AccessedBlock getImporting() {
        return importing;
    }

    public void setImporting(AccessedBlock _importing) {
        importing = _importing;
    }

    public ExecAccessingImportingBlock getImportingAcces() {
        return importingAcces;
    }

    public void setImportingAcces(ExecAccessingImportingBlock importingAcces) {
        this.importingAcces = importingAcces;
    }

    public AccessedBlock getImportingTypes() {
        return importingTypes;
    }

    public void setImportingTypes(AccessedBlock importingTypes) {
        this.importingTypes = importingTypes;
    }

    public int getIndexChildType() {
        return indexChildType;
    }

    public void setIndexChildType(int _indexChildType) {
        indexChildType = _indexChildType;
    }

    public boolean isMerged() {
        return merged;
    }

    public void setMerged(boolean _merged) {
        merged = _merged;
    }

    public boolean isAcceptCommaInstr() {
        return acceptCommaInstr;
    }

    public void setAcceptCommaInstr(boolean _acceptCommaInstr) {
        acceptCommaInstr = _acceptCommaInstr;
    }

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public void setFinalVariable(boolean _finalVariable) {
        finalVariable = _finalVariable;
    }

    public String getCurrentVarSetting() {
        return currentVarSetting;
    }

    public void setCurrentVarSetting(String _currentVarSetting) {
        currentVarSetting = _currentVarSetting;
    }

    public StringList getNeedInterfaces() {
        return needInterfaces;
    }

    public AnaGeneType getAnaGeneType(String _type) {
        RootBlock r_ = getAnaClassBody(_type);
        if (r_ != null) {
            return r_;
        }
        return standards.getStandards().getVal(_type);
    }
    public RootBlock getAnaClassBody(String _type) {
        for (RootBlock r: refFoundTypes) {
            if (StringList.quickEq(r.getFullName(),_type)) {
                return r;
            }
        }
        return null;
    }

    public StringMap<Integer> getAvailableVariables() {
        return availableVariables;
    }
    public StringList getVariablesNames() {
        return variablesNames;
    }

    public StringList getVariablesNamesToInfer() {
        return variablesNamesToInfer;
    }

    public boolean isAssignedStaticFields() {
        return assignedStaticFields;
    }

    public void setAssignedStaticFields(boolean _assignedStaticFields) {
        assignedStaticFields = _assignedStaticFields;
    }

    public boolean isAssignedFields() {
        return assignedFields;
    }

    public void setAssignedFields(boolean _assignedFields) {
        assignedFields = _assignedFields;
    }
    public ForLoopPart getForLoopPartState() {
        return forLoopPart;
    }
    public void setForLoopPartState(ForLoopPart _forLoopPart) {
        forLoopPart = _forLoopPart;
    }

    public AnalyzingEl getAnalysisAss() {
        return analysisAss;
    }

    public void setAnalysisAss(AnalyzingEl _analysisAss) {
        analysisAss = _analysisAss;
    }

    public boolean isAnnotAnalysis() {
        return annotAnalysis;
    }

    public void setAnnotAnalysis(boolean _annotAnalysis) {
        annotAnalysis = _annotAnalysis;
    }

    public boolean isAnnotAnalysisField() {
        return annotAnalysisField;
    }

    public void setAnnotAnalysisField(boolean _annotAnalysisField) {
        annotAnalysisField = _annotAnalysisField;
    }

    public String getLookLocalClass() {
        return lookLocalClass;
    }
    public void setLookLocalClass(String _lookLocalClass) {
        lookLocalClass = _lookLocalClass;
    }

    public boolean isOkNumOp() {
        return okNumOp;
    }

    public void setOkNumOp(boolean _okNumOp) {
        okNumOp = _okNumOp;
    }

    public CustList<InaccessibleType> getCurrentBadIndexes() {
        return currentBadIndexes;
    }

    public StringList getInitFields() {
        return initFields;
    }

    public StringList getInitFieldsCtors() {
        return initFieldsCtors;
    }

    public StringList getAssignedDeclaredFields() {
        return assignedDeclaredFields;
    }

    public StringList getAllDeclaredFields() {
        return allDeclaredFields;
    }

    public CustList<RootBlock> getListTypesNames() {
        return listTypesNames;
    }

    public CustList<RootBlock> getFoundTypes() {
        return foundTypes;
    }

    public CustList<RootBlock> getAllFoundTypes() {
        return allFoundTypes;
    }

    public NatStringTreeMap<RootBlock> getSorted() {
        return sorted;
    }

    public CustList<OperatorBlock> getFoundOperators() {
        return foundOperators;
    }

    public CustList<OperatorBlock> getAllOperators() {
        return allOperators;
    }

    public CustList<RootBlock> getPrevFoundTypes() {
        return prevFoundTypes;
    }

    public CustList<RootBlock> getRefFoundTypes() {
        return refFoundTypes;
    }

    public int getCountTypes() {
        return countTypes;
    }

    public void setCountTypes(int countTypes) {
        this.countTypes = countTypes;
    }

    public IdMap<InnerElementBlock, ExecInnerElementBlock> getMapInnerEltTypes() {
        return mapInnerEltTypes;
    }

    public int getCountInnerEltTypes() {
        return countInnerEltTypes;
    }

    public void setCountInnerEltTypes(int countInnerEltTypes) {
        this.countInnerEltTypes = countInnerEltTypes;
    }

    public IdMap<RootBlock, Members> getMapMembers() {
        return mapMembers;
    }

    public IdMap<OperatorBlock, ExecOperatorBlock> getMapOperators() {
        return mapOperators;
    }

    public int getCountOperators() {
        return countOperators;
    }

    public void setCountOperators(int countOperators) {
        this.countOperators = countOperators;
    }

    public AbstractProcessKeyWord getProcessKeyWord() {
        return processKeyWord;
    }

    public void setProcessKeyWord(AbstractProcessKeyWord processKeyWord) {
        this.processKeyWord = processKeyWord;
    }

    public AnalyzedBlock getCurrentAnaBlock() {
        return currentAnaBlock;
    }

    public void setCurrentAnaBlock(AnalyzedBlock currentAnaBlock) {
        this.currentAnaBlock = currentAnaBlock;
    }

    public AbstractHiddenTypes getHiddenTypes() {
        return hiddenTypes;
    }

    public void setHiddenTypes(AbstractHiddenTypes hiddenTypes) {
        this.hiddenTypes = hiddenTypes;
    }

    public AbstractCurrentConstraints getCurrentConstraints() {
        return currentConstraints;
    }

    public void setCurrentConstraints(AbstractCurrentConstraints currentConstraints) {
        this.currentConstraints = currentConstraints;
    }

    public AbstractAnnotationAnalysis getAnnotationAnalysis() {
        return annotationAnalysis;
    }

    public void setAnnotationAnalysis(AbstractAnnotationAnalysis annotationAnalysis) {
        this.annotationAnalysis = annotationAnalysis;
    }

    public AbstractCurrentGlobalBlock getCurrentGlobalBlock() {
        return currentGlobalBlock;
    }

    public void setCurrentGlobalBlock(AbstractCurrentGlobalBlock currentGlobalBlock) {
        this.currentGlobalBlock = currentGlobalBlock;
    }

    public AbstractLoopDeclaring getLoopDeclaring() {
        return loopDeclaring;
    }

    public void setLoopDeclaring(AbstractLoopDeclaring loopDeclaring) {
        this.loopDeclaring = loopDeclaring;
    }

    public AbstractLocalDeclaring getLocalDeclaring() {
        return localDeclaring;
    }

    public void setLocalDeclaring(AbstractLocalDeclaring localDeclaring) {
        this.localDeclaring = localDeclaring;
    }

    public AbstractBuildingConstraints getBuildingConstraints() {
        return buildingConstraints;
    }

    public void setBuildingConstraints(AbstractBuildingConstraints buildingConstraints) {
        this.buildingConstraints = buildingConstraints;
    }

    public AbstractLocalizer getLocalizer() {
        return localizer;
    }

    public void setLocalizer(AbstractLocalizer localizer) {
        this.localizer = localizer;
    }

    public AbstractTokenValidation getTokenValidation() {
        return tokenValidation;
    }

    public void setTokenValidation(AbstractTokenValidation tokenValidation) {
        this.tokenValidation = tokenValidation;
    }

    public CustList<PartOffset> getCurrentParts() {
        return currentParts;
    }

    public String getCurrentEmptyPartErr() {
        return currentEmptyPartErr;
    }

    public void setCurrentEmptyPartErr(String currentEmptyPartErr) {
        this.currentEmptyPartErr = currentEmptyPartErr;
    }

    public Errors getErrors() {
        return errors;
    }

    public StringMap<MappingLocalType> getMappingLocal() {
        return mappingLocal;
    }

    public MethodHeaders getHeaders() {
        return headers;
    }

    public ReportedMessages getMessages() {
        return messages;
    }

    public StringMap<CustList<MethodHeaderInfo>> getUnary() {
        return headers.getUnary();
    }

    public StringMap<CustList<MethodHeaderInfo>> getBinaryAll() {
        return headers.getBinaryAll();
    }

    public StringMap<CustList<MethodHeaderInfo>> getBinaryFirst() {
        return headers.getBinaryFirst();
    }

    public StringMap<CustList<MethodHeaderInfo>> getBinarySecond() {
        return headers.getBinarySecond();
    }

    public StringMap<CustList<MethodHeaderInfo>> getExplicitCastMethods() {
        return headers.getExplicitCastMethods();
    }

    public StringMap<CustList<MethodHeaderInfo>> getExplicitIdCastMethods() {
        return headers.getExplicitIdCastMethods();
    }

    public StringMap<CustList<MethodHeaderInfo>> getExplicitFromCastMethods() {
        return headers.getExplicitFromCastMethods();
    }

    public StringMap<CustList<MethodHeaderInfo>> getImplicitCastMethods() {
        return headers.getImplicitCastMethods();
    }

    public StringMap<CustList<MethodHeaderInfo>> getImplicitIdCastMethods() {
        return headers.getImplicitIdCastMethods();
    }

    public StringMap<CustList<MethodHeaderInfo>> getImplicitFromCastMethods() {
        return headers.getImplicitFromCastMethods();
    }

    public StringMap<CustList<MethodHeaderInfo>> getTrues() {
        return headers.getTrues();
    }

    public StringMap<CustList<MethodHeaderInfo>> getFalses() {
        return headers.getFalses();
    }

    public StringList getTypesWithInnerOperators() {
        return headers.getTypesWithInnerOperators();
    }

    public StringList getPackagesFound() {
        return headers.getPackagesFound();
    }


    public StringList getBasePackagesFound() {
        return headers.getBasePackagesFound();
    }

    public boolean isEmptyErrors() {
        return messages.isAllEmptyErrors();
    }


    public void addLocWarning(FoundWarningInterpret _warning) {
        _warning.setLocationFile(getLocationFile(_warning.getFileName(),_warning.getIndexFile(), this));
        addWarning(_warning);
    }

    public void addLocError(FoundErrorInterpret _error) {
        _error.setLocationFile(getLocationFile(_error.getFileName(),_error.getIndexFile(), this));
        addError(_error);
    }

    private static String getLocationFile(String _fileName, int _sum, AnalyzedPageEl analyzing) {
        FileBlock file_ = analyzing.getFileBody(_fileName);
        FileMetrics metrics_ = file_.getMetrics(analyzing.getTabWidth());
        int r_ = metrics_.getRowFile(_sum);
        int c_ = metrics_.getColFile(_sum,r_);
        return StringList.concat( Integer.toString(r_),",",Integer.toString(c_),",",Integer.toString(_sum));
    }

    public void addError(FoundErrorInterpret _error) {
        messages.addError(_error);
    }

    public boolean isEmptyMessageError() {
        return messages.isEmptyMessageError();
    }
    public void addMessageError(String _std) {
        messages.addMessageError(_std);
    }

    public boolean isEmptyStdError() {
        return messages.isEmptyStdError();
    }
    public void addStdError(StdWordError _std) {
        messages.addStdError(_std);
    }

    public void addWarning(FoundWarningInterpret _warning) {
        messages.addWarning(_warning);
    }

    public void putFileBlock(String _fileName, FileBlock _fileBlock) {
        filesBodies.put(_fileName, _fileBlock);
    }
    public FileBlock getFileBody(String _string) {
        return filesBodies.getVal(_string);
    }
    public StringMap<FileBlock> getFilesBodies() {
        return filesBodies;
    }

    public int getLocalInType() {
        return localInType;
    }

    public void setLocalInType(int _localInType) {
        localInType = _localInType;
    }

    public String getRefFileName() {
        return refFileName;
    }

    public void setRefFileName(String _refFileName) {
        refFileName = _refFileName;
    }

    public int getIndexBlock() {
        return indexBlock;
    }

    public void setIndexBlock(int _indexBlock) {
        indexBlock = _indexBlock;
    }

    public StringMap<ToStringMethodHeader> getToStringMethods() {
        return toStringMethods;
    }

    public CustList<AnonymousResult> getAnonymousResults() {
        return anonymousResults;
    }

    public IdMap<AnonymousInstancingOperation, ExecAnonymousInstancingOperation> getMapAnonymous() {
        return mapAnonymous;
    }

    public IdMap<AnonymousLambdaOperation, ExecAnonymousLambdaOperation> getMapAnonymousLambda() {
        return mapAnonymousLambda;
    }

    public CustList<CustList<AnonymousInstancingOperation>> getAnonymous() {
        return anonymous;
    }

    public CustList<AnonymousInstancingOperation> getAnonymousList() {
        return anonymousList;
    }

    public CustList<CustList<AnonymousLambdaOperation>> getAnonymousLambda() {
        return anonymousLambda;
    }
    public CustList<AnonymousLambdaOperation> getAllAnonymousLambda() {
        return allAnonymousLambda;
    }

    public IdMap<AnonymousFunctionBlock, ExecAnonymousFunctionBlock> getMapAnonLambda() {
        return mapAnonLambda;
    }

    public IdMap<AnonymousTypeBlock, ExecAnonymousTypeBlock> getMapAnonTypes() {
        return mapAnonTypes;
    }

    public int getCountAnonTypes() {
        return countAnonTypes;
    }

    public void setCountAnonTypes(int countAnonTypes) {
        this.countAnonTypes = countAnonTypes;
    }

    public IdMap<Block, AssBlock> getFieldsAssSt() {
        return fieldsAssSt;
    }

    public IdMap<Block, AssBlock> getFieldsAss() {
        return fieldsAss;
    }

    public IdMap<MemberCallingsBlock, AnalyzingEl> getResultsAna() {
        return resultsAna;
    }

    public IdMap<MemberCallingsBlock, AnalyzingEl> getResultsAnaInst() {
        return resultsAnaInst;
    }

    public IdMap<MemberCallingsBlock, AnalyzingEl> getResultsAnaNamed() {
        return resultsAnaNamed;
    }

    public IdMap<MemberCallingsBlock, AnalyzingEl> getResultsAnaMethod() {
        return resultsAnaMethod;
    }

    public IdMap<AnonymousFunctionBlock, AnalyzingEl> getResultsMethod() {
        return resultsMethod;
    }

    public IdMap<OperatorBlock, AnalyzingEl> getResultsAnaOperator() {
        return resultsAnaOperator;
    }

    public CustList<ClassMetaInfo> getClassMetaInfos() {
        return classMetaInfos;
    }

    public boolean isVariableIssue() {
        return variableIssue;
    }

    public void setVariableIssue(boolean variableIssue) {
        this.variableIssue = variableIssue;
    }

    public StringList getToStringOwners() {
        return toStringOwners;
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public void setComments(CustList<CommentDelimiters> comments) {
        this.comments = comments;
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

    public void setKeyWords(KeyWords keyWords) {
        this.keyWords = keyWords;
    }

    public boolean isGettingParts() {
        return isCovering() || isGettingErrors();
    }

    public boolean isCovering() {
        return getOptions().isCovering();
    }

    public boolean isGettingErrors() {
        return gettingErrors;
    }

    public void setGettingErrors(boolean gettingErrors) {
        this.gettingErrors = gettingErrors;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public IdMap<MemberCallingsBlock, ExecMemberCallingsBlock> getAllFct() {
        return allFct;
    }

    public IdMap<RootBlock, ClassMethodIdReturn> getToStr() {
        return toStr;
    }
}
