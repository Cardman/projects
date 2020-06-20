package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.util.Members;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.instr.AbstractProcessKeyWord;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.types.*;
import code.util.*;

public final class AnalyzedPageEl {

    /**Only used while throwing exception*/
    private Block currentBlock;
    private AnalyzedBlock currentAnaBlock;

    private String globalClass = "";

    private CustList<StringMap<AnaLoopVariable>> vars;
    private CustList<StringMap<AnaLoopVariable>> mutableVars = new CustList<StringMap<AnaLoopVariable>>();

    private CustList<StringMap<AnaLocalVariable>> catchVars;

    private CustList<StringMap<AnaLocalVariable>> localVars;
    private CustList<StringList> localVarsInfers = new CustList<StringList>();
    private CustList<StringList> mutableLocalVarsInfers = new CustList<StringList>();

    private StringMap<AnaLocalVariable> parameters = new StringMap<AnaLocalVariable>();

    private MemberCallingsBlock currentFct;
    private ExecAccessingImportingBlock importing;
    private final CustList<RootBlock> listTypesNames = new CustList<RootBlock>();
    private final IdMap<RootBlock,ExecRootBlock> mapTypes = new IdMap<RootBlock,ExecRootBlock>();
    private final IdMap<InnerElementBlock,ExecInnerElementBlock> mapInnerEltTypes = new IdMap<InnerElementBlock,ExecInnerElementBlock>();
    private final IdMap<UniqueRootedBlock,ExecUniqueRootedBlock> mapTypesUniqType = new IdMap<UniqueRootedBlock,ExecUniqueRootedBlock>();
    private final IdMap<RootBlock,ExecInterfacable> mapInterfaces = new IdMap<RootBlock,ExecInterfacable>();
    private final IdMap<RootBlock,Members> mapMembers = new IdMap<RootBlock,Members>();
    private final IdMap<OperatorBlock,ExecOperatorBlock> mapOperators = new IdMap<OperatorBlock,ExecOperatorBlock>();
    private final CustList<RootBlock> allFoundTypes = new CustList<RootBlock>();
    private final CustList<RootBlock> foundTypes = new CustList<RootBlock>();
    private final CustList<RootBlock> previousFoundTypes = new CustList<RootBlock>();
    private ExecBracedBlock blockToWrite;
    private boolean duplicatedType;

    private int offset;

    private MethodAccessKind staticContext;

    private int globalOffset;

    private int translatedOffset;
    private int indexChildType;

    private boolean merged;
    private boolean acceptCommaInstr;
    private boolean finalVariable;
    private String currentVarSetting;
    private StringList needInterfaces = new StringList();
    private StringMap<Integer> availableVariables = new StringMap<Integer>();
    private StringList variablesNames = new StringList();
    private StringList variablesNamesToInfer = new StringList();
    private StringList variablesNamesLoopToInfer = new StringList();
    private boolean assignedStaticFields;
    private boolean assignedFields;
    private ForLoopPart forLoopPart;
    private AnalyzingEl analysisAss;
    private boolean annotAnalysisField;
    private boolean annotAnalysis;
    private String lookLocalClass = "";
    private boolean okNumOp;
    private Ints currentBadIndexes = new Ints();
    private StringList initFields = new StringList();
    private StringList initFieldsCtors = new StringList();
    private StringList assignedDeclaredFields = new StringList();
    private StringList allDeclaredFields = new StringList();
    private ExecDeclareVariable execDeclareVariable;

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

