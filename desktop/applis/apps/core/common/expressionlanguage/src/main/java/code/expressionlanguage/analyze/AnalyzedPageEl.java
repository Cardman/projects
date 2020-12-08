package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.DefaultAccess;
import code.expressionlanguage.analyze.opers.AnonymousInstancingOperation;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
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
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.instr.AbstractProcessKeyWord;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.DefaultProcessKeyWord;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.AliasCharSequence;
import code.expressionlanguage.stds.AliasCore;
import code.expressionlanguage.stds.AliasMath;
import code.expressionlanguage.stds.AliasNumber;
import code.expressionlanguage.stds.DisplayedStrings;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.*;
import code.util.core.StringUtil;

public final class AnalyzedPageEl {

    /**Only used while throwing exception*/

    private static final int DEFAULT_TAB_WIDTH = 4;

    private int tabWidth = DEFAULT_TAB_WIDTH;
    private final StringList predefinedClasses = new StringList();

    private final StringList predefinedInterfacesInitOrder = new StringList();
    private AbstractConstantsCalculator calculator;
    private AbstractFieldFilter fieldFilter;
    private AbstractFileBuilder fileBuilder;
    private StringMap<String> resources;
    private StringMap<StringMap<Struct>> staticFields;

    private Block currentBlock;
    private ImportForEachLoop currentAnaBlockForEachLoop;
    private ImportForEachTable currentAnaBlockForEachTable;

    private String globalClass = "";
    private RootBlock globalType;
    private RootBlock globalDirType;

    private final StringMap<AnaLocalVariable> infosVars = new StringMap<AnaLocalVariable>();
    private final StringMap<AnaLoopVariable> loopsVars = new StringMap<AnaLoopVariable>();
    private final AnaCache cache = new AnaCache();

    private MemberCallingsBlock currentFct;
    private AccessedBlock importing;
    private AccessingImportingBlock importingAcces;
    private CustList<StringList> importingTypes = new CustList<StringList>();
    private final CustList<RootBlock> listTypesNames = new CustList<RootBlock>();
    private int countTypes;
    private int countInnerEltTypes;
    private int countOperators;
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
    private final IdMap<RootBlock,ClassMethodIdReturn> toStr = new IdMap<RootBlock, ClassMethodIdReturn>();

    private int offset;

    private MethodAccessKind staticContext;

    private int globalOffset;

    private int translatedOffset;
    private int indexChildType;

    private boolean merged;
    private boolean acceptCommaInstr;
    private boolean finalVariable;
    private boolean refVariable;
    private String currentVarSetting = "";
    private final StringList needInterfaces = new StringList();
    private final StringMap<Integer> availableVariables = new StringMap<Integer>();
    private final StringList variablesNames = new StringList();
    private final StringList variablesNamesToInfer = new StringList();
    private boolean assignedStaticFields;
    private boolean assignedFields;
    private ForLoopPart forLoopPart;
    private AnalyzingEl analysisAss;
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
    private AbstractForEachFetch forEachFetch;
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
    private final StringMap<FileBlock> filesBodies = new StringMap<FileBlock>();
    private int localInType = -1;
    private String refFileName = "";
    private int indexBlock;
    private final StringMap<ToStringMethodHeader> toStringMethods = new StringMap<ToStringMethodHeader>();
    private final CustList<ClassMetaInfo> classMetaInfos = new CustList<ClassMetaInfo>();
    private boolean variableIssue;
    private final StringList toStringOwners = new StringList();
    private CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
    private DefaultAccess defaultAccess;
    private AnalysisMessages analysisMessages;
    private KeyWords keyWords;
    private boolean gettingErrors;
    private Options options;
    private LgNamesContent content;

    public static AnalyzedPageEl setInnerAnalyzing() {
        AnalyzedPageEl page_ = new AnalyzedPageEl();
        page_.setProcessKeyWord(new DefaultProcessKeyWord());
        page_.setHiddenTypes(new DefaultHiddenTypes(page_));
        page_.setForEachFetch(new DefaultForEachFetch(page_));
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
        return staticFields;
    }

