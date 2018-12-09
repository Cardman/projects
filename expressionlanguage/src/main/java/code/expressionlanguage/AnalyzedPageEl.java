package code.expressionlanguage;

import code.expressionlanguage.methods.AnalyzingEl;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class AnalyzedPageEl {

    /**Only used while throwing exception*/
    private Block currentBlock;

    private String globalClass;

    private CustList<StringMap<LoopVariable>> vars = new CustList<StringMap<LoopVariable>>();
    private CustList<StringMap<LoopVariable>> mutableVars = new CustList<StringMap<LoopVariable>>();

    private CustList<StringMap<LocalVariable>> catchVars = new CustList<StringMap<LocalVariable>>();

    private CustList<StringMap<LocalVariable>> localVars = new CustList<StringMap<LocalVariable>>();
    private CustList<StringList> localVarsInfers = new CustList<StringList>();
    private CustList<StringList> mutableLocalVarsInfers = new CustList<StringList>();
    private StringMap<LocalVariable> internVars = new StringMap<LocalVariable>();

    private StringMap<LocalVariable> parameters = new StringMap<LocalVariable>();

    private AssignedVariablesBlock assignedVariables = new AssignedVariablesBlock();

    private CustList<OperationNode> textualSortedOperations = new CustList<OperationNode>();

    private boolean enabledInternVars;

    private String readUrl;

    private int tabWidth;
    private int offset;

    private boolean staticContext;

    private int globalOffset;

    private int translatedOffset;
    private int indexChildType;

    private boolean ambigous;
    private boolean directImport;
    private boolean rootAffect;
    private boolean analyzingRoot;
    private boolean merged;
    private boolean finalVariable;
    private String currentVarSetting;
    private boolean gearConst;
    private StringList needInterfaces = new StringList();
    private StringList availableVariables = new StringList();
    private StringList variablesNames = new StringList();
    private boolean assignedStaticFields;
    private boolean assignedFields;
    private ForLoopPart forLoopPart;
    private AnalyzingEl analysisAss;
    private boolean annotAnalysis;
    private String lookLocalClass = "";
    private SortedClassField currentInitizedField;
    private boolean okNumOp;
    private Numbers<Integer> currentBadIndexes = new Numbers<Integer>();
    public void setTranslatedOffset(int _translatedOffset) {
        translatedOffset = _translatedOffset;
    }

    public void setGlobalOffset(int _globalOffset) {
        globalOffset = _globalOffset;
    }

    public int getTraceIndex() {
        return globalOffset + offset + translatedOffset;
    }

    public void addToOffset(int _offset) {
        offset += _offset;
    }

    public String getNextTempVar(Classes _classes) {
        int i_ = CustList.FIRST_INDEX;
        while (true) {
            if (!internVars.getKeys().containsStr(StringList.concatNbs(Classes.TEMP_PREFIX,i_))) {
                break;
            }
            i_++;
        }
        return StringList.concatNbs(Classes.TEMP_PREFIX,i_);
    }

    public boolean isEnabledInternVars() {
        return enabledInternVars;
    }

    public void setEnabledInternVars(boolean _enabledInternVars) {
        enabledInternVars = _enabledInternVars;
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
        vars.add(new StringMap<LoopVariable>());
    }

    public void removeVars() {
        vars.removeLast();
    }

    public void putVar(String _key, LoopVariable _var) {
        vars.last().put(_key, _var);
    }

    public boolean containsVar(String _key) {
        for (StringMap<LoopVariable> m: vars) {
            if (m.contains(_key)) {
                return true;
            }
        }
        return false;
    }

    public LoopVariable getVar(String _key) {
        for (StringMap<LoopVariable> m: vars) {
            if (m.contains(_key)) {
                return m.getVal(_key);
            }
        }
        return null;
    }

    public void setVars(StringMap<LoopVariable> _localVars) {
        vars = new CustList<StringMap<LoopVariable>>(new CollCapacity(1));
        vars.add(_localVars);
    }

    public CustList<StringMap<LoopVariable>> getMutableLoopVars() {
        return mutableVars;
    }

    public void initMutableLoopVars() {
        mutableVars.add(new StringMap<LoopVariable>());
        mutableLocalVarsInfers.add(new StringList());
    }

    public void removeMutableLoopVars() {
        mutableVars.removeLast();
        mutableLocalVarsInfers.removeLast();
    }

    public void putMutableLoopVar(String _key) {
        mutableLocalVarsInfers.last().add(_key);
    }

    public void putMutableLoopVar(String _key, LoopVariable _var) {
        mutableVars.last().put(_key, _var);
    }
    public CustList<StringMap<LocalVariable>> getLocalVars() {
        return localVars;
    }

    public void initLocalVars() {
        localVars.add(new StringMap<LocalVariable>());
        localVarsInfers.add(new StringList());
    }

    public void removeLocalVars() {
        localVars.removeLast();
        localVarsInfers.removeLast();
    }

    public void putLocalVar(String _key) {
        localVarsInfers.last().add(_key);
    }
    public void putLocalVar(String _key, LocalVariable _var) {
        localVars.last().put(_key, _var);
    }

    public void clearAllLocalVars() {
        localVars.clear();
        localVarsInfers.clear();
        mutableVars.clear();
        mutableLocalVarsInfers.clear();
        assignedVariables.getFinalVariablesGlobal().getVariables().clear();
        assignedVariables.getFinalVariablesGlobal().getVariablesRoot().clear();
        assignedVariables.getFinalVariablesGlobal().getVariablesRootBefore().clear();
        assignedVariables.getFinalVariablesGlobal().getVariablesBefore().clear();
        assignedVariables.getFinalVariablesGlobal().getMutableLoop().clear();
        assignedVariables.getFinalVariablesGlobal().getMutableLoopRoot().clear();
        assignedVariables.getFinalVariablesGlobal().getMutableLoopRootBefore().clear();
        assignedVariables.getFinalVariablesGlobal().getMutableLoopBefore().clear();
        vars.clear();
        catchVars.clear();
    }

    public boolean containsMutableLoopVar(String _key) {
        for (StringMap<LoopVariable> m: mutableVars) {
            if (m.contains(_key)) {
                return true;
            }
        }
        return false;
    }

    public LoopVariable getMutableLoopVar(String _key) {
        for (StringMap<LoopVariable> m: mutableVars) {
            if (m.contains(_key)) {
                return m.getVal(_key);
            }
        }
        return null;
    }

    public LoopVariable getMutableLoopVar(String _key, int _index) {
        for (StringMap<LoopVariable> m: mutableVars.getReverse()) {
            LoopVariable l_ = m.getVal(_key);
            if (l_ != null) {
                return l_;
            }
        }
        return null;
    }

    public void setMutableLoopVars(StringMap<LoopVariable> _localVars) {
        mutableVars = new CustList<StringMap<LoopVariable>>(new CollCapacity(1));
        mutableVars.add(_localVars);
        mutableLocalVarsInfers = new CustList<StringList>();
        mutableLocalVarsInfers.add(new StringList());
    }

    public void setMutableLoopVars(CustList<StringMap<LoopVariable>> _localVars) {
        mutableVars = _localVars;
    }
    public CustList<StringList> getMutableLocalVarsInfers() {
        return mutableLocalVarsInfers;
    }
    public void setMutableLocalVarsInfers(
            CustList<StringList> _mutableLocalVarsInfers) {
        mutableLocalVarsInfers = _mutableLocalVarsInfers;
    }
    public boolean containsLocalVar(String _key) {
        for (StringMap<LocalVariable> m: localVars) {
            if (m.contains(_key)) {
                return true;
            }
        }
        return false;
    }

    public LocalVariable getLocalVar(String _key) {
        for (StringMap<LocalVariable> m: localVars) {
            if (m.contains(_key)) {
                return m.getVal(_key);
            }
        }
        return null;
    }

    public LocalVariable getLocalVar(String _key, int _index) {
        if (!localVars.isValidIndex(_index)) {
            return null;
        }
        return localVars.get(_index).getVal(_key);
    }

    public void setLocalVars(StringMap<LocalVariable> _localVars) {
        localVars = new CustList<StringMap<LocalVariable>>(new CollCapacity(1));
        localVars.add(_localVars);
        localVarsInfers = new CustList<StringList>();
        localVarsInfers.add(new StringList());
    }

    public void setLocalVars(CustList<StringMap<LocalVariable>> _localVars) {
        localVars = _localVars;
    }

    public void setCatchVars(StringMap<LocalVariable> _localVars) {
        catchVars = new CustList<StringMap<LocalVariable>>(new CollCapacity(1));
        catchVars.add(_localVars);
    }
    public void initCatchVars() {
        catchVars.add(new StringMap<LocalVariable>());
    }

    public void removeCatchVars() {
        catchVars.removeLast();
    }

    public void putCatchVar(String _key, LocalVariable _var) {
        catchVars.last().put(_key, _var);
    }

    public boolean containsCatchVar(String _key) {
        for (StringMap<LocalVariable> m: catchVars) {
            if (m.contains(_key)) {
                return true;
            }
        }
        return false;
    }

    public LocalVariable getCatchVar(String _key) {
        for (StringMap<LocalVariable> m: catchVars) {
            if (m.contains(_key)) {
                return m.getVal(_key);
            }
        }
        return null;
    }

    public StringMap<LocalVariable> getParameters() {
        return parameters;
    }

    public void setParameters(StringMap<LocalVariable> _parameters) {
        parameters = _parameters;
    }

    public String getReadUrl() {
        return readUrl;
    }

    public void setReadUrl(String _readUrl) {
        readUrl = _readUrl;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int _offset) {
        offset = _offset;
    }

    public boolean isStaticContext() {
        return staticContext;
    }

    public void setStaticContext(boolean _staticContext) {
        staticContext = _staticContext;
    }

    public int getIndexChildType() {
        return indexChildType;
    }

    public void setIndexChildType(int _indexChildType) {
        indexChildType = _indexChildType;
    }

    public boolean isAmbigous() {
        return ambigous;
    }

    public void setAmbigous(boolean _ambigous) {
        ambigous = _ambigous;
    }

    public boolean isDirectImport() {
        return directImport;
    }

    public void setDirectImport(boolean _directImport) {
        directImport = _directImport;
    }

    public boolean isRootAffect() {
        return rootAffect;
    }

    public void setRootAffect(boolean _rootAffect) {
        rootAffect = _rootAffect;
    }

    public boolean isAnalyzingRoot() {
        return analyzingRoot;
    }

    public void setAnalyzingRoot(boolean _analyzingRoot) {
        analyzingRoot = _analyzingRoot;
    }

    public boolean isMerged() {
        return merged;
    }

    public void setMerged(boolean _merged) {
        merged = _merged;
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

    public int getGlobalOffset() {
        return globalOffset;
    }

    public int getTranslatedOffset() {
        return translatedOffset;
    }

    public AssignedVariablesBlock getAssignedVariables() {
        return assignedVariables;
    }
    public CustList<OperationNode> getTextualSortedOperations() {
        return textualSortedOperations;
    }

    public boolean isGearConst() {
        return gearConst;
    }

    public void setGearConst(boolean _gearConst) {
        gearConst = _gearConst;
    }
    public StringList getNeedInterfaces() {
        return needInterfaces;
    }
    public StringMap<LocalVariable> getInternVars() {
        return internVars;
    }
    public StringList getAvailableVariables() {
        return availableVariables;
    }
    public StringList getVariablesNames() {
        return variablesNames;
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

    public SortedClassField getCurrentInitizedField() {
        return currentInitizedField;
    }

    public void setCurrentInitizedField(SortedClassField _currentInitizedField) {
        currentInitizedField = _currentInitizedField;
    }

    public boolean isOkNumOp() {
        return okNumOp;
    }

    public void setOkNumOp(boolean _okNumOp) {
        okNumOp = _okNumOp;
    }

    public Numbers<Integer> getCurrentBadIndexes() {
        return currentBadIndexes;
    }
}
