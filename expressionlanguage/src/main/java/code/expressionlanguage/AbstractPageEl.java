package code.expressionlanguage;

import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbstractPageEl extends PageEl {

    protected static final String EMPTY_STRING = "";

    private static final String READ_URL = "readUrl";

    private static final String LINE_COL = "line col";

    private static final String BEAN_CLASS = "bean class";

    private static final String PARAMATERS = "parameters";
    private static final String CATCH_VARIABLES = "catch variables";
    private static final String LOCAL_VARIABLES = "local variables";

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

    private ReadWrite readWrite;
    private Block blockRoot;

    /**Only used while throwing exception*/
    private Block currentBlock;

    private CustList<RemovableVars> blockStacks = new CustList<RemovableVars>();

    private CustList<ExpressionLanguage> currentEls = new CustList<ExpressionLanguage>();

    private boolean finallyToProcess;

    private int globalOffset;

    private int translatedOffset;

    private int tabWidth;

    private int offset;
    private StringMap<LocalVariable> internVars = new StringMap<LocalVariable>();

    public boolean receive(Argument _argument, ContextEl _context) {
        getLastEl().setArgument(_argument, _context);
        return _context.processException();
    }

    public void addToOffset(int _offset) {
        offset += _offset;
    }

    public void setTranslatedOffset(int _translatedOffset) {
        translatedOffset = _translatedOffset;
    }

    public void setGlobalOffset(int _globalOffset) {
        globalOffset = _globalOffset;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int _offset) {
        offset = _offset;
    }
    public String getInfos(ContextEl _context) {
        return getCommonInfosAndRc(getTrace(), _context);
    }

    public String getCommonInfosAndRc(RowCol _rc,ContextEl _context) {
        StringBuilder str_ = new StringBuilder(getCommonInfos(_context));
        str_.append(_rc.display());
        return str_.toString();
    }

    public RowCol getTrace() {
        RowCol rc_ = new RowCol();
        if (currentBlock != null){
            int sum_ = globalOffset + offset + translatedOffset;
            rc_ = currentBlock.getRowCol(sum_);
        }
        return rc_;
    }

    private String getCommonInfos(ContextEl _context) {
        StringList list_ = new StringList();
        if (currentBlock != null) {
            list_.add(READ_URL);
            list_.add(currentBlock.getFile().getFileName());
        }
        Argument globalArgument_ = getGlobalArgument();
        if (globalArgument_ != null) {
            Object glel_ = globalArgument_.getObject();
            if (glel_ != null) {
                list_.add(StringList.concat(BEAN_CLASS,SEP_KEY_VAL,globalArgument_.getObjectClassName(_context)));
            } else {
                list_.add(StringList.concat(BEAN_CLASS,SEP_KEY_VAL,null));
            }
        } else {
            list_.add(StringList.concat(BEAN_CLASS,SEP_KEY_VAL,null));
        }
        for (EntryCust<String,LoopVariable> e: getVars().entryList()) {
            list_.add(StringList.concat(e.getKey(),SEP_KEY_VAL,SEP_INFO,e.getValue().getInfos(_context)));
        }
        list_.add(LOCAL_VARIABLES);
        for (EntryCust<String,LocalVariable> f: getLocalVars().entryList()) {
            list_.add(StringList.concat(f.getKey(),SEP_KEY_VAL,SEP_INFO,f.getValue().getInfos()));
        }
        list_.add(CATCH_VARIABLES);
        for (EntryCust<String,LocalVariable> e: getCatchVars().entryList()) {
            list_.add(StringList.concat(e.getKey(),SEP_KEY_VAL,SEP_INFO,e.getValue().getInfos()));
        }
        list_.add(PARAMATERS);
        for (EntryCust<String,LocalVariable> e: getParameters().entryList()) {
            list_.add(StringList.concat(e.getKey(),SEP_KEY_VAL,SEP_INFO,e.getValue().getInfos()));
        }
        list_.add(SEP_INFO);
        for (RemovableVars b: blockStacks) {
            list_.add(b.getInfos());
        }
        StringBuilder keyMessage_ = new StringBuilder(SEP_INFO);
        return keyMessage_.append(list_.join(SEP_INFO)).append(SEP_INFO).append(LINE_COL).append(SEP_KEY_VAL).toString();
    }

    public abstract boolean checkCondition(ContextEl _context);

    public ExpressionLanguage getCurrentEl(ContextEl _context, Block _block, int _index, int _indexProcess) {
        ExpressionLanguage el_;
        if (_index < currentEls.size()) {
            el_ = currentEls.get(_index);
        } else {
            el_ = _block.getEl(_context, _indexProcess);
            setCurrentBlock(_block);
            currentEls.add(el_);
        }
        return el_;
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

    public boolean isFinallyToProcess() {
        return finallyToProcess;
    }

    public void setFinallyToProcess(boolean _finallyToProcess) {
        finallyToProcess = _finallyToProcess;
    }
    public Block getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Block _currentBlock) {
        currentBlock = _currentBlock;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }
    public void clearCurrentEls() {
        currentEls.clear();
    }

    public boolean isEmptyEl() {
        return currentEls.isEmpty();
    }
    public ExpressionLanguage getLastEl() {
        return currentEls.last();
    }

    public void addCurrentEl(ExpressionLanguage _el) {
        currentEls.add(_el);
    }

    public ReadWrite getReadWrite() {
        return readWrite;
    }
    public void setNullReadWrite() {
        readWrite = null;
    }

    public void setReadWrite(ReadWrite _readWrite) {
        readWrite = _readWrite;
    }

    public abstract void tryProcessEl(ContextEl _context);
    public abstract ParentStackBlock getNextBlock(Block _block, ContextEl _context);
    public abstract void postBlock(ContextEl _context);
    public abstract void endRoot(ContextEl _context);

    public Block getBlockRoot() {
        return blockRoot;
    }

    public void setBlockRoot(Block _blockRoot) {
        blockRoot = _blockRoot;
    }

    public StringMap<LocalVariable> getInternVars() {
        return internVars;
    }
}