    public StringMap<String> getResources() {
        return resources;
    }

    public void addResources(StringMap<String> _resources) {
        for (EntryCust<String, String> e: _resources.entryList()) {
            resources.addEntry(e.getKey(), e.getValue());
        }
    }

    public void setStaticFields(StringMap<StringMap<Struct>> _staticFields) {
        this.staticFields = _staticFields;
    }

    public void setResources(StringMap<String> _resources) {
        this.resources = _resources;
    }

    public String getDefaultPkg() {
        return content.getDefaultPkg();
    }

    public AliasMath getMathRef() {
        return content.getMathRef();
    }
    public AliasCharSequence getCharSeq() {
        return content.getCharSeq();
    }
    public AliasNumber getNbAlias() {
        return content.getNbAlias();
    }

    public AliasCore getCoreNames() {
        return content.getCoreNames();
    }

    public String getAliasEnumPredValueOf() {
        return content.getPredefTypes().getAliasEnumPredValueOf();
    }
    public String getAliasMetaInfo() {
        return content.getReflect().getAliasMetaInfo();
    }
    public String getAliasAnnotated() {
        return content.getReflect().getAliasAnnotated();
    }
    public String getAliasInstance() {
        return content.getReflect().getAliasInstance();
    }
    public String getAliasCall() {
        return content.getReflect().getAliasCall();
    }
    public String getAliasEnumValues() {
        return content.getPredefTypes().getAliasEnumValues();
    }
    public String getAliasLength() {
        return content.getCoreNames().getAliasArrayLength();
    }

    public String getAliasAnnotationType() {
        return content.getReflect().getAliasAnnotationType();
    }
    public String getAliasEnumParam() {
        return content.getPredefTypes().getAliasEnumParam();
    }
    public String getAliasEnumType() {
        return content.getPredefTypes().getAliasEnumType();
    }

    public StringMap<String> buildFiles(){
        AbstractFileBuilder fileBuilder_ = getFileBuilder();
        StringMap<String> m_ = fileBuilder_.buildFiles(keyWords);
        predefinedClasses.addAllElts(fileBuilder_.getPredefinedClasses());
        predefinedInterfacesInitOrder.addAllElts(fileBuilder_.getPredefinedInterfacesInitOrder());
        return m_;
    }
    public StringMap<StandardType> getStandardsTypes() {
        return content.getStandards();
    }

    public String getAliasIterable() {
        return content.getPredefTypes().getAliasIterable();
    }

    public String getAliasIterableTable() {
        return content.getPredefTypes().getAliasIterableTable();
    }

    public String getAliasNullPe() {
        return content.getCoreNames().getAliasNullPe();
    }

    public String getAliasClassType() {
        return content.getReflect().getAliasClassType();
    }

    public String getAliasClone() {
        return content.getCoreNames().getAliasClone();
    }

    public String getAliasFct() {
        return content.getReflect().getAliasFct();
    }
    public String getAliasVoid() {
        return content.getCoreNames().getAliasVoid();
    }

    public String getAliasBoolean() {
        return content.getNbAlias().getAliasBoolean();
    }

    public String getAliasPrimBoolean() {
        return content.getPrimTypes().getAliasPrimBoolean();
    }

    public String getAliasObject() {
        return content.getCoreNames().getAliasObject();
    }

    public String getAliasNumber() {
        return content.getNbAlias().getAliasNumber();
    }

    public String getAliasStringBuilder() {
        return content.getCharSeq().getAliasStringBuilder();
    }

    public String getAliasReplacement() {
        return content.getCharSeq().getAliasReplacement();
    }

    public String getAliasString() {
        return content.getCharSeq().getAliasString();
    }

    public String getAliasByte() {
        return content.getNbAlias().getAliasByte();
    }

    public String getAliasPrimByte() {
        return content.getPrimTypes().getAliasPrimByte();
    }

    public String getAliasShort() {
        return content.getNbAlias().getAliasShort();
    }

