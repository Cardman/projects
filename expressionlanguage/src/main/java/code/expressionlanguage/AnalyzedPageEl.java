package code.expressionlanguage;

import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.FileBlock;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.RowCol;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.EntryCust;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class AnalyzedPageEl {

    private static final String READ_URL = "readUrl";

    private static final String LINE_COL = "line col";

    private static final String PARAMATERS = "parameters";
    private static final String CATCH_VARIABLES = "catch variables";
    private static final String LOCAL_VARIABLES = "local variables";

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

    /**Only used while throwing exception*/
    private Block currentBlock;

    private String globalClass;

    private CustList<StringMap<LoopVariable>> vars = new CustList<StringMap<LoopVariable>>();
    private CustList<StringMap<LoopVariable>> mutableVars = new CustList<StringMap<LoopVariable>>();

    private CustList<StringMap<LocalVariable>> catchVars = new CustList<StringMap<LocalVariable>>();

    private CustList<StringMap<LocalVariable>> localVars = new CustList<StringMap<LocalVariable>>();
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
    public void setTranslatedOffset(int _translatedOffset) {
        translatedOffset = _translatedOffset;
    }

    public void setGlobalOffset(int _globalOffset) {
        globalOffset = _globalOffset;
    }

    public String getInfos(ContextEl _context) {
        StringBuilder str_ = new StringBuilder(getCommonInfosAndRc(getTrace(), _context));
        str_.insert(0, SEP_INFO);
        str_.insert(0, readUrl);
        str_.insert(0, SEP_KEY_VAL);
        str_.insert(0, READ_URL);
        return str_.toString();
    }

    public String getCommonInfosAndRc(RowCol _rc,ContextEl _context) {
        StringBuilder str_ = new StringBuilder(getCommonInfos(_context));
        str_.append(_rc.display());
        return str_.toString();
    }

    public RowCol getTrace() {
        RowCol rc_ = new RowCol();
        if (currentBlock != null){
            FileBlock f_ = currentBlock.getFile();
            int sum_ = globalOffset + offset + translatedOffset;
            Numbers<Integer> lineReturn_ = f_.getLineReturns();
            Numbers<Integer> leftSpaces_ = f_.getLeftSpaces();
            int len_ = lineReturn_.size();
            int i_ = 0;
            while (i_ < len_) {
                if (sum_ < lineReturn_.get(i_)) {
                    int j_ = 0;
                    if (i_ > 0) {
                        j_ = sum_ - lineReturn_.get(i_ - 1);
                        j_ += leftSpaces_.get(i_/2 - 1);
                    } else {
                        j_ = sum_;
                    }
                    rc_.setCol(j_);
                    rc_.setRow(i_/2);
                    break;
                }
                i_ += 2;
            }
//            StringMap<RowCol> a_;
//            a_ = currentBlock.getAttributes();
//            StringMap<NatTreeMap<Integer,Integer>> e_;
//            e_ = currentBlock.getEncoded();
//            StringMap<Numbers<Integer>> o_;
//            o_ = currentBlock.getOffsets();
//            StringMap<Numbers<Integer>> t_;
//            t_ = currentBlock.getTabs();
//            RowCol endHeader_;
//            endHeader_ = currentBlock.getEndHeader();
//            rc_ = DocumentBuilder.getOffset(processingAttribute, a_, e_, offset, o_, t_, endHeader_, tabWidth);
        }
        return rc_;
    }

    private String getCommonInfos(ContextEl _context) {
        StringList list_ = new StringList();
        list_.add(globalClass);
        for (StringMap<LoopVariable> e: vars) {
            for (EntryCust<String,LoopVariable> f: e.entryList()) {
                list_.add(StringList.concat(f.getKey(),SEP_KEY_VAL,SEP_INFO,f.getValue().getInfos(_context)));
            }
        }
        list_.add(LOCAL_VARIABLES);
        for (StringMap<LocalVariable> e: localVars) {
            for (EntryCust<String,LocalVariable> f: e.entryList()) {
                list_.add(StringList.concat(f.getKey(),SEP_KEY_VAL,SEP_INFO,f.getValue().getInfos()));
            }
        }
        list_.add(CATCH_VARIABLES);
        for (StringMap<LocalVariable> e: catchVars) {
            for (EntryCust<String,LocalVariable> f: e.entryList()) {
                list_.add(StringList.concat(f.getKey(),SEP_KEY_VAL,SEP_INFO,f.getValue().getInfos()));
            }
        }
        list_.add(PARAMATERS);
        for (EntryCust<String,LocalVariable> e: parameters.entryList()) {
            list_.add(StringList.concat(e.getKey(),SEP_KEY_VAL,SEP_INFO,e.getValue().getInfos()));
        }
        StringBuilder keyMessage_ = new StringBuilder(SEP_INFO);
        return keyMessage_.append(list_.join(SEP_INFO)).append(SEP_INFO).append(LINE_COL).append(SEP_KEY_VAL).toString();
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
    }

    public void removeMutableLoopVars() {
        mutableVars.removeLast();
    }

    public void putMutableLoopVar(String _key, LoopVariable _var) {
        mutableVars.last().put(_key, _var);
    }
    public CustList<StringMap<LocalVariable>> getLocalVars() {
        return localVars;
    }

    public void initLocalVars() {
        localVars.add(new StringMap<LocalVariable>());
    }

    public void removeLocalVars() {
        localVars.removeLast();
    }

    public void putLocalVar(String _key, LocalVariable _var) {
        localVars.last().put(_key, _var);
    }

    public void clearAllLocalVars() {
        localVars.clear();
        mutableVars.clear();
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
        if (!mutableVars.isValidIndex(_index)) {
            return null;
        }
        return mutableVars.get(_index).getVal(_key);
    }

    public void setMutableLoopVars(StringMap<LoopVariable> _localVars) {
        mutableVars = new CustList<StringMap<LoopVariable>>(new CollCapacity(1));
        mutableVars.add(_localVars);
    }

    public void setMutableLoopVars(CustList<StringMap<LoopVariable>> _localVars) {
        mutableVars = _localVars;
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
}
