package code.expressionlanguage.analyze;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.InaccessibleType;
import code.expressionlanguage.analyze.util.Members;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.analyze.util.ToStringMethodHeader;
import code.expressionlanguage.instr.AbstractProcessKeyWord;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.types.*;
import code.util.*;

public final class AnalyzedPageEl {

    /**Only used while throwing exception*/
    private Block currentBlock;
    private AnalyzedBlock currentAnaBlock;

    private String globalClass = "";

    private final CustList<StringMap<AnaLoopVariable>> mutableVars = new CustList<StringMap<AnaLoopVariable>>();

    private final CustList<StringMap<AnaLocalVariable>> localVars = new CustList<StringMap<AnaLocalVariable>>();

    private final StringMap<AnaLocalVariable> infosVars = new StringMap<AnaLocalVariable>();
    private final StringMap<AnaLoopVariable> loopsVars = new StringMap<AnaLoopVariable>();

    private MemberCallingsBlock currentFct;
    private AccessedBlock importing;
    private ExecAccessingImportingBlock importingAcces;
    private AccessedBlock importingTypes;
    private final CustList<RootBlock> listTypesNames = new CustList<RootBlock>();
    private final IdMap<RootBlock,ExecRootBlock> mapTypes = new IdMap<RootBlock,ExecRootBlock>();
    private final IdMap<RootBlock,ExecRootBlock> allMapTypes = new IdMap<RootBlock,ExecRootBlock>();
    private final IdMap<InnerElementBlock,ExecInnerElementBlock> mapInnerEltTypes = new IdMap<InnerElementBlock,ExecInnerElementBlock>();
    private final IdMap<UniqueRootedBlock,ExecUniqueRootedBlock> mapTypesUniqType = new IdMap<UniqueRootedBlock,ExecUniqueRootedBlock>();
    private final IdMap<RootBlock,ExecInterfacable> mapInterfaces = new IdMap<RootBlock,ExecInterfacable>();
    private final IdMap<RootBlock,Members> mapMembers = new IdMap<RootBlock,Members>();
    private final IdMap<RootBlock,Members> allMapMembers = new IdMap<RootBlock,Members>();
    private final IdMap<OperatorBlock,ExecOperatorBlock> mapOperators = new IdMap<OperatorBlock,ExecOperatorBlock>();
    private final CustList<RootBlock> allFoundTypes = new CustList<RootBlock>();
    private final CustList<RootBlock> foundTypes = new CustList<RootBlock>();
    private final CustList<RootBlock> previousFoundTypes = new CustList<RootBlock>();
    private ExecBracedBlock blockToWrite;

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
    private ExecDeclareVariable execDeclareVariable;

    private final CustList<PartOffset> currentParts = new CustList<PartOffset>();
    private OperationNode currentRoot;
    private String currentEmptyPartErr = "";
    private final Errors errors = new Errors();
    private MethodHeaders headers = new MethodHeaders();
    private final ReportedMessages messages = new ReportedMessages();

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
    private final StringMap<ExecFileBlock> filesBodies = new StringMap<ExecFileBlock>();
    private int localInType = -1;
    private String refFileName = "";
    private int indexBlock;
    private final StringMap<ToStringMethodHeader> toStringMethods = new StringMap<ToStringMethodHeader>();

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

    public void setGlobalClass(String _globalClass) {
        globalClass = _globalClass;
    }

    public void initMutableLoopVars() {
        mutableVars.add(new StringMap<AnaLoopVariable>());
    }

    public void removeMutableLoopVars() {
        mutableVars.removeLast();
    }

    public void putMutableLoopVar(String _key, AnaLoopVariable _var) {
        mutableVars.last().put(_key, _var);
    }

    public void initLocalVars() {
        localVars.add(new StringMap<AnaLocalVariable>());
    }

    public void removeLocalVars() {
        localVars.removeLast();
    }