    public String getAliasPrimShort() {
        return content.getPrimTypes().getAliasPrimShort();
    }

    public String getAliasCharacter() {
        return content.getNbAlias().getAliasCharacter();
    }

    public String getAliasPrimChar() {
        return content.getPrimTypes().getAliasPrimChar();
    }

    public String getAliasInteger() {
        return content.getNbAlias().getAliasInteger();
    }

    public String getAliasPrimInteger() {
        return content.getPrimTypes().getAliasPrimInteger();
    }

    public String getAliasLong() {
        return content.getNbAlias().getAliasLong();
    }

    public String getAliasPrimLong() {
        return content.getPrimTypes().getAliasPrimLong();
    }

    public String getAliasFloat() {
        return content.getNbAlias().getAliasFloat();
    }

    public String getAliasPrimFloat() {
        return content.getPrimTypes().getAliasPrimFloat();
    }

    public String getAliasDouble() {
        return content.getNbAlias().getAliasDouble();
    }

    public String getAliasPrimDouble() {
        return content.getPrimTypes().getAliasPrimDouble();
    }

    public StringMap<PrimitiveType> getPrimitiveTypes() {
        return getPrimTypes().getPrimitiveTypes();
    }

    public PrimitiveTypes getPrimTypes() {
        return content.getPrimTypes();
    }

    public DisplayedStrings getDisplayedStrings() {
        return content.getDisplayedStrings();
    }

    public void setStandards(LgNamesContent _content) {
        content = _content;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        this.tabWidth = _tabWidth;
    }

    public void setTranslatedOffset(int _translatedOffset) {
        translatedOffset = _translatedOffset;
    }

    public int getGlobalOffset() {
        return globalOffset;
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
        if (_currentBlock instanceof ImportForEachTable) {
            setCurrentAnaBlockForEachTable((ImportForEachTable) _currentBlock);
        } else {
            setCurrentAnaBlockForEachTable(null);
        }
        if (_currentBlock instanceof ImportForEachLoop) {
            setCurrentAnaBlockForEachLoop((ImportForEachLoop) _currentBlock);
        } else {
            setCurrentAnaBlockForEachLoop(null);
        }
    }

