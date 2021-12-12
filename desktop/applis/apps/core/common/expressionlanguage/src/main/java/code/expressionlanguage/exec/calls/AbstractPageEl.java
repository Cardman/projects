package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.expressionlanguage.exec.stacks.ConditionBlockStack;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class AbstractPageEl {

    protected static final String EMPTY_STRING = "";

    private final ExecFormattedRootBlock globalClass;

    private ExecRootBlock blockRootType;

    private ReadWrite readWrite;
    private ExecBlock execBlock;
    private ExecBlock blockRoot;

    private final CustList<AbstractStask> blockStacks = new CustList<AbstractStask>();

    private final CustList<ExpressionLanguage> currentEls = new CustList<ExpressionLanguage>();

    private int globalOffset;

    private int translatedOffset;

    private int offset;
    private final StringMap<LocalVariable> internVars = new StringMap<LocalVariable>();
    private ExecFileBlock file;

    private AbstractWrapper wrapper;
    private Argument returnedArgument = Argument.createVoid();

    private final PageElContent contentEx = new PageElContent();

    protected AbstractPageEl(ExecFormattedRootBlock _global) {
        globalClass = _global;
    }
    public Struct getGlobalStruct() {
        return contentEx.getGlobalStruct();
    }
    public Argument getGlobalArgument() {
        return contentEx.getGlobalArgument();
    }
    public final void setGlobalArgumentStruct(Struct _obj) {
        contentEx.setGlobalArgumentStruct(_obj);
    }

    public void setGlobalArgument(Argument _globalArgument) {
        contentEx.setGlobalArgument(_globalArgument);
    }

    public StringMap<AbstractWrapper> getRefParams() {
        return contentEx.getRefParams();
    }

    public void putValueVar(String _key, LocalVariable _var) {
        contentEx.getRefParams().put(_key, new VariableWrapper(_var));
    }
    public StringMap<LoopVariable> getVars() {
        return contentEx.getVars();
    }

    public void removeRefVar(String _key) {
        contentEx.removeRefVar(_key);
    }

    public Cache getCache() {
        return contentEx.getCache();
    }

    public void setCache(Cache _cache) {
        this.contentEx.setCache(_cache);
    }

    public final void forwardTo(AbstractPageEl _page, ContextEl _context, StackCall _stack) {
        _page.receive(wrapper, returnedArgument, _context, _stack);
    }
    public abstract void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack);
    public final String formatVarType(String _varType) {
//        if (getGlobalArgument().isNull()) {
//            return _varType;
//        }
        return ExecInherits.quickFormat(globalClass, _varType);
    }

    protected void basicReceive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stackCall) {
        if (isEmptyEl()) {
            return;
        }
        getLastEl().setArgument(_wrap,_argument, _context, _stackCall);
    }

    public void setTranslatedOffset(int _translatedOffset) {
        translatedOffset = _translatedOffset;
    }

    public void setGlobalOffset(int _globalOffset) {
        globalOffset = _globalOffset;
    }

    public void setOffset(int _offset) {
        offset = _offset;
    }

    public int getTraceIndex() {
        return globalOffset + offset + translatedOffset;
    }

    public int getTranslatedOffset() {
        return translatedOffset;
    }

    public abstract void processTagsBase(ContextEl _context, StackCall _stack);


    public ExpressionLanguage getCurrentEl(int _index, CustList<ExecOperationNode> _e, ExecBlock _coveredBlock) {
        ExpressionLanguage el_ = getNullableExp(_index);
        if (el_ == null) {
            el_ = new ExpressionLanguage(_e, _coveredBlock);
            currentEls.add(el_);
        }
        return el_;
    }

    private ExpressionLanguage getNullableExp(int _index) {
        if (_index < currentEls.size()) {
            return currentEls.get(_index);
        }
        return null;
    }
    public boolean noBlock() {
        return blockStacks.isEmpty();
    }

    public LoopBlockStack getLastLoopIfPossible(ExecBlock _bl) {
        AbstractStask last_ = tryGetLastStack();
        if (last_ instanceof LoopBlockStack && ((LoopBlockStack)last_).getExecBlock() == _bl) {
            return (LoopBlockStack) last_;
        }
        return null;
    }
    public boolean matchStatement(ExecBlock _bl) {
        AbstractStask last_ = tryGetLastStack();
        return last_ instanceof ConditionBlockStack && _bl == ((ConditionBlockStack) last_).getBlock();
    }

    public AbstractStask tryGetLastStack() {
        if (noBlock()) {
            return null;
        }
        return blockStacks.last();
    }

    public void addBlock(AbstractStask _b) {
        blockStacks.add(_b);
    }

    public void removeLastBlock() {
        AbstractStask last_ = blockStacks.last();
        last_.getCurrentVisitedBlock().removeAllVars(this);
        blockStacks.removeQuicklyLast();
    }

    protected void commonTageBase(ContextEl _context, StackCall _stack, Argument _arg) {
        //method walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof ExecAbstractSwitchMethod) {
            setBlock(((ExecAbstractSwitchMethod)en_).processCase(_context, Argument.getNullableValue(_arg),_stack));
            return;
        }
        if (en_ instanceof ExecAbstractExpressionReturnMethod) {
            ((ExecAbstractExpressionReturnMethod)en_).processEl(_context, _stack);
            return;
        }
        if (en_ instanceof MethodCallingFinally) {
            ((MethodCallingFinally)en_).removeBlockFinally(_context,_stack);
            return;
        }
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context, _stack);
            return;
        }
        setNullReadWrite();
    }

    public void clearCurrentEls() {
        currentEls.clear();
    }

    public int sizeEl(){
        return currentEls.size();
    }

    public Argument getValue(int _index){
        if (_index >= currentEls.size()) {
            return Argument.createVoid();
        }
        return currentEls.get(_index).getArgument();
    }

    public boolean isEmptyEl() {
        return currentEls.isEmpty();
    }
    public ExecBlock getCoveredBlock() {
        return getLastEl().getCoveredBlock();
    }
    private ExpressionLanguage getLastEl() {
        return currentEls.last();
    }

    public ExecBlock getBlock() {
        return execBlock;
    }

    public void setBlock(ExecBlock _block) {
        execBlock = _block;
    }

    public ReadWrite getReadWrite() {
        return readWrite;
    }
    public void setNullReadWrite() {
        readWrite = null;
    }

    public void setReadWrite(ReadWrite _readWrite) {
        readWrite = _readWrite;
        execBlock = _readWrite.getBlock();
    }

    public ExecBlock getBlockRoot() {
        return blockRoot;
    }

    public void setBlockRoot(ExecBlock _execBlockRoot) {
        blockRoot = _execBlockRoot;
    }

    public void fileOffset(ExecRootBlock _type) {
        setFile(_type.getFile());
        globalOffset(_type.getIdRowCol());
    }
    public void globalOffset(int _offset) {
        setOffset(0);
        setGlobalOffset(_offset);
    }

    public StringMap<LocalVariable> getInternVars() {
        return internVars;
    }
    public void putInternVars(String _key, Struct _struct, ContextEl _context) {
        internVars.put(StringUtil.nullToEmpty(_key),LocalVariable.newLocalVariable(_struct,_context));
    }

    public ExecFileBlock getFile() {
        return file;
    }

    public void setFile(ExecFileBlock _execFile) {
        file = _execFile;
    }

    public Argument getReturnedArgument() {
        return returnedArgument;
    }

    public void setReturnedArgument(Argument _returnedArgument) {
        returnedArgument = Argument.getNullableValue(_returnedArgument);
    }

    public AbstractWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(AbstractWrapper _wrapper) {
        wrapper = _wrapper;
    }

    public ExecFormattedRootBlock getGlobalClass() {
        return globalClass;
    }

    public ExecRootBlock getBlockRootType() {
        return blockRootType;
    }

    public void setBlockRootType(ExecRootBlock _blockRootType) {
        blockRootType = _blockRootType;
    }

}