    public AnalyzedPageEl() {
        setCatchVars(new CustList<StringMap<AnaLocalVariable>>());
        setLocalVars(new CustList<StringMap<AnaLocalVariable>>());
        setVars(new CustList<StringMap<AnaLoopVariable>>());
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

    public void setGlobalClass(String _globalClass) {
        globalClass = _globalClass;
    }

    public void initVars() {
        vars.add(new StringMap<AnaLoopVariable>());
    }

    public void removeVars() {
        vars.removeLast();
    }

    public void putVar(String _key, AnaLoopVariable _var) {
        vars.last().put(_key, _var);
    }

    public boolean containsVar(String _key) {
        for (StringMap<AnaLoopVariable> m: vars) {
            if (m.contains(_key)) {
                return true;
            }
        }
        return false;
    }

    public AnaLoopVariable getVar(String _key) {
        for (StringMap<AnaLoopVariable> m: vars) {
            if (m.contains(_key)) {
                return m.getVal(_key);
            }
        }
        return null;
    }

    public void setVars(CustList<StringMap<AnaLoopVariable>> _localVars) {
        vars = _localVars;
    }

    public void initMutableLoopVars() {
        mutableVars.add(new StringMap<AnaLoopVariable>());
        mutableLocalVarsInfers.add(new StringList());
    }

    public void removeMutableLoopVars() {
        mutableVars.removeLast();
        mutableLocalVarsInfers.removeLast();
    }

    public void putMutableLoopVar(String _key) {
        mutableLocalVarsInfers.last().add(_key);
    }

    public void putMutableLoopVar(String _key, AnaLoopVariable _var) {
        mutableVars.last().put(_key, _var);
    }
    public CustList<StringMap<AnaLocalVariable>> getLocalVars() {
        return localVars;
    }

    public void initLocalVars() {
        localVars.add(new StringMap<AnaLocalVariable>());
        localVarsInfers.add(new StringList());
    }

    public void removeLocalVars() {
        localVars.removeLast();
        localVarsInfers.removeLast();
    }

    public void putLocalVar(String _key) {
        localVarsInfers.last().add(_key);
    }
    public void putLocalVar(String _key, AnaLocalVariable _var) {
        localVars.last().put(_key, _var);
    }

    public void clearAllLocalVars(AssignedVariablesBlock _a) {
        localVars.clear();
        localVarsInfers.clear();
        mutableVars.clear();
        mutableLocalVarsInfers.clear();
        _a.getFinalVariablesGlobal().getVariables().clear();
        _a.getFinalVariablesGlobal().getVariablesRoot().clear();
        _a.getFinalVariablesGlobal().getVariablesRootBefore().clear();
        _a.getFinalVariablesGlobal().getVariablesBefore().clear();
        _a.getFinalVariablesGlobal().getMutableLoop().clear();
        _a.getFinalVariablesGlobal().getMutableLoopRoot().clear();
        _a.getFinalVariablesGlobal().getMutableLoopRootBefore().clear();
        _a.getFinalVariablesGlobal().getMutableLoopBefore().clear();
        vars.clear();
        catchVars.clear();
    }

    public void clearAllLocalVarsReadOnly() {
        localVars.clear();
        localVarsInfers.clear();
        mutableVars.clear();
        mutableLocalVarsInfers.clear();
        vars.clear();
        catchVars.clear();
    }

    public boolean containsMutableLoopVar(String _key) {
        for (StringMap<AnaLoopVariable> m: mutableVars) {
            if (m.contains(_key)) {
                return true;
            }
        }
        return false;
    }

    public AnaLoopVariable getMutableLoopVar(String _key) {
        for (StringMap<AnaLoopVariable> m: mutableVars) {
            if (m.contains(_key)) {
                return m.getVal(_key);
            }
        }
        return null;
    }

    public boolean isFinalMutableLoopVar(String _key, int _index) {
        return mutableVars.get(_index+1).getVal(_key).isFinalVariable();
    }

    public boolean containsLocalVar(String _key) {
        for (StringMap<AnaLocalVariable> m: localVars) {
            if (m.contains(_key)) {
                return true;
            }
        }
        return false;
    }

    public AnaLocalVariable getLocalVar(String _key) {
        for (StringMap<AnaLocalVariable> m: localVars) {
            if (m.contains(_key)) {
                return m.getVal(_key);
            }
        }
        return null;
    }

    public boolean isFinalLocalVar(String _key, int _index) {
        return localVars.get(_index).getVal(_key).isFinalVariable();
    }

    public void setLocalVars(CustList<StringMap<AnaLocalVariable>> _localVars) {
        localVars = _localVars;
    }

    public void setCatchVars(CustList<StringMap<AnaLocalVariable>> _localVars) {
        catchVars = _localVars;
    }
    public void initCatchVars() {
        catchVars.add(new StringMap<AnaLocalVariable>());
    }

    public void removeCatchVars() {
        catchVars.removeLast();
    }

    public void putCatchVar(String _key, AnaLocalVariable _var) {
        catchVars.last().put(_key, _var);
    }

    public boolean containsCatchVar(String _key) {
        for (StringMap<AnaLocalVariable> m: catchVars) {
            if (m.contains(_key)) {
                return true;
            }
        }
        return false;
    }

    public AnaLocalVariable getCatchVar(String _key) {
        for (StringMap<AnaLocalVariable> m: catchVars) {
            if (m.contains(_key)) {
                return m.getVal(_key);
            }
        }
        return null;
    }

    public StringMap<Boolean> getDeclaredAssignments() {
        StringMap<Boolean> o_ = new StringMap<Boolean>();
        for (String f: allDeclaredFields) {
            o_.addEntry(f, !StringList.contains(assignedDeclaredFields,f));
        }
        return o_;
    }
    public StringMap<AnaLocalVariable> getParameters() {
        return parameters;
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

    public ExecAccessingImportingBlock getImporting() {
        return importing;
    }

    public void setImporting(ExecAccessingImportingBlock _importing) {
        importing = _importing;
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

    public StringMap<Integer> getAvailableVariables() {
        return availableVariables;
    }
    public StringList getVariablesNames() {
        return variablesNames;
    }

    public StringList getVariablesNamesToInfer() {
        return variablesNamesToInfer;
    }

    public StringList getVariablesNamesLoopToInfer() {
        return variablesNamesLoopToInfer;
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

    public StringList getLastLocalVarsInfers() {
        return localVarsInfers.last();
    }
    public StringList getLastMutableLoopVarsInfers() {
        return mutableLocalVarsInfers.last();
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

    public Ints getCurrentBadIndexes() {
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

    public IdMap<OperatorBlock, ExecOperatorBlock> getMapOperators() {
        return mapOperators;
    }

    public CustList<RootBlock> getPreviousFoundTypes() {
        return previousFoundTypes;
    }

    public boolean isDuplicatedType() {
        return duplicatedType;
    }

    public void setDuplicatedType(boolean _duplicatedType) {
        duplicatedType = _duplicatedType;
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
}
