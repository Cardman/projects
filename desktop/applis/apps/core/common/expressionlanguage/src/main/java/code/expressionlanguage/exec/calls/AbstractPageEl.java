package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class AbstractPageEl {

    protected static final String EMPTY_STRING = "";

    private String globalClass = "";

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

    private LoopBlockStack lastLoop;
    private IfBlockStack lastIf;
    private TryBlockStack lastTry;
    private final PageElContent content = new PageElContent();
    public Struct getGlobalStruct() {
        return content.getGlobalStruct();
    }
    public Argument getGlobalArgument() {
        return content.getGlobalArgument();
    }
    public void setGlobalArgumentStruct(Struct _obj) {
        content.setGlobalArgumentStruct(_obj);
    }

    public void setGlobalArgument(Argument _globalArgument) {
        content.setGlobalArgument(_globalArgument);
    }

    public StringMap<AbstractWrapper> getRefParams() {
        return content.getRefParams();
    }

    public void putValueVar(String _key, LocalVariable _var) {
        content.getRefParams().put(_key, new VariableWrapper(_var));
    }
    public StringMap<LoopVariable> getVars() {
        return content.getVars();
    }

    public void removeRefVar(String _key) {
        content.removeRefVar(_key);
    }

    public Cache getCache() {
        return content.getCache();
    }

    public void setCache(Cache _cache) {
        this.content.setCache(_cache);
    }

    public final void forwardTo(AbstractPageEl _page, ContextEl _context, StackCall _stack) {
        _page.receive(wrapper, returnedArgument, _context, _stack);
    }
    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        basicReceive(_wrap, _argument,_context, _stack);
    }
    public String formatVarType(String _varType) {
        if (getGlobalArgument().isNull()) {
            return _varType;
        }
        return ExecInherits.quickFormat(blockRootType, globalClass, _varType);
    }

    void basicReceive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stackCall) {
        if (isEmptyEl()) {
            return;
        }
        getLastEl().setArgument(_wrap,_argument, _context, _stackCall);
    }

    public void processVisitedLoop(LoopBlockStack _l, ExecLoop _bl, ExecBlock _next, ContextEl _context, StackCall _stackCall) {
        if (_l.isEvaluatingKeepLoop()) {
            _bl.processLastElementLoop(_context,_l, _stackCall);
            return;
        }
        if (_l.isFinished()) {
            _next.processBlockAndRemove(_context, _stackCall);
            return;
        }
        setBlock(_l.getBlock().getFirstChild());
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

    public abstract boolean checkCondition(ContextEl _context, StackCall _stack);


    public ExpressionLanguage getCurrentEl(ContextEl _context, BuildingEl _block, int _index, int _indexProcess) {
        ExpressionLanguage el_ = getNullableExp(_index);
        if (el_ == null) {
            el_ = _block.getEl(_context, _indexProcess);
            currentEls.add(el_);
        }
        return el_;
    }
    public ExpressionLanguage getCurrentEl(int _index, CustList<ExecOperationNode> _e) {
        ExpressionLanguage el_ = getNullableExp(_index);
        if (el_ == null) {
            el_ = new ExpressionLanguage(_e);
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
    public boolean hasBlock() {
        return !blockStacks.isEmpty();
    }

    public LoopBlockStack getLastLoopIfPossible(ExecBlock _bl) {
        LoopBlockStack c_ = null;
        if (hasBlock()) {
            AbstractStask lastStack_ = getLastStack();
            if (lastStack_ instanceof LoopBlockStack) {
                c_ = (LoopBlockStack) lastStack_;
            }
        }
        if (c_ != null && c_.getBlock() == _bl) {
            return c_;
        }
        return null;
    }
    public boolean matchStatement(ExecBlock _bl) {
        if (!hasBlock()) {
            return false;
        }
        return _bl == getLastStack().getBlock();
    }

    public AbstractStask getLastStack() {
        return blockStacks.last();
    }

    public void addBlock(AbstractStask _b) {
        blockStacks.add(_b);
    }

    public void removeLastBlock() {
        AbstractStask last_ = blockStacks.last();
        last_.getCurrentVisitedBlock().removeAllVars(this);
        blockStacks.removeQuicklyLast();
        if (hasBlock()) {
            AbstractStask before_ = blockStacks.last();
            if (before_ instanceof LoopBlockStack) {
                setLastLoop((LoopBlockStack) before_);
            }
            if (before_ instanceof IfBlockStack) {
                setLastIf((IfBlockStack) before_);
            }
            if (before_ instanceof TryBlockStack) {
                setLastTry((TryBlockStack) before_);
            }
        }
    }

    public static boolean setRemovedCallingFinallyToProcess(AbstractPageEl _ip,AbstractStask _vars, MethodCallingFinally _call, Struct _ex) {
        if (!(_vars instanceof TryBlockStack)) {
            _ip.removeLastBlock();
            return false;
        }
        TryBlockStack try_ = (TryBlockStack) _vars;
        if (try_.getCurrentVisitedBlock() instanceof ExecFinallyEval) {
            _ip.removeLastBlock();
            return false;
        }
        ExecBracedBlock br_ = try_.getLastBlock();
        if (br_ instanceof ExecFinallyEval) {
            _ip.setLastTry(try_);
            _ip.setBlock(br_);
            try_.setException(_ex);
            try_.setCalling(new AbruptCallingFinally(_call));
            return true;
        }
        _ip.removeLastBlock();
        return false;
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

    public abstract void tryProcessEl(ContextEl _context, StackCall _stack);

    public ExecBlock getBlockRoot() {
        return blockRoot;
    }

    public void setBlockRoot(ExecBlock _execBlockRoot) {
        blockRoot = _execBlockRoot;
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

    public LoopBlockStack getLastLoop() {
        return lastLoop;
    }

    public void setLastLoop(LoopBlockStack _lastLoop) {
        this.lastLoop = _lastLoop;
    }

    public IfBlockStack getLastIf() {
        return lastIf;
    }

    public void setLastIf(IfBlockStack _lastIf) {
        this.lastIf = _lastIf;
    }

    public TryBlockStack getLastTry() {
        return lastTry;
    }

    public void setLastTry(TryBlockStack _lastTry) {
        this.lastTry = _lastTry;
    }

    public String getGlobalClass() {
        return globalClass;
    }

    public void setGlobalClass(String _globalClass) {
        globalClass = StringUtil.nullToEmpty(_globalClass);
    }
    public ExecRootBlock getBlockRootType() {
        return blockRootType;
    }

    public void setBlockRootType(ExecRootBlock _blockRootType) {
        blockRootType = _blockRootType;
    }

}
