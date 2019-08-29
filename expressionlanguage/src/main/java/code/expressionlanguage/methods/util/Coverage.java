package code.expressionlanguage.methods.util;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.ReflectAnnotationPageEl;
import code.expressionlanguage.calls.ReflectGetDefaultValuePageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.exec.ExecInternVariableOperation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class Coverage {
    private IdMap<Block,IdMap<ExecOperationNode,AbstractCoverageResult>> covers = new IdMap<Block,IdMap<ExecOperationNode,AbstractCoverageResult>>();
    private IdMap<Block,BooleanCoverageResult> coverLoops = new IdMap<Block,BooleanCoverageResult>();
    private StringMap<IdMap<NamedFunctionBlock,Boolean>> calls = new StringMap<IdMap<NamedFunctionBlock,Boolean>>();
    private IdMap<Block,IdMap<Block,StandardCoverageResult>> coverSwitchs = new IdMap<Block,IdMap<Block,StandardCoverageResult>>();
    private IdMap<Block,StandardCoverageResult> coverNoDefSwitchs = new IdMap<Block,StandardCoverageResult>();
    private IdMap<Block,Boolean> catches = new IdMap<Block,Boolean>();
    private IdMap<Block,IdMap<ExecOperationNode,OperationNode>> mapping = new IdMap<Block,IdMap<ExecOperationNode,OperationNode>>();
    private IdMap<Block,IdMap<ExecOperationNode,OperationNode>> mappingAnnot = new IdMap<Block,IdMap<ExecOperationNode,OperationNode>>();
    private IdMap<Block,IdMap<ExecOperationNode,OperationNode>> mappingAnnotMembers = new IdMap<Block,IdMap<ExecOperationNode,OperationNode>>();
    private StringMap<Integer> localVars = new StringMap<Integer>();
    private StringMap<Integer> mutableVars = new StringMap<Integer>();
    private StringMap<Integer> loopVars = new StringMap<Integer>();
    private StringMap<Integer> catchVars = new StringMap<Integer>();
    private StringMap<Integer> paramVars = new StringMap<Integer>();
    private String currentFileName = "";
    private boolean possibleDeclareLoopVars;
    private CustList<PartOffset> currentParts = new CustList<PartOffset>();
    public void putBlockOperationsLoops(Analyzable _context, Block _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        coverLoops.put(_block,new BooleanCoverageResult());
    }
    public void putBlockOperationsSwitchs(Analyzable _context, Block _block, boolean _def) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        coverSwitchs.put(_block, new IdMap<Block, StandardCoverageResult>());
        if (!_def) {
            coverNoDefSwitchs.put(_block,new StandardCoverageResult());
        }
    }
    public void putBlockOperationsSwitchs(Analyzable _context, Block _block, Block _child) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        coverSwitchs.getVal(_block).put(_child, new StandardCoverageResult());
    }
    public void putBlockOperations(Analyzable _context, Block _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        covers.put(_block, new IdMap<ExecOperationNode, AbstractCoverageResult>());
        getMapping().put(_block,new IdMap<ExecOperationNode, OperationNode>());
    }
    public void putBlockOperationsField(Analyzable _context, Block _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        if (_context.getContextEl().isAnnotAnalysisField()) {
            mappingAnnot.put(_block,new IdMap<ExecOperationNode, OperationNode>());
            return;
        }
        mappingAnnotMembers.put(_block,new IdMap<ExecOperationNode, OperationNode>());
    }
    public void putBlockOperation(Analyzable _context, Block _block, OperationNode _op, ExecOperationNode _exec) {
        if (!_context.getContextEl().isCovering() || _context.getContextEl().isEnabledInternVars()) {
            return;
        }
        if (_context.getContextEl().isAnnotAnalysis()) {
            if (_context.getContextEl().isAnnotAnalysisField()) {
                IdMap<ExecOperationNode, OperationNode> mapping_ = mappingAnnot.getVal(_block);
                mapping_.put(_exec,_op);
                return;
            }
            IdMap<ExecOperationNode, OperationNode> mapping_ = mappingAnnotMembers.getVal(_block);
            mapping_.put(_exec,_op);
            return;
        }
        IdMap<ExecOperationNode,AbstractCoverageResult> instr_;
        instr_ = covers.getVal(_block);
        IdMap<ExecOperationNode, OperationNode> mapping_ = getMapping().getVal(_block);
        if (mapping_ == null) {
            return;
        }
        mapping_.put(_exec,_op);
        String b_ = _context.getStandards().getAliasPrimBoolean();
        if (_op.getResultClass().matchClass(b_) && _exec.getArgument() == null) {
            instr_.put(_exec,new BooleanCoverageResult());
        } else {
            instr_.put(_exec,new StandardCoverageResult());
        }
    }
    public void putCalls(Analyzable _context, String _type) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        calls.put(_type,new IdMap<NamedFunctionBlock,Boolean>());
    }
    public void putCalls(Analyzable _context, String _type,NamedFunctionBlock _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        calls.getVal(_type).put(_block,false);
    }
    public void putCatches(Analyzable _context, Block _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        catches.put(_block,false);
    }
    public void passLoop(Analyzable _context, Argument _value) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        ReadWrite rw_ = _context.getContextEl().getLastPage().getReadWrite();
        Block en_ = rw_.getBlock();
        coverLoops.getVal(en_).cover(_value);
    }
    public void passSwitch(Analyzable _context, Block _child,Argument _value) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        ReadWrite rw_ = _context.getContextEl().getLastPage().getReadWrite();
        Block en_ = rw_.getBlock();
        coverSwitchs.getVal(en_).getVal(_child).cover(_value);
    }
    public void passSwitch(Analyzable _context, Argument _value) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        ReadWrite rw_ = _context.getContextEl().getLastPage().getReadWrite();
        Block en_ = rw_.getBlock();
        StandardCoverageResult cov_ = coverNoDefSwitchs.getVal(en_);
        cov_.cover(_value);
    }
    public void passBlockOperation(Analyzable _context, ExecOperationNode _exec, Argument _value, boolean _full) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getContextEl().getLastPage();
        if (lastPage_ instanceof ReflectAnnotationPageEl || lastPage_ instanceof ReflectGetDefaultValuePageEl) {
            return;
        }
        ReadWrite rw_ = lastPage_.getReadWrite();
        Block en_ = rw_.getBlock();
        IdMap<ExecOperationNode,AbstractCoverageResult> instr_;
        instr_ = covers.getVal(en_);
        if (instr_ == null) {
            return;
        }
        AbstractCoverageResult result_ = instr_.getVal(_exec);
        if (result_ == null) {
            return;
        }
        if (_full) {
            result_.fullCover();
        } else {
            result_.cover(_value);
        }
    }
    public void passCalls(Analyzable _context, String _type,NamedFunctionBlock _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        calls.getVal(_type).set(_block,true);
    }
    public void passCatches(Analyzable _context, Block _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        catches.set(_block,true);
    }
    public IdMap<Block, IdMap<ExecOperationNode, AbstractCoverageResult>> getCovers() {
        return covers;
    }

    public IdMap<Block, BooleanCoverageResult> getCoverLoops() {
        return coverLoops;
    }

    public IdMap<Block, IdMap<ExecOperationNode, OperationNode>> getMapping() {
        return mapping;
    }

    public IdMap<Block, IdMap<ExecOperationNode, OperationNode>> getMappingAnnot() {
        return mappingAnnot;
    }

    public IdMap<Block, IdMap<ExecOperationNode, OperationNode>> getMappingAnnotMembers() {
        return mappingAnnotMembers;
    }

    public IdMap<Block, IdMap<Block, StandardCoverageResult>> getCoverSwitchs() {
        return coverSwitchs;
    }

    public IdMap<Block, StandardCoverageResult> getCoverNoDefSwitchs() {
        return coverNoDefSwitchs;
    }

    public StringMap<IdMap<NamedFunctionBlock, Boolean>> getCalls() {
        return calls;
    }

    public IdMap<Block, Boolean> getCatches() {
        return catches;
    }

    public StringMap<Integer> getLocalVars() {
        return localVars;
    }

    public StringMap<Integer> getMutableVars() {
        return mutableVars;
    }

    public StringMap<Integer> getLoopVars() {
        return loopVars;
    }

    public StringMap<Integer> getCatchVars() {
        return catchVars;
    }

    public StringMap<Integer> getParamVars() {
        return paramVars;
    }

    public boolean isPossibleDeclareLoopVars() {
        return possibleDeclareLoopVars;
    }

    public void setPossibleDeclareLoopVars(boolean _possibleDeclareLoopVars) {
        possibleDeclareLoopVars = _possibleDeclareLoopVars;
    }

    public String getCurrentFileName() {
        return currentFileName;
    }

    public void setCurrentFileName(String _currentFileName) {
        currentFileName = _currentFileName;
    }

    public CustList<PartOffset> getCurrentParts() {
        return currentParts;
    }
}