    public String getGlobalClass() {
        return globalClass;
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
            o_.addEntry(f, !StringUtil.contains(assignedDeclaredFields,f));
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

    public AccessingImportingBlock getImportingAcces() {
        return importingAcces;
    }

    public void setImportingAcces(AccessingImportingBlock _importingAcces) {
        this.importingAcces = _importingAcces;
    }

    public CustList<StringList> getImportingTypes() {
        return importingTypes;
    }

    public void setImportingTypes(AccessedBlock _importingTypes) {
        importingTypes.clear();
        if (_importingTypes instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _importingTypes;
            importingTypes.add(r_.getImports());
            for (RootBlock r: r_.getAllParentTypes()) {
                importingTypes.add(r.getImports());
            }
            importingTypes.add(r_.getFileImports());
        }
        if (_importingTypes instanceof OperatorBlock) {
            importingTypes.add(_importingTypes.getImports());
            importingTypes.add(_importingTypes.getFileImports());
        }
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

    public boolean isRefVariable() {
        return refVariable;
    }

    public void setRefVariable(boolean _refVariable) {
        refVariable = _refVariable;
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

    public boolean matchPrimWrap(StringList _first, StringList _second) {
        String w_ = getWrap(_first);
        if (w_.isEmpty()) {
            return false;
        }
        return StringUtil.equalsSet(new StringList(w_),_second);
    }
    public StringList getTernary(StringList _list) {
        String w_ = getWrap(_list);
        if (w_.isEmpty()) {
            return _list;
        }
        return new StringList(w_);
    }

    private String getWrap(StringList _list) {
        AnaClassArgumentMatching first_ = new AnaClassArgumentMatching(_list);
        String uniq_ = first_.getSingleNameOrEmpty();
        String w_ = "";
        for (EntryCust<String, PrimitiveType> e: content.getPrimTypes().getPrimitiveTypes().entryList()) {
            if (StringUtil.quickEq(e.getKey(), uniq_)) {
                w_ = e.getValue().getWrapper();
                break;
            }
        }
        return w_;
    }

    public StringList getAllGenericSuperTypesWrapper(String _prim, int _d) {
        StringList list_ = new StringList();
        for (EntryCust<String, PrimitiveType> e: content.getPrimTypes().getPrimitiveTypes().entryList()) {
            if (StringUtil.quickEq(e.getKey(), _prim)) {
                for (EntryCust<String,StandardType> f: content.getStandards().entryList()) {
                    String wrapper_ = e.getValue().getWrapper();
                    if (StringUtil.quickEq(wrapper_,f.getKey())) {
                        list_.add(StringExpUtil.getPrettyArrayType(wrapper_,_d));
                        for (String t: f.getValue().getAllGenericSuperTypes()) {
                            list_.add(StringExpUtil.getPrettyArrayType(t,_d));
                        }
                    }
                }
            }
        }
        return list_;
    }
    public AnaGeneType getAnaGeneType(String _type) {
        RootBlock r_ = getAnaClassBody(_type);
        if (r_ != null) {
            return r_;
        }
        return content.getStandards().getVal(_type);
    }
    public RootBlock getAnaClassBody(String _type) {
        for (RootBlock r: refFoundTypes) {
            if (StringUtil.quickEq(r.getFullName(),_type)) {
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

    public void setCountTypes(int _countTypes) {
        this.countTypes = _countTypes;
    }

    public int getCountInnerEltTypes() {
        return countInnerEltTypes;
    }

    public void setCountInnerEltTypes(int _countInnerEltTypes) {
        this.countInnerEltTypes = _countInnerEltTypes;
    }

    public int getCountOperators() {
        return countOperators;
    }

    public void setCountOperators(int _countOperators) {
        this.countOperators = _countOperators;
    }

    public AbstractProcessKeyWord getProcessKeyWord() {
        return processKeyWord;
    }

    public void setProcessKeyWord(AbstractProcessKeyWord _processKeyWord) {
        this.processKeyWord = _processKeyWord;
    }

    public ImportForEachLoop getCurrentAnaBlockForEachLoop() {
        return currentAnaBlockForEachLoop;
    }

    public void setCurrentAnaBlockForEachLoop(ImportForEachLoop _currentAnaBlockForEachLoop) {
        this.currentAnaBlockForEachLoop = _currentAnaBlockForEachLoop;
    }

    public ImportForEachTable getCurrentAnaBlockForEachTable() {
        return currentAnaBlockForEachTable;
    }

    public void setCurrentAnaBlockForEachTable(ImportForEachTable _currentAnaBlockForEachTable) {
        this.currentAnaBlockForEachTable = _currentAnaBlockForEachTable;
    }

    public AbstractHiddenTypes getHiddenTypes() {
        return hiddenTypes;
    }

    public void setHiddenTypes(AbstractHiddenTypes _hiddenTypes) {
        this.hiddenTypes = _hiddenTypes;
    }

    public AbstractForEachFetch getForEachFetch() {
        return forEachFetch;
    }

    public void setForEachFetch(AbstractForEachFetch _forEachFetch) {
        this.forEachFetch = _forEachFetch;
    }

    public AbstractCurrentConstraints getCurrentConstraints() {
        return currentConstraints;
    }

    public void setCurrentConstraints(AbstractCurrentConstraints _currentConstraints) {
        this.currentConstraints = _currentConstraints;
    }

    public AbstractAnnotationAnalysis getAnnotationAnalysis() {
        return annotationAnalysis;
    }

    public void setAnnotationAnalysis(AbstractAnnotationAnalysis _annotationAnalysis) {
        this.annotationAnalysis = _annotationAnalysis;
    }

    public AbstractCurrentGlobalBlock getCurrentGlobalBlock() {
        return currentGlobalBlock;
    }

    public void setCurrentGlobalBlock(AbstractCurrentGlobalBlock _currentGlobalBlock) {
        this.currentGlobalBlock = _currentGlobalBlock;
    }

    public AbstractLoopDeclaring getLoopDeclaring() {
        return loopDeclaring;
    }

    public void setLoopDeclaring(AbstractLoopDeclaring _loopDeclaring) {
        this.loopDeclaring = _loopDeclaring;
    }

    public AbstractLocalDeclaring getLocalDeclaring() {
        return localDeclaring;
    }

    public void setLocalDeclaring(AbstractLocalDeclaring _localDeclaring) {
        this.localDeclaring = _localDeclaring;
    }

    public AbstractBuildingConstraints getBuildingConstraints() {
        return buildingConstraints;
    }

    public void setBuildingConstraints(AbstractBuildingConstraints _buildingConstraints) {
        this.buildingConstraints = _buildingConstraints;
    }

    public AbstractLocalizer getLocalizer() {
        return localizer;
    }

    public void setLocalizer(AbstractLocalizer _localizer) {
        this.localizer = _localizer;
    }

    public AbstractTokenValidation getTokenValidation() {
        return tokenValidation;
    }

    public void setTokenValidation(AbstractTokenValidation _tokenValidation) {
        this.tokenValidation = _tokenValidation;
    }

    public CustList<PartOffset> getCurrentParts() {
        return currentParts;
    }

    public String getCurrentEmptyPartErr() {
        return currentEmptyPartErr;
    }

    public void setCurrentEmptyPartErr(String _currentEmptyPartErr) {
        this.currentEmptyPartErr = _currentEmptyPartErr;
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

    private static String getLocationFile(String _fileName, int _sum, AnalyzedPageEl _analyzing) {
        FileBlock file_ = _analyzing.getFileBody(_fileName);
        FileMetrics metrics_ = file_.getMetrics(_analyzing.getTabWidth());
        int r_ = metrics_.getRowFile(_sum);
        int c_ = metrics_.getColFile(_sum,r_);
        return StringUtil.concat( Long.toString(r_),",",Long.toString(c_),",",Long.toString(_sum));
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

    public int getCountAnonTypes() {
        return countAnonTypes;
    }

    public void setCountAnonTypes(int _countAnonTypes) {
        this.countAnonTypes = _countAnonTypes;
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

    public void setVariableIssue(boolean _variableIssue) {
        this.variableIssue = _variableIssue;
    }

    public StringList getToStringOwners() {
        return toStringOwners;
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public void setComments(CustList<CommentDelimiters> _comments) {
        this.comments = _comments;
    }

    public DefaultAccess getDefaultAccess() {
        return defaultAccess;
    }

    public void setDefaultAccess(DefaultAccess _defaultAccess) {
        this.defaultAccess = _defaultAccess;
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
        this.keyWords = _keyWords;
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

    public void setGettingErrors(boolean _gettingErrors) {
        this.gettingErrors = _gettingErrors;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options _options) {
        this.options = _options;
    }

    public IdMap<RootBlock, ClassMethodIdReturn> getToStr() {
        return toStr;
    }

    public StringList getPredefinedClasses() {
        return predefinedClasses;
    }

    public StringList getPredefinedInterfacesInitOrder() {
        return predefinedInterfacesInitOrder;
    }

    public AbstractConstantsCalculator getCalculator() {
        return calculator;
    }

    public void setCalculator(AbstractConstantsCalculator _calculator) {
        this.calculator = _calculator;
    }

    public AbstractFieldFilter getFieldFilter() {
        return fieldFilter;
    }

    public void setFieldFilter(AbstractFieldFilter _fieldFilter) {
        this.fieldFilter = _fieldFilter;
    }

    public AbstractFileBuilder getFileBuilder() {
        return fileBuilder;
    }

    public void setFileBuilder(AbstractFileBuilder _fileBuilder) {
        this.fileBuilder = _fileBuilder;
    }
}