    public void putLocalVar(String _key, AnaLocalVariable _var) {
        localVars.last().put(_key, _var);
    }

    public void clearAllLocalVars(AssignedVariablesBlock _a) {
        localVars.clear();
        mutableVars.clear();
        _a.getFinalVariablesGlobal().getVariables().clear();
        _a.getFinalVariablesGlobal().getVariablesRoot().clear();
        _a.getFinalVariablesGlobal().getVariablesRootBefore().clear();
        _a.getFinalVariablesGlobal().getVariablesBefore().clear();
        _a.getFinalVariablesGlobal().getMutableLoop().clear();
        _a.getFinalVariablesGlobal().getMutableLoopRoot().clear();
        _a.getFinalVariablesGlobal().getMutableLoopRootBefore().clear();
        _a.getFinalVariablesGlobal().getMutableLoopBefore().clear();
    }

    public void clearAllLocalVarsReadOnly() {
        localVars.clear();
        mutableVars.clear();
    }

    public boolean isFinalMutableLoopVar(String _key, int _index) {
        return mutableVars.get(_index+1).getVal(_key).isFinalVariable();
    }

    public boolean isFinalLocalVar(String _key, int _index) {
        return localVars.get(_index).getVal(_key).isFinalVariable();
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

    public AnaGeneType getAnaGeneType(ContextEl _cont, String _type) {
        RootBlock r_ = getAnaClassBody(_type);
        if (r_ != null) {
            return r_;
        }
        return _cont.getStandards().getStandards().getVal(_type);
    }
    public RootBlock getAnaClassBody(String _type) {
        for (RootBlock r: allFoundTypes) {
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

    public IdMap<RootBlock, ExecRootBlock> getAllMapTypes() {
        return allMapTypes;
    }

    public IdMap<RootBlock, ExecRootBlock> getMapTypes() {
        return mapTypes;
    }

    public IdMap<InnerElementBlock, ExecInnerElementBlock> getMapInnerEltTypes() {
        return mapInnerEltTypes;
    }

    public IdMap<UniqueRootedBlock, ExecUniqueRootedBlock> getMapTypesUniqType() {
        return mapTypesUniqType;
    }

    public IdMap<RootBlock, ExecInterfacable> getMapInterfaces() {
        return mapInterfaces;
    }

    public IdMap<RootBlock, Members> getMapMembers() {
        return mapMembers;
    }

    public IdMap<RootBlock, Members> getAllMapMembers() {
        return allMapMembers;
    }

    public IdMap<OperatorBlock, ExecOperatorBlock> getMapOperators() {
        return mapOperators;
    }

    public CustList<RootBlock> getPreviousFoundTypes() {
        return previousFoundTypes;
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

    public ExecBracedBlock getBlockToWrite() {
        return blockToWrite;
    }

    public void setBlockToWrite(ExecBracedBlock blockToWrite) {
        this.blockToWrite = blockToWrite;
    }

    public ExecDeclareVariable getExecDeclareVariable() {
        return execDeclareVariable;
    }

    public void setExecDeclareVariable(ExecDeclareVariable execDeclareVariable) {
        this.execDeclareVariable = execDeclareVariable;
    }

    public CustList<PartOffset> getCurrentParts() {
        return currentParts;
    }

    public OperationNode getCurrentRoot() {
        return currentRoot;
    }

    public void setCurrentRoot(OperationNode currentRoot) {
        this.currentRoot = currentRoot;
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

    public MethodHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(MethodHeaders _headers) {
        headers = _headers;
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

    public boolean isEmptyErrors() {
        return messages.isEmptyErrors();
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

    public void putFileBlock(String _fileName, ExecFileBlock _fileBlock) {
        filesBodies.put(_fileName, _fileBlock);
    }
    public ExecFileBlock getFileBody(String _string) {
        return filesBodies.getVal(_string);
    }
    public StringMap<ExecFileBlock> getFilesBodies() {
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
}
