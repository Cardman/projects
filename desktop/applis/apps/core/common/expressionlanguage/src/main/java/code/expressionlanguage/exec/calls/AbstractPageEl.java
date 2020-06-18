package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.stacks.RemovableVars;
import code.expressionlanguage.exec.stacks.TryBlockStack;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.util.CustList;
import code.util.StringMap;

public abstract class AbstractPageEl extends PageEl {

    protected static final String EMPTY_STRING = "";

    private ReadWrite readWrite;
    private ExecBlock blockRoot;

    private CustList<RemovableVars> blockStacks = new CustList<RemovableVars>();

    private CustList<ExpressionLanguage> currentEls = new CustList<ExpressionLanguage>();

    private int globalOffset;

    private int translatedOffset;

    private int offset;
    private StringMap<LocalVariable> internVars = new StringMap<LocalVariable>();
    private ExecFileBlock file;

    public void receive(Argument _argument, ContextEl _context) {
        basicReceive(_argument,_context);
    }

    void basicReceive(Argument _argument, ContextEl _context) {
        if (isEmptyEl()) {
            return;
        }
        getLastEl().setArgument(_argument, _context);
    }

    public void processVisitedLoop(LoopBlockStack _l, ExecLoop _bl, ExecBlock _next, ContextEl _context) {
        if (_l.isEvaluatingKeepLoop()) {
            _bl.processLastElementLoop(_context);
            return;
        }
        if (_l.isFinished()) {
            _next.processBlockAndRemove(_context);
            return;
        }
        getReadWrite().setBlock(_l.getBlock().getFirstChild());
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

    public abstract boolean checkCondition(ContextEl _context);


    public ExpressionLanguage getCurrentEl(ContextEl _context, WithNotEmptyEl _block, int _index, int _indexProcess) {
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
        if (hasBlock() && getLastStack() instanceof LoopBlockStack) {
            c_ = (LoopBlockStack) getLastStack();
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

    public RemovableVars getLastStack() {
        return blockStacks.last();
    }

    public void addBlock(RemovableVars _b) {
        blockStacks.add(_b);
    }

    public void removeLastBlock() {
        RemovableVars last_ = blockStacks.last();
        last_.getCurrentVisitedBlock().removeAllVars(this);
        blockStacks.removeLast();
    }

    public static boolean setRemovedCallingFinallyToProcess(AbstractPageEl _ip,RemovableVars _vars, CallingFinally _call, Struct _ex) {
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
            _ip.getReadWrite().setBlock(br_);
            try_.setCalling(_call.newAbruptCallingFinally(_ex));
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
        return currentEls.get(_index).getArgument();
    }

    public boolean isEmptyEl() {
        return currentEls.isEmpty();
    }
    ExpressionLanguage getLastEl() {
        return currentEls.last();
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

    public ExecBlock getBlockRoot() {
        return blockRoot;
    }

    public void setBlockRoot(ExecBlock _execBlockRoot) {
        blockRoot = _execBlockRoot;
    }

    public StringMap<LocalVariable> getInternVars() {
        return internVars;
    }

    public ExecFileBlock getFile() {
        return file;
    }

    public void setFile(ExecFileBlock _execFile) {
        file = _execFile;
    }
}
