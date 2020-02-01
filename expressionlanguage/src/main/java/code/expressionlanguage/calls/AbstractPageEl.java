package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.StringMap;

public abstract class AbstractPageEl extends PageEl {

    protected static final String EMPTY_STRING = "";

    private ReadWrite readWrite;
    private Block blockRoot;

    private CustList<RemovableVars> blockStacks = new CustList<RemovableVars>();

    private CustList<ExpressionLanguage> currentEls = new CustList<ExpressionLanguage>();

    private int globalOffset;

    private int translatedOffset;

    private int offset;
    private StringMap<LocalVariable> internVars = new StringMap<LocalVariable>();
    private FileBlock file;

    public boolean receive(Argument _argument, ContextEl _context) {
        return basicReceive(_argument,_context);
    }

    boolean basicReceive(Argument _argument, ContextEl _context) {
        if (isEmptyEl()) {
            return true;
        }
        getLastEl().setArgument(_argument, _context);
        return _context.processException();
    }

    public void processVisitedLoop(LoopBlockStack _l,Loop _bl,Block _next,ContextEl _context) {
        if (_l.isEvaluatingKeepLoop()) {
            _bl.processLastElementLoop(_context);
            return;
        }
        if (_l.isFinished()) {
            _l.getBlock().removeAllVars(this);
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

    public LoopBlockStack getLastLoopIfPossible(Block _bl) {
        LoopBlockStack c_ = null;
        if (hasBlock() && getLastStack() instanceof LoopBlockStack) {
            c_ = (LoopBlockStack) getLastStack();
        }
        if (c_ != null && c_.getBlock() == _bl) {
            return c_;
        }
        return null;
    }
    public boolean matchStatement(Block _bl) {
        if (!hasBlock()) {
            return false;
        }
        return _bl == getLastStack().getBlock();
    }
    public ParentStackBlock getNextBlock(Block _bl) {
        ParentStackBlock parElt_;
        Block nextSibling_ = _bl.getNextSibling();
        if (nextSibling_ != null) {
            parElt_ = new ParentStackBlock(null);
        } else {
            BracedBlock n_ = _bl.getParent();
            //n_ != null because strictly in class
            if (n_ != blockRoot) {
                parElt_ =  new ParentStackBlock(n_);
            } else {
                //directly at the root => last element in the block root
                parElt_ = null;
            }
        }
        return parElt_;
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

    public static boolean setRemovedCallingFinallyToProcess(AbstractPageEl _ip,RemovableVars _vars, CallingFinally _call) {
        if (!(_vars instanceof TryBlockStack)) {
            _ip.removeLastBlock();
            return false;
        }
        TryBlockStack try_ = (TryBlockStack) _vars;
        if (try_.getCurrentVisitedBlock() instanceof FinallyEval) {
            _ip.removeLastBlock();
            return false;
        }
        BracedBlock br_ = try_.getLastBlock();
        if (br_ instanceof FinallyEval) {
            _ip.getReadWrite().setBlock(br_);
            try_.setCalling(_call);
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

    public Block getBlockRoot() {
        return blockRoot;
    }

    public void setBlockRoot(Block _blockRoot) {
        blockRoot = _blockRoot;
    }

    public StringMap<LocalVariable> getInternVars() {
        return internVars;
    }

    public FileBlock getFile() {
        return file;
    }

    public void setFile(FileBlock _file) {
        file = _file;
    }
    
}
