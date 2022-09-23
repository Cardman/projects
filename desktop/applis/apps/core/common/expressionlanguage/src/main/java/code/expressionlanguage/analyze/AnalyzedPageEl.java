package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.accessing.OperatorAccessor;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.analyze.errors.stds.StdWordError;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.DefaultAccess;
import code.expressionlanguage.analyze.files.SegmentStringPart;
import code.expressionlanguage.analyze.instr.NumberInfosOutput;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.opers.SwitchOperation;
import code.expressionlanguage.analyze.syntax.IntermediaryResults;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.InaccessibleType;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.WarningShow;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class AnalyzedPageEl {

    /**Only used while throwing exception*/

    private static final int DEFAULT_TAB_WIDTH = 4;

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private AbstractConstantsCalculator calculator;
    private AbstractFileBuilder fileBuilder;
    private StringMap<String> resources;
    private StringMap<StringMap<Struct>> staticFields;

    private AbsBk currentBlock;
    private ImportForEachLoop currentAnaBlockForEachLoop;
    private ImportForEachTable currentAnaBlockForEachTable;

    private AnaFormattedRootBlock globalType = AnaFormattedRootBlock.defValue();
    private String currentPkg = "";
    private FileBlock currentFile;

    private final StringMap<AnaLocalVariable> infosVars = new StringMap<AnaLocalVariable>();
    private final StringMap<AnaLoopVariable> loopsVars = new StringMap<AnaLoopVariable>();
    private final AnaCache cache = new AnaCache();

    private MemberCallingsBlock currentFct;
    private AccessedFct accessedFct;
    private WithContext currentCtx;
    private AccessedBlock importing;
    private AccessingImportingBlock importingAcces;
    private final CustList<StringList> importingTypes = new CustList<StringList>();
    private final CustList<RootBlock> listTypesNames = new CustList<RootBlock>();
    private int countTypes;
    private int countInnerEltTypes;
    private int countOperators;
    private int countAnonTypes;
    private final IdMap<AbsBk,AssBlock> fieldsAssSt = new IdMap<AbsBk,AssBlock>();
    private final IdMap<AbsBk,AssBlock> fieldsAss = new IdMap<AbsBk,AssBlock>();
    private final IdMap<MemberCallingsBlock,AnalyzingEl> resultsAna = new IdMap<MemberCallingsBlock,AnalyzingEl>();
    private final IdMap<MemberCallingsBlock,AnalyzingEl> resultsAnaInst = new IdMap<MemberCallingsBlock,AnalyzingEl>();
    private final IdMap<MemberCallingsBlock,AnalyzingEl> resultsAnaNamed = new IdMap<MemberCallingsBlock,AnalyzingEl>();
    private final IdMap<MemberCallingsBlock,AnalyzingEl> resultsAnaMethod = new IdMap<MemberCallingsBlock,AnalyzingEl>();
    private final IdMap<NamedCalledFunctionBlock,AnalyzingEl> resultsMethod = new IdMap<NamedCalledFunctionBlock,AnalyzingEl>();
    private final IdMap<SwitchMethodBlock,AnalyzingEl> resultsSwMethod = new IdMap<SwitchMethodBlock,AnalyzingEl>();
    private final IdMap<OperatorBlock,AnalyzingEl> resultsAnaOperator = new IdMap<OperatorBlock,AnalyzingEl>();
    private final CustList<RootBlock> outerTypes = new CustList<RootBlock>();
    private final CustList<RootBlock> foundTypes = new CustList<RootBlock>();
    private CustList<IntermediaryResults> nextResults = new CustList<IntermediaryResults>();
    private final NatStringTreeMap<RootBlock> sorted = new NatStringTreeMap<RootBlock>();
    private final CustList<RootBlock> allFoundTypes = new CustList<RootBlock>();
    private final CustList<OperatorBlock> allOperators = new CustList<OperatorBlock>();
    private final CustList<OperatorBlock> sortedOperators = new CustList<OperatorBlock>();
    private final CustList<RootBlock> prevFoundTypes = new CustList<RootBlock>();
    private final CustList<RootBlock> refFoundTypes = new CustList<RootBlock>();
    private final IdMap<RootBlock,ClassMethodIdReturn> toStr = new IdMap<RootBlock, ClassMethodIdReturn>();
    private final IdMap<RootBlock,ClassMethodIdReturn> randCodes = new IdMap<RootBlock, ClassMethodIdReturn>();

    private int offset;

    private MethodAccessKind staticContext;

    private int sumOffset;

    private boolean acceptCommaInstr;
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
    private boolean okNumOp;
    private final CustList<InaccessibleType> currentBadIndexes = new CustList<InaccessibleType>();
    private final StringList initFields = new StringList();
    private final StringList initFieldsCtors = new StringList();
    private final StringList assignedDeclaredFields = new StringList();
    private final StringList allDeclaredFields = new StringList();
    private String currentEmptyPartErr = "";
    private final Errors errors = new Errors();
    private final MethodHeaders headers = new MethodHeaders();
    private final ReportedMessages messages = new ReportedMessages();
    private final StringMap<Integer> counts = new StringMap<Integer>();
    private final StringMap<Integer> countsAnon = new StringMap<Integer>();

    private final StringMap<MappingLocalType> mappingLocal = new StringMap<MappingLocalType>();
    private DefaultCurrentConstraints currentConstraints;
    private DefaultBuildingConstraints buildingConstraints;
    private DefaultLocalizer localizer;
    private CustList<NumberInfosOutput> currentNumbers = new CustList<NumberInfosOutput>();
    private CustList<AnonymousResult> currentAnonymousResults = new CustList<AnonymousResult>();
    private CustList<SegmentStringPart> currentParts = new CustList<SegmentStringPart>();
    private final CustList<AnonymousLambdaOperation> allAnonymousLambda = new CustList<AnonymousLambdaOperation>();
    private final CustList<SwitchOperation> allSwitchMethods = new CustList<SwitchOperation>();
    private final StringMap<FileBlock> filesBodies = new StringMap<FileBlock>();
    private FileBlock refFileName;
    private final StringMap<ToStringMethodHeader> toStringMethods = new StringMap<ToStringMethodHeader>();
    private final StringMap<ToStringMethodHeader> randCodeMethods = new StringMap<ToStringMethodHeader>();
    private final CustList<ClassMetaInfo> classMetaInfos = new CustList<ClassMetaInfo>();
    private boolean variableIssue;
    private final StringList toStringOwners = new StringList();
    private final StringList randCodeOwners = new StringList();
    private CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
    private DefaultAccess defaultAccess;
    private AnalysisMessages analysisMessages;
    private KeyWords keyWords;
    private boolean gettingErrors;
    private Options options;
    private LgNamesContent content;
    private BuildableLgNames logErr;
    private AbsLineDeclarator lineDeclarator;
    private final CustList<OperatorBlock> sortedNbOperators = new CustList<OperatorBlock>();
    private boolean sortNbOperators;
    private Ints currentAnnotDelNew = new Ints();
    private Ints currentAnnotDelSwitch = new Ints();

    public static AnalyzedPageEl setInnerAnalyzing() {
        AnalyzedPageEl page_ = new AnalyzedPageEl();
        page_.setCurrentConstraints(new DefaultCurrentConstraints(page_));
        page_.setBuildingConstraints(new DefaultBuildingConstraints(page_));
        page_.setLocalizer(new DefaultLocalizer(page_));
        return page_;
    }

    public static CustList<RootBlock> customTypes(CustList<RootBlock> _foundTypes) {
        CustList<RootBlock> types_ = new CustList<RootBlock>();
        for (RootBlock r: _foundTypes) {
            if (!r.getFile().isPredefined()) {
                types_.add(r);
            }
        }
        return types_;
    }

    public static CustList<FileBlock> customFiles(CustList<FileBlock> _files) {
        CustList<FileBlock> files_ = new CustList<FileBlock>();
        for (FileBlock r: _files) {
            if (!r.isPredefined()) {
                files_.add(r);
            }
        }
        return files_;
    }

    public AbstractTypePairHash getChecker() {
        return options.getChecker();
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

    public AliasCharSequenceType getCharSeq() {
        return content.getCharSeq();
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
        return fileBuilder_.buildFiles(keyWords);
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

    public String getAliasRange() {
        return content.getCoreNames().getAliasRange();
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

    public void setLogErr(BuildableLgNames _logErr) {
        logErr = _logErr;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        this.tabWidth = _tabWidth;
    }

    public int getSumOffset() {
        return sumOffset;
    }

    public void setSumOffset(int _sum) {
        this.sumOffset = _sum;
    }

    public static String getFileName(FileBlock _file) {
        if (_file == null) {
            return "";
        }
        return _file.getFileName();
    }
    public static int getTraceIndex(FileBlock _file,int _index) {
        if (_file == null) {
            return 0;
        }
        return _file.getFileEscapedCalc().realIndex(_index);
    }
    public int getTraceIndex() {
        return getTraceIndex(currentFile, getIndex());
    }
    public int getIndex() {
        return getOffset() + sumOffset;
    }

    public AbsBk getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(AbsBk _currentBlock) {
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
        return globalType.getFormatted();
    }

    public AnaFormattedRootBlock getGlobalType() {
        return globalType;
    }

    public void setGlobalType(AnaFormattedRootBlock _globalType) {
        globalType = _globalType;
    }

    public void setupFctChars(NamedCalledFunctionBlock _fct) {
        setFctChars(_fct.getAccessedBlock(), _fct.getFile());
    }

    public void setupFctChars(SwitchMethodBlock _fct) {
        setFctChars(_fct.getAccessedBlock(), _fct.getFile());
    }

    private void setFctChars(AccessedBlock _acc, FileBlock _file) {
        ClassesUtil.globalType(this);
        setImporting(_acc);
        setImportingAcces(acc(_acc));
        ClassesUtil.globalType(this, _acc);
        setCurrentPkg(pkg(_acc));
        setCurrentFile(_file);
    }

    public String pkg(AccessedBlock _acc) {
        if (_acc instanceof RootBlock) {
            return ((RootBlock)_acc).getPackageName();
        }
        return getDefaultPkg();
    }
    public static AccessingImportingBlock acc(AccessedBlock _acc) {
        if (_acc instanceof RootBlock) {
            return new TypeAccessor(((RootBlock)_acc).getFullName());
        }
        return new OperatorAccessor();
    }
    public String getCurrentPkg() {
        return currentPkg;
    }

    public void setCurrentPkg(String _currentPkg) {
        this.currentPkg = _currentPkg;
    }

    public FileBlock getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(FileBlock _currentFile) {
        this.currentFile = _currentFile;
    }

    public void clearAllLocalVars(AssignedVariablesBlock _a) {
        _a.getLocalVars().clear();
        _a.getVariables().clear();
        _a.getFinalVariablesGlobal().getVariables().clear();
        _a.getFinalVariablesGlobal().getVariablesRoot().clear();
        _a.getFinalVariablesGlobal().getVariablesRootBefore().clear();
        _a.getFinalVariablesGlobal().getVariablesBefore().clear();
    }

    public StringMap<BoolVal> getDeclaredAssignments() {
        StringMap<BoolVal> o_ = new StringMap<BoolVal>();
        for (String f: allDeclaredFields) {
            o_.addEntry(f, ComparatorBoolean.of(!StringUtil.contains(assignedDeclaredFields,f)));
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

    public void zeroOffset() {
        setOffset(0);
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
        setAccessedFct(_currentFct);
        setCurrentCtx(_currentFct);
    }

    public AccessedFct getAccessedFct() {
        return accessedFct;
    }

    public void setAccessedFct(AccessedFct _v) {
        this.accessedFct = _v;
    }

    public WithContext getCurrentCtx() {
        return currentCtx;
    }

    public void setCurrentCtx(WithContext _curr) {
        this.currentCtx = _curr;
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
            CustList<RootBlock> types_ = new CustList<RootBlock>();
            types_.add(r_);
            importingTypes.add(r_.getImports());
            for (RootBlock r: r_.getAllParentTypes()) {
                types_.add(r);
                importingTypes.add(r.getImports());
            }
            for (RootBlock r:types_){
                AccessedBlock operator_ = r.getAccessedBlock();
                if (operator_ != null) {
                    importingTypes.add(operator_.getImports());
                }
            }
            importingTypes.add(r_.getFileImports());
        } else {
            importingTypes.add(_importingTypes.getImports());
            importingTypes.add(_importingTypes.getFileImports());
        }
    }

    public boolean isAcceptCommaInstr() {
        return acceptCommaInstr;
    }

    public void setAcceptCommaInstr(boolean _acceptCommaInstr) {
        acceptCommaInstr = _acceptCommaInstr;
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

    public void addRefFoundType(RootBlock _type) {
        _type.setReference(true);
        refFoundTypes.add(_type);
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

    public CustList<RootBlock> getOuterTypes() {
        return outerTypes;
    }

    public CustList<IntermediaryResults> getNextResults() {
        return nextResults;
    }

    public void setNextResults(CustList<IntermediaryResults> _nextResults) {
        this.nextResults = _nextResults;
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

    public CustList<OperatorBlock> getAllOperators() {
        return allOperators;
    }

    public CustList<OperatorBlock> getSortedOperators() {
        return sortedOperators;
    }

    public CustList<RootBlock> getPrevFoundTypes() {
        return prevFoundTypes;
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

    public DefaultCurrentConstraints getCurrentConstraints() {
        return currentConstraints;
    }

    public void setCurrentConstraints(DefaultCurrentConstraints _currentConstraints) {
        this.currentConstraints = _currentConstraints;
    }

    public DefaultBuildingConstraints getBuildingConstraints() {
        return buildingConstraints;
    }

    public void setBuildingConstraints(DefaultBuildingConstraints _buildingConstraints) {
        this.buildingConstraints = _buildingConstraints;
    }

    public DefaultLocalizer getLocalizer() {
        return localizer;
    }

    public void setLocalizer(DefaultLocalizer _localizer) {
        this.localizer = _localizer;
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
        _warning.setLocationFile(getLocationFile(_warning.getIndexFile(),_warning.getFile(), getTabWidth()));
        addWarning(_warning);
    }

    public void addLocError(FoundErrorInterpret _error) {
        _error.setLocationFile(getLocationFile(_error.getIndexFile(),_error.getFile(), getTabWidth()));
        addError(_error);
    }

    public static String getLocationFile(int _sum, FileBlock _file, int _tab) {
        int res_ = getTraceIndex(_file,_sum);
        if (_file == null) {
            return "";
        }
        FileMetrics metrics_ = _file.getMetrics(_tab);
        int r_ = metrics_.getRowFile(res_);
        int c_ = metrics_.getColFile(res_,r_);
        return StringUtil.concat(Long.toString(r_), ",", Long.toString(c_), ",", Long.toString(res_));
    }

    public void addError(FoundErrorInterpret _error) {
        messages.addError(_error);
        logErr.logIssue(_error.display(),messages);
    }

    public boolean isEmptyMessageError() {
        return messages.isEmptyMessageError();
    }
    public void addMessageError(String _std) {
        messages.addMessageError(_std);
        logErr.logIssue(_std,messages);
    }

    public boolean isEmptyStdError() {
        return messages.isEmptyStdError();
    }
    public void addStdError(StdWordError _std) {
        messages.addStdError(_std);
        logErr.logIssue(_std.display(),messages);
    }

    public void addWarning(FoundWarningInterpret _warning) {
        messages.addWarning(_warning);
        logErr.logIssue(_warning.display(),messages);
    }

    public void putFileBlock(String _fileName, FileBlock _fileBlock) {
        filesBodies.put(_fileName, _fileBlock);
    }

    public StringMap<FileBlock> getFilesBodies() {
        return filesBodies;
    }

    public FileBlock getRefFileName() {
        return refFileName;
    }

    public void setRefFileName(FileBlock _refFileName) {
        refFileName = _refFileName;
    }

    public StringMap<ToStringMethodHeader> getToStringMethods() {
        return toStringMethods;
    }

    public StringMap<ToStringMethodHeader> getRandCodeMethods() {
        return randCodeMethods;
    }

    public StringMap<Integer> getCounts() {
        return counts;
    }

    public StringMap<Integer> getCountsAnon() {
        return countsAnon;
    }

    public CustList<AnonymousResult> getCurrentAnonymousResults() {
        return currentAnonymousResults;
    }

    public void setCurrentAnonymousResults(CustList<AnonymousResult> _currentAnonymousResults) {
        this.currentAnonymousResults = _currentAnonymousResults;
    }

    public CustList<NumberInfosOutput> getCurrentNumbers() {
        return currentNumbers;
    }

    public void setCurrentNumbers(CustList<NumberInfosOutput> _cu) {
        this.currentNumbers = _cu;
    }

    public CustList<SegmentStringPart> getCurrentParts() {
        return currentParts;
    }

    public void setCurrentParts(CustList<SegmentStringPart> _currentParts) {
        this.currentParts = _currentParts;
    }

    public CustList<AnonymousLambdaOperation> getAllAnonymousLambda() {
        return allAnonymousLambda;
    }

    public CustList<SwitchOperation> getAllSwitchMethods() {
        return allSwitchMethods;
    }

    public int getCountAnonTypes() {
        return countAnonTypes;
    }

    public void setCountAnonTypes(int _countAnonTypes) {
        this.countAnonTypes = _countAnonTypes;
    }

    public IdMap<AbsBk, AssBlock> getFieldsAssSt() {
        return fieldsAssSt;
    }

    public IdMap<AbsBk, AssBlock> getFieldsAss() {
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

    public IdMap<SwitchMethodBlock, AnalyzingEl> getResultsSwMethod() {
        return resultsSwMethod;
    }

    public IdMap<NamedCalledFunctionBlock, AnalyzingEl> getResultsMethod() {
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

    public StringList getRandCodeOwners() {
        return randCodeOwners;
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

    public boolean isImplicit() {
        return getOptions().isDisplayImplicit();
    }

    public boolean isDisplayWarningTernary() {
        WarningShow warningShow_ = getOptions().getWarningShow();
        return WarningShow.isTernary(warningShow_);
    }

    public boolean isDisplayUnusedParameterStaticMethod() {
        WarningShow warningShow_ = getOptions().getWarningShow();
        return WarningShow.isUnusedParameterStaticMethod(warningShow_);
    }

    public boolean isEncodeHeader() {
        return getOptions().isEncodeHeader();
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

    public IdMap<RootBlock, ClassMethodIdReturn> getRandCodes() {
        return randCodes;
    }

    public AbstractConstantsCalculator getCalculator() {
        return calculator;
    }

    public void setCalculator(AbstractConstantsCalculator _calculator) {
        this.calculator = _calculator;
    }

    public AbstractFileBuilder getFileBuilder() {
        return fileBuilder;
    }

    public void setFileBuilder(AbstractFileBuilder _fileBuilder) {
        this.fileBuilder = _fileBuilder;
    }

    public AbsLineDeclarator getLineDeclarator() {
        return lineDeclarator;
    }

    public void setLineDeclarator(AbsLineDeclarator _lineDeclarator) {
        this.lineDeclarator = _lineDeclarator;
    }

    public CustList<OperatorBlock> getSortedNbOperators() {
        return sortedNbOperators;
    }

    public boolean isSortNbOperators() {
        return sortNbOperators;
    }

    public void setSortNbOperators(boolean _sortNbOperators) {
        this.sortNbOperators = _sortNbOperators;
    }

    public Ints getCurrentAnnotDelNew() {
        return currentAnnotDelNew;
    }

    public void setCurrentAnnotDelNew(Ints _c) {
        this.currentAnnotDelNew = _c;
    }

    public Ints getCurrentAnnotDelSwitch() {
        return currentAnnotDelSwitch;
    }

    public void setCurrentAnnotDelSwitch(Ints _c) {
        this.currentAnnotDelSwitch = _c;
    }
}
