package code.expressionlanguage;

import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.FileBlock;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public class AnalyzedPageEl {

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

    private StringMap<LoopVariable> vars = new StringMap<LoopVariable>();

    private StringMap<LocalVariable> catchVars = new StringMap<LocalVariable>();

    private StringMap<LocalVariable> localVars = new StringMap<LocalVariable>();

    private StringMap<LocalVariable> parameters = new StringMap<LocalVariable>();

    private CustList<RemovableVars> blockStacks = new CustList<RemovableVars>();

    private String readUrl;

    private int tabWidth;

    private int offset;

    private boolean staticContext;

    private int globalOffset;

    private int translatedOffset;

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
        for (EntryCust<String,LoopVariable> e: vars.entryList()) {
            list_.add(StringList.concat(e.getKey(),SEP_KEY_VAL,SEP_INFO,e.getValue().getInfos(_context)));
        }
        list_.add(LOCAL_VARIABLES);
//        for (StringMap<LocalVariable> e: localVars) {
//            for (EntryCust<String,LocalVariable> f: localVars.entryList()) {
//                list_.add(StringList.concat(f.getKey(),SEP_KEY_VAL,SEP_INFO,f.getValue().getInfos()));
//            }
//        }
        for (EntryCust<String,LocalVariable> f: localVars.entryList()) {
            list_.add(StringList.concat(f.getKey(),SEP_KEY_VAL,SEP_INFO,f.getValue().getInfos()));
        }
        list_.add(CATCH_VARIABLES);
        for (EntryCust<String,LocalVariable> e: catchVars.entryList()) {
            list_.add(StringList.concat(e.getKey(),SEP_KEY_VAL,SEP_INFO,e.getValue().getInfos()));
        }
        list_.add(PARAMATERS);
        for (EntryCust<String,LocalVariable> e: parameters.entryList()) {
            list_.add(StringList.concat(e.getKey(),SEP_KEY_VAL,SEP_INFO,e.getValue().getInfos()));
        }
        list_.add(SEP_INFO);
        for (RemovableVars b: blockStacks) {
            list_.add(b.getInfos());
        }
        StringBuilder keyMessage_ = new StringBuilder(SEP_INFO);
        return keyMessage_.append(list_.join(SEP_INFO)).append(SEP_INFO).append(LINE_COL).append(SEP_KEY_VAL).toString();
    }

    public void addToOffset(int _offset) {
        offset += _offset;
    }

    public String getNextTempVar(Classes _classes) {
        StringList resVar_ = _classes.getLocalVariablesNames();
        int i_ = CustList.FIRST_INDEX;
        while (true) {
            if (!resVar_.containsStr(StringList.concatNbs(Classes.TEMP_PREFIX,i_))) {
                if (!localVars.contains(StringList.concatNbs(Classes.TEMP_PREFIX,i_))) {
                    break;
                }
            }
            i_++;
        }
        return StringList.concatNbs(Classes.TEMP_PREFIX,i_);
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

    public StringMap<LoopVariable> getVars() {
        return vars;
    }

    public void setVars(StringMap<LoopVariable> _vars) {
        vars = _vars;
    }

    public void putLocalVar(String _key, LocalVariable _var) {
        localVars.put(_key, _var);
    }

    public void clearAllLocalVars() {
        localVars.clear();
    }

    public void removeLocalVar(String _key) {
        localVars.removeKey(_key);
    }

    public boolean containsLocalVar(String _key) {
        return localVars.contains(_key);
    }

    public LocalVariable getLocalVar(String _key) {
        return localVars.getVal(_key);
    }

    public StringMap<LocalVariable> getLocalVars() {
        return localVars;
    }

    public void setLocalVars(StringMap<LocalVariable> _localVars) {
        localVars = _localVars;
    }

    public void setLocalVars(CustList<StringMap<LocalVariable>> _localVars) {
        localVars = _localVars.last();
    }

    public StringMap<LocalVariable> getCatchVars() {
        return catchVars;
    }

    public void setCatchVars(StringMap<LocalVariable> _catchVars) {
        catchVars = _catchVars;
    }

    public StringMap<LocalVariable> getParameters() {
        return parameters;
    }

    public void setParameters(StringMap<LocalVariable> _parameters) {
        parameters = _parameters;
    }
    public boolean noBlock() {
        return blockStacks.isEmpty();
    }

    public int nbBlocks() {
        return blockStacks.size();
    }

    public LoopBlockStack getLastLoopIfPossible() {
        LoopBlockStack c_ = null;
        if (!noBlock() && getLastStack() instanceof LoopBlockStack) {
            c_ = (LoopBlockStack) getLastStack();
        }
        return c_;
    }

    public RemovableVars getLastStack() {
        return blockStacks.last();
    }

    public void addBlock(RemovableVars _b) {
        blockStacks.add(_b);
    }

    public void removeLastBlock() {
        blockStacks.removeLast();
    }

    public CustList<RemovableVars> getBlockStacks() {
        return blockStacks;
    }

    public void setBlockStacks(CustList<RemovableVars> _blockStacks) {
        blockStacks = _blockStacks;
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
}
